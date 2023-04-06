package ca.mcmaster.cas.se2aa4.a2.island.cli.options;

import org.apache.commons.cli.Option;

public class CitiesOption extends Option {
    public static final String OPTION_STR = "cities";
    private static final String DESCRIPTION = "Sets the number of cities to be generated on the island. Cities are set to be on the hull of the separate tiles.";
    public static final String DEFAULT_VALUE = "0";

    public CitiesOption() {
        super(OPTION_STR.substring(0, 1), OPTION_STR, true, DESCRIPTION);
    }
}
