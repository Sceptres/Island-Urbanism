package ca.mcmaster.cas.se2aa4.a2.island.point;

import ca.mcmaster.cas.se2aa4.a2.island.elevation.IElevation;
import ca.mcmaster.cas.se2aa4.a2.island.elevation.handler.ElevationHandler;
import ca.mcmaster.cas.se2aa4.a2.island.elevation.handler.handlers.LowerElevationHandler;
import ca.mcmaster.cas.se2aa4.a2.island.elevation.profiles.ElevationProfile;
import ca.mcmaster.cas.se2aa4.a2.island.point.type.PointType;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.services.Positionable;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.services.Thickenable;
import ca.mcmaster.cas.se2aa4.a2.mesh.adt.vertex.Vertex;

import java.awt.*;
import java.util.Objects;

public final class Point implements Positionable<Double>, IElevation, Thickenable {
    private PointType type;
    private final Vertex vertex;
    private final ElevationProfile elevationProfile;
    private final ElevationHandler elevationHandler;

    public Point(Vertex vertex) {
        this.vertex = vertex;
        this.elevationProfile = new ElevationProfile();
        this.elevationHandler = new LowerElevationHandler();

        this.setType(PointType.NONE);
        this.elevationProfile.setElevation(1);
    }

    /**
     *
     * @return The {@link Vertex} of this point
     */
    public Vertex getVertex() {
        return this.vertex;
    }

    /**
     *
     * @return The {@link PointType} of this point
     */
    public PointType getType() {
        return this.type;
    }

    /**
     *
     * @param type The {@link PointType} of this point
     */
    public void setType(PointType type) {
        this.type = type;
        Color color = this.type.getColorGenerator().generateColor();
        this.vertex.setColor(color);
    }

    /**
     * Only centroid vertices can be a city.
     * @return True if this point can be a city. False otherwise.
     */
    public boolean canCity() {
        return this.type != PointType.CITY && this.vertex.isCentroid();
    }

    @Override
    public Double getX() {
        return this.vertex.getX();
    }

    @Override
    public Double getY() {
        return this.vertex.getY();
    }

    @Override
    public Double[] getPosition() {
        return this.vertex.getPosition();
    }

    @Override
    public double getElevation() {
        return this.elevationProfile.getElevation();
    }

    @Override
    public void setElevation(double elevation) {
        this.elevationHandler.takeElevation(this.elevationProfile, elevation);
    }

    @Override
    public void setThickness(float x) {
        this.vertex.setThickness(x);
    }

    @Override
    public float getThickness() {
        return this.vertex.getThickness();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return type == point.type && Objects.equals(vertex, point.vertex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex);
    }
}
