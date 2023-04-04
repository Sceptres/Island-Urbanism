package ca.mcmaster.cas.se2aa4.a4.pathfinder.path;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

import java.util.List;

public interface PathAlgorithm<T> {
    /**
     *
     * @return The list of nodes in order that create the shortest path
     */
    List<T> calculatePath(T start, T end);
}
