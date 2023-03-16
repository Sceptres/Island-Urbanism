package ca.mcmaster.cas.se2aa4.a2.island.generator.generators;

import ca.mcmaster.cas.se2aa4.a2.island.generator.AbstractIslandGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.geometry.Shape;
import ca.mcmaster.cas.se2aa4.a2.island.geometry.shapes.Circle;
import ca.mcmaster.cas.se2aa4.a2.island.tile.Tile;
import ca.mcmaster.cas.se2aa4.a2.island.tile.type.TileGroup;
import ca.mcmaster.cas.se2aa4.a2.island.tile.type.TileType;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.mesh.Mesh;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.vertex.Vertex;

import java.util.List;

public class LagoonIslandGenerator extends AbstractIslandGenerator {
    public LagoonIslandGenerator(Mesh mesh, Shape shape) {
        super(mesh, shape, 1);
    }

    @Override
    protected void generateIsland(List<Tile> tiles, Shape shape) {
        tiles.stream().filter(t -> !shape.contains(t)).forEach(t -> t.setType(TileType.OCEAN_TILE));

        List<Tile> landTiles = tiles.stream().filter(t -> t.getType().getGroup() == TileGroup.LAND).toList();
        landTiles.stream().filter(t ->
                t.getNeighbors().stream().anyMatch(t1 -> t1.getType() == TileType.OCEAN_TILE)
        ).forEach(t -> t.setType(TileType.BEACH_TILE));
    }

    @Override
    protected void generateLakes(List<Tile> mainLandTiles, int numLakes) {
        int[] meshDimension = super.mesh.getDimension();
        Vertex meshCenter = new Vertex(meshDimension[0]/2f, meshDimension[1]/2f);

        double diagonalLength = Math.hypot(meshDimension[0]/2f, meshDimension[1]/2f);
        Shape circle = new Circle(diagonalLength/4f, meshCenter);

        mainLandTiles.stream().filter(circle::contains).forEach(t -> t.setType(TileType.LAGOON_TILE));
        mainLandTiles.stream().filter(t -> !circle.contains(t)).filter( t ->
                t.getNeighbors().stream().anyMatch(t1 -> t1.getType() == TileType.LAGOON_TILE)
        ).forEach(t -> t.setType(TileType.BEACH_TILE));
    }
}