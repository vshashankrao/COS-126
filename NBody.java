/* *****************************************************************************
 *  Name:    Shashank Rao
 *  NetID:   svr38
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */
/*
 * loop variables used so far
 * i, e, r, w
 * */
public class NBody {
    public static void main(String[] args) {
        double simulationT = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double G = 6.67e-11;
        int n = StdIn.readInt(); // number of bodies
        double radius = StdIn.readDouble();
        double bound = Math.ceil(radius); // boundary value of window
        Double[] px = new Double[n];
        Double[] py = new Double[n];
        Double[] vx = new Double[n];
        Double[] vy = new Double[n];
        Double[] mass = new Double[n];
        String[] image = new String[n];
        Double[] ax = new Double[n];
        Double[] ay = new Double[n];
        Double[] fx = new Double[n];
        Double[] fy = new Double[n];
        /* This loop creates arrays for
        the x, y and y coordinates as
        well as the mass and image info
        stated in the text file*/
        for (int i = 0; i < n; i++) {
            px[i] = StdIn.readDouble();
            py[i] = StdIn.readDouble();
            vx[i] = StdIn.readDouble();
            vy[i] = StdIn.readDouble();
            mass[i] = StdIn.readDouble();
            image[i] = StdIn.readString();
        }
        for (int v = 0; v < n; v++) {
            fx[v] = 0.0;
            fy[v] = 0.0;
            ax[v] = 0.0;
            ay[v] = 0.0;
        }


        StdDraw.setXscale(-bound, bound);
        StdDraw.setYscale(-bound, bound);
        StdDraw.enableDoubleBuffering();
        StdAudio.play("2001.wav");


        double g = 0.0;
        // This is the time loop
        for (double t = 0; t <= simulationT; t += dt) {
            //g += dt;
            //System.out.println("t = " + g);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        double diffX = px[j] - px[i];
                        double diffY = py[j] - py[i];
                        double m1 = mass[i];
                        double m2 = mass[j];
                        double r = Math.sqrt((diffX * diffX) + (diffY * diffY));
                        double force = (G * m1 * m2) / r;
                        fx[i] += (diffX / r) * (force);
                        fy[i] += (diffY / r) * (force);
                    }
                }
            }
            for (int d = 0; d < n; d++) {
                ax[d] = fx[d] / mass[d];
                ay[d] = fy[d] / mass[d];
                vx[d] = vx[d] + (ax[d] * dt);
                vy[d] = vy[d] + (ay[d] * dt);
                px[d] = (dt * vx[d]);
                py[d] = (dt * vy[d]);
            }
            StdDraw.show();
            StdDraw.picture(0, 0, "starfield.jpg");
            for (int s = 0; s < n; s++) {
                StdDraw.picture(px[s], py[s], image[s]);
            }

        }



        /* This code block prints all of
         the information from the text
         file in a formatted pattern */
        System.out.println(n);
        System.out.println(radius);
        for (int e = 0; e < n; e++) {
            System.out.println(
                    String.format("%e", px[e]) + String.format("%15e", py[e]) + String
                            .format("%15e", vx[e]) + String.format("%15e", vy[e]) + String
                            .format("%15e", mass[e])
                            + String.format("%15s", image[e]));
        }


    }
}
