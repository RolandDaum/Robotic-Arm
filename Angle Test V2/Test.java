import java.util.Vector;

import Objects.Vector3D;

public void main() {

    Vector3D vL45PU = new Vector3D(1,1,1); 
    Vector3D vRA = new Vector3D(1,1,1);

    double t3 = -Math.asin(
    (
        vL45PU.x() * (vL45PU.z() * vRA.x() + vRA.y()) + vL45PU.y() * (vL45PU.z() * vRA.y() - vRA.x()) + Math.pow(vL45PU.z(), 2) * vRA.z()
    ) / Math.sqrt(
        Math.pow(vL45PU.x(), 2) * Math.pow(vL45PU.z(), 2) * Math.pow(vRA.x(), 2)
        + 2 * vL45PU.x() * (vL45PU.y() * vL45PU.z() * vRA.y() + (Math.pow(vL45PU.z(), 2) - 1) * vRA.z()) * vL45PU.z() * vRA.x()
        + Math.pow(vL45PU.y(), 2) * Math.pow(vL45PU.z(), 2) * Math.pow(vRA.y(), 2)
        + 2 * vL45PU.y() * vL45PU.z() * (Math.pow(vL45PU.z(), 2) - 1) * vRA.y() * vRA.z()
        + Math.pow(vL45PU.z(), 4) * Math.pow(vRA.z(), 2)
        - 2 * Math.pow(vL45PU.z(), 2) * Math.pow(vRA.z(), 2)
        + Math.pow(vRA.z(), 2)
        + 1
    )
    ) - Math.atan(
        vL45PU.x() * vL45PU.z() * vRA.x() + vL45PU.y() * vL45PU.z() * vRA.y() + (Math.pow(vL45PU.z(), 2) - 1) * vRA.z()
    ) + (2 * 1 + 1) * Math.PI;
}