/* *****************************************************************************
 *  Name: Shashank Rao
 *  NetID: svr38
 *  Precept: 004
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description:  This is a template file for RingBuffer.java. It lists the
 *                constructors and methods you need, along with descriptions
 *                of what they're supposed to do.
 *
 *                Note: it won't compile until you fill in the constructors
 *                and methods (or at least commment out the ones whose return
 *                type is non-void).
 *
 **************************************************************************** */

import java.util.ArrayList;

public class RingBuffer {
    // Instance variable aCapacity which takes the specified capacity for the newly constructed object.
    private int aCapacity;
    // Instance variable buffList which creates an ArrayList based on specified capacity.
    private ArrayList<Double> buffList;


    /**
     * The RingBuffer constructor takes in an int as input, and creates a RingBuffer object.
     *
     * @param Integer capacity to be stored.
     * @return A RingBuffer object with a specified capacity.
     */
    public RingBuffer(int capacity) {
        aCapacity = capacity;
        buffList = new ArrayList<Double>(aCapacity);
    }

    /**
     * The capacity() method takes in no inputs simply returns the capacity of the RingBuffer
     * object.
     *
     * @param None
     * @return An int which returns the capacity of the specified RingBuffer.
     */
    public int capacity() {
        return aCapacity;
    }

    /**
     * The size() method takes in no inputs and simply returns the number of items in the RingBuffer
     * object.
     *
     * @param None
     * @return An int which returns the size of the specified RingBuffer.
     */
    public int size() {
        return buffList.size();
    }

    /**
     * The isEmpty() method takes in no inputs and simply checks whether this ring buffer is empty
     * (size equals zero)
     *
     * @param None
     * @return A boolean based on whether or not the buffer is empty .
     */
    public boolean isEmpty() {

        if (buffList.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * The isFull() method takes in no inputs and simply checks whether this ring buffer is full
     * (size equals capacity)
     *
     * @param None
     * @return A boolean based on whether or not the buffer is full .
     */
    public boolean isFull() {

        if (buffList.size() == aCapacity) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * The enqueue() method adds item x to the end of this ring buffer. A Runtime exception is
     * thrown if the RingBuffer is full.
     *
     * @param Double x which is the item added to the buffer
     * @return None.
     */
    public void enqueue(double x) {
        if (buffList.size() == aCapacity) {
            throw new RuntimeException("The RingBuffer is full");
        }
        buffList.add(x);
    }

    /**
     * The dequeue() method deletes and returns the item at the front of this ring buffer. A Runtime
     * exception is thrown if the RingBuffer is empty.
     *
     * @param None
     * @return Double x which returns the value at the front of the ring buffer.
     */
    public double dequeue() {
        if (buffList.size() == 0) {
            throw new RuntimeException("The RingBuffer is empty");
        }
        double tempVal = buffList.get(0);
        buffList.remove(0);
        return tempVal;

    }

    /**
     * The peek() method returns the item at the front of this ring buffer. A Runtime exception is
     * thrown if the RingBuffer is empty.
     *
     * @param None
     * @return Double x which returns the value at the front of the ring buffer.
     */
    //
    public double peek() {
        if (buffList.size() == 0) {
            throw new RuntimeException("The RingBuffer is empty");
        }
        return buffList.get(0);
    }

    /**
     * The main method tests all of the functions in the RingBuffer class.
     *
     * @param String array for all of the arguments.
     * @return none.
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.println(buffer.peek());
    }

}
