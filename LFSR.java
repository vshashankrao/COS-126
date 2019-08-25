/* *****************************************************************************
 * Name:  Shashank Rao
 * NetID:   svr38
 * Precept: 009
 *
 * Description: Creates and LFSR with specified seed and tap
 *
 *
 **************************************************************************** */

public class LFSR {
    // Instance variable aSeed which takes the seed from the newly constructed LFSR object.
    private String aSeed;
    // Instance variable aTap which takes the tap from the newly constructed LFSR object.
    private int aTap;

    /**
     * The LFSR constructor takes in a String and an int as input(referred to as seed and tap
     * respectively), and creates an LFSR object.
     *
     * @param String seed and int tap to be stored.
     * @return An LFSR object with a seed and tap as properties.
     */
    public LFSR(String seed, int tap) {
        aSeed = seed;
        aTap = tap;
    }

    /**
     * The length function takes in no paramters, and returns the length of the seed of an LFSR
     * object.
     *
     * @param None.
     * @return length of LFSR object.
     */
    public int length() {
        int n = aSeed.length();
        return n;
    }

    /**
     * The bitAt function takes in a single int parameter, and returns the bit at the specified
     * location of the parameter.
     *
     * @param int location of bit to be found.
     * @return int which is the bit that is at location specified by parameter.
     */
    public int bitAt(int i) {
        String reverse = "";
        int n = i - 1;
        for (int j = aSeed.length() - 1; j >= 0; j--) {
            reverse = reverse + aSeed.charAt(j);
        }
        int x = Integer.parseInt(reverse.substring(n, n + 1));
        return x;
    }

    /**
     * The toString function takes in no paramters, and returns the string representation of the
     * seed of an LFSR object
     *
     * @param None.
     * @return String representation of LFSR object.
     */
    public String toString() {
        return aSeed;
    }

    /**
     * The step function takes in no paramters, simulates one step of specified LFSR, and returns
     * the new bit (as 0 or 1) of specified LFSR object.
     *
     * @param None.
     * @return int represetnation of newly calculated bit.
     */
    public int step() {
        int LFSRLENGTH = aSeed.length();

        LFSR tempSeed = new LFSR(aSeed, aTap);
        int f1 = tempSeed.bitAt(LFSRLENGTH);
        int f2 = tempSeed.bitAt(aTap);
        int val = 0;

        if (f1 == f2) {
            String strval = String.valueOf(val);
            aSeed = aSeed.substring(1, LFSRLENGTH);
            aSeed += strval;
        }
        else {
            val = 1;
            String strval = String.valueOf(val);
            aSeed = aSeed.substring(1, LFSRLENGTH);
            aSeed += strval;
        }

        return val;

    }

    /**
     * The generate function takes in an int paramter, simulates k steps of specified LFSR, and
     * returns the k bits as a k-bit integer.
     *
     * @param None.
     * @return int represetnation of newly calculated bit.
     */
    public int generate(int k) {
        LFSR tempSeed = new LFSR(aSeed, aTap);
        int out = 0;
        for (int i = 0; i < k; i++) {
            out = 2 * out;
            out += tempSeed.step();
        }
        aSeed = tempSeed.aSeed;
        return out;

    }

    /**
     * The main method tests all of the functions in the LFSR class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        LFSR lfsr0 = new LFSR("01101000010", 5);

        // This block tests the length() method
        int a = lfsr0.length();
        System.out.println("This length of this LFSR should be 11, the actual length is " + a);

        // This block tests the bitAt() method
        int b = lfsr0.bitAt(7);
        System.out.println("This should return 1, it returns " + b);

        // This tests the toString() method
        StdOut.println(lfsr0);

        // This block tests the step() method
        LFSR lfsr1 = new LFSR("01101000010", 9);
        StdOut.println(lfsr1);
        for (int i = 0; i < 10; i++) {
            int bit = lfsr1.step();
            StdOut.println(lfsr1 + " " + bit);
        }

        // This block tests the generate() method
        LFSR lfsr2 = new LFSR("01101000010", 9);
        StdOut.println(lfsr2);
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.generate(5);
            StdOut.println(lfsr2 + " " + r);
        }
    }

}
