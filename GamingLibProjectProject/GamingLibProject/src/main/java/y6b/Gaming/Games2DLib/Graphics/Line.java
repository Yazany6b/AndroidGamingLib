package y6b.Gaming.Games2DLib.Graphics;

/**
 * Created by Yazan on 8/4/14.
 */
public final class Line {

    public Vector2D start;
    public Vector2D end;

    public Line(){
        start = new Vector2D();
        end = new Vector2D();
    }

    public Line(Vector2D start, Vector2D end) {
        this.start = start;
        this.end = end;
    }

    public double calcultePerpendicularLineSlope(){
        return -1 / slope();
    }


    public float run(){
        return end.x - start.x;
    }

    public float raise(){
        return end.y - start.y;
    }

    public double slope(){
        return Vector2D.slope(start,end);
    }

    public double length(){
        return Vector2D.distance(start,end);
    }

    public double yIntersept(){
        return Vector2D.yIntersept(start,slope());
    }

    public static boolean IsParallel(Line l1,Line l2){
        double slope1 = l1.slope();
        double slope2 = l2.slope();

        double yinter1 = l1.yIntersept();
        double yinter2 = l2.yIntersept();

        return slope1 == slope2 && yinter1 != yinter2;
    }

    static boolean IsPerpendicular(Line l1,Line l2){
        double slope1 = l1.slope();
        double slope2 = l2.slope();

        return slope1 * slope2 == -1;
    }
}
