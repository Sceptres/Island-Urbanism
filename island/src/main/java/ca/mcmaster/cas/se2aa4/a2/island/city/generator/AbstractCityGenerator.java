package ca.mcmaster.cas.se2aa4.a2.island.city.generator;

import ca.mcmaster.cas.se2aa4.a2.island.geography.Land;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;

import java.util.List;
import java.util.Random;

public abstract class AbstractCityGenerator implements CityGenerator {
    private final Land land;

    protected AbstractCityGenerator(Land land) {
        this.land = land;
    }

    @Override
    public final List<Point> generate(Random random, int numCities) {
        return this.generateCities(random, this.land, numCities);
    }

    protected abstract List<Point> generateCities(Random random, Land land, int numCities);
}
