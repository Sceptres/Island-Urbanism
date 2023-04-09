package ca.mcmaster.cas.se2aa4.a4.pathfinder.path;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

import java.util.*;

public abstract class AbstractPathAlgorithm<T> implements PathAlgorithm<T> {

    private final T start;
    private final Map<T, T> paths;

    protected AbstractPathAlgorithm(Graph<T> graph, T start) {
        if(!graph.containsNode(start)) {
            String message = String.format("Node %s does not exist within the graph", start);
            throw new IllegalArgumentException(message);
        }

        this.start = start;
        this.paths = this.findPaths(graph, start);
    }

    /**
     *
     * @param end The node where the path ends
     * @return A list of nodes in orders that represents the path
     */
    @Override
    public final List<T> findPath(T end) {
        List<T> finalPathReversed = new ArrayList<>();

        T node = end;
        finalPathReversed.add(node);

        while(node != this.start) {
            if(Objects.isNull(node)) {
                return List.of();
            }

            node = this.paths.get(node);
            finalPathReversed.add(node);
        }

        Collections.reverse(finalPathReversed);

        return finalPathReversed;
    }

    /**
     *
     * @param graph The {@link Graph} to find path on
     * @param start The start node of the path
     * @return The list of nodes in order that represent the path
     */
    protected abstract Map<T, T> findPaths(Graph<T> graph, T start);
}
