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

public class BeadTracker {
    /**
     * The BeadTracker main method determines how far a bead moves from one time t to the next time
     * t + Δt based on the min, tau, delta, and text file all given in the command line argument.
     *
     * @param String[] array
     * @return None.
     */
    public static void main(String[] args) {
        // Arguments for min, tau, and delta
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);

        // Primary function where values are calculated and updated
        for (int i = 3; i < args.length - 1; i++) {
            Picture frame = new Picture(args[i]);
            Picture nFrame = new Picture(args[i + 1]);
            BeadFinder frameBeads = new BeadFinder(frame, tau);
            BeadFinder nFrameBeads = new BeadFinder(nFrame, tau);
            Blob[] frameCont = frameBeads.getBeads(min);
            Blob[] nFrameCont = nFrameBeads.getBeads(min);

            for (int x = 0; x < nFrameCont.length; x++) {
                double sDist = Double.POSITIVE_INFINITY;
                for (int y = 0; y < frameCont.length; y++) {
                    double distance = nFrameCont[x].distanceTo(frameCont[y]);
                    if (sDist > distance && delta >= distance) {
                        sDist = distance;
                    }
                }
                if (delta >= sDist) {
                    StdOut.printf("%.4f\n", sDist);
                }
            }
        }
    }
}
