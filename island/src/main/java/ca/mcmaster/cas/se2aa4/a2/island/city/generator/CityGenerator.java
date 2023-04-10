package ca.mcmaster.cas.se2aa4.a2.island.city.generator;

import ca.mcmaster.cas.se2aa4.a2.island.point.Point;

import java.util.List;
import java.util.Random;

public interface CityGenerator {
    List<Point> generate(Random random, int numCities);
}
