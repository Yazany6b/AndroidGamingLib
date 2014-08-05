package y6b.Gaming.Games2DLib.Graphics;

/**
 * Created by Yazan on 8/4/14.
 */
public final class Circle {

    public Vector2D center;
    public float radius;

    public Circle(){
        center = new Vector2D();
        radius = 1;
    }
    public Circle(Vector2D center, float radius) {
        this.center = center;
        this.radius = radius;
    }



}
