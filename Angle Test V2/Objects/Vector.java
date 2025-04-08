package Objects;
class Vector {
    private double[] v;
    private int dimension;
    private boolean unitized = false;

    // CONSTRUCTOR
    /**
     * Creates a unit vector with all values set to 1.
     * @param dimension Number of dimensions.
     */
    public Vector(int dimension) {
        if (dimension < 1) {
            dimension = 1;
        }
        this.v = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            v[i] = 1;
        }
        this.dimension = dimension;
    }
    /**
     * Creates a vector with the given coordinates.
     * @param v Array of coordinate values.
     */
    public Vector(double[] v) {
        this.v = v;
        this.dimension = this.v.length;
    }

    // GET - SET
    /**
     * Gets the coordinate value at a specific dimension.
     * @param d Dimension index.
     * @return Coordinate value.
     */
    public double getC(int d) {
        if (d >= dimension) {
            throw new IllegalArgumentException("Dimension index out of bounds.");
        }
        return v[d];
    }
    /**
     * Sets the coordinate value at a specific dimension.
     * @param d Dimension index.
     * @param cord Coordinate value.
     */
    public void setC(int d, double cord) {
        if (!(d >= 0 && d < dimension)) {
            return;
        }
        this.v[d] = cord;
    }
    /**
     * Gets the number of dimensions.
     * @return Dimension count.
     */
    public int getD() {
        return this.dimension;
    }
    /**
     * Returns true if the Vector has been unitized aka. has norm == 1
     * @return boolean state of unitisation
     */
    public boolean unitized() { return this.unitized; }

    // METHODS
    protected void assignV(Vector v) {
        this.v = v.v;
        this.dimension = v.dimension;
    } 
    /**
     * Adds another vector to this vector.
     * @param v Vector to add.
     */
    public void add(Vector v) { assignV(Vector.add(this, v));}
    public void subtract(Vector v) { assignV(Vector.subtract(this, v)); }
    /**
     * Scales this vector by a factor.
     * @param factor Scaling factor.
     */
    public void scale(double factor) { assignV(Vector.scale(this, factor)); }
    /**
     * Converts this vector to a unit vector.
     */
    public void unitize() { assignV(Vector.unitize(this)); }
    /**
     * Computes the norm (magnitude) of this vector.
     * @return Norm value.
     */
    public double norm() { return Vector.norm(this); }
    
    // STATIC METHODS
    /**
     * Computes the dot product of two vectors.
     * @param v1 First vector.
     * @param v2 Second vector.
     * @return Dot product value.
     */
    public static double dotP(Vector v1, Vector v2) {
        double sum = 0;
        for (int i = 0; i < Math.min(v1.getD(), v2.getD()); i++) {
            sum += (v1.getC(i) * v2.getC(i));
        }
        return sum;
    }

    /**
     * Computes the norm (magnitude) of a vector.
     * @param v Vector to compute norm for.
     * @return Norm value.
     */
    public static double norm(Vector v) {
        double sqrSum = 0;
        for (int i = 0; i < v.getD(); i++) {
            sqrSum += Math.pow(v.getC(i),2);
        }
        return Math.sqrt(sqrSum);
    }
    /**
     * Adds two vectors and returns the result.
     * @param v1 First vector.
     * @param v2 Second vector.
     * @return Resultant vector.
     */
    public static Vector add(Vector v1, Vector v2) {
        if (v1.getD() != v2.getD()) {
            throw new IllegalArgumentException("Dimensions do not match");
        }
        Vector v = new Vector(v1.getD());
        for (int i = 0; i < v.getD(); i++) {
            v.setC(i, v1.getC(i) + v2.getC(i));
        }
        return v;
    }
     /**
     * Subtracts two vectors and returns the result.
     * @param v1 First vector.
     * @param v2 Second vector.
     * @return Resultant vector.
     */
    public static Vector subtract(Vector v1, Vector v2) {
        if (v1.getD() != v2.getD()) {
            throw new IllegalArgumentException("Dimensions do not match");
        }
        return Vector.add(v1, Vector.scale(v2,-1));
    }
    /**
     * Scales a vector by a factor.
     * @param v Vector to scale.
     * @param factor Scaling factor.
     */
    public static Vector scale(Vector v, double factor) {
        Vector vN = new Vector(v.getD());
        for (int i = 0; i < v.getD(); i++) {
            vN.setC(i, v.getC(i)*factor);
        }
        return vN;
    }
    /**
     * Converts a vector to a unit vector.
     * @param v Vector to unitize.
     */
    public static Vector unitize(Vector v) {
        double norm = v.norm();
        if (norm == 0) {
            throw new IllegalArgumentException("Cannot unitize a zero vector");
        }
        v = Vector.scale(v, 1 / norm);
        v.unitized = true;
        return v;
    }
}