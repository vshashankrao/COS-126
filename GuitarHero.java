/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   svr38
 *  Precept: P00
 *
 *  Partner Name:
 *  Partner NetID:
 *  Partner Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

public class GuitarHero {
    public static void main(String[] args) {
        // Create two guitar strings, for concert A and C
        String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] NOTES = new GuitarString[37];

        for (int i = 0; i < 37; i++) {
            NOTES[i] = new GuitarString(440.0 * Math.pow(2, ((i - 24) / 12.0)));
        }

        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if (KEYBOARD.indexOf(key) < 0 || KEYBOARD.indexOf(key) >= 37) {
                    StdOut.println("Not a Key!");
                }
                else {
                    NOTES[KEYBOARD.indexOf(key)].pluck();
                }
            }

            // compute the superposition of the samples

            double sample = 0.0;
            for (int i = 0; i < 37; i++) {
                sample += NOTES[i].sample();
            }
            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < 37; i++) {
                NOTES[i].tic();
            }
        }
    }
}
