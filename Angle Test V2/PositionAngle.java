public void main() {
    PositionAngle posa = new PositionAngle(0,0,50, 1,1,1);
    posa.calc();
    System.out.println(posa.toString());
}

public class PositionAngle {
    double p1;
    double p2;
    double p3;
    double p;

    double r1;
    double r2;
    double r3;

    // Identical length of l3 and l4 in order to reach everypoint without a third arm segment
    // TODO: Implement reverse point vector size/length claculation
    double l1 = 10;
    double l2 = 20;
    double l3 = 50;
    double l4 = 50;
    double l5 = 20;
    double l6 = 40; 
    double lq;

    double[] vl3;
    double[] vl45;
    double[] vl6;
    double[] vq;

    double t1;
    double t2;
    double t3;
    double t4;
    double t5;
    double t6;

    public PositionAngle(double p1, double p2, double p3, double r1, double r2, double r3) {
        // TODO: Add special case for P(0|0)
        // TODO: Update this max length thing and make it it own function. NOTE: it prob. doesn't work like this anymore cause of the P destination translation
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        p = Math.sqrt(Math.pow(this.p1,2)+Math.pow(this.p2,2)+Math.pow(this.p3,2));
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }
    public void calc() {
        // TODO: Add angle to vector conversion
        calct1();
        calct2t3();
        calcVl3Vl45();
        calct4t5t6();
    }
    private void calct1() {
        double x = l6/(Math.sqrt(Math.pow(r1,2) + Math.pow(r2, 2)+ Math.pow(r3,2)));
        vl6 = new double[3];
        vl6[0] = x*r1;
        vl6[1] = x*r2;
        vl6[2] = x*r3;

        vq = new double[3];
        vq[0] = this.p1-vl6[0];
        vq[1] = this.p2-vl6[1];
        vq[2] = this.p3-vl6[2]-(l1+l2);
        lq = Math.sqrt(Math.pow(vq[0],2) + Math.pow(vq[1],2) + Math.pow(vq[2],2));

        t1 = Math.atan2(vq[0],vq[1]);
    }
    private void calct2t3() {
        double x = (Math.pow(lq,2) + Math.pow(l3,2) - Math.pow(l4+l5,2) ) / (2 * Math.pow(lq,2));

        double h = Math.sqrt(Math.pow(l3,2)-Math.pow(x*lq,2));
        double alpha = Math.atan2(h,x*lq);
        double gama = Math.atan2(h, (1-x)*lq);
        double beta = Math.atan2(Math.sqrt(Math.pow(vq[0],2)+Math.pow(vq[1],2)),vq[2]);
        t2 = beta - alpha;
        t3 = -(alpha + gama);
    }
    private void calcVl3Vl45() {
        vl3 = new double[3];
        vl3[0] = Math.sin(t1) * Math.sin(t2) * l3;
        vl3[1] = Math.cos(t1) * Math.sin(t2) * l3;
        vl3[2] = Math.cos(t2) * l3;

        vl45 = new double[3];
        vl45[0] = Math.sin(t1) * Math.sin(t3-t2) * (l4+l5);
        vl45[1] = Math.cos(t1) * Math.sin(t3-t2) * (l4+l5);
        vl45[2] = Math.cos(t3-t2) * (l4+l5);
    }
    private void calct4t5t6() {
        // double x = (-l6)/(Math.sqrt(Math.pow(r1,2) + Math.pow(r2, 2)+ Math.pow(r3,2)));
        // double l61 = x*r1;
        // double l62 = x*r2;
        // double l63 = x*r3;


        // KIND A WORKING
        // t4 = Math.atan2(r2,r1);
        // t5 = -(t3 - t2); // Rotates the tip vertical up
        // t6 = 0;


        // NEW
        // double x = (vl45[0]*vl6[0]+vl45[1]*vl6[1]+vl45[2]*vl6[2])/(Math.pow(vl45[0],2)+Math.pow(vl45[1],2)+Math.pow(vl45[2],2));
        // double aK = Math.sqrt(Math.pow(x*vl45[0],2)+Math.pow(x*vl45[1],2)+Math.pow(x*vl45[2],2));
        // double gK = Math.sqrt(Math.pow(vl6[0]-(x*vl45[0]),2)+Math.pow(vl6[1]-(x*vl45[1]),2)+Math.pow(vl6[2]-(x*vl45[2]),2));
        // t5 = Math.atan2(gK,aK)+Math.PI;
        // System.out.println("aKgK:  " + (360*t5)/(2*Math.PI));
        
        // // ^^ working in flat directions but not in 3D spaced ones

        // // OR (Phasenverschoben)
        // t5 = Math.acos((vl45[0]*vl6[0]+vl45[1]*vl6[1]+vl45[2]*vl6[2])/((l4+l5)*l6))+(Math.PI/2);
        // System.out.println("Vector Angle:  " + (360*t5)/(2*Math.PI));

        // t5 and t4 if l34 is vertical (0|0|1)
        // t5 = Math.atan2(Math.sqrt(Math.pow(r2,2) + Math.pow(r3,2)),r3);
        // t4 = Math.atan2(r2,r1);

        // OR (Phasenverschoben)
        t5 = Math.acos((vl45[0]*r1+vl45[1]*r2+vl45[2]*r3)/((l4+l5)*(Math.sqrt(Math.pow(r1,2)+Math.pow(r2,2)+Math.pow(r3,2)))));
        System.out.println("Vector Angle:  " + (360*t5)/(2*Math.PI));

        double x = (vl45[0]*vl6[0]+vl45[1]*vl6[1]+vl45[2]*vl6[2])/(Math.pow(l4+l5,2));
        double rp1 = r1-(x*vl45[0]);
        double rp2 = r2-(x*vl45[1]);
        double rp3 = r3-(x*vl45[2]);

        double y1 = (rp3*0)-(rp2*l6);
        double y2 = (rp1*l6)-(rp3*0);
        double y3 = (rp2*0)-(rp1*0);

        t4 = Math.acos(
            (rp1*y1+rp2*y2+rp3*y3)/
            (Math.sqrt(Math.pow(rp1,2)+Math.pow(rp2,2)+Math.pow(rp3,2)))*
            (Math.sqrt(Math.pow(y1,2)+Math.pow(y2,2)+Math.pow(y3,2))))+(Math.PI/2);

    }

    @Override
    public String toString() {
        return
        "Theta 1: " + (360*t1)/(2*Math.PI) + 
        "\nTheta 2: " + (360*t2)/(2*Math.PI) + 
        "\nTheta 3: " + (360*t3)/(2*Math.PI) + 
        "\nTheta 4: " + (360*t4)/(2*Math.PI) + 
        "\nTheta 5: " + (360*t5)/(2*Math.PI) + 
        "\nTheta 6: " + (360*t6)/(2*Math.PI);
    }   
}