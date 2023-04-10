package ca.mcmaster.cas.se2aa4.a2.island.color.colors;

import ca.mcmaster.cas.se2aa4.a2.island.color.ColorGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.color.Colors;

import java.awt.*;

public class ClearColorGenerator implements ColorGenerator {
    @Override
    public Color generateColor() {
        return Colors.TRANSPARENT;
    }
}
