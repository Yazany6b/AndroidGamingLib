package y6b.Gaming.Games2DLib.Graphics;

/**
 * Created by Yazan on 8/4/14.
 */
public class Collision {

    public static boolean hasCollision(Line l1, Line l2){
        double slope1 = l1.slope();
        double slope2 = l2.slope();

        double yinter1 = l1.yIntersept();
        double yinter2 = l2.yIntersept();

        return !(slope1 == slope2 && yinter1 != yinter2);
    }

    public static Vector2D getCollisionPoint(Line l1,Line l2){
        if(Line.IsParallel(l1,l2))
            return Vector2D.Empty;

        double slope1 = l1.slope();

        double slope2 = l2.slope();

        double x = (l2.start.y - l1.start.y - slope2 * l2.start.y + slope1 * l1.start.y) / (slope1 - slope2);

        double y = slope1 * x - slope1 * l1.start.y + l1.start.y;

        return new Vector2D((float)x,(float)y);
    }

    public static boolean hasCollision(Circle c1,Circle c2){
        double dist = Vector2D.distance(c1.center,c2.center);

        return dist <= c1.radius+c2.radius;
    }

    public static boolean hasCollision(Circle c,Line l){
        return false;
    }
}
