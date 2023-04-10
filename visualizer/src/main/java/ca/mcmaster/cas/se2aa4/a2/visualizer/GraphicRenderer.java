package ca.mcmaster.cas.se2aa4.a2.visualizer;

import ca.mcmaster.cas.se2aa4.a2.mesh.adt.mesh.Mesh;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.segment.Segment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicRenderer {

    private final boolean isDebug;

    public GraphicRenderer(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public void render(Mesh mesh, Graphics2D canvas) {
        canvas.setColor(Color.BLACK);

        List<Segment> debugSegments = new ArrayList<>();

        // Render polygons
        mesh.getPolygons().forEach(polygon -> {
            if(this.isDebug) {
                polygon.getNeighbors().forEach(p -> {
                    // Segment that will be drawn to show neighborhood
                    Segment segment = new Segment(polygon.getCentroid(), p.getCentroid());
                    segment.setColor(Color.GRAY);
                    debugSegments.add(segment);
                });
            }

            polygon.render(canvas);
        });

        // Render segments
        mesh.getSegments().forEach(segment -> {
            if(this.isDebug) {
                segment.setColor(Color.BLACK);
            }
            segment.render(canvas);
        });

        // Render vertices
        mesh.getVertices().forEach(vertex -> {
            if(this.isDebug) {
                if(vertex.isCentroid())
                    vertex.setColor(Color.GRAY);
                else
                    vertex.setColor(Color.BLACK);

                vertex.setThickness(3);
            }
            vertex.render(canvas);
        });

        if(this.isDebug) {
            debugSegments.forEach(s -> s.render(canvas));
        }
    }
}