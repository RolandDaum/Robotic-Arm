import java.util.Vector;

import Objects.Vector3D;

public void main(String[] args) {
    PositionAngleV2 idk = new PositionAngleV2(new Vector3D(-50,-40,0), new Vector3D(1,1,1)); // x and y of Point vector *-1 !!!
    System.out.println(idk);
}
public static double radDeg(double rad) {
    return (180*rad)/(Math.PI);
}
public static double degRad(double deg) {
    return (Math.PI*deg)/180;
}

public class PositionAngleV2 {
    double[] l = new double[]{20,20,60,60,50,40}; // Arm Segment lenghts array
    double[] t = new double[6]; // Theta rotation array

    Vector3D vP; // Global Pin Point
    Vector3D vD; // Direction to be pointing at vP
    Vector3D vL3; // Vector of Arm segment L3
    Vector3D vL34; // Vector of Arm segment L4 plus L5 (collinear)
    Vector3D vL5; // Vector of Arm segment L6, same direction as vD, but different length
    Vector3D vQ; // Vector of Angle T2 to T5

    PositionAngleV2(Vector3D point, Vector3D direction) {
        this.vP = point;
        this.vD = direction;
        calc();
    }

    void calc() {
        vL5 = Vector3D.scaleToSize(vD, l[5]);
        vQ = Vector3D.subtract(Vector3D.subtract(vP, vL5), new Vector3D(0, 0, (l[0] + l[1])));   
        
        t0();
        t1t2();

        vL3 = new Vector3D(
            Math.sin(t[0]) * Math.sin(t[1]) * l[2],
            Math.cos(t[0]) * Math.sin(t[1]) * l[2],
            Math.cos(t[1]) * l[2]
        );
        vL34 = new Vector3D(
            Math.sin(t[0]) * Math.sin(t[2]-t[1]) * (l[3]+l[4]),
            Math.cos(t[0]) * Math.sin(t[2]-t[1]) * (l[3]+l[4]),
            Math.cos(t[2]-t[1]) * (l[3]+l[4])
        );

        t3t4();
        
    }

    void t0() {
        t[0] = Math.atan2(vQ.c(0),vQ.c(1));
    }
    void t1t2() {
        double vQnorm = vQ.norm();
        double x = (Math.pow(vQnorm,2) + Math.pow(l[2],2) - Math.pow(l[3]+l[4],2) ) / (2 * Math.pow(vQnorm,2));
        double h = Math.sqrt(Math.pow(l[2],2)-Math.pow(x*vQnorm,2));
        double alpha = Math.atan2(h,x*vQnorm);
        double gama = Math.atan2(h, (1-x)*vQnorm);
        double beta = Math.atan2(Math.sqrt(Math.pow(vQ.c(0),2)+Math.pow(vQ.c(1),2)),vQ.c(2));

        t[1] = beta - alpha;
        t[2] = -(alpha + gama);
    }
    void t3t4() {
        Vector3D vREALT4RotatingAxis = Vector3D.crossP(vL34, vL5);
        Vector3D vT4RotatingAxisInXYPlane = Vector3D.rotate(new Vector3D(1,0,0), new Vector3D(0,0,1), t[0]);
        t[3] = Vector3D.vAngle(vREALT4RotatingAxis, vT4RotatingAxisInXYPlane);

        t[4] = -Vector3D.vAngle(vL34, vL5);
    }

    @Override
    public String toString() {
        return 
        "T0: " + radDeg(t[0]) + "\n" + 
        "T1: " + radDeg(t[1]) + "\n" + 
        "T2: " + radDeg(t[2]) + "\n" + 
        "T3: " + radDeg(t[3]) + "\n" + 
        "T4: " + radDeg(t[4]) + "\n" + 
        "T5: " + radDeg(t[5]);
    }
}
