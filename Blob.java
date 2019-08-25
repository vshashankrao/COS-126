/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   svr38
 *  Precept: P00
 *
 *  Partner Name:    Ada Lovelace
 *  Partner NetID:   alovelace
 *  Partner Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */


public class Blob {
    // Instance variable pixels which represents the pixels in the blob
    private int pixels;
    // private int mass;
    // Instance variable cx which represents the x coordinate of the center of mass of the blob
    private double cx;
    // Instance variable cx which represents the y coordinate of the center of mass of the blob
    private double cy;

    /**
     * The Blob constructor takes in no parameters for input, and simply creates a creates an empty
     * Blob object
     *
     * @param None
     * @return Blob object
     */
    public Blob() {
        pixels = 0;
        cx = 0.0;
        cy = 0.0;
    }

    /**
     * The add method takes in two integers x and y for input, and adds the pixel with specified
     * coordinates (x and y) to the blob
     *
     * @param Integer x, Integer y
     * @return None
     */
    public void add(int x, int y) {
        pixels += 1;
        cx = ((cx * pixels) + x) / pixels;
        cy = ((cy * pixels) + y) / pixels;
    }

    /**
     * The mass constructor takes in no parameters for input, and simply returns the mass of the
     * blob
     *
     * @param None
     * @return Integer Mass
     */
    public int mass() {
        return pixels;
    }

    /**
     * The distanceTo method takes in a Blob object for a parameter for input, and calculates the
     * Euclidean distance between the center of masses of the two blobs and returns the value.
     *
     * @param Blob that
     * @return double distance
     */
    public double distanceTo(Blob that) {
        return Math.sqrt((that.cy - this.cy) * (that.cy - this.cy) + (that.cx - this.cx) * (that.cx
                - this.cx));
    }

    /**
     * The toString method takes in no parameters for input, and returns string representation of
     * this blob
     *
     * @param None
     * @return String output
     */
    public String toString() {
        return String.format("%2d (%8.4f, %8.4f)", mass(), cx, cy);
    }

    /**
     * The main method tests all of the functions in the GuitarString class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        Blob test = new Blob();
        Blob test2 = new Blob();

        test.add(50, 50);
        test.add(50, 100);
        test.add(100, 100);
        test.add(100, 50);

        test2.add(200, 200);
        test2.add(150, 200);
        test2.add(200, 150);
        test2.add(150, 150);

        StdOut.println(test);
        StdOut.println(test2);
        StdOut.printf("The distance is %8.4f", test.distanceTo(test2));

    }
}
