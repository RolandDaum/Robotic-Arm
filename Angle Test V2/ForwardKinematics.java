import java.util.ArrayList;

import Objects.Vector3D;

public class ForwardKinematics {
    private double[] l; // Arm Segment lenghts array
    private double[] t;  // Theta rotation array
    
    private Vector3D vL0;
    private Vector3D vL1;
    private Vector3D vL2;
    private Vector3D vL3;
    private Vector3D vL4;
    private Vector3D vL5;

    public ForwardKinematics(double[] t, double[] l) {
        this.t = t;
        this.l = l;
        calc();
    }

    public Vector3D getvP() {
        Vector3D vP = Vector3D.add(vL0, vL1);
        vP.add(vL2);
        vP.add(vL3);
        vP.add(vL4);
        vP.add(vL5);
        return vP;
    }
    public Vector3D getvD() {
        return Vector3D.unitize(vL5);
    }

    public void calc() {
       vl0();
       vl1();
       vl2();
       vl3();
       vl4();
       vl5();

       System.out.println("vL0: " + vL0);
       System.out.println("vL1: " + vL1);
       System.out.println("vL2: " + vL2);
       System.out.println("vL3: " + vL3);
       System.out.println("vL4: " + vL4);
       System.out.println("vL5: " + vL5 + "\n");
        System.out.println("T1: " + radDeg(Vector3D.vAngle(vL1, vL2)));
        System.out.println("T2: " + radDeg(Vector3D.vAngle(vL2, vL3)));
        System.out.println("T4: " + radDeg(Vector3D.vAngle(vL4, vL5)));


    System.out.println("\n\n\n");

    Vector3D vL0vL1 = Vector3D.add(vL0, vL1);

    System.out.println(vL0vL1);

    System.out.println(
        "Gerade(Punkt({" + 
        vL0vL1.x() + "," + 
        vL0vL1.y() + "," + 
        vL0vL1.z() + "})," + vL2 + "))");
    
    Vector3D vL0L1L2 = Vector3D.add(vL0vL1, vL2);

    System.out.println(
        "Gerade(Punkt({" + 
        vL0L1L2.x() + "," + 
        vL0L1L2.y() + "," + 
        vL0L1L2.z() + "})," + vL3 + "))");

    Vector3D vL0L1L2L3L4 = Vector3D.add(Vector3D.add(vL0L1L2, vL3), vL4);

    System.out.println(
        "Gerade(Punkt({" + 
        vL0L1L2L3L4.x() + "," + 
        vL0L1L2L3L4.y() + "," + 
        vL0L1L2L3L4.z() + "})," + vL5 + "))");

    Vector3D vL0L1L2L3L4L5 = Vector3D.add(vL0L1L2L3L4, vL5);
    System.out.println(vL0L1L2L3L4L5);
    

    System.out.println("\n\n\n");


    }

    private void vl0() {
        vL0 = new Vector3D(0,0,l[0]);
    }
    private void vl1() {
        vL1 = new Vector3D(0,0,l[1]);
    }
    private void vl2() {
        vL2 = new Vector3D(0,0,1);
        vL2.rotate(new Vector3D(1,0,0), -t[1]);
        vL2.rotate(new Vector3D(0,0,1), -t[0]);
        vL2.scaleToSize(l[2]);
        System.out.println(vL2 + "\n");
    }
    private void vl3() {
        vL3 = new Vector3D(vL2);
        vL3.rotate(Vector3D.rotate(new Vector3D(1,0,0), new Vector3D(0,0,1), -t[0]), t[2]);
        vL3.scaleToSize(l[3]);

    }
    private void vl4() {
        vL4 = Vector3D.scaleToSize(vL3, l[4]);
    }
    private void vl5() {
        vL5 = new Vector3D(vL4);
        vL5.rotate(Vector3D.rotate(new Vector3D(1,0,0), new Vector3D(0,0,1), -t[0]), t[4]);
        vL5.rotate(vL4, -t[3]);
        vL5.scaleToSize(l[5]);
    }

    @Override
    public String toString() {
        String str = "Point: " + getvP().toString() + "\n";
        str += "Direction: " + getvD().toString() + "\n\n";
        // str += vL0.toString() + "\n";
        // str += vL1.toString() + "\n";
        // str += vL2.toString() + "\n";
        // str += vL3.toString() + "\n";
        // str += vL4.toString() + "\n";
        // str += vL5.toString() + "\n";

        return str;
    }

    private static double radDeg(double rad) {
        return (180*rad)/(Math.PI);
    }
    private static double degRad(double deg) {
        return (Math.PI*deg)/180;
    }

}