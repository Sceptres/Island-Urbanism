package ca.mcmaster.cas.se2aa4.a4.pathfinder.edge;

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
     * @return The source node of the edge
     */
    T getSourceNode();

    /**
     *
     * @return The target node of the edge
     */
    T getTargetNode();
}
