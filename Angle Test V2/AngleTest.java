import Objects.Vector3D;

public void main() {
    double[] l = new double[]{20,20,60,60,50,40};
    Vector3D vP = new Vector3D(69,47,35);
    Vector3D vD = new Vector3D(-1,2,2);

    InverseKinematics inverseKinematics = new InverseKinematics(vP, vD, l);
    System.out.println(inverseKinematics);

    ForwardKinematics forwardKinematics = new ForwardKinematics(inverseKinematics.getT(), l);
    System.out.println(forwardKinematics);
}