package ca.mcmaster.cas.se2aa4.a2.island.tile.configuration;

import ca.mcmaster.cas.se2aa4.a2.island.elevation.handler.ElevationHandler;
import ca.mcmaster.cas.se2aa4.a2.island.color.ColorGenerator;

import java.awt.*;

public interface Configurator {
    /**
     *
     * @return The {@link ColorGenerator}
     */
    ColorGenerator getColorGenerator();

    /**
     *
     * @return The {@link ElevationHandler}
     */
    ElevationHandler getElevationHandler();

    /**
     *
     * @return The {@link Color} after applying configurations
     */
    Color apply();
}