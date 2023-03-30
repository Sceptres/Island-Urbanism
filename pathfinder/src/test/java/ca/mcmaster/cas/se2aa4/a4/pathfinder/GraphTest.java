package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.edges.DefaultEdge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs.DirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class GraphTest {

    private Graph<Integer> graph;

    @BeforeEach
    public void beforeTest() {
        this.graph = new DirectedGraph<>(Integer.class);
    }

    @Test
    public void addNodeTest() {
        assertEquals(0, this.graph.getNumNodes());
        this.graph.addNode(1);
        assertEquals(1, this.graph.getNumNodes());
    }

    @Test
    public void removeNodeTest() {
        this.graph.addNode(1);
        this.graph.addNode(2);
        this.graph.addNode(3);
        assertEquals(3, this.graph.getNumNodes());
        this.graph.removeNode(1);
        assertEquals(2, this.graph.getNumNodes());
    }

    @Test
    public void addEdgeTest() {
        assertEquals(0, this.graph.getNumEdges());
        this.graph.addNode(1);
        this.graph.addNode(2);
        this.graph.addEdge(1, 2);
        assertEquals(1, this.graph.getNumEdges());

        assertThrows(IllegalArgumentException.class, () -> {
            this.graph.addEdge(1, 3);
        });
    }

    @Test
    public void removeEdgeTest() {
        this.graph.addNode(1);
        this.graph.addNode(2);
        this.graph.addEdge(1, 2);
        assertEquals(2, this.graph.getNumNodes());
        assertEquals(1, this.graph.getNumEdges());
        this.graph.removeEdge(1, 2);
        assertEquals(0, this.graph.getNumEdges());
    }

    @Test
    public void clearGraphTest() {
        this.graph.addNode(1);
        this.graph.addNode(2);
        this.graph.addNode(3);
        this.graph.addEdge(1, 2);
        this.graph.addEdge(1, 3);
        this.graph.addEdge(2, 3);
        assertEquals(3, this.graph.getNumNodes());
        assertEquals(3, this.graph.getNumEdges());

        this.graph.clear();
        assertEquals(0, this.graph.getNumNodes());
        assertEquals(0, this.graph.getNumEdges());
    }

    @Test
    public void containsNodeTest() {
        boolean contains = this.graph.containsNode(1);
        assertFalse(contains);

        this.graph.addNode(2);
        contains = this.graph.containsNode(2);
        assertTrue(contains);
    }

    @Test
    public void containsEdgeTest() {
        this.graph.addNode(1);
        this.graph.addNode(2);
        this.graph.addEdge(1, 2);

        boolean contains = this.graph.hasEdge(1, 2);
        assertTrue(contains);

        contains = this.graph.hasEdge(2, 3);
        assertFalse(contains);
    }

    @Test
    public void edgeWeightTest() {
        this.graph = new DirectedGraph<>(Integer.class, true);

        this.graph.addNode(1);
        this.graph.addNode(2);

        Edge edge = new DefaultEdge(1, 2);
        this.graph.addEdge(edge);

        double edgeWeight = this.graph.getEdgeWeight(edge);
        assertEquals(1, edgeWeight);

        this.graph.setEdgeWeight(edge, 2);
        edgeWeight = this.graph.getEdgeWeight(edge);
        assertEquals(2, edgeWeight);
    }
}
