package ca.mcmaster.cas.se2aa4.a4.pathfinder;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs.DirectedGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs.UnDirectedGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.PathAlgorithm;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.algorithms.ShortestPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortestPathTest {
    private Graph<Integer> graph;

    @Test
    public void directedUnweightedShortestPathTest() {
        this.graph = new DirectedGraph<>();
        this.fillGraph(false);

        PathAlgorithm<Integer> shortestPath = new ShortestPath<>(this.graph, 3);

        List<Integer> path39 = shortestPath.findPath(9);
        assertEquals(List.of(3, 8, 5, 9), path39);

        shortestPath = new ShortestPath<>(this.graph, 0);

        List<Integer> path09 = shortestPath.findPath(9);
        assertEquals(List.of(), path09);
    }

    @Test
    public void directedWeightedShortestPathTest() {
        this.graph = new DirectedGraph<>(true);
        this.fillGraph(true);

        PathAlgorithm<Integer> shortestPath = new ShortestPath<>(this.graph, 3);

        List<Integer> path30 = shortestPath.findPath(0);
        assertEquals(List.of(3, 0), path30);

        shortestPath = new ShortestPath<>(this.graph, 0);

        List<Integer> path09 = shortestPath.findPath(9);
        assertEquals(List.of(), path09);
    }

    @Test
    public void undirectedUnweightedShortestPathTest() {
        this.graph = new UnDirectedGraph<>();
        this.fillGraph(false);

        PathAlgorithm<Integer> shortestPath = new ShortestPath<>(this.graph, 3);

        List<Integer> path36 = shortestPath.findPath(6);
        assertEquals(List.of(3, 0, 9, 6), path36);
    }

    @Test
    public void undirectedWeightedShortestPathTest() {
        this.graph = new UnDirectedGraph<>(true);
        this.fillGraph(true);

        PathAlgorithm<Integer> shortestPath = new ShortestPath<>(this.graph, 3);

        List<Integer> path36 = shortestPath.findPath(6);
        assertEquals(List.of(3, 8, 1, 5, 6), path36);
    }

    /**
     * Fills the graph with nodes an edges matching an example from
     * the slides on graphs in 2C03.
     */
    private void fillGraph(boolean weighted) {
        this.graph.addNode(0);
        this.graph.addNode(1);
        this.graph.addNode(2);
        this.graph.addNode(3);
        this.graph.addNode(4);
        this.graph.addNode(5);
        this.graph.addNode(6);
        this.graph.addNode(7);
        this.graph.addNode(8);
        this.graph.addNode(9);

        this.graph.addEdge(3,8);
        this.graph.addEdge(3,0);
        this.graph.addEdge(3,2);
        this.graph.addEdge(1,5);
        this.graph.addEdge(1,8);
        this.graph.addEdge(8,5);
        this.graph.addEdge(2,7);
        this.graph.addEdge(4,0);
        this.graph.addEdge(5,9);
        this.graph.addEdge(6,5);
        this.graph.addEdge(6,9);
        this.graph.addEdge(7,0);
        this.graph.addEdge(9,0);

        if(weighted) {
            this.graph.setEdgeWeight(3, 8, 1);
            this.graph.setEdgeWeight(3, 0, 9);
            this.graph.setEdgeWeight(3, 2, 5);
            this.graph.setEdgeWeight(1, 5, 2);
            this.graph.setEdgeWeight(1, 8, 4);
            this.graph.setEdgeWeight(8, 5, 7);
            this.graph.setEdgeWeight(2, 7, 7);
            this.graph.setEdgeWeight(4, 0, 13);
            this.graph.setEdgeWeight(5, 9, 8);
            this.graph.setEdgeWeight(6, 5, 3);
            this.graph.setEdgeWeight(6, 9, 4);
            this.graph.setEdgeWeight(7, 0, 12);
            this.graph.setEdgeWeight(9, 0, 11);
        }
    }
}
