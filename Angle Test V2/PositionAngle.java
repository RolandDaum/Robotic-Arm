public void main() {
    PositionAngle posa = new PositionAngle(-10,-10,25, 1,0,0);
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

    // Identical length in order to reach everypoint without a third arm segment
    // TODO: Implement reverse point vector size/length claculation
    double l1 = 10;
    double l2 = 5;
    double l3 = 50;
    double l4 = 50;
    double l5 = 0;
    double l6 = 0;

    double t1;
    double t2;
    double t3;
    double t4;
    double t5;
    double t6;

    public PositionAngle(double p1, double p2, double p3, double r1, double r2, double r3) {
        // TODO: Add special case for P(0|0)
        if (Math.sqrt(Math.pow(p1,2) + Math.pow(p2,2) + Math.pow(p3,2)) > (l3+l4)) {
            this.p1 = 5;
            this.p2 = 5;
            System.out.println("NON REACHABLE POINT");
        } else {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
        }
        p = Math.sqrt(Math.pow(this.p1,2)+Math.pow(this.p2,2)+Math.pow(p3,2));
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
    }
    public void calc() {
        calct1();
        calct2t3();
        calct4t5t6();
    }
    private void calct1() {
        t1 = Math.atan2(p1,p2);
    }
    // Calculates the Coordinates in the Quadrant 1 and 4. For Q2 and Q3 just rotate the arm
    private void calct2t3() {
        double x = (-l6)/(Math.sqrt(Math.pow(r1,2) + Math.pow(r2, 2)+ Math.pow(r3,2)));
        double p1 = this.p1-(-(x*r1));
        double p2 = this.p2-(-(x*r2));
        double p3 = this.p3-(-(x*r3))-(l1+l2);
        double p = Math.sqrt(Math.pow(p1,2) + Math.pow(p2, 2)+ Math.pow(p3,2));
        x = (Math.pow(p,2)+Math.pow(l3,2)-Math.pow(l4,2))/(2*Math.pow(p,2));
        double alpha = Math.acos((x*p)/l3);
        double beta = Math.atan2(Math.sqrt(Math.pow(p1,2) + Math.pow(p2,2)), p3);
        double gama = Math.acos(((1-x)*p)/l4);
        t2 = (beta-alpha);
        t3 = alpha + gama;
 
        return;
    }
    private void calct4t5t6() {
        t4 = Math.atan2(r3,r1);
        t5 = Math.atan2(r1,r2);
        t6 = Math.atan2(r3,r2);
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