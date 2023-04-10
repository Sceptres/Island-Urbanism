package ca.mcmaster.cas.se2aa4.a2.island.color.colors.paths;

import ca.mcmaster.cas.se2aa4.a2.island.color.ColorGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.color.Colors;

import java.awt.*;

public class RoadColorGenerator implements ColorGenerator {
    @Override
    public Color generateColor() {
        return Colors.ROAD_COLOR;
    }
}
