package ca.mcmaster.cas.se2aa4.a2.island.city.road.generator.generators;

import ca.mcmaster.cas.se2aa4.a2.island.city.road.generator.AbstractRoadGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.geography.Land;
import ca.mcmaster.cas.se2aa4.a2.island.mesh.IslandMesh;
import ca.mcmaster.cas.se2aa4.a2.island.path.Path;
import ca.mcmaster.cas.se2aa4.a2.island.path.type.PathType;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a2.island.point.type.PointType;
import ca.mcmaster.cas.se2aa4.a2.island.tile.Tile;
import ca.mcmaster.cas.se2aa4.a2.island.tile.type.TileGroup;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs.UnDirectedGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.PathAlgorithm;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.algorithms.ShortestPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StarNetwork extends AbstractRoadGenerator {
    public StarNetwork(IslandMesh mesh, Land land) {
        super(mesh, land);
    }

    @Override
    protected Graph<Point> generateGraph(List<Tile> tiles, List<Path> paths, List<Point> points, List<Point> cities) {
        Graph<Point> graph = new UnDirectedGraph<>(true);

        tiles.forEach(t -> {
            Point tCentroid = t.getCentroid();
            graph.addNode(tCentroid);

            List<Path> tPaths = t.getPaths();

            List<Tile> neighbors = t.getNeighbors();
            neighbors.forEach(t1 -> {
                Point t1Centroid = t1.getCentroid();
                List<Path> t1Paths = t1.getPaths();

                t1Paths.retainAll(tPaths);

                double weight = (t.getElevation() + t1.getElevation()) / 2;

                if(
                        t.getType().getGroup() == TileGroup.WATER ||
                        t1.getType().getGroup() == TileGroup.WATER ||
                        t1Paths.stream().anyMatch(p -> p.getType() == PathType.RIVER)
                ) {
                    weight = 1000;
                }

                graph.addNode(t1Centroid);
                graph.addEdge(tCentroid, t1Centroid);
                graph.setEdgeWeight(tCentroid, t1Centroid, weight);
            });
        });

        return graph;
    }

    @Override
    protected List<List<Point>> generateRoads(Graph<Point> graph, List<Point> cities) {
        List<List<Point>> roads = new ArrayList<>();

        Point city = cities.stream().max(Comparator.comparing(Point::getThickness, Float::compareTo)).get();
        city.setType(PointType.CAPITAL);

        PathAlgorithm<Point> roadFinder = new ShortestPath<>(graph, city);

        List<Point> otherCities = cities.stream().filter(p -> !p.equals(city)).toList();
        otherCities.parallelStream().unordered().forEach(c -> {
            List<Point> path = roadFinder.findPath(c);
            roads.add(path);
        });
        return roads;
    }
}
