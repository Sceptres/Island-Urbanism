package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public interface Graph<T> {
    /**
     *
     * @return True if the graph is empty. False otherwise.
     */
    boolean isEmpty();

    /**
     * Clears the graph
     */
    void clear();

    /**
     *
     * @return The number of nodes in this graph
     */
    int getNumNodes();

    /**
     *
     * @param t The element to add as a node to the graph
     */
    void addNode(T t);

    /**
     *
     * @param nodes The nodes to add to the graph
     */
    void addAllNodes(Collection<? extends T> nodes);

    /**
     *
     * @param t The element to remove from the graph
     */
    void removeNode(T t);

    /**
     *
     * @param nodes The list of nodes to remove from the graph
     */
    void removeAllNodes(Collection<? extends T> nodes);

    /**
     *
     * @param predicate The {@link Predicate} function that represents when to remove a node.
     */
    void removeNodeIf(Predicate<T> predicate);

    /**
     *
     * @param t The node to look for
     * @return True if this node already exists. False otherwise.
     */
    boolean containsNode(T t);

    /**
     *
     * @return The list of nodes in the graph
     */
    List<T> getNodes();

    /**
     *
     * @return The number of edges in the graph
     */
    int getNumEdges();

    /**
     *
     * @param t1 The first node of the edge
     * @param t2 The second node of the edge
     */
    void addEdge(T t1, T t2);

    /**
     *
     * @param edge The {@link Edge} to add to the graph
     */
    void addEdge(Edge edge);

    /**
     *
     * @param edges The {@link Collection} of edges to add to the graph
     */
    void addAllEdges(Collection<? extends Edge> edges);

    /**
     *
     * @param edge The {@link Edge} to set weight of
     * @param weight The weight to set the edge to
     */
    void setEdgeWeight(Edge edge, double weight);

    /**
     *
     * @param t1 The source node of the edge to set weight of
     * @param t2 The target node of the edge to set weight of
     * @param weight The weight of the edge
     */
    void setEdgeWeight(T t1, T t2, double weight);

    /**
     *
     * @param edge The {@link Edge} to get weight of
     * @return The weight of the given edge
     */
    double getEdgeWeight(Edge edge);

    /**
     *
     * @param edge The {@link Edge} to remove.
     */
    void removeEdge(Edge edge);

    /**
     *
     * @param t1 The first node of the edge
     * @param t2 The second node of the edge
     */
    void removeEdge(T t1, T t2);

    /**
     *
     * @param edges The edges to remove from the graph
     */
    void removeAllEdges(Collection<? extends Edge> edges);

    /**
     *
     * @param predicate The {@link Predicate} that represents when to remove an edge
     */
    void removeEdgesIf(Predicate<Edge> predicate);

    /**
     *
     * @return The list of edges in the graph
     */
    List<Edge> getEdges();

    /**
     *
     * @param t1 The source node of the {@link Edge}
     * @param t2 The target node of the {@link Edge}
     * @return The {@link Edge} that contains the 2 given nodes
     */
    Edge getEdge(T t1, T t2);

    /**
     *
     * @param t The node to check edges
     * @return The list of edges connected to the node
     */
    List<Edge> getNodeEdges(T t);

    /**
     *
     * @param edge The {@link Edge} to get source node of
     * @return The source node of the given edge
     */
    T getEdgeSourceNode(Edge edge);

    /**
     *
     * @param edge The {@link Edge} to get the target node of
     * @return The target node of the given edge
     */
    T getEdgeTargetNode(Edge edge);

    /**
     *
     * @param t1 The source node of the {@link Edge}
     * @param t2 The target node of the {@link Edge}
     * @return True if there already exists an edge connecting these nodes. False otherwise.
     */
    boolean hasEdge(T t1, T t2);

    /**
     *
     * @param edge The {@link Edge} to check for
     * @return True if the edge exists. False otherwise.
     */
    boolean hasEdge(Edge edge);

    /**
     *
     * @param var1 The object to check equality with
     * @return True if var1 is equivalent to this object. False otherwise.
     */
    boolean equals(Object var1);

    /**
     *
     * @return The hash code of this object
     */
    int hashCode();
}
