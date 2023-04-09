package ca.mcmaster.cas.se2aa4.a2.island.city.generator.generators;

import ca.mcmaster.cas.se2aa4.a2.island.Util;
import ca.mcmaster.cas.se2aa4.a2.island.city.generator.AbstractCityGenerator;
import ca.mcmaster.cas.se2aa4.a2.island.geography.Land;
import ca.mcmaster.cas.se2aa4.a2.island.path.Path;
import ca.mcmaster.cas.se2aa4.a2.island.point.Point;
import ca.mcmaster.cas.se2aa4.a2.island.point.type.PointType;
import ca.mcmaster.cas.se2aa4.a2.island.tile.Tile;
import ca.mcmaster.cas.se2aa4.a2.island.tile.type.TileGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomCityGenerator extends AbstractCityGenerator {
    public RandomCityGenerator(Land land) {
        super(land);
    }

    @Override
    protected List<Point> generateCities(Random random, Land land, int numCities) {
        List<Point> points = new ArrayList<>();

        List<Tile> tiles = land.getTiles().stream().filter(t -> t.getType().getGroup() != TileGroup.WATER).toList();
        List<Point> cityPoints =tiles.stream().map(Tile::getCentroid).filter(Point::canCity).collect(Collectors.toList());

        for(int i=0; i < numCities; i++) {
            int randomIdx = random.nextInt(cityPoints.size());
            Point point = cityPoints.get(randomIdx);
            point.setType(PointType.CITY);

            float citySize = random.nextFloat(5, 10);
            point.setThickness(citySize);

            List<Path> paths = land.getPaths().stream().filter(p -> p.hasPoint(point)).toList();
            List<Point> ineligiblePoints = Util.getPathPoints(paths);
            cityPoints.removeAll(ineligiblePoints);

            points.add(point);
        }

        return points;
    }
}
