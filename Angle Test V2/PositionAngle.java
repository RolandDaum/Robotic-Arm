public  void main() {
    PositionAngle posa = new PositionAngle(0,0);
    posa.calc();
    System.out.println(posa.toString());
}

public class PositionAngle {
    double p1;
    double p2;
    double p;

    // Identical length in order to reach everypoint without a third arm segment
    double l3 = 50;
    double l4 = 50; 

    double t2;
    double t3;

    public PositionAngle(double p1, double p2) {
        if (Math.sqrt(Math.pow(p1,2) + Math.pow(p2,2)) > (l3+l4)) {
            this.p1 = 5;
            this.p2 = 5;
            System.out.println("NON REACHABLE POINT");
        } else {
            this.p1 = p1;
            this.p2 = p2;
        }
        p = Math.sqrt(Math.pow(this.p1,2)+Math.pow(this.p2,2));
    }
    // Calculates the Coordinates in the Quadrant 1 and 4. For Q2 and Q3 just rotate the arm
    public void calc() {
        double x = (Math.pow(p,2)+Math.pow(l3,2)-Math.pow(l4,2))/(2*Math.pow(p,2));
        double alpha = Math.acos((x*p)/l3);
        double beta = Math.acos(p2/p);
        double gama = Math.acos(((1-x)*p)/l4);
        t2 = (beta-alpha);
        t3 = alpha + gama;
        t2 = (360*t2)/(2*Math.PI);
        t3 = (360*t3)/(2*Math.PI);
        return;
    }

    @Override
    public String toString() {
        return "Theta 2: " + t2 + "\nTheta 3: " + t3;
    }   
}