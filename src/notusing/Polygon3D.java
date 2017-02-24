package notusing;

/**
 * The Polygon3D clas represents a polygon as a series of
 * vertices.
 */
public class Polygon3D implements Transformable {
    // temporary vectors used for calculation
    private static Vector3D temp1 = new Vector3D();
    private static Vector3D temp2 = new Vector3D();

    private Vector3D[] v;
    private int numVertices;
    private Vector3D normal;

    /**
     * Creates an empty polygon that can be used as a a "scratch"
     * polygon for transforms, projections, etc.
     */
    public Polygon3D() {
        numVertices = 0;
        v = new Vector3D[0];
        normal = new Vector3D();
    }

    /**
     * Creates a new Polygon3D with the specified vertices.
     */
    public Polygon3D(Vector3D v0, Vector3D v1, Vector3D v2) {
        this(new Vector3D[]{ v0, v1, v2 });
    }

    /**
     * Creates a new Polygon3D with the specified vertices. All
     * the vertices are assumed to be in the same plane.
     */
    public Polygon3D(Vector3D v0, Vector3D v1, Vector3D v2, Vector3D v3) {
        this(new Vector3D[]{ v0, v1, v2, v3 });
    }

    public Polygon3D(Vector3D[] vertices) {
        this.v = vertices;
        numVertices = vertices.length;
        calcNormal();
    }

    public void setTo(Polygon3D polygon){
        numVertices = polygon.numVertices;
        normal.setTo(polygon.normal);

        ensureCapacity(numVertices);
        for(int i =0; i < numVertices; i++){
            v[i].setTo(polygon.v[i]);
        }
    }

    /**
     * Ensures this polygon has enough capacity to hold the specified number of
     * vertices.
     */
    protected void ensureCapacity(int length) {
        if(v.length < length){
            Vector3D[] newV = new Vector3D[length];
            System.arraycopy(v, 0, newV, 0, v.length);
            for(int i = v.length; i< newV.length; i++) {
                newV[i] = new Vector3D();
            }
            v = newV;
        }
    }

    /**
     * Gets the number of vertices this polygon has.
     */
    public int getNumVertices() {
        return numVertices;
    }

    public Vector3D getVertex(int index) {
        return v[index];
    }

    public void project(ViewWindow view) {
        for(int i = 0; i < numVertices; i++){
            view.project(v[i]);
        }
    }

    // methods from the Transformable interface.

    public void add(Vector3D u) {
        for (int i=0; i<numVertices; i++) {
            v[i].add(u);
        }
    }

    public void subtract(Vector3D u) {
        for (int i=0; i<numVertices; i++) {
            v[i].subtract(u);
        }
    }

    public void add(Transform3D xform) {
        addRotation(xform);
        add(xform.getLocation());
    }

    public void subtract(Transform3D xform) {
        subtract(xform.getLocation());
        subtractRotation(xform);
    }

    public void addRotation(Transform3D xform) {
        for (int i=0; i<numVertices; i++) {
            v[i].addRotation(xform);
        }
        normal.addRotation(xform);
    }

    public void subtractRotation(Transform3D xform) {
        for (int i=0; i<numVertices; i++) {
            v[i].subtractRotation(xform);
        }
        normal.subtractRotation(xform);
    }

    /**
     Calculates the unit-vector normal of this polygon.
     This method uses the first, second, and third vertices
     to calcuate the normal, so if these vertices are
     collinear, this method will not work. In this case,
     you can get the normal from the bounding rectangle.
     Use setNormal() to explicitly set the normal.
     This method uses static objects in the Polygon3D class
     for calculations, so this method is not thread-safe across
     all instances of Polygon3D.
     */
    public Vector3D calcNormal() {
        if (normal == null) {
            normal = new Vector3D();
        }
        temp1.setTo(v[2]);
        temp1.subtract(v[1]);
        temp2.setTo(v[0]);
        temp2.subtract(v[1]);
        normal.setToCrossProduct(temp1, temp2);
        normal.normalize();
        return normal;
    }


    /**
     Gets the normal of this polygon. Use calcNormal() if
     any vertices have changed.
     */
    public Vector3D getNormal() {
        return normal;
    }


    /**
     Sets the normal of this polygon.
     */
    public void setNormal(Vector3D n) {
        if (normal == null) {
            normal = new Vector3D(n);
        }
        else {
            normal.setTo(n);
        }
    }

    /**
     * Tests if this polygon is facing the specified location.
     * This method uses static objects in the Polygon3D class
     * for calculations, so this method is not thread=safe across
     * all instances of Polygon3D.
     */
    public boolean isFacing(Vector3D u) {
        temp1.setTo(u);
        temp1.subtract(v[0]);
        return (normal.getDotProduct(temp1) >= 0);
    }
}
