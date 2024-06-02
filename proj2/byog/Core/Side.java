package byog.Core;

public class Side {
    public int length;
    public Position begin;
    public boolean isVertical;
    public Position end;

    public Side(int length, Position begin, boolean isVertical) {
        this.length = length;
        this.begin = begin;
        this.isVertical = isVertical;
        if (this.isVertical) {
            this.end = new Position(this.begin.x, this.begin.y + length - 1);
        } else {
            this.end = new Position(this.begin.x + length - 1, this.begin.y);
        }
    }
}