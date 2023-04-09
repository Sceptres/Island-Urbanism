package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.AdjacencyGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DirectedGraph<T> extends AdjacencyGraph<T> {

    public DirectedGraph() {
        this(false);
    }

    public DirectedGraph(boolean isWeighted) {
        super(isWeighted);
    }

    @Override
    protected void specificRemoveEdge(T t1, T t2) {
        Edge<T> edge = Edge.of(t1, t2);
        Set<Edge<T>> edges = super.graph.get(t1);
        super.weightMap.remove(edge);
        edges.remove(edge);
    }

    @Override
    public List<Edge<T>> getNodeEdges(T t) {
        return new ArrayList<>(super.graph.get(t));
    }

    @Override
    public boolean checkEdges(T t1, T t2) {
        Set<Edge<T>> nodeEdges = super.graph.get(t1);
        return nodeEdges.stream().anyMatch(e -> t1.equals(e.getSourceNode()) && t2.equals(e.getTargetNode()));
    }
}
