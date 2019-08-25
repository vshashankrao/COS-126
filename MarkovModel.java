/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   svr38
 *  Precept: P00
 *
 *  Partner Name:
 *  Partner NetID:
 *  Partner Precept:
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

public class MarkovModel {
    // Instance variable aText which represents text in the MarkovModel Object
    private String aText;
    // Instance variable anOrder which represents the order for the MarkovModel Object
    private int anOrder;

    /**
     * The MarkovModel constructor takes in a String and an int as input, and creates a Markov Model
     * object of order k for the specified text.
     *
     * @param String and Integer text and order respectively to be stored.
     * @return A MarkovModel object with a specified text and order.
     */
    public MarkovModel(String text, int k) {
        aText = text;
        anOrder = k;
    }

    /**
     * The order() method takes in no inputs simply returns the k of this Markov Model.
     *
     * @param None
     * @return An int which returns the order k of the specified RingBuffer.
     */
    public int order() {
        return anOrder;
    }

    /**
     * The toString method takes in no parameters for input, and returns a string representation of
     * the Markov model
     *
     * @param None
     * @return String output
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < aText.length(); i += anOrder) {
            out.append(aText.substring(0, i)).append(this.freq(aText)).append("\n");
        }
        return out.toString();
    }

    /**
     * The freq method takes in a String representing the kgram for input, and returns an integer
     * representing all of the times the specified kgram appears in the main text of the Markov
     * Model object
     *
     * @param String kgram
     * @return Integer
     */
    public int freq(String kgram) {
        int val = kgram.length();
        int big = aText.length();
        int count = 0;
        for (int i = 0; i < (big - val + 1); i++) {
            if (aText.substring(i, i + val).equals(kgram)) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * The freq method takes in a String representing the kgram as well as a character, and returns
     * the number of times the character c follows the specified kgram in the main text of the
     * Markov Model object
     *
     * @param String kgram
     * @return Integer
     */
    public int freq(String kgram, char c) {
        String tester = kgram + c;
        String circular = aText + aText.substring(0, anOrder - 1);
        int val = tester.length();
        int big = circular.length();
        int count = 0;
        for (int i = 0; i < (big - val + 1); i++) {
            if (circular.substring(i, i + val).equals(tester)) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * The random method takes in a String representing the kgram, and returns a random character
     * that follows the specified kgram in the text, chosen with weight proportional to the number
     * of times that character follows the specified kgram in the text.
     *
     * @param String kgram
     * @return char
     */
    public char random(String kgram) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Integer[] stor = new Integer[26];
        Double[] prop = new Double[26];
        double r = StdRandom.uniform();
        double s = 0.0;
        int total = freq(kgram);
        for (int i = 0; i < 26; i++) {
            stor[i] = freq(kgram, alphabet[i]);
        }
        for (int i = 0; i < 26; i++) {
            double q = stor[i];
            prop[i] = q / total;
        }
        for (int i = 0; i < 26; i++) {
            s += prop[i];
            if (r < s) {
                return alphabet[i];
            }
        }
        return alphabet[alphabet.length - 1];
    }

    /**
     * The main method tests all of the functions in the MarkovModel class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        String text1 = "banana";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println("freq(\"an\", 'a')    = " + model1.freq("an", 'a'));
        StdOut.println("freq(\"na\", 'b')    = " + model1.freq("na", 'b'));
        StdOut.println("freq(\"na\", 'a')    = " + model1.freq("na", 'a'));
        StdOut.println("freq(\"na\")         = " + model1.freq("na"));
        StdOut.println();

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));

        for (int i = 0; i < 10; i++) {
            StdOut.println(model3.random("fish"));
        }
    }
}
