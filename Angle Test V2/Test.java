import java.util.Vector;

import Objects.Vector3D;

public void main() {
    Vector3D a = new Vector3D(0,1,0);
    Vector3D b = new Vector3D(1,-1,0);
    Vector3D n = Vector3D.crossP(a, b);
    // Vector3D n = new Vector3D(0,0,-1);
    System.out.println(n);

    System.out.println(radDeg(Vector3D.vAngleN(a, b, n)));
}


private static double radDeg(double rad) {
    return (180*rad)/(Math.PI);
}
private static double degRad(double deg) {
    return (Math.PI*deg)/180;
}
