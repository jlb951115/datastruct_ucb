package byog.Core;

import javax.lang.model.util.Elements;

public class Room {

    public Side LeftSide;
    public Side RightSide;
    public Side DownSide;
    public Side TopSide;

    public Room(int width, int height, Position DownLeft) {
        LeftSide = new Side(height, DownLeft, true);
        TopSide = new Side(width, LeftSide.end, false);
        DownSide = new Side(width, DownLeft, false);
        RightSide = new Side(height, DownSide.end, true);
    }

    public boolean isOverLap(Room r) {
        Position p = r.LeftSide.begin;
        Position p1 = r.RightSide.end;
        boolean x = this.LeftSide.begin.isRightTop(p) && this.RightSide.end.isLeftDown(p);
        boolean y = p.isRightTop(this.LeftSide.begin) && p1.isLeftDown(this.LeftSide.begin);
        return x || y;
    }
}
