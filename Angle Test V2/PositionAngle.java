public void main() {
    PositionAngle posa = new PositionAngle(50,50,0, 0,1,1);
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

    double[] vl3;
    double[] vl45;

    double t1;
    double t2;
    double t3;
    double t4;
    double t5;
    double t6;

    public PositionAngle(double p1, double p2, double p3, double r1, double r2, double r3) {
        // TODO: Add special case for P(0|0)
        // TODO: Update this max length thing and make it it own function. NOTE: it prob. doesn't work like this anymore cause of the P destination translation
        // if (Math.sqrt(Math.pow(p1,2) + Math.pow(p2,2) + Math.pow(p3,2)) > (l3+l4)) {
        //     this.p1 = 5;
        //     this.p2 = 5;
        //     System.out.println("NON REACHABLE POINT");
        // } else {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        // }
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
        double x = (-l6)/(Math.sqrt(Math.pow(r1,2) + Math.pow(r2, 2)+ Math.pow(r3,2)));
        double p1 = this.p1-(-(x*r1));
        double p2 = this.p2-(-(x*r2));
        double p3 = this.p3-(-(x*r3))-(l1+l2);

        t1 = Math.atan2(p1,p2);
    }
    // Calculates the Coordinates in the Quadrant 1 and 4. For Q2 and Q3 just rotate the arm
    private void calct2t3() {
        double x = (-l6)/(Math.sqrt(Math.pow(r1,2) + Math.pow(r2, 2)+ Math.pow(r3,2)));
        double p1 = this.p1-(-(x*r1));
        double p2 = this.p2-(-(x*r2));
        double p3 = this.p3-(-(x*r3))-(l1+l2);
        double p = Math.sqrt(Math.pow(p1,2) + Math.pow(p2, 2)+ Math.pow(p3,2));

        x = (Math.pow(p,2)+Math.pow(l3,2)-Math.pow((l4+l5),2))/(2*Math.pow(p,2));
        double alpha = Math.acos((x*p)/l3);
        double beta = Math.atan2(Math.sqrt(Math.pow(p1,2) + Math.pow(p2,2)), p3);
        double gama = Math.acos(((1-x)*p)/(l4+l5));
        t2 = (beta-alpha);
        t3 = alpha + gama;
    }
    private void calcVl3Vl45() {
        vl3 = new double[3];
        vl3[0] = Math.sin(t1) * Math.sin(t2) * l3;
        vl3[1] = Math.cos(t1) * Math.sin(t2) * l3;
        vl3[2] = Math.cos(t2) * l3;

        vl45 = new double[3];
        vl45[0] = Math.sin(t1) * Math.sin(t3-t2) * l3;
        vl45[1] = Math.cos(t1) * Math.sin(t3-t2) * l3;
        vl45[2] = Math.cos(t3-t2) * l3;
    }
    private void calct4t5t6() {
        double x = (-l6)/(Math.sqrt(Math.pow(r1,2) + Math.pow(r2, 2)+ Math.pow(r3,2)));
        double l61 = x*r1;
        double l62 = x*r2;
        double l63 = x*r3;

        t4 = Math.atan2(r2,r1);
        t5 = (t3-t2)-(Math.PI/2); // IST RICHTIG, damit es gerade nach oben zeigt
 
        t6 = 0;
    }

    @Override
    public String toString() {
        return "Theta 1: " + (360*t1)/(2*Math.PI) + 
        "\nTheta 2: " + (360*t2)/(2*Math.PI) + 
        "\nTheta 3: " + (360*t3)/(2*Math.PI) + 
        "\nTheta 4: " + (360*t4)/(2*Math.PI) + 
        "\nTheta 5: " + (360*t5)/(2*Math.PI) + 
        "\nTheta 6: " + (360*t6)/(2*Math.PI);
    }   
}