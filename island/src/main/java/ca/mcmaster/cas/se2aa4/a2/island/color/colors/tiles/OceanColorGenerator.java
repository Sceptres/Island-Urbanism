package ca.mcmaster.cas.se2aa4.a2.island.color.colors.tiles;

import ca.mcmaster.cas.se2aa4.a2.island.color.Colors;
import ca.mcmaster.cas.se2aa4.a2.island.color.ColorGenerator;

import java.awt.*;

public class OceanColorGenerator implements ColorGenerator {
    @Override
    public Color generateColor() {
        return Colors.OCEAN;
    }
}
