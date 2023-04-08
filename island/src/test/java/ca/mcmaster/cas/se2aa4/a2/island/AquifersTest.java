package ca.mcmaster.cas.se2aa4.a2.island;

import ca.mcmaster.cas.se2aa4.a2.island.path.Path;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a2.island.tile.Tile;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.polygon.Polygon;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.segment.Segment;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.vertex.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testable
public class AquifersTest {

    private Polygon polygon;
    private Tile tile;

    @BeforeEach
    public void beforeTest() {
        Vertex v1 = new Vertex(0, 0);
        Vertex v2 = new Vertex(100, 0);
        Vertex v3 = new Vertex(100, 100);
        Vertex v4 = new Vertex(0, 100);

        Point p1 = new Point(v1);
        Point p2 = new Point(v2);
        Point p3 = new Point(v3);
        Point p4 = new Point(v4);

        Segment s1 = new Segment(v1, v2);
        Segment s2 = new Segment(v2, v3);
        Segment s3 = new Segment(v3, v4);
        Segment s4 = new Segment(v4, v1);

        Path pth1 = new Path(s1, p1, p2);
        Path pth2 = new Path(s1, p2, p3);
        Path pth3 = new Path(s1, p3, p4);
        Path pth4 = new Path(s1, p4, p1);

        List<Segment> polygonSegments = List.of(s1, s2, s3, s4);
        List<Path> paths = List.of(pth1, pth2, pth3, pth4);

        this.polygon = new Polygon(polygonSegments);

        Point point = new Point(this.polygon.getCentroid());

        this.tile = new Tile(this.polygon, paths, point);
    }

    /**
     * Test for hasAquifer and putAquifer
     */
    @Test
    public void hasAquiferTest() {
        boolean x = this.tile.hasAquifer();
        assertFalse(x);
        this.tile.putAquifer();
        boolean y = this.tile.hasAquifer();
        assertTrue(y);
    }

    /**
     * Test for removeAquifer
     */
    @Test
    public void removeAquiferTest() {
        this.tile.putAquifer();
        this.tile.removeAquifer();
        boolean y = this.tile.hasAquifer();
        assertFalse(y);
    }
}