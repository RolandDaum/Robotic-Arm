import java.util.ArrayList;

import Objects.Vector3D;

public void main() {
    double[] l = new double[]{20,20,60,60,50,40};
    Vector3D vP = new Vector3D(100,69.2,50);
    Vector3D vD = new Vector3D(0,-1,1);

    InverseKinematics inverseKinematics = new InverseKinematics(vP, vD, l);
    System.out.println(inverseKinematics);

    ForwardKinematics forwardKinematics = new ForwardKinematics(inverseKinematics.getT(), l);
    System.out.println(forwardKinematics);


    System.out.println("\n--------------------------------\n");
    ArrayList<Vector3D> vF = forwardKinematics.getV();
    Vector3D wrist = Vector3D.subtract(forwardKinematics.getvP(), vF.get(5));
    
    System.out.println(
        "Normalebene(Punkt("+ wrist +"),Vektor(Punkt("+ vF.get(4) +")))\n"
    );
    System.out.println(
        "Vektor(Punkt(" + vP + "))"
    );
    System.out.println(
        "Gerade(Punkt(" + wrist + "), Vektor(Punkt(" + vD + ")))\n"
    );

    System.out.println(
        "Vektor(Punkt(" + forwardKinematics.getvP() + "))"
    );
    System.out.println(
        "Gerade(Punkt(" + wrist + "), Vektor(Punkt(" + vF.get(5) + ")))"
    );
    






 
    
}

private static double radDeg(double rad) {
    return (180*rad)/(Math.PI);
}
private static double degRad(double deg) {
    return (Math.PI*deg)/180;
}