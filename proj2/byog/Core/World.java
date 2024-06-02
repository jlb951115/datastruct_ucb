package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class World {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    public TETile[][] world;

    public World() {
        world = new TETile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }
    public void addSide(Side s) {
        int begin_x = s.begin.x;
        int begin_y = s.begin.y;
        int end_x = s.end.x;
        int end_y = s.end.y;
        if (s.isVertical) {
            for (int i = begin_y; i <= end_y; i++) {
                world[begin_x][i] = Tileset.WALL;
            }
        } else {
            for (int i = begin_x; i <= end_x; i++) {
                world[i][begin_y] = Tileset.WALL;
            }
        }
    }

    private void DrawInSide(Room r) {
        Position p = r.LeftSide.begin;
        Position p1 = r.TopSide.end;
        int begin_x = p.x + 1;
        int begin_y = p.y + 1;
        int end_x = p1.x - 1;
        int end_y = p1.y - 1;
        for (int i = begin_x; i <= end_x; i++) {
            for (int j = begin_y; j <= end_y; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
    }
    public void addRoom(Room r) {
        addSide(r.DownSide);
        addSide(r.LeftSide);
        addSide(r.RightSide);
        addSide(r.TopSide);
        DrawInSide(r);
    }

    public ArrayDeque<Room> GenerateRoomList(Random r, int number) {
        ArrayDeque<Room> ArrayRoom = new ArrayDeque<Room>();
        boolean regenerate = true;
        for (int i = 0; i < number; i++) {
            int width = r.nextInt(WIDTH);
            int height = r.nextInt(HEIGHT);
            int x = r.nextInt(WIDTH - width);
            int y = r.nextInt(HEIGHT - height);
            Position p = new Position(x, y);
            Room r1 = new Room(width, height, p);
            ArrayRoom.addLast(r1);
        }
        return ArrayRoom;
    }

    public TETile[][] Generate(long seed) {
        Random r = new Random(seed);
        int number = r.nextInt(40);
        ArrayDeque<Room> RoomList = GenerateRoomList(r, number);
        for (int i = 0; i < RoomList.size(); i++) {
            addRoom(RoomList.get(i));
        }
        return this.world;
    }
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        World w = new World();
        TETile[][] world = w.Generate(18);
        ter.initialize(WIDTH, HEIGHT);
        ter.renderFrame(world);
    }
}
