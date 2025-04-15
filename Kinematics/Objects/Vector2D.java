package Objects;
public class Vector2D extends Vector {

    public Vector2D(double x, double y) { super(new double[]{x,y}); }

    public double x() { return c(0); }
    public double y() { return c(1); }

    public static double vAngle(Vector2D v1, Vector2D v2) {
        return Math.atan2(v1.y()-v2.y(), v1.x()-v2.x());
    }

    @Override
    protected void assignV(Vector v) {
        if (!(v.getD() == 2)) {
            throw new IllegalArgumentException("Only Vector2D objects are allowed.");
        }
        super.assignV(v);
    }
}
