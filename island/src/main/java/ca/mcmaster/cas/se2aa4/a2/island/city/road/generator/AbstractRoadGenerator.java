package ca.mcmaster.cas.se2aa4.a2.island.city.road.generator;

import ca.mcmaster.cas.se2aa4.a2.island.geography.Land;
import ca.mcmaster.cas.se2aa4.a2.island.mesh.IslandMesh;
import ca.mcmaster.cas.se2aa4.a2.island.path.Path;
import ca.mcmaster.cas.se2aa4.a2.island.path.type.PathType;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a2.island.tile.Tile;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.segment.Segment;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRoadGenerator implements RoadGenerator {
    private final IslandMesh mesh;
    private final List<Tile> tiles;
    private final List<Path> paths;
    private final List<Point> cities;
    private final List<Point> points;

    public AbstractRoadGenerator(IslandMesh mesh, Land land) {
        this.mesh = mesh;
        this.tiles = land.getTiles();
        this.paths = land.getPaths();
        this.cities = land.getCities();
        this.points = tiles.stream().map(Tile::getCentroid).collect(Collectors.toList());
    }

    @Override
    public final void generate() {
        if(cities.size() > 1) {
            Graph<Point> graph = this.generateGraph(this.tiles, this.paths, this.points, this.cities);
            List<List<Point>> roads = this.generateRoads(graph, this.cities);

            roads.forEach(r -> {
                for (int i = 0; i < r.size() - 1; i++) {
                    Point start = r.get(i);
                    Point next = r.get(i + 1);

                    Segment segment = new Segment(start.getVertex(), next.getVertex());
                    Path path = new Path(segment, start, next);
                    path.setType(PathType.ROAD);

                    this.mesh.addPath(path);
                }
            });
        }
    }

    /**
     *
     * @param tiles The tiles to generate the graph from
     * @param paths The paths to generate the graph from
     * @param points The list of all points allowed in the graph
     * @param cities The cities of the island
     * @return The {@link Graph} generated from the given land
     */
    protected abstract Graph<Point> generateGraph(List<Tile> tiles, List<Path> paths, List<Point> points, List<Point> cities);

    /**
     *
     * @param graph The {@link Graph} to find paths from
     * @param cities The cities of the island
     * @return All the roads between the cities
     */
    protected abstract List<List<Point>> generateRoads(Graph<Point> graph, List<Point> cities);
}
