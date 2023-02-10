package ca.mcmaster.cas.se2aa4.a2.mesh.adt.segment;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.services.Indexable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Segment implements Indexable {

    private Structs.Segment segment;
    private int index;

    public Segment() {
        this.segment = Structs.Segment.newBuilder().build();
    }

    /**
     *
     * @param v1Idx Index of the first {@link Structs.Vertex} of the {@link Segment}
     * @param v2Idx Index of the second {@link Structs.Vertex} of the {@link Segment}
     */
    public Segment(int v1Idx, int v2Idx) {
        this.segment = Structs.Segment.newBuilder().setV1Idx(v1Idx).setV2Idx(v2Idx).build();
    }

    /**
     *
     * @param segment Create a {@link Segment} from a {@link Structs.Segment}
     */
    public Segment(Structs.Segment segment) {
        this.segment = segment;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    /**
     *
     * @return The index of the first {@link Structs.Vertex} of the segment
     */
    public int getV1Idx() {
        return this.segment.getV1Idx();
    }

    /**
     *
     * @return The index of the second {@link Structs.Vertex} of the segment
     */
    public int getV2Idx() {
        return this.segment.getV2Idx();
    }

    /**
     *
     * @return The {@link List} of properties associated with this segment
     */
    public List<Structs.Property> getProperties() {
        return this.segment.getPropertiesList();
    }

    /**
     *
     * @param key The key of the {@link Structs.Property} to get
     * @return The {@link Structs.Property} with the given key
     */
    public Structs.Property getProperty(String key) {
        Optional<Structs.Property> property = this.getProperties().stream().filter(p -> p.getKey().equals(key)).findFirst();
        return property.orElse(null);
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *
     * @param v1Idx The new index of the first {@link Structs.Vertex} for the segment
     */
    public void setV1Idx(int v1Idx) {
        this.segment = Structs.Segment.newBuilder(this.segment).setV1Idx(v1Idx).build();
    }

    /**
     *
     * @param v2Idx The new index of the second {@link Structs.Vertex} for the segment
     */
    public void setV2Idx(int v2Idx) {
        this.segment = Structs.Segment.newBuilder(this.segment).setV2Idx(v2Idx).build();
    }

    /**
     *
     * @param p The {@link Structs.Property} to add to this segment
     */
    public void addProperty(Structs.Property p) {
        this.segment = Structs.Segment.newBuilder(this.segment).addProperties(p).build();
    }

    /**
     *
     * @param properties All the {@link Structs.Property} to add
     */
    public void addAllProperties(Iterable<? extends Structs.Property> properties) {
        this.segment = Structs.Segment.newBuilder(this.segment).addAllProperties(properties).build();
    }

    /**
     *
     * @return Get the {@link Structs.Segment} instance that stores all segment data
     */
    public Structs.Segment getSegment() {
        return this.segment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment1 = (Segment) o;
        return Objects.equals(segment, segment1.segment);
    }
}
