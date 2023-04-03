package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testable
public class EdgeTest {
    @Test
    public void edgeNodeTest() {
        Edge edge;

        String n1 = "test1";
        String n2 = "test2";

        edge = Edge.of(n1, n2);

        assertEquals(n1, edge.getSourceNode());
        assertEquals(n2, edge.getTargetNode());
    }
}
