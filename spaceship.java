/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Spaceship                                               *
 * Problem Author:  Mike B                                                  *
 * Solution Author: Jason                                                   *
 * Data Author:     Nick                                                    *
 ***************************************************************************/
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;

class spaceship
{
  public spaceship()
  {
  }

  public void solve() throws java.io.IOException
  {
    String          line;
    StringTokenizer tokens;

    // Open the input file for reading
    FileInputStream fp = new FileInputStream("spaceship.in");
    BufferedReader reader = new BufferedReader(new InputStreamReader(fp));
        
    // Read the line containing the number of data sets
    line = reader.readLine();
     
    // Get the number of data sets from the input (trim the string
    // to get rid of any stray spaces)
    int  numDataSets = Integer.parseInt(line.trim());
    
    // Loop over each data set
    for (int i = 0; i < numDataSets; i++)
    {
      // Read the line with the coordinates of your spaceship and the 
      // number of radars in the area
      line = reader.readLine();

      // Parse the information from the input
      tokens = new StringTokenizer(line);
      int shipX = Integer.parseInt(tokens.nextToken());
      int shipY = Integer.parseInt(tokens.nextToken());
      int shipZ = Integer.parseInt(tokens.nextToken());
      int numRadars = Integer.parseInt(tokens.nextToken());
    
      // Initialize the number of radars detecting you to zero
      int numDetects = 0;
    
      // Loop over each radar
      for (int j = 0; j < numRadars; j++)
      {
        int radarX, radarY, radarZ, radarRadius;
    
        // Read line containing the position and radius of the 
        // next radar
        line = reader.readLine();

        // Parse the radar information
        tokens = new StringTokenizer(line);
        radarX = Integer.parseInt(tokens.nextToken());
        radarY = Integer.parseInt(tokens.nextToken());
        radarZ = Integer.parseInt(tokens.nextToken());
        radarRadius = Integer.parseInt(tokens.nextToken());
    
        // Calculate the distance from the radar to your ship
        // Recall that the distance formula is:
        //           ____________________________________
        //  dist = \/ (x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2
        //
        int xDiff = radarX - shipX;  // (x2 - x1)
        int yDiff = radarY - shipY;  // (y2 - y1)
        int zDiff = radarZ - shipZ;  // (z2 - z1)
        double distance = 
          Math.sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);
    
        // If the distance is less than or equal to the radar's radius
        // we've been spotted!
        if (distance <= radarRadius)
          numDetects++;
      }
    
      // Print the required output
      System.out.println("You will be picked up by " + numDetects +
                         " radars.");
    }
  }

  public static void main(String args[]) throws java.io.IOException
  {
    // Create an instance of the spaceship class
    spaceship solution = new spaceship();

    // Solve the problem
    solution.solve();
  }
};
