/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Raiders                                                 *
 * Problem Author:  Glenn                                                   *
 * Solution Author: Mike B                                                  *
 * Data Author:     Richard                                                 *
 ***************************************************************************/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.text.DecimalFormat;


/* This problem translates into the following:

                N
              |             |
              |             |
              |             |
W   (0, 0, 0) Origin        Window (0, 50, 100)    E
              |             |
              |---- 100 ----|
              |             |

                S

Given two points in 3D space (the window and the tip of the staff), determine where the line crosses the plane Z = 0.

X - Feet north of origin
Y - Feet upward from origin
Z - Feel east from origin

*/

class raiders {
  public static void main(String args []) {
    try {
      BufferedReader inputBuf = new BufferedReader(new FileReader("raiders.in"));
      DecimalFormat fmt = new DecimalFormat( "0.00" );
      String nextLine = inputBuf.readLine();
      StringTokenizer input = new StringTokenizer(nextLine);

      // (x1, y1, z1) is the window
      double y1 = 50;
      double z1 = 100;
      double x1 = 0;

      // From origin
      double pacesEast, pacesNorth, feetUpward;  


      while (nextLine != null) {
        pacesEast = Double.parseDouble(input.nextToken());
        pacesNorth = Double.parseDouble(input.nextToken());
        feetUpward = Double.parseDouble(input.nextToken());

        // (x2, y2, z2) is the tip of the staff
        double x2, y2, z2;
        x2 = pacesNorth * 4;
        y2 = feetUpward;
        z2 = pacesEast * 4;

        /* I will find the answer using the following method:
           Generate a parametric equation in the form
           <line> = (x1 + t * dx, y1 + t * dy, z1 + t * dz)

           I want to solve for t when z = 0.  Since we don't really care about the value of t in terms of the answer, I
           can define it as anything.  I choose to define t = 0 as the window and t = 1 as the tip of the staff.
        */
    
        // Solve for dx, dy, and dz.
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;

        /* Since I want to know the value of t when z = 0, I can say:
           z1 + t * dz = 0
           t * dz = -z1
           t = -z1 / dz
        */
        double t = (-z1) / dz;

        // Solve for x and y
        double x = x1 + dx * t;
        double y = y1 + dy * t;

        /* The answer has to be in terms of x and y.  Round
           the values to two decimal places by doing the
           following:

           Multiply it by 100, add .5 and floor it.  This will
           give you a rounded value time 100.  To print it
           out, print all the the last two numbers (integer
           division by 100 should handle that), a decimal, and
           the last two numbers (integer mod 100 should handle
           that).
        */

        System.out.println( "The last arc is at ("
                            + fmt.format( Math.floor( ( x * 100.0 ) + 0.5 ) / 100.0 )
                            + ", "
                            + fmt.format( Math.floor( ( y * 100.0 ) + 0.5 ) / 100.0 )
                            + ")." );

        // Read the next line
        nextLine = inputBuf.readLine();
        if (nextLine != null)
          input = new StringTokenizer(nextLine);
      }
    }
    catch (Exception e) {System.out.println(e);}
  }
}
