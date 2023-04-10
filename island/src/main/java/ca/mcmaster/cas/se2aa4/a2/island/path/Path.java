package ca.mcmaster.cas.se2aa4.a2.island.path;

import ca.mcmaster.cas.se2aa4.a2.island.elevation.IElevation;
import ca.mcmaster.cas.se2aa4.a2.island.elevation.handler.ElevationHandler;
import ca.mcmaster.cas.se2aa4.a2.island.elevation.handler.handlers.LowerElevationHandler;
import ca.mcmaster.cas.se2aa4.a2.island.elevation.profiles.ElevationProfile;
import ca.mcmaster.cas.se2aa4.a2.island.path.type.PathType;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.segment.Segment;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.vertex.Vertex;

import java.util.Objects;

public final class Path implements IElevation {
    private PathType type;
    private final Point p1;
    private final Point p2;
    private final Segment segment;
    private final ElevationProfile elevationProfile;
    private final ElevationHandler elevationHandler;

    public Path(Segment segment, Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.segment = segment;
        this.setType(PathType.NONE);
        this.setWidth(1f);
        this.elevationProfile = new ElevationProfile();
        this.elevationHandler = new LowerElevationHandler();

        this.elevationProfile.setElevation(1);
    }

    /**
     *
     * @return The first {@link Point} of the path
     */
    public Point getP1() {
        return this.p1;
    }

    /**
     *
     * @return The second {@link Point} of the path
     */
    public Point getP2() {
        return this.p2;
    }

    /**
     *
     * @return The {@link Segment} of this path
     */
    public Segment getSegment() {
        return this.segment;
    }

    /**
     *
     * @return The width of this path
     */
    public float getWidth() {
        return this.segment.getThickness();
    }

    /**
     *
     * @param width The new width of this path
     */
    public void setWidth(float width) {
        if(width > 3f)
            this.segment.setThickness(3f);
        else
            this.segment.setThickness(Math.max(width, 1f));
    }

    /**
     *
     * @param increment The amount to increase the width of this path by
     */
    public void addWidth(float increment) {
        float oldWidth = this.getWidth();
        float newWidth = oldWidth + increment;
        this.setWidth(newWidth);
    }

    /**
     *
     * @return The {@link PathType} of this path
     */
    public PathType getType() {
        return this.type;
    }

    /**
     *
     * @param type The {@link PathType} to set this path to
     */
    public void setType(PathType type) {
        this.type = type;
        this.segment.setColor(this.type.getColorGenerator().generateColor());
    }

    @Override
    public double getElevation() {
        return this.elevationProfile.getElevation();
    }

    @Override
    public void setElevation(double elevation) {
        this.elevationHandler.takeElevation(this.elevationProfile, elevation);
    }

    /**
     *
     * @param path The {@link Path} to check connection with
     * @return True if the 2 paths are connected. False otherwise.
     */
    public boolean isConnected(Path path) {
        return this.segment.shareVertex(path.segment);
    }

    /**
     *
     * @param path The {@link Path} to check connection with
     * @param point An end of the path to check connection to
     * @return True if both paths are connected through the given vertex. False otherwise.
     */
    public boolean isConnected(Path path, Point point) {
        Vertex vertex = point.getVertex();
        return this.segment.shareVertex(path.segment) && this.segment.getSharedVertex(path.segment).equals(vertex);
    }

    /**
     *
     * @param point The {@link Point} to check if this path has
     * @return True if this path contains the vertex. False otherwise
     */
    public boolean hasPoint(Point point) {
        return this.getP1().equals(point) || this.getP2().equals(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path path)) return false;
        return type == path.type && Objects.equals(segment, path.segment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, segment);
    }
}
