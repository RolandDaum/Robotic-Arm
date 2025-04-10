import java.util.ArrayList;

import Objects.Vector3D;

public void main() {
    double[] l = new double[]{20,20,60,60,50,40};
    Vector3D vP = new Vector3D(100,69.2,50);
    Vector3D vD = new Vector3D(0,-1,1);

    InverseKinematics inverseKinematics = new InverseKinematics(vP, vD, l);
    System.out.println(inverseKinematics + "\n\n\n");

    ForwardKinematics forwardKinematics = new ForwardKinematics(inverseKinematics.getT(), l);
    System.out.println(forwardKinematics);

    // v Forward Geogebra Calculation
    ArrayList<Vector3D> vF = forwardKinematics.getV();
    System.out.println("\n\n\n");

    Vector3D vl0l1 = Vector3D.add(vF.get(0), vF.get(1));
    Vector3D vl0l1l2 = Vector3D.add(vl0l1, vF.get(2));
    Vector3D vl0l1l2l3l4 = Vector3D.add(Vector3D.add(vl0l1l2, vF.get(3)), vF.get(4));

    System.out.println(
        "Vektor(Punkt(" + forwardKinematics.getvP() + "))\n"
    );
    System.out.println(
        "Gerade(Punkt(" + Vector3D.subtract(forwardKinematics.getvP(), vF.get(5)) + "), Vektor(Punkt(" + vF.get(5) + ")))\n"
    );
    
}

private static double radDeg(double rad) {
    return (180*rad)/(Math.PI);
}
private static double degRad(double deg) {
    return (Math.PI*deg)/180;
}