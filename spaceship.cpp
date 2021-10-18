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
#include <stdio.h>
#include <math.h>

int main(void)
{
  // Open the input file for reading
  FILE *fp = fopen("spaceship.in", "r");

  // Read the number of data sets
  int  numDataSets;
  fscanf(fp, "%d", &numDataSets);

  // Loop over each data set
  for (int i = 0; i < numDataSets; i++)
  {
    // Read the coordinates of your spaceship, and the number of
    // radars in the area
    int  shipX, shipY, shipZ;
    int  numRadars;
    fscanf(fp, "%d %d %d %d\n", &shipX, &shipY, &shipZ, &numRadars);

    // Initialize the number of radars detecting you to zero
    int numDetects = 0;

    // Loop over each radar
    for (int j = 0; j < numRadars; j++)
    {
      int radarX, radarY, radarZ, radarRadius;

      // Read the position and radius of the radar
      fscanf(fp, "%d %d %d %d\n", &radarX, &radarY, &radarZ,
             &radarRadius);

      // Calculate the distance from the radar to your ship
      // Recall that the distance formula is:
      //           ____________________________________
      //  dist = \/ (x2 - x1)^2 + (y2-y1)^2 + (z2-z1)^2
      //
      int xDiff = radarX - shipX;  // (x2 - x1)
      int yDiff = radarY - shipY;  // (y2 - y1)
      int zDiff = radarZ - shipZ;  // (z2 - z1)
      double distance = sqrt(xDiff*xDiff + yDiff*yDiff + zDiff*zDiff);

      // If the distance is less than or equal to the radar's radius
      // we've been spotted!
      if (distance <= radarRadius)
        numDetects++;
    }

    // Print the required output
    printf("You will be picked up by %d radars.\n", numDetects);
  }

  return 0;
}
