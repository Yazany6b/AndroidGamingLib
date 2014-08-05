package y6b.Gaming.Games2DLib.Graphics;

/**
 * Created by Yazan on 8/4/14.
 */
public class Vector3D {
    public float x;
    public float y;
    public float z;

    public Vector3D(){
        x = y = z = 0;
    }

    public Vector3D(float value){
        x = y = z = value;
    }

    public Vector3D(float x, float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public static double distance(Vector3D v1,Vector3D v2){
        float x = .2f;

        return java.lang.Math.sqrt(Math.pow(v2.x - v1.x,2) + Math.pow(v2.y - v1.y,2) + Math.pow(v2.z - v1.z,2));
    }

    public static double slope(Vector3D v1,Vector3D v2){
        return (v2.y - v1.y) / Math.sqrt(Math.pow(v2.z - v1.z, 2) + Math.pow(v2.x - v1.x, 2));
    }

    public static double yIntersept(Vector3D v,double slope){
        double x = v.x * -1;
        double y = v.y;

        return (x * slope) + y;
    }

    public static final Vector3D Empty = new Vector3D(-1);
}
