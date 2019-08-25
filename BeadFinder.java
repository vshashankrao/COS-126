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

import java.awt.Color;


public class BeadFinder {
    // Instance variable aHeight which represents the height of the specified picture
    private int aHeight;
    // Instance variable aWidth which represents the width of the specified picture
    private int aWidth;
    // Instance LinkedList values which creates an empty Blob Linked List to store all of the discovered blobs
    private Queue<Blob> values;
    // Instance array which creates an empty boolean array to find the blobs in the BeadFinder object
    private boolean[][] blobFinder;

    /**
     * The BeadFinder constructor takes in a picture as input and double as input, and creates a
     * BeadFinder object and finds all blobs in the specified picture using luminance threshold tau
     *
     * @param Picture picture, double tau
     * @return A BeadFinder object with a specified picture and tau.
     */
    public BeadFinder(Picture picture, double tau) {
        aHeight = picture.height();
        aWidth = picture.width();
        values = new Queue<Blob>();
        blobFinder = new boolean[aHeight][aWidth];

        for (int r = 0; r < aHeight; r++) {
            for (int c = 0; c < aWidth; c++) {
                Color input = picture.get(c, r);
                if (Luminance.intensity(input) >= tau) {
                    blobFinder[r][c] = true;
                }
                else {
                    blobFinder[r][c] = false;
                }
            }
        }

        for (int i = 0; i < aHeight; i++) {
            for (int j = 0; j < aWidth; j++) {
                if (blobFinder[i][j]) {
                    Blob newBlob = new Blob();
                    dfs(newBlob, i, j);
                    values.enqueue(newBlob);
                }
            }
        }
    }

    /**
     * The getBeads method takes in an integer as input, and returns all beads (blobs with >= min
     * pixels)
     *
     * @param Integer min
     * @return A Blob[] array with blobs >= min pixels.
     */
    public Blob[] getBeads(int min) {
        int a = 0;
        int b = 0;
        for (Blob each : values) {
            if (each.mass() >= min) {
                a += 1;
            }
        }
        Blob[] output = new Blob[a];
        for (Blob each : values) {
            if (each.mass() >= min) {
                output[b] = each;
                b += 1;
            }
        }
        return output;
    }

    /**
     * The dfs method takes in two integers as input along with a Blob, and returns nothing. This
     * method runs a recursive depth first search algorithm and updates the inputted Blob object
     * with the new information
     *
     * @param Blob visited, Integer x, Integer y
     * @return None.
     */
    private void dfs(Blob visited, int x, int y) {
        if ((x >= aHeight || y >= aWidth) || !blobFinder[x][y]) {
            return;
        }
        visited.add(x, y);
        // visited[x][y] = true;
        blobFinder[x][y] = false;

        dfs(visited, x - 1, y);
        dfs(visited, x + 1, y);
        dfs(visited, x, y - 1);
        dfs(visited, x, y + 1);
    }

    /**
     * The main method tests all of the functions in the BeadFinder class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        int testMass = Integer.parseInt(args[0]);
        double testTau = Double.parseDouble(args[1]);
        Picture inputPic = new Picture(args[2]);
        BeadFinder output = new BeadFinder(inputPic, testTau);
        for (int i = 0; i < output.getBeads(testMass).length; i++) {
            StdOut.println(output.getBeads(testMass)[i]);
        }
    }
}

