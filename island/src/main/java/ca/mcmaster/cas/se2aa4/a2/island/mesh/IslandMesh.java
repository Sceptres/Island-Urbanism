package ca.mcmaster.cas.se2aa4.a2.island.mesh;

import ca.mcmaster.cas.se2aa4.a2.island.humidity.soil.SoilAbsorptionProfile;
import ca.mcmaster.cas.se2aa4.a2.island.neighborhood.NeighborhoodRelation;
import ca.mcmaster.cas.se2aa4.a2.island.neighborhood.TileNeighborhood;
import ca.mcmaster.cas.se2aa4.a2.island.path.Path;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a2.island.tile.Tile;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.mesh.Mesh;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.polygon.Polygon;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.segment.Segment;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.services.Converter;

import java.util.ArrayList;
import java.util.List;

public class IslandMesh implements Converter<Mesh> {
    private final Mesh mesh;
    private final List<Tile> tiles;
    private final List<Path> paths;
    private final List<Point> points;

    /**
     *
     * @param mesh The {@link Mesh} to get the polygons from
     * @param paths All the paths in the mesh to tie to the tiles
     * @return All the {@link Tile} in the mesh
     */
    private static List<Tile> createTiles(Mesh mesh, List<Path> paths, List<Point> points, SoilAbsorptionProfile soilAbsorptionProfile) {
        List<Polygon> polygons = mesh.getPolygons();
        List<Tile> tiles = new ArrayList<>();

        for(Polygon polygon : polygons) {
            Structs.Polygon converted = polygon.getConverted();
            List<Path> tilePaths = converted.getSegmentIdxsList().stream().map(paths::get).toList();
            Point point = points.get(converted.getCentroidIdx());
            Tile tile = new Tile(polygon, tilePaths, point, soilAbsorptionProfile);
            tiles.add(tile);
        }

        return tiles;
    }

    /**
     *
     * @param mesh The {@link Mesh} to get the segments from
     * @return The {@link List} of {@link Path}
     */
    private static List<Path> createPaths(Mesh mesh, List<Point> points) {
        List<Segment> segments = mesh.getSegments();
        List<Path> paths = new ArrayList<>();

        for(Segment segment : segments) {
            Structs.Segment convertedSegment = segment.getConverted();
            Point p1 = points.get(convertedSegment.getV1Idx());
            Point p2 = points.get(convertedSegment.getV2Idx());

            Path path = new Path(segment, p1, p2);
            paths.add(path);
        }

        return paths;
    }

    private static List<Point> createPoints(Mesh mesh) {
        return mesh.getVertices().stream().map(Point::new).toList();
    }

    /**
     *
     * @param mesh The {@link Mesh} to read from
     */
    public IslandMesh(Mesh mesh, SoilAbsorptionProfile soilAbsorptionProfile) {
        this.mesh = mesh;
        this.points = IslandMesh.createPoints(mesh);
        this.paths = IslandMesh.createPaths(mesh, this.points);
        this.tiles = IslandMesh.createTiles(mesh, this.paths, this.points, soilAbsorptionProfile);

        NeighborhoodRelation relation = new TileNeighborhood();
        relation.calculateNeighbors(this.tiles);
    }

    /**
     *
     * @param path The {@link Path} to add to this mesh
     */
    public void addPath(Path path) {
        this.paths.add(path);
        this.mesh.addSegment(path.getSegment());
    }

    /**
     *
     * @return The {@link Point} found in this mesh
     */
    public List<Point> getPoints() {
        return this.points;
    }

    /**
     *
     * @return The {@link Path} found in this mesh
     */
    public List<Path> getPaths() {
        return new ArrayList<>(this.paths);
    }

    /**
     *
     * @return The {@link Tile} found in this mesh
     */
    public List<Tile> getTiles() {
        return new ArrayList<>(this.tiles);
    }

    /**
     *
     * @return The dimensions of this mesh
     */
    public int[] getDimension() {
        return this.mesh.getDimension();
    }

    @Override
    public Mesh getConverted() {
        return this.mesh;
    }
}
