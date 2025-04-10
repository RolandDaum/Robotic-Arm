import Objects.Vector3D;

public void main() {
    double[] l = new double[]{20,20,60,60,50,40};
    Vector3D vP = new Vector3D(100,100,50);
    Vector3D vD = new Vector3D(0,-1,1);
    InverseKinematics inverseKinematics = new InverseKinematics(vP, vD, l);
    System.out.println(inverseKinematics + "\n\n\n");

    ForwardKinematics forwardKinematics = new ForwardKinematics(inverseKinematics.getT(), l);
    System.out.println(forwardKinematics);
    // System.out.println(vP.norm() + " | " + forwardKinematics.getvP().norm());



}

private static double radDeg(double rad) {
    return (180*rad)/(Math.PI);
}
private static double degRad(double deg) {
    return (Math.PI*deg)/180;
}