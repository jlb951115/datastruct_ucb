package byog.Core;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position p) {
        this.x = p.x;
        this.y = p.y;
    }


    public boolean isLeftDown(Position p) {
        return p.x <= this.x && p.y <= this.y;
    }

    public boolean isLeftTop(Position p) {
        return p.x >= this.x && p.y >= this.y;
    }

    public boolean isRightTop(Position p) {
        return p.x >= this.x && p.y >= this.y;
    }

    public boolean isRightDown(Position p) {
        return p.x >= this.x && p.y <= this.y;
    }

    public boolean isRight(Position p) {
        return this.y == p.y && p.x >= this.x;
    }

    public boolean isLeft(Position p) {
        return this.y == p.y && p.x <= this.x;
    }

    public boolean isTop(Position p) {
        return this.x == p.x && p.y >= this.y;
    }

    public boolean isDown(Position p) {
        return p.x == this.x && p.y <= this.y;
    }
}
