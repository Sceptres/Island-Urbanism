package ca.mcmaster.cas.se2aa4.a4.pathfinder.edge;

public interface Edge {
    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    static Edge of(Object o1, Object o2) {
        return new DefaultEdge(o1, o2);
    }

    /**
     *
     * @return The source node of the edge
     */
    Object getSourceNode();

    /**
     *
     * @return The target node of the edge
     */
    Object getTargetNode();
}
