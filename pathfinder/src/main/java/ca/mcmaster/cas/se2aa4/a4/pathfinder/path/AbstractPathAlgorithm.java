package ca.mcmaster.cas.se2aa4.a4.pathfinder.path;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

import java.util.List;

public abstract class AbstractPathAlgorithm<T> implements PathAlgorithm<T> {

    private final Graph<T> graph;

    protected AbstractPathAlgorithm(Graph<T> graph) {
        this.graph = graph;
    }

    /**
     *
     * @param start The node where the path starts
     * @param end The node where the path ends
     * @return A list of nodes in orders that represents the path
     * @throws IllegalArgumentException If at least one of the given nodes does not exist within the graph
     */
    @Override
    public final List<T> calculatePath(T start, T end) {
        if(!this.graph.containsNode(start) || !this.graph.containsNode(end)) {
            String message = String.format("Node %s or %s do not exist within the graph", start, end);
            throw new IllegalArgumentException(message);
        }

        return this.findPath(this.graph, start, end);
    }

    /**
     *
     * @param graph The {@link Graph} to find path on
     * @param start The start node of the path
     * @param end The end node of the path
     * @return The list of nodes in order that represent the path
     */
    protected abstract List<T> findPath(Graph<T> graph, T start, T end);
}
