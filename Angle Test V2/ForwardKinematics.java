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
    public ArrayList<Vector3D> getV() {
        ArrayList<Vector3D> v = new ArrayList<Vector3D>();
        v.add(vL0);
        v.add(vL1);
        v.add(vL2);
        v.add(vL3);
        v.add(vL4);
        v.add(vL5);
        return v;
    }

    public void calc() {
       vl0();
       vl1();
       vl2();
       vl3();
       vl4();
       vl5();
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
        str += "Direction: " + getvD().toString();
        return str;
    }

    private static double radDeg(double rad) {
        return (180*rad)/(Math.PI);
    }
    private static double degRad(double deg) {
        return (Math.PI*deg)/180;
    }

}