package ca.mcmaster.cas.se2aa4.a2.island.city.road.generator.generators;

import ca.mcmaster.cas.se2aa4.a2.island.city.road.generator.AbstractRoadGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.geography.Land;
import ca.mcmaster.cas.se2aa4.a2.island.path.Path;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs.UnDirectedGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.PathAlgorithm;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.algorithms.ShortestPath;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StarNetwork extends AbstractRoadGenerator {
    public StarNetwork(Land land) {
        super(land);
    }

    @Override
    protected Graph<Point> generateGraph(Land land) {
        Graph<Point> graph = new UnDirectedGraph<>(Point.class, true);

        super.points.stream().unordered().parallel().forEach(point -> {
            synchronized(graph) {
                graph.addNode(point);
            }

            List<Path> pointPaths = super.paths.stream().filter(p -> p.hasPoint(point)).toList();
            pointPaths.forEach(p -> {
                Point p1 = p.getP1();
                Point p2 = p.getP2();
                Point connectedPoint = p1.equals(point) ? p2 : p1;

                synchronized(graph) {
                    graph.addNode(connectedPoint);
                    graph.addEdge(point, connectedPoint);
                    graph.setEdgeWeight(point, connectedPoint, p.getElevation()*100);
                }
            });
        });

        return graph;
    }

    @Override
    protected List<List<Point>> generateRoads(Graph<Point> graph) {
        List<List<Point>> roads = new ArrayList<>();

        PathAlgorithm<Point> roadFinder = new ShortestPath<>(graph);
        Point city = super.cities.stream().max(Comparator.comparing(Point::getThickness, Float::compareTo)).get();

        List<Point> otherCities = super.cities.stream().filter(p -> !p.equals(city)).toList();
        otherCities.stream().parallel().unordered().forEach(c -> {
            List<Point> path = roadFinder.calculatePath(city, c);
            synchronized(roads) {
                roads.add(path);
            }
        });
        return roads;
    }
}
