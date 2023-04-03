package ca.mcmaster.cas.se2aa4.a4.pathfinder.edge;

import java.util.Objects;

class DefaultEdge implements Edge {
    private final Object source;
    private final Object target;

    DefaultEdge(Object source, Object target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public Object getSourceNode() {
        return this.source;
    }

    @Override
    public Object getTargetNode() {
        return this.target;
    }

    @Override
    public String toString() {
        return this.source + " -> " + this.target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultEdge that)) return false;
        return Objects.equals(source, that.source) && Objects.equals(target, that.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }
}
