package ca.mcmaster.cas.se2aa4.a4.pathfinder.path.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Util;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.data.Tuple;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.AbstractPathAlgorithm;

import java.util.*;

public class ShortestPath<T> extends AbstractPathAlgorithm<T> {
    public ShortestPath(Graph<T> graph) {
        super(graph);
    }

    @Override
    protected List<T> findPath(Graph<T> graph, T start, T end) {
        Map<T, T> paths = Util.dijkstraAlgorithm(graph, start);

        List<T> finalPathReversed = new ArrayList<>();

        T node = end;
        finalPathReversed.add(node);

        while(node != start) {
            if(Objects.isNull(node)) {
                return List.of();
            }

            node = paths.get(node);
            finalPathReversed.add(node);
        }

        Collections.reverse(finalPathReversed);

        return finalPathReversed;
    }
}
