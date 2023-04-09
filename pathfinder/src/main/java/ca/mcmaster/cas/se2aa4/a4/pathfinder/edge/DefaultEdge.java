package ca.mcmaster.cas.se2aa4.a4.pathfinder.edge;

import java.util.Objects;

class DefaultEdge<T> implements Edge<T> {
    private final T source;
    private final T target;

    DefaultEdge(T source, T target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public T getSourceNode() {
        return this.source;
    }

    @Override
    public T getTargetNode() {
        return this.target;
    }

    @Override
    public String toString() {
        return this.source + " -> " + this.target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultEdge<?> that)) return false;
        return Objects.equals(source, that.source) && Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }
}
