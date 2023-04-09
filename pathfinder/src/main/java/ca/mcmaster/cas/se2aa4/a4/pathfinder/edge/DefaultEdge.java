package ca.mcmaster.cas.se2aa4.a4.pathfinder.edge;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.node.Node;

import java.util.Objects;

class DefaultEdge<T> implements Edge<T> {
    private double weight;
    private final boolean isWeighted;
    private final Node<T> source;
    private final Node<T> target;

    DefaultEdge(T source, T target, boolean isWeighted) {
        this(Node.of(source), Node.of(target), isWeighted);
    }

    DefaultEdge(Node<T> source, Node<T> target, boolean isWeighted) {
        this.source = source;
        this.target = target;
        this.isWeighted = isWeighted;
        this.weight = 1;
    }

    @Override
    public Node<T> getSourceNode() {
        return this.source;
    }

    @Override
    public Node<T> getTargetNode() {
        return this.target;
    }

    @Override
    public T getSourceNodeData() {
        return this.source.getData();
    }

    @Override
    public T getTargetNodeData() {
        return this.target.getData();
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double weight) {
        if(this.isWeighted)
            this.weight = weight;
    }

    @Override
    public String toString() {
        return this.source + " -> " + this.target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultEdge<?> that)) return false;
        return Objects.equals(source, that.source) && Objects.equals(target, that.target) && isWeighted == that.isWeighted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, isWeighted);
    }
}
