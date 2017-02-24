package notusing;

/**
 * A horizontal ascan line.
 */
public class Scan {
    public int left;
    public int right;

    /**
     * Sets the left and right boundaries for this scan if
     * the x value is outside the current boundery.
     */
    public void setBoundary(int x) {
        if(x < left) {
            left = x;
        }

        if(x-1 > right) {
            right = x-1;
        }
    }

    /**
     * Clears this scan line.
     */
    public void clear() {
        left = Integer.MAX_VALUE;
        right = Integer.MIN_VALUE;
    }

    /**
     * Determines if this scan is valid (if left <= right).
     */
    public boolean isValid() {
        return (left <= right);
    }

    /**
     * Sets this scan.
     */
    public void setTo(int left, int right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Checks if this scan is equal to the specified values.
     */
    public boolean equals(int left, int right) {
        return (this.left == left && this.right == right);
    }
}
