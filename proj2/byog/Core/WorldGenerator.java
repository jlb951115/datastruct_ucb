package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Random;

public class WorldGenerator {
    public TETile[][] Generate(long seed, int width, int height) {
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

    private void InitBroad(TETile[][] world) {
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

    private  void InitEmpty(TETile[][] world) {
        int width = world.length;
        int height = world[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    private  boolean iswall(int x, int y, TETile[][] T) {
        Postion[] p = new Postion[4];
        int size = 0;
        if (x > 0) {
            p[size] = new Postion(x - 1, y);
            size++;
        }
        if (x < T.length - 1) {
            p[size] = new Postion(x + 1, y);
            size++;
        }
        if (y < T[0].length - 1) {
            p[size] = new Postion(x, y + 1);
            size++;
        }
        if (y > 0) {
            p[size] = new Postion(x, y - 1);
            size++;
        }
        for (int i = 0; i < size; i++) {
            if (!T[p[i].getX()][p[i].getY()].equals(Tileset.WALL)) {
                return false;
            }
        }
        return true;
    }

    private void lockdoor(int width, int height, TETile[][] T, long seed) {
        Random r = new Random(seed);
        while (true) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            if (!iswall(x, y, T)) {
                T[x][y] = Tileset.LOCKED_DOOR;
                break;
            }
        }
        return;
    }
}
