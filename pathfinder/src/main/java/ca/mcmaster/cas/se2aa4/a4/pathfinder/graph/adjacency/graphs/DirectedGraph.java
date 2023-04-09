package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.AdjacencyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.node.Node;

import java.util.List;

public class DirectedGraph<T> extends AdjacencyGraph<T> {

    public DirectedGraph() {
        this(false);
    }

    public DirectedGraph(boolean isWeighted) {
        super(isWeighted);
    }

    @Override
    protected void specificRemoveEdge(Node<T> t1, Node<T> t2) {
        Edge<T> edge = Edge.of(t1, t2, super.isWeighted);
        t1.removeEdge(edge);
    }

    @Override
    public boolean checkEdges(Node<T> n1, Node<T> n2) {
        List<Edge<T>> nodeEdges = n1.getEdges();
        return nodeEdges.contains(Edge.of(n1, n2, super.isWeighted));
    }
}
