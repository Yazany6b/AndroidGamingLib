package y6b.Gaming.Games2DLib.Graphics;

import java.lang.*;
import java.lang.Math;

/**
 * Created by Yazan on 8/4/14.
 */
public class Vector2D {
    public float x;
    public float y;

    public Vector2D(){
        x = y = 0;
    }

    public Vector2D(float value){
        x = y = value;
    }

    public Vector2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEmpty(){
        return this.equals(Empty);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Vector2D) || o == null){
            return false;
        }

        return this.x == ((Vector2D)o).x && this.y == ((Vector2D)o).y;
    }

    @Override
    public String toString() {
        return String.format("{%s,%s}",x,y);
    }

    public static double distance(Vector2D v1,Vector2D v2){
        return java.lang.Math.sqrt(Math.pow(v2.x - v1.x,2) + Math.pow(v2.y - v1.y,2));
    }

    public static double slope(Vector2D v1,Vector2D v2){
        return (v2.y - v1.y) / (v2.x - v1.x);
    }

    public static double yIntersept(Vector2D v,double slope){
        double x = v.x * -1;
        double y = v.y;

        return (x * slope) + y;
    }


    public static final Vector2D Empty = new Vector2D(-1);
}
