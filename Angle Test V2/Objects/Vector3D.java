package Objects;
public class Vector3D extends Vector {

    public Vector3D(double x, double y, double z) {
        super(new double[]{x,y,z});
    }
    public Vector3D(Vector v) {
        super(new double[]{v.c(0),v.c(1),v.c(2)});
    }
    public double x() { return c(0); }
    public double y() { return c(1); }
    public double z() { return c(2); }
   
    public double xAngle() { return Vector3D.xAngle(this); }
    public double yAngle() { return Vector3D.yAngle(this); }
    public double zAngle() { return Vector3D.zAngle(this); }

    public void rotate(Vector3D axis, double theta) {
        assignV(Vector3D.rotate(this, axis, theta));
    }  

    // STATIC METHODS
    public static double xAngle(Vector3D v) { return Vector3D.vAngle(v, new Vector3D(1,0,0)); }
    public static double yAngle(Vector3D v) { return Vector3D.vAngle(v, new Vector3D(0,1,0)); }
    public static double zAngle(Vector3D v) { return Vector3D.vAngle(v, new Vector3D(0,0,1)); }
    // TODO: Add rotation around Angle
    public static double vAngle(Vector3D v1, Vector3D v2) {
        if (v1.norm() == 0 || v2.norm() == 0) {
            throw new IllegalArgumentException("No angle between zero vectors");
        }
        // return Math.acos(Vector3D.dotP(v1, v2)/(v1.norm()*v2.norm()));
        return Math.atan2(Vector3D.norm(Vector3D.crossP(v1, v2)), Vector3D.dotP(v1, v2));
    }
    public static double vAngleN(Vector3D v1, Vector3D v2, Vector n) {
        v1.unitize();
        v2.unitize();
        double angle = Math.acos(Vector3D.dotP(v1, v2));
        Vector3D cross = Vector3D.crossP(v1, v2);
        if (Vector3D.dotP(n,cross) < 0) {
            angle = -angle;
        }
        return angle;
    }
    public static Vector3D crossP(Vector3D v1, Vector3D v2) {
        return new Vector3D(
            (v1.y() * v2.z()) - (v1.z() * v2.y()),
            (v1.z() * v2.x()) - (v1.x() * v2.z()),
            (v1.x() * v2.y()) - (v1.y() * v2.x())
        );
    }
    public static Vector3D rotate(Vector3D v, Vector3D axis, double theta) {
        Vector3D k = new Vector3D(axis);
        k.unitize(); // HAS TO BE IN ORDER TO WORK
        Vector3D vR = new Vector3D(v);

        vR.scale(Math.cos(theta));
        vR.add(Vector3D.scale(Vector3D.crossP(k,v), Math.sin(theta)));
        vR.add(Vector3D.scale(k, Vector3D.dotP(k,v)*(1-Math.cos(theta))));

        return vR;
    }
    
    // Vector class indirect Overrides, cause of wrong return types when calling the e.g. add method from Vector.add which returns Vector object, but here we want Vector3D object
    public static Vector3D add(Vector3D v1, Vector3D v2) {
        Vector v = Vector.add(v1, v2);
        return new Vector3D(v);
    }
    public static Vector3D subtract(Vector3D v1, Vector3D v2) {
        Vector v = Vector.subtract(v1, v2);
        return new Vector3D(v);
    }
    public static Vector3D scaleToSize(Vector3D v, double factor) {
        Vector vScaledToSize = Vector.scaleToSize(v, factor);
        return new Vector3D(vScaledToSize);
    }
    public static Vector3D scale(Vector3D v, double factor) {
        Vector vScaled = Vector.scale(v, factor);
        return new Vector3D(vScaled);
    }
    public static Vector3D unitize(Vector3D v) {
        return new Vector3D(Vector.unitize(v));
    }

    @Override
    protected void assignV(Vector v) {
        if (!(v.getD() == 3)) {
            throw new IllegalArgumentException("Only Vector3D objects are allowed.");
        }
        super.assignV(v);
    }   
}
