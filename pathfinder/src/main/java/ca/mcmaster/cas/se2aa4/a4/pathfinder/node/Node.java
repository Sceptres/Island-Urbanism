package ca.mcmaster.cas.se2aa4.a4.pathfinder.node;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;

import java.util.List;

public interface Node<T> {
    /**
     *
     * @param data The data that the node will hold
     * @return The node instance with the data
     * @param <T> The type of the data the node will hold
     */
    static <T> Node<T> of(T data) {
        return new DefaultNode<>(data);
    }

    /**
     *
     * @return The data that this node holds
     */
    T getData();

    /**
     *
     * @param edge Adds an edge to this node is a source to
     * @throws IllegalArgumentException If the source node of the {@link Edge} is not this node
     */
    void addEdge(Edge<T> edge) throws IllegalArgumentException;

    /**
     *
     * @return The list of {@link Edge} that this node is a source to
     */
    List<Edge<T>> getEdges();
}
