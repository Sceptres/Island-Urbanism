package ca.mcmaster.cas.se2aa4.a4.pathfinder.path.algorithms;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.data.Tuple;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.path.AbstractPathAlgorithm;

import java.util.*;

public class ShortestPath<T> extends AbstractPathAlgorithm<T> {
    public ShortestPath(Graph<T> graph, T start) {
        super(graph, start);
    }

    @Override
    protected Map<T, T> findPaths(Graph<T> graph, T start) {
        /*
        Dijkstra's shortest path algorithm
         */
        Map<T, T> paths = new HashMap<>();
        Map<T, Double> costs = new HashMap<>();

        graph.getNodes().forEach(n -> {
            paths.put(n, null);
            costs.put(n, Double.POSITIVE_INFINITY);
        });

        paths.put(start, start);
        costs.put(start, 0d);

        PriorityQueue<Tuple<T, Double>> q = new PriorityQueue<>(Comparator.comparingDouble(Tuple::e2));
        q.add(Tuple.of(start, 0d));

        while(!q.isEmpty()) {
            T m = q.poll().e1();
            List<Edge<T>> nodeEdges = graph.getNodeEdges(m);
            for(Edge<T> edge : nodeEdges) {
                T n = graph.getEdgeTargetNode(edge);

                double mCost = costs.get(m);
                double nCost = costs.get(n);
                double weight = graph.getEdgeWeight(edge);

                if(mCost + weight < nCost) {
                    paths.put(n, m);
                    costs.put(n, weight+mCost);
                    q.add(Tuple.of(n, nCost));
                }
            }
        }

        return paths;
    }
}
