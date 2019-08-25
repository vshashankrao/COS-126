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


public class Tour {
    private class Node {
        private Point p;
        private Node next;
    }

    // Instance variable size which represents the size of the tour
    private int aSize;
    // Instance variable items which represents the initial node in the tour
    private Node items;


    /**
     * The Tour constructor takes in no parameters as input, and creates an empty Tour
     *
     * @param None.
     * @return An empty Tour object.
     */
    public Tour() {
        items = new Node();
        items.p = null;
        items.next = items;

    }

    /**
     * The alternate Tour constructor takes in 4 Point parameters as input, and creates a Tour with
     * four points
     *
     * @param Point a, Point b, Point c, Point d.
     * @return A specified 4 point tour.
     */
    public Tour(Point a, Point b, Point c, Point d) {
        items = new Node();
        Node itemsB = new Node();
        Node itemsC = new Node();
        Node itemsD = new Node();
        items.p = a;
        items.next = itemsB;

        itemsB.p = b;
        itemsB.next = itemsC;

        itemsC.p = c;
        itemsC.next = itemsD;

        itemsD.p = d;
        itemsD.next = items;
    }

    /**
     * The size method takes in no parameters as input, and returns the number of poiunts in the
     * tour
     *
     * @param None
     * @return Integer representing the size.
     */
    public int size() {
        int output = 1;
        Node val = items;
        while (val.next != items) {
            output += 1;
            val = val.next;
        }
        return output;
    }

    /**
     * The length method takes in no parameters as input, and returns the length of the tour
     *
     * @param None
     * @return Double representing the length.
     */
    public double length() {
        double val = 0.0;
        Node firstN = items;
        Node secondN = items;
        if (aSize == 1 || aSize == 0) {
            return 0.0;
        }
        while (firstN != null) {
            val += firstN.p.distanceTo(secondN.p);
            firstN = secondN;
        }
        return val;
    }

    /**
     * The toString method takes in no parameters for input, and returns a string representation of
     * the tour
     *
     * @param None
     * @return String output
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        Node val = items;
        for (int i = size() - 1; i >= 0; i--) {
            out.append(val.p.toString() + "\n");
            val = val.next;
        }
        return out.toString();
    }

    /**
     * The draw method takes in no parameters for input, and returns nothing. It draws the tour to
     * standard drawing
     *
     * @param None
     * @return None
     */
    public void draw() {
        Node firstN = items;
        Node secondN = items.next;
        firstN.p.drawTo(secondN.p);
        firstN = firstN.next;
        secondN = secondN.next;
        while (firstN != items) {
            firstN.p.drawTo(secondN.p);
            firstN = firstN.next;
            secondN = secondN.next;
        }
    }

    /**
     * The insertNearest method takes in a Point parameter for input, and returns nothing. It
     * inserts the point using the nearest neighbor heuristic
     *
     * @param Point p
     * @return None
     */
    public void insertNearest(Point p) {

        double cons = Double.POSITIVE_INFINITY;
        double val = 0.0;
        Node firstN = items;
        Node nearN = new Node();
        Node output = new Node();
        if (size() == 0) {
            items.p = p;
            items.next = items;
        }
        while (firstN != items) {
            if (cons > val) {
                cons = val;
                nearN = firstN;
            }
            firstN = firstN.next;

        }
        output.p = p;
        output.next = nearN.next;
        nearN.next = output;

    }

    /**
     * The insertSmallest method takes in a Point parameter for input, and returns nothing. It
     * inserts the point using the smallest increase heuristic
     *
     * @param Point p
     * @return None
     */
    public void insertSmallest(Point p) {
        double cons = Double.POSITIVE_INFINITY;
        double val = 0.0;
        Node firstN = items;
        Node smallN = new Node();
        Node output = new Node();
        if (size() == 0) {
            items.p = p;
            items.next = items;
        }
        double dist = 0.0;
        while (firstN != items) {
            if (cons > val) {
                cons = val;
                smallN = firstN;
            }
            firstN = firstN.next;

        }
        output.p = p;
        output.next = smallN.next;
        smallN.next = output;
    }

    /**
     * The main method tests all of the functions in the MarkovModel class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        // define 4 points that are the corners of a square
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        // create the tour a -> b -> c -> d -> a
        Tour squareTour = new Tour(a, b, c, d);

        // print the size to standard output
        int size = squareTour.size();
        StdOut.println("Number of points = " + size);

        // print the tour length to standard output
        double length = squareTour.length();
        StdOut.println("Tour length = " + length);

        // print the tour to standard output
        StdOut.println(squareTour);


        // Tests insertNearest
        Point r = new Point(110.0, 110.0);
        squareTour.insertNearest(r);
        StdOut.println(squareTour);


        // tests draw method
        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);
        squareTour.draw();

        // Tests insertNearest
        Point g = new Point(110.0, 110.0);
        squareTour.insertSmallest(g);
        StdOut.println(squareTour);

        Tour emptyTour = new Tour();
        emptyTour.insertNearest(g);

    }
}
