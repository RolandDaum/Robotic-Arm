package Objects;
public class Vector3D extends Vector {

    public Vector3D(double x, double y, double z) {
        super(new double[]{x,y,z});
    }
    public double x() { return getC(0); }
    public double y() { return getC(1); }
    public double z() { return getC(2); }
   
    public double xAngle() { return Vector3D.xAngle(this); }
    public double yAngle() { return Vector3D.yAngle(this); }
    public double zAngle() { return Vector3D.zAngle(this); }

    // STATIC METHODS
    public static double xAngle(Vector3D v) { return Vector3D.vAngle(v, new Vector3D(1,0,0)); }
    public static double yAngle(Vector3D v) { return Vector3D.vAngle(v, new Vector3D(0,1,0)); }
    public static double zAngle(Vector3D v) { return Vector3D.vAngle(v, new Vector3D(0,0,1)); }

    public static double vAngle(Vector3D v1, Vector3D v2) {
        if (v1.norm() == 0 || v2.norm() == 0) {
            throw new IllegalArgumentException("No angle between zero vectors");
        }
        return Math.acos(Vector3D.dotP(v1, v2)/(v1.norm()*v2.norm()));
        // return Math.atan2(Vector3D.norm(Vector3D.crossP(v1, v2)), Vector3D.dotP(v1, v2));
    }
    public static Vector3D crossP(Vector3D v1, Vector3D v2) {
        return new Vector3D(
            (v1.y() * v2.z()) - (v1.z() * v2.y()),
            (v1.z() * v2.x()) - (v1.x() * v2.z()),
            (v1.x() * v2.y()) - (v1.y() * v2.x())
        );
    }
    public static void rotate(Vector3D v, Vector3D axis, double theta) {
        v.scale(Math.cos(theta));
        v.add(Vector3D.scale(Vector3D.crossP(v, axis), theta));
        v.add(Vector3D.scale(v, Vector3D.dotP(v, axis)*(1-Math.cos(theta))));
    }

    @Override
    protected void assignV(Vector v) {
        if (!(v.getD() == 3)) {
            throw new IllegalArgumentException("Only Vector3D objects are allowed.");
        }
        super.assignV(v);
    }
}
