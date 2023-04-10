# Assignment A4: Urbanism
  - Abdallah Alqashqish [alqashqa@mcmaster.ca]

## How to run the product

_This section needs to be edited to reflect how the user can interact with thefeature released in your project_

### Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A2`

To install the different tooling on your computer, simply run:

```
mosser@azrael A2 % mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

### Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one single argument (so far), the name of the file where the generated mesh will be stored as binary.

Generator options include:
1. -c,--color <vertex coloring> <segment coloring> <polygon coloring>   Sets the color generation for all the elements of the mesh.
2. -d,--dimension <widthxheight>                                        Sets the dimensions of the mesh.
3. -h,--help                                                            Displays program usage.
4. -m,--mesh <mesh type>                                                The type of mesh to generate. Either `grid` or `irregular`.
5. -np,--numPolygons <number of polygons>                               Sets the number of polygons to generate in the irregular mesh. Will be ignored if the mesh is a grid.
6. -out,--output <output file>                                          The file to output to.
7. -rl,--relaxationLevel <relaxation level>                             Sets the relaxation level of an irregular mesh. Will be ignored by grid mesh
8. -ss,--squareSize <square size>                                       Sets the size of the squares in the grid mesh. Ignored by irregular mesh.
9. -t,--thickness <vertex thickness> <segment thickness>                Sets the thickness for the vertices and segments.

```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar -out sample.mesh -m grid (Generates a grid mesh)
mosser@azrael generator % java -jar generator.jar -out sample.mesh -m irregular (Generates an irregular mesh)
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

### Island Generator

To run the island generator, go to the `island` directory, and use `java -jar` to run the product.

Island Generator options include:
1. -i,--input <input file>                            Sets the file to read the mesh from.
2. -o,--output <output file>                          Sets the file to output the new island mesh to.
3. -h,--help                                          Displays program usage.
4. -m,--mode <mode>                                   The island generation mode. At the moment only `lagoon` and `random`.
5. -a,--altitude <altimetric profile>                 The island altimetric profile generation mode. At the moment only `lagoon`, `volcano`, or `hills`.
6. -l,--lakes <# of lakes>                            The number of lakes to place on the island. Note that lakes can merge.
7. -s,--shape <shape>                                 The shape to set the island to. Available shapes are `circle`, `oval`, and `star`.
8. -aq,--aquifers <# of aquifers>                     The number of random aquifers to add to the island.
9. -r, --rivers <# of rivers>                         The number of rivers to add to the island.
10. -s, --soil <absorption>                           The soil absorption profile to set for this island. Only `wet` and `dry`. Wet is the default.
11. -sed, --seed <seed>                               The seed of the island to generate. Generator will generate a random one if none are given.
12. -b, --biomes <biomes>                             The biomes of the island to generate. Options are `tropical` and `temperate`. Generator will generate a `tropical` island if none is given.
13. -H, --hook  <hook>                                Sets the hook to run after the generation of the island. Current options are `moisture` and `elevation`.
14. -c, --cities <# of cities>                        Sets the number of cities to generate into the island.
```
mosser@azrael A2 % cd island 
mosser@azrael island % java -jar island.jar -i ../generator/sample.mesh -o island.mesh -m lagoon
mosser@azrael island % java -jar island.jar -i ../generator/sample.mesh -o island.mesh -m random --shape circle --altitude volcano --biomes tropical # Generates a volcanic tropical island in the shape of a circle
mosser@azrael island % java -jar island.jar -i ../generator/sample.mesh -o island.mesh -m random --hook moisture # Moisture heatmap
mosser@azrael island % java -jar island.jar -i ../generator/sample.mesh -o island.mesh -m random --hook elevation # Elevation heatmap
mosser@azrael island % ls -lh island.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael island % 
```

Cities generated are connected through a star network. The black lines represents the roads between the cities. The dark grey
dots represent the cities. Roads over bodies of water are ignored unless it is absolutely necessary. The elevation profile
of the island is also taken into account to find the cheapest path between the cities. The red city represents the
capital city (ie the center of the star network). The capital city is set as the largest generated city. The number of 
cities to generate is set through the `--cities` option.

### Visualizer

To visualize an existing mesh, go to the `visualizer` directory, and use `java -jar` to run the product. The product take two arguments (so far): the file containing the mesh, and the name of the file to store the visualization (as an SVG image).

Visualizer options include:
1. -h,--help                      Displays program usage.
2. -in,--input <input file>       Takes in the mesh file to read from.
3. -out,--output <output file>    The file to output to.
4. -X,--debug                     Enable debug mode when rendering the mesh.

```
mosser@azrael A2 % cd visualizer 
mosser@azrael visualizer % java -jar visualizer.jar -in ../generator/sample.mesh -out sample.svg
mosser@azrael visualizer % java -jar visualizer.jar -in ../generator/sample.mesh -out sample.svg -X (For debugging mode)

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```
To viualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tool slike `rsvg-convert`

## Pathfinder
This module contains libraries for representing graphs as well as finding the shortest path. Furthermore, to implement a 
new graph, extend the Graph interface and implement all the methods. Also, to implement a new path finding algorithm, just
extend the abstract path algorithm class or the path algorithm interface and implement the methods.

```java
import java.util.List;

public class NewGraph<T> implements Graph<T> {
    /*
     * Implement all the methods of the graph to match your representation of the graph.
     */
}

public class ShortestPath<T> extends AbstractPathAlgorithm<T> {
    public ShortestPath(Graph<T> graph) {
        super(graph);
    }

    @Override
    protected Map<T, T> findPaths(Graph<T> graph, T start) {
        /*
         * Implement algorithm to find the paths that start at `start`.
         * The returned map has the key as the node and the value as the
         * that comes before the key node in the path.
         */
    }
}

public class ShortestPath<T> implements PathAlgorithm<T> {
    @Override
    protected List<T> findPath(T end) {
        /*
         * Implement algorithm to find the path that ends with end. 
         * The returned list has a start node as the first element and end as
         * the last element. The elements in between represent the path to
         * take to get to end from start.
         */
    }
}
```

## How to contribute to the project

When you develop features and enrich the product, remember that you have first to `package` (as in `mvn package`) it so that the `jar` file is re-generated by maven.

## Backlog

### Definition of Done

1. Works as expected per feature outline and requirements
2. No bugs and unexpected behaviour
3. Smooth program execution