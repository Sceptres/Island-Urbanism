package ca.mcmaster.cas.se2aa4.a2.island;

import ca.mcmaster.cas.se2aa4.a2.island.path.Path;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a2.island.tile.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    /**
     *
     * @param tiles The group of {@link Tile} to get the {@link Point}
     * @return The list of {@link Point} belonging to the tiles
     */
    public static List<Point> getTilePoints(List<Tile> tiles) {
        return tiles.stream().flatMap(t -> t.getPoints().stream()).distinct().collect(Collectors.toList());
    }

    /**
     *
     * @param paths The group of {@link Path} to get the {@link Point} of
     * @return The group of {@link Point} belonging to the group of paths
     */
    public static List<Point> getPathPoints(List<Path> paths) {
        return paths.stream()
                .flatMap(p -> Arrays.stream(new Point[]{p.getP1(), p.getP2()}))
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     *
     * @param tiles The group of {@link Tile} to extract get the {@link Path} of
     * @return The group of {@link Path} belonging to the group of tiles
     */
    public static List<Path> getTilePaths(List<Tile> tiles) {
        return tiles.stream().flatMap(t -> t.getPaths().stream()).distinct().collect(Collectors.toList());
    }
}
