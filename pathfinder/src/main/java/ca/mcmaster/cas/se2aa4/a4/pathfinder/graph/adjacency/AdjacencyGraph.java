package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.node.Node;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The adjacency list representation of a graph taught in 2C03. This design is also
 * partially inspired by the Java JGraphT library, a library that I have used in
 * assignment #3 in the river generation.
 * @param <T> The type of the nodes in the graph
 */
public abstract class AdjacencyGraph<T> implements Graph<T> {

    protected int numNodes;
    protected int numEdges;
    protected final boolean isWeighted;
    protected final Set<Node<T>> graph;
    protected final Map<Edge<T>, Double> weightMap;

    protected AdjacencyGraph(boolean isWeighted) {
        this.numNodes = 0;
        this.numEdges = 0;
        this.isWeighted = isWeighted;
        this.graph = new HashSet<>();
        this.weightMap = new HashMap<>();
    }

    @Override
    public boolean isEmpty() {
        return this.numNodes == 0;
    }

    @Override
    public void clear() {
        this.numNodes = 0;
        this.numEdges = 0;
        this.graph.clear();
        this.weightMap.clear();
    }

    @Override
    public int getNumNodes() {
        return this.numNodes;
    }

    @Override
    public void addNode(T t) {
        if (!this.containsNode(t)) {
            Node<T> node = Node.of(t);
            this.graph.add(node);
            this.numNodes++;
        }
    }

    protected Node<T> getNode(T t) {
        Optional<Node<T>> optional = this.graph.stream().filter(n -> n.getData().equals(t)).findFirst();

        if(optional.isEmpty()) {
            String message = String.format("Node %s does not exist in the graph!", t);
            throw new IllegalArgumentException(message);
        }

        return optional.get();
    }

    @Override
    public void addAllNodes(Collection<? extends T> nodes) {
        nodes.forEach(this::addNode);
    }

    @Override
    public void removeNode(T t) {
        if (this.containsNode(t)) {
            Node<T> node = this.getNode(t);
            List<Edge<T>> edges = node.getEdges();
            this.graph.remove(node);

            this.numNodes--;
            this.numEdges -= edges.size();
        }
    }

    @Override
    public void removeAllNodes(Collection<? extends T> nodes) {
        nodes.forEach(this::removeNode);
    }

    @Override
    public void removeNodeIf(Predicate<T> predicate) {
        this.getNodes().forEach(n -> {
            if (predicate.test(n))
                this.removeNode(n);
        });
    }

    @Override
    public boolean containsNode(T t) {
        return this.graph.contains(Node.of(t));
    }

    @Override
    public List<T> getNodes() {
        return this.graph.stream().map(Node::getData).collect(Collectors.toList());
    }

    @Override
    public int getNumEdges() {
        return this.numEdges;
    }

    @Override
    public void addEdge(T t1, T t2) {
        if(!this.containsNode(t1) || !this.containsNode(t2)) {
            String message = String.format("Nodes %s and %s are not added to the graph!", t1, t2);
            throw new IllegalArgumentException(message);
        }

        if(!this.hasEdge(t1, t2)) {
            Node<T> node1 = this.getNode(t1);
            Node<T> node2 = this.getNode(t2);
            Edge<T> edge = Edge.of(node1, node2);
            node1.addEdge(edge);
            this.weightMap.put(edge, 1d);
            this.numEdges++;
        }
    }

    @Override
    public void addEdge(Edge<T> edge) {
        T source = this.getEdgeSourceNode(edge);
        T target = this.getEdgeTargetNode(edge);
        this.addEdge(source, target);
    }

    @Override
    public List<Edge<T>> getNodeEdges(T t) {
        Node<T> node = this.getNode(t);
        return node.getEdges();
    }

    @Override
    public void addAllEdges(Collection<? extends Edge<T>> edges) {
        edges.forEach(this::addEdge);
    }

    @Override
    public void setEdgeWeight(Edge<T> edge, double weight) {
        if(this.isWeighted)
            this.weightMap.put(edge, weight);
    }

