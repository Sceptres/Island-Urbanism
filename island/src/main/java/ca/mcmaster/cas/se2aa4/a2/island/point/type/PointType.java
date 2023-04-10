package ca.mcmaster.cas.se2aa4.a2.island.point.type;

import ca.mcmaster.cas.se2aa4.a2.island.color.ColorGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.color.colors.ClearColorGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.color.colors.points.CapitalColorGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.color.colors.points.CityColorGenerator;

public enum PointType {
    NONE(new ClearColorGenerator()),
    CITY(new CityColorGenerator()),
    CAPITAL(new CapitalColorGenerator());

    private final ColorGenerator colorGenerator;

    PointType(ColorGenerator generator) {
        this.colorGenerator = generator;
    }

    /**
     *
     * @return The {@link ColorGenerator} of this point type
     */
    public ColorGenerator getColorGenerator() {
        return this.colorGenerator;
    }
}
