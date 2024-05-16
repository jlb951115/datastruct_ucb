package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldGenerator {
    public static TETile[][] Generate(long seed, int width, int height) {
        TETile[][] world = new TETile[width][height];
        InitEmpty(world);
        InitBroad(world);
        Random r = new Random(seed);
        double complexity = r.nextDouble();
        double density = r.nextDouble();
        int c = (int) (complexity * (5 * (width + height)));
        int d = (int) (density * (width / 2) * (height / 2));
        for (int i = 0; i < d; i++) {
            int x = r.nextInt(width / 2) * 2;
            int y = r.nextInt(height / 2) * 2;
            world[x][y] = Tileset.WALL;
            for (int j = 0; j < c; j++) {
                Postion[] neighbors = new Postion[4];
                int number = 0;
                if (x > 1) {
                    neighbors[number] = new Postion(x - 2, y);
                    number += 1;
                }
                if (x < width - 2) {
                    neighbors[number] = new Postion(x + 2, y);
                    number += 1;
                }
                if (y > 1) {
                    neighbors[number] = new Postion(x, y - 2);
                    number += 1;
                }
                if (y < height - 2) {
                    neighbors[number] = new Postion(x, y + 2);
                }
                if (number > 0) {
                    Postion newPosition = neighbors[r.nextInt(number)];
                    int x_position = newPosition.getX();
                    int y_position = newPosition.getY();
                    if (world[x_position][y_position].equals(Tileset.NOTHING)) {
                        world[x_position][y_position] = Tileset.WALL;
                        world[x_position + (int) ((x - x_position) / 2)] [y_position + ((int) (y - y_position) / 2)] = Tileset.WALL;
                        x = x_position;
                        y = y_position;
                    }
                }
            }
        }
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[0].length; j++) {
                if (world[i][j].equals(Tileset.NOTHING)) {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
        return world;
    }

    private static void InitBroad(TETile[][] world) {
        int width = world.length;
        int height = world[0].length;
        for (int i = 0; i < height; i++) {
            world[0][i] = Tileset.WALL;
            world[width - 1][i] = Tileset.WALL;
        }
        for (int j = 0; j < width; j++) {
            world[j][0] = Tileset.WALL;
            world[j][height - 1] = Tileset.WALL;
        }
    }

    private static void InitEmpty(TETile[][] world) {
        int width = world.length;
        int height = world[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }
}