    @Override
    public void setEdgeWeight(T t1, T t2, double weight) {
        Edge<T> edge = this.getEdge(t1, t2);
        this.setEdgeWeight(edge, weight);
    }

    @Override
    public double getEdgeWeight(Edge<T> edge) {
        if(!this.getEdges().contains(edge)) {
            String message = String.format("The edge %s does not exist in this graph!", edge);
            throw new IllegalArgumentException(message);
        }
        return this.weightMap.get(edge);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        T source = this.getEdgeSourceNode(edge);
        T target = this.getEdgeTargetNode(edge);
        this.removeEdge(source, target);
    }

    @Override
    public void removeEdge(T t1, T t2) {
        if(!this.getEdges().contains(Edge.of(t1, t2))) {
            String message = String.format("Nodes %s and %s do not exist in the graph!", t1, t2);
            throw new IllegalArgumentException(message);
        }

        Node<T> node1 = this.getNode(t1);
        Node<T> node2 = this.getNode(t2);

        this.specificRemoveEdge(node1, node2);
        this.numEdges--;
    }

    /**
     *
     * @param n1 The source node of the edge
     * @param n2 The target node of the edge
     */
    protected abstract void specificRemoveEdge(Node<T> n1, Node<T> n2);

    @Override
    public void removeAllEdges(Collection<? extends Edge<T>> edges) {
        edges.forEach(this::removeEdge);
    }

    @Override
    public void removeEdgesIf(Predicate<Edge<T>> predicate) {
        this.getEdges().forEach(e -> {
            if(predicate.test(e)) {
                this.removeEdge(e);
            }
        });
    }

    @Override
    public List<Edge<T>> getEdges() {
        return this.graph.stream().flatMap(n -> n.getEdges().stream()).toList();
    }

    @Override
    public T getEdgeSourceNode(Edge<T> edge) {
        return edge.getSourceNodeData();
    }

    @Override
    public T getEdgeTargetNode(Edge<T> edge) {
        return edge.getTargetNodeData();
    }

    @Override
    public Edge<T> getEdge(T t1, T t2) {
        Node<T> node = this.getNode(t1);
        List<Edge<T>> edges = node.getEdges();

        Optional<Edge<T>> edgeOptional = edges.stream()
                .filter(e -> e.getSourceNodeData().equals(t1) && e.getTargetNodeData().equals(t2))
                .findFirst();

        if(edgeOptional.isEmpty()) {
            String message = String.format("Unable to find the edge (%s, %s)!", t1, t2);
            throw new IllegalArgumentException(message);
        }

        return edgeOptional.get();
    }

    @Override
    public boolean hasEdge(T t1, T t2) {
        if(!this.containsNode(t1) || !this.containsNode(t2))
            return false;

        Node<T> node1 = this.getNode(t1);
        Node<T> node2 = this.getNode(t2);

        return this.checkEdges(node1, node2);
    }

    @Override
    public boolean hasEdge(Edge<T> edge) {
        T source = this.getEdgeSourceNode(edge);
        T target = this.getEdgeTargetNode(edge);
        return false;
    }

    protected abstract boolean checkEdges(Node<T> n1, Node<T> n2);

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");

        for(Node<T> t : this.graph) {
            builder.append(String.format("\t%s => [", t));

            List<Edge<T>> edges = t.getEdges();

            for(Edge<T> edge : edges) {
                builder.append(String.format("(%s):", edge));
                builder.append(String.format("%.2f", this.getEdgeWeight(edge)));
                builder.append(", ");
            }

            if(!edges.isEmpty()) {
                builder.deleteCharAt(builder.length() - 1);
                builder.deleteCharAt(builder.length() - 1);
            }
            builder.append("]\n");
        }

        builder.append("}");

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdjacencyGraph<?> that)) return false;
        return numNodes == that.numNodes &&
               numEdges == that.numEdges &&
               isWeighted == that.isWeighted &&
               Objects.equals(weightMap, that.weightMap) &&
               Objects.equals(graph, that.graph);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numNodes, graph);
    }
}
