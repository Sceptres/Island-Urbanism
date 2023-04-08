package ca.mcmaster.cas.se2aa4.a2.island.city.road.generator;

import ca.mcmaster.cas.se2aa4.a2.island.Util;
import ca.mcmaster.cas.se2aa4.a2.island.geography.Land;
import ca.mcmaster.cas.se2aa4.a2.island.path.Path;
import ca.mcmaster.cas.se2aa4.a2.island.path.type.PathType;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a2.island.tile.Tile;
import ca.mcmaster.cas.se2aa4.a2.island.tile.type.TileGroup;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

import java.util.List;

public abstract class AbstractRoadGenerator implements RoadGenerator {
    private final Land land;
    protected final List<Path> paths;
    protected final List<Point> cities;
    protected final List<Point> points;

    public AbstractRoadGenerator(Land land) {
        this.land = land;
        List<Tile> tiles = land.getTiles().stream().filter(t -> t.getType().getGroup() != TileGroup.WATER).toList();
        this.paths = land.getPaths();
        this.cities = land.getCities();
        this.points = Util.getTilePoints(tiles);
    }

    @Override
    public final void generate() {
        Graph<Point> graph = this.generateGraph(this.land);
        List<List<Point>> roads = this.generateRoads(graph);

        roads.stream().unordered().parallel().forEach(r -> {
            for(int i=0; i < r.size()-1; i++) {
                Point start = r.get(i);
                Point next = r.get(i+1);

                Path pointsPath = this.paths.stream().filter(p -> p.hasPoint(start) && p.hasPoint(next)).findFirst().get();
                synchronized(this.paths) {
                    pointsPath.setType(PathType.ROAD);
                }
            }
        });
    }

    /**
     *
     * @param land The {@link Land} to generate graph from
     * @return The {@link Graph} generated from the given land
     */
    protected abstract Graph<Point> generateGraph(Land land);

    /**
     *
     * @param graph The {@link Graph} to find paths from
     * @return All the roads between the cities
     */
    protected abstract List<List<Point>> generateRoads(Graph<Point> graph);
}
