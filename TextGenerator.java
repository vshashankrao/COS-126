/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   aturing
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

public class TextGenerator {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        String maintext = StdIn.readString();
        MarkovModel tester = new MarkovModel(maintext, k);
        String output = maintext.substring(0, k);
        tester.freq(output);
        for (int i = 0; i < t; i++) {
            output += tester.random(output);
        }
        StdOut.println(output);
    }
}
