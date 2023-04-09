package ca.mcmaster.cas.se2aa4.a4.pathfinder.path;

import java.util.List;

public interface PathAlgorithm<T> {
    /**
     *
     * @param end The end node of the path
     * @return The list of nodes in order that create the shortest path
     */
    List<T> findPath(T end);
}
