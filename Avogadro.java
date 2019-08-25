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

public class Avogadro {
    // Instance variable T which represents the absolute temperature constant
    private static int T = 297;
    // Instance variable N which represents the viscosity of water at room temp
    private static double N = 9.135E-4;
    // Instance variable P which represents the radius of bead
    private static double P = 0.5E-6;
    // Instance variable R whcih represents the universal gas constant
    private static double R = 8.31446;
    // Instance variable convPM which represents the conversion rate from Pixels to meters
    private static double convPM = 0.175E-6;

    /**
     * The Avogadro main method calculates the avogadro number based on values in a text file given
     * in the command line argument.
     *
     * @param String[] array
     * @return None.
     */
    public static void main(String[] args) {
        double selfDiff = 0.0;
        int disp = 0;
        double radDisp = 0;
        while (!StdIn.isEmpty()) {
            radDisp = StdIn.readDouble() * convPM;
            selfDiff += Math.pow(radDisp, 2);
            disp += 1;
        }
        double kB = (6 * Math.PI * N * P * selfDiff) / T;
        double avogadro = R / kB;
        StdOut.printf("Boltzmann = %.4e", kB);
        StdOut.println();
        StdOut.printf("Avogadro  = %.4e", avogadro);
        StdOut.println();
    }
}
