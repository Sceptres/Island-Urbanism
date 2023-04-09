package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.graphs;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency.AdjacencyGraph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.node.Node;

import java.util.List;

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
    public void setEdgeWeight(Edge<T> edge, double weight) {
        super.setEdgeWeight(edge, weight);

        T t1 = super.getEdgeSourceNode(edge);
        T t2 = super.getEdgeTargetNode(edge);
        Edge<T> edge1 = super.getEdge(t2, t1);
        super.setEdgeWeight(edge1, weight);
    }

    @Override
    protected void specificRemoveEdge(Node<T> n1, Node<T> n2) {
        Edge<T> t1EdgeRemove = Edge.of(n1, n2);
        Edge<T> t2EdgeRemove = Edge.of(n2, n1);

        n1.removeEdge(t1EdgeRemove);
        n2.removeEdge(t2EdgeRemove);

        super.weightMap.remove(t1EdgeRemove);
        super.weightMap.remove(t2EdgeRemove);
    }

    @Override
    protected boolean checkEdges(Node<T> n1, Node<T> n2) {
        List<Edge<T>> n1Edges = n1.getEdges();
        List<Edge<T>> n2Edges = n2.getEdges();
        boolean t1Contains = n1Edges.stream().anyMatch(e -> n2.equals(e.getTargetNode()));
        boolean t2Contains = n2Edges.stream().anyMatch(e -> n1.equals(e.getTargetNode()));
        return t1Contains && t2Contains;
    }
}
