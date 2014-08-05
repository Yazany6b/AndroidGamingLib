package y6b.Gaming.Games2DLib.Graphics;

/**
 * Created by Yazan on 8/4/14.
 */
public final class Angles {
    public static final double PI = 3.141592654;
    public static final double RAD_TO_DEG = 57.29577951;
    public static final double DEG_TO_RAD = 0.017453293;

    public static double calculateAnglw(Vector2D v1,Vector2D v2){

        float diffy = v2.y - v1.y;
        float diffx = v2.x - v1.x;

        float tanAlpha = diffy / diffx;

        double alpha = Math.atan(tanAlpha) * RAD_TO_DEG; // calculat the angle

        //if the angle in the first quadrant
        if(v2.y < v1.y && v2.x > v1.x)
            return alpha;
            //if the angle in the second of third quadrat
        else if((v2.y < v1.y && v2.x < v1.x) ||
                (v2.y > v1.y && v2.x < v1.x))
            return alpha + 180;
        else
            return alpha + 360;
    }

    public static double toDegree(double radianAngle){
        return PI / 180 * radianAngle;
    }

    public static double toRadian(double degreeAngle){
        return 180 / PI * degreeAngle;
    }

    public static double alphaSin(double oppsite,double hypotenuse){
        return oppsite / hypotenuse;
    }

    public static double alphaCos(double adjacent,double hypotenuse){
        return adjacent / hypotenuse;
    }

    public static double alphaTan(double oppsite,double adjacent){
        return oppsite / adjacent;
    }
}
