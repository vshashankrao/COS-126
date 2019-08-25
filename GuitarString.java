/* *****************************************************************************
 *  Name: Shashank Rao
 *  NetID: svr38
 *  Precept:
 *
 *  Partner Name:
 *  Partner NetID:
 *  Partner Precept:
 *
 *  Description:  This is a template file for GuitarString.java. It lists the
 *                constructors and methods you need, along with descriptions
 *                of what they're supposed to do.
 *
 *                Note: it won't compile until you fill in the constructors
 *                and methods (or at least commment out the ones whose return
 *                type is non-void).
 *
 **************************************************************************** */

public class GuitarString {
    // Instance variable aBuff which represents an empty Ring Buffer object
    private RingBuffer aBuff;
    // Instance variable aFreq which represents the frequency
    private double aFreq;
    // Instance variable n which represents the desired capacity
    private int n;

    /**
     * The GuitarString constructor takes in a double as input, and creates a GuitarString object of
     * specified frequency.
     *
     * @param double frequency
     * @return A GuitarString object with a specified capacity.
     */
    public GuitarString(double frequency) {
        aFreq = frequency;
        n = (int) Math.ceil(44100.0 / aFreq);
        aBuff = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            aBuff.enqueue(0.0);
        }
    }

    /**
     * The alternate GuitarString constructor takes in a specified array as input, and creates a
     * GuitarString object of specified frequency.
     *
     * @param double array init
     * @return A GuitarString object with a specified array.
     */
    public GuitarString(double[] init) {

        n = init.length;
        aBuff = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            aBuff.enqueue(init[i]);
        }
    }

    /**
     * The length() method takes in no inputs simply returns the number of samples in the RingBuffer
     * object.
     *
     * @param None
     * @return An int which returns the number of samples in the specified RingBuffer.
     */
    public int length() {
        return n;
    }

    /**
     * The pluck() method takes in no inputs and plucks the guitar string (by replacing the buffer
     * with white noise).
     *
     * @param None
     * @return None
     */
    public void pluck() {
        for (int i = 0; i < n; i++) {
            aBuff.dequeue();
            aBuff.enqueue(StdRandom.uniform(-0.5, 0.5));
        }
    }

    /**
     * The tic() method takes in no inputs and advances the Karplus-Strong simulation one time step
     *
     * @param None
     * @return None
     */
    public void tic() {

        double r = aBuff.dequeue();
        double s = aBuff.peek();
        aBuff.enqueue(((r + s) / 2) * 0.996);

    }

    /**
     * The sample() method takes in no inputs and simply returns the current sample in the
     * GuitarString object.
     *
     * @param None
     * @return A double which returns the current sample of the specified GuitarString.
     */
    public double sample() {
        return aBuff.peek();
    }


    /**
     * The main method tests all of the functions in the GuitarString class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);

        int m = 25; // 25 tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
    }

}
