import Objects.Vector;
import Objects.Vector3D;



public class InverseKinematics {
    private double[] l; // Arm Segment lenghts array
    private double[] t = new double[6]; // Theta rotation array

    private Vector3D vP; // Global Pin Point
    private Vector3D vD; // Direction to be pointing at vP
    private Vector3D vL2; // Vector of Arm segment L2
    private Vector3D vL34; // Vector of Arm segment L4 plus L5 (collinear)
    private Vector3D vL5; // Vector of Arm segment L6, same direction as vD, but different length
    private Vector3D vQ; // Vector of Angle T2 to T5

    public InverseKinematics(Vector3D vP, Vector3D vD, double[] l) {
        this.vP = vP;
        this.vD = vD;
        this.l = l;
        
        calc();
    }

    public double[] getT() {
        return t;
    } 

    private void calc() {
        vL5 = Vector3D.scaleToSize(vD, l[5]);
        vQ = Vector3D.subtract(Vector3D.subtract(vP, vL5), new Vector3D(0, 0, (l[0] + l[1])));   
        
        t0();
        t1t2();

        // vL2 = new Vector3D(
        //     Math.sin(t[0]) * Math.sin(t[1]) * l[2],
        //     Math.cos(t[0]) * Math.sin(t[1]) * l[2],
        //     Math.cos(t[1]) * l[2]
        // );
        // vL34 = new Vector3D(
        //     Math.sin(t[0]) * Math.sin(t[2]-t[1]) * (l[3]+l[4]),
        //     Math.cos(t[0]) * Math.sin(t[2]-t[1]) * (l[3]+l[4]),
        //     Math.cos(t[2]-t[1]) * (l[3]+l[4])
        // );
        vL2 = new Vector3D(0,0,1);
        vL2.rotate(new Vector3D(1,0,0), -t[1]);
        vL2.rotate(new Vector3D(0,0,1), -t[0]);
        vL2.scaleToSize(l[2]);

        vL34 = new Vector3D(vL2);
        vL34.rotate(Vector3D.rotate(new Vector3D(1,0,0), new Vector3D(0,0,1), -t[0]), t[2]);
        vL34.scaleToSize(l[3]+l[4]);

        t3t4();

        
        System.out.println(
            "Gerade(Punkt(" + Vector3D.add(vQ, new Vector3D(0,0,l[0]+l[1])) + "), Vektor(Punkt(" + vL5 + ")))"
        );
        

    }

    private void t0() {
        t[0] = Math.atan2(vQ.c(0),vQ.c(1));
    }
    private void t1t2() {
        double vQnorm = vQ.norm();
        double x = (Math.pow(vQnorm,2) + Math.pow(l[2],2) - Math.pow(l[3]+l[4],2) ) / (2 * Math.pow(vQnorm,2));
        double h = Math.sqrt(Math.pow(l[2],2)-Math.pow(x*vQnorm,2));
        double alpha = Math.atan2(h,x*vQnorm);
        double gama = Math.atan2(h, (1-x)*vQnorm);
        double beta = Math.atan2(Math.sqrt(Math.pow(vQ.c(0),2)+Math.pow(vQ.c(1),2)),vQ.c(2));

        t[1] = beta - alpha;
        t[2] = -(alpha + gama);
    }
    private void t3t4() {
        // Vector3D vREALT4RotatingAxis = Vector3D.crossP(vL34, vL5);
        // Vector3D vT4RotatingAxisInXYPlane = Vector3D.rotate(new Vector3D(1,0,0), new Vector3D(0,0,1), t[0]);
        // t[3] = Vector3D.vAngle(vREALT4RotatingAxis, vT4RotatingAxisInXYPlane);
        // System.out.println(radDeg(t[3]));


        Vector3D vHorAxisInPlane = Vector3D.rotate(new Vector3D(1,0,0), new Vector3D(0,0,1), t[0]);

        double x = 
            Vector3D.dotP(
                vL34, 
                Vector3D.subtract(vL5, Vector3D.subtract(vD, vL5))
            )/
            Math.pow(vL34.norm(),2);

        Vector3D vPL5 = Vector3D.add(vL5, Vector3D.scale(vL34, x));
        t[3] = -Vector3D.vAngle(vPL5, vHorAxisInPlane);


        // t[4] = (-Vector3D.vAngle(vL34, vL5)) + (Math.PI); // IST ZU 100% RICHTIG !!!
        t[4] = (-Vector3D.vAngle(vPL5, vL34))+Math.PI/2; // IST ZU 100% RICHTIG !!!

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
    private static double radDeg(double rad) {
        return (180*rad)/(Math.PI);
    }
    private static double degRad(double deg) {
        return (Math.PI*deg)/180;
    }
}
