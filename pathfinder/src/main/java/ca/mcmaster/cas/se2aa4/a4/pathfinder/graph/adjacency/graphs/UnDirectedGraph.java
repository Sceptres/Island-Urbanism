package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.AdjacencyGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UnDirectedGraph<T> extends AdjacencyGraph<T> {

    public UnDirectedGraph() {
        this(false);
    }

    public UnDirectedGraph(boolean isWeighted) {
        super(isWeighted);
    }

    @Override
    public void addEdge(T t1, T t2) {
        super.addEdge(t1, t2);
        super.addEdge(t2, t1);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        T t1 = super.getEdgeSourceNode(edge);
        T t2 = super.getEdgeTargetNode(edge);
        this.addEdge(t1, t2);
    }

    @Override
    public int getNumEdges() {
        return super.getNumEdges()/2;
    }

    @Override
    protected void specificRemoveEdge(T t1, T t2) {
        Set<Edge<T>> t1Edges = super.graph.get(t1);
        Set<Edge<T>> t2Edges = super.graph.get(t2);
        Edge<T> t1EdgeRemove = Edge.of(t1, t2);
        Edge<T> t2EdgeRemove = Edge.of(t2, t1);
        super.weightMap.remove(t1EdgeRemove);
        super.weightMap.remove(t2EdgeRemove);
        t1Edges.remove(t1EdgeRemove);
        t2Edges.remove(t2EdgeRemove);
    }

    @Override
    public List<Edge<T>> getNodeEdges(T t) {
        return new ArrayList<>(super.graph.get(t));
    }

    @Override
    protected boolean checkEdges(T t1, T t2) {
        Set<Edge<T>> t1Edges = super.graph.get(t1);
        Set<Edge<T>> t2Edges = super.graph.get(t2);
        boolean t1Contains = t1Edges.stream().anyMatch(e -> t2.equals(e.getTargetNode()));
        boolean t2Contains = t2Edges.stream().anyMatch(e -> t1.equals(e.getTargetNode()));
        return t1Contains && t2Contains;
    }

    @Override
    public void setEdgeWeight(Edge<T> edge, double weight) {
        super.setEdgeWeight(edge, weight);

        T t1 = super.getEdgeSourceNode(edge);
        T t2 = super.getEdgeTargetNode(edge);
        Edge<T> edge1 = super.getEdge(t2, t1);
        super.setEdgeWeight(edge1, weight);
    }
}
