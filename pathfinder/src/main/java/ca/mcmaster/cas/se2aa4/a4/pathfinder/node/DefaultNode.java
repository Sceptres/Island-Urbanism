package ca.mcmaster.cas.se2aa4.a4.pathfinder.node;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultNode<T> implements Node<T> {

    private final T data;
    private final Set<Edge<T>> edges;

    DefaultNode(T data) {
        this.data = data;
        this.edges = new HashSet<>();
    }

    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public void addEdge(Edge<T> edge) throws IllegalArgumentException {

    }

    @Override
    public List<Edge<T>> getEdges() {
        return new ArrayList<>(this.edges);
    }
}
