import Objects.Vector3D;

public void main() {
    double[] l = new double[]{20,20,60,60,50,40};
    Vector3D vP = new Vector3D(-40,50,0);
    Vector3D vD = new Vector3D(2,-1,0);

    InverseKinematics inverseKinematics = new InverseKinematics(vP, vD, l);
    System.out.println(inverseKinematics);

    ForwardKinematics forwardKinematics = new ForwardKinematics(inverseKinematics.getT(), l);
    System.out.println(forwardKinematics);
}