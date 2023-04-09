package ca.mcmaster.cas.se2aa4.a4.pathfinder.edge;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.node.Node;

public interface Edge<T> {
    /**
     *
     * @param t1 The source node of the edge
     * @param t2 The target node of the edge
     * @return The edge with the given nodes
     */
    static <T> Edge<T> of(T t1, T t2) {
        return new DefaultEdge<>(t1, t2);
    }

    /**
     *
     * @param n1 The source node of the edge
     * @param n2 The target node of the edge
     * @return The edge with the given nodes
     */
    static <T> Edge<T> of(Node<T> n1, Node<T> n2) {
        return new DefaultEdge<>(n1, n2);
    }

    /**
     *
     * @return The source {@link Node} of the edge
     */
    Node<T> getSourceNode();

    /**
     *
     * @return The target {@link Node} of the edge
     */
    Node<T> getTargetNode();

    /**
     *
     * @return The data that the source {@link Node} of the edge holds
     */
    T getSourceNodeData();

    /**
     *
     * @return The data that the target {@link Node} of the edge holds
     */
    T getTargetNodeData();
}
