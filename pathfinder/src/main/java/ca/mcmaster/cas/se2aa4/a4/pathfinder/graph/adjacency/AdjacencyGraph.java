package ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.adjacency;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.edge.Edge;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.graph.Graph;

import java.util.*;
import java.util.function.Predicate;

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
    protected final Class<T> dataClass;
    protected final Map<T, Set<Edge>> graph;
    protected final Map<Edge, Double> weightMap;

    protected AdjacencyGraph(Class<T> dataClass, boolean isWeighted) {
        this.numNodes = 0;
        this.numEdges = 0;
        this.isWeighted = isWeighted;
        this.dataClass = Objects.requireNonNull(dataClass);
        this.graph = new HashMap<>();
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
        if (!this.graph.containsKey(t)) {
            Set<Edge> set = new HashSet<>();
            this.graph.put(t, set);
            this.numNodes++;
        }
    }

    @Override
    public void addAllNodes(Collection<? extends T> nodes) {
        nodes.forEach(this::addNode);
    }

    @Override
    public void removeNode(T t) {
        if (this.graph.containsKey(t)) {
            Set<Edge> edges = this.graph.get(t);
            this.graph.remove(t);

            List<Edge> notRemovedEdges = this.getEdges().stream().filter(e -> e.getTargetNode().equals(t)).toList();
            this.removeAllEdges(notRemovedEdges);

            this.numNodes--;
            this.numEdges -= edges.size()+notRemovedEdges.size();
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
        return this.graph.containsKey(t);
    }

    @Override
    public List<T> getNodes() {
        return new ArrayList<>(this.graph.keySet());
    }

    @Override
    public int getNumEdges() {
        return this.numEdges;
    }

    @Override
    public void addEdge(T t1, T t2) {
        if(!this.graph.containsKey(t1) || !this.graph.containsKey(t2)) {
            String message = String.format("Nodes %s and %s are not added to the graph!", t1, t2);
            throw new IllegalArgumentException(message);
        }

        if(!this.hasEdge(t1, t2)) {
            Edge edge = Edge.of(t1, t2);
            Set<Edge> t1Edges = this.graph.get(t1);
            t1Edges.add(edge);
            this.weightMap.put(edge, 1d);
            this.numEdges++;
        }
    }

    @Override
    public void addEdge(Edge edge) {
        T source = this.getEdgeSourceNode(edge);
        T target = this.getEdgeTargetNode(edge);
        this.addEdge(source, target);
    }

    @Override
    public void addAllEdges(Collection<? extends Edge> edges) {
        edges.forEach(this::addEdge);
    }

    @Override
    public void setEdgeWeight(Edge edge, double weight) {
        if(this.isWeighted)
            this.weightMap.put(edge, weight);
    }

    @Override
    public void setEdgeWeight(T t1, T t2, double weight) {
        Edge edge = this.getEdge(t1, t2);
        this.setEdgeWeight(edge, weight);
    }

    @Override
    public double getEdgeWeight(Edge edge) {
        if(!this.getEdges().contains(edge)) {
            String message = String.format("The edge %s does not exist in this graph!", edge);
            throw new IllegalArgumentException(message);
        }
        return this.weightMap.get(edge);
    }

    @Override
    public void removeEdge(Edge edge) {
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

        this.specificRemoveEdge(t1, t2);
        this.numEdges--;
    }

    /**
     *
     * @param t1 The source node of the edge
     * @param t2 The target node of the edge
     */
    protected abstract void specificRemoveEdge(T t1, T t2);

    @Override
    public void removeAllEdges(Collection<? extends Edge> edges) {
        edges.forEach(this::removeEdge);
    }

    @Override
    public void removeEdgesIf(Predicate<Edge> predicate) {
        this.getEdges().forEach(e -> {
            if(predicate.test(e)) {
                this.removeEdge(e);
            }
        });
    }

    @Override
    public List<Edge> getEdges() {
        return this.graph.values().stream().flatMap(Set::stream).toList();
    }

    @Override
    public T getEdgeSourceNode(Edge edge) {
        return this.dataClass.cast(edge.getSourceNode());
    }

    @Override
    public T getEdgeTargetNode(Edge edge) {
        return this.dataClass.cast(edge.getTargetNode());
    }

    @Override
    public Edge getEdge(T t1, T t2) {
        Optional<Edge> edgeOptional = this.getEdges().stream()
                .filter(e -> e.getSourceNode().equals(t1) && e.getTargetNode().equals(t2))
                .findFirst();
        if(edgeOptional.isPresent()) {
            return edgeOptional.get();
        } else {
            String message = String.format("Unable to find the edge (%s, %s)!", t1, t2);
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public abstract List<Edge> getNodeEdges(T t);

    @Override
    public boolean hasEdge(T t1, T t2) {
        Set<T> keys = this.graph.keySet();

        if(!keys.contains(t1) || !keys.contains(t2))
            return false;

        return this.checkEdges(t1, t2);
    }

    @Override
    public boolean hasEdge(Edge edge) {
        T source = this.getEdgeSourceNode(edge);
        T target = this.getEdgeTargetNode(edge);
        return this.hasEdge(source, target);
    }

    protected abstract boolean checkEdges(T t1, T t2);

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\n");

        for(T t : this.graph.keySet()) {
            builder.append(String.format("\t%s => [", t));

            Set<Edge> edges = this.graph.get(t);

            for(Edge edge : edges) {
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
