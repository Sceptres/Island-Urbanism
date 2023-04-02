package ca.mcmaster.cas.se2aa4.a4.pathfinder.data;

public record Tuple<D, E>(D e1, E e2) {
    public static <F, G> Tuple<F, G> of(F e1, G e2) {
        return new Tuple<>(e1, e2);
    }
}
