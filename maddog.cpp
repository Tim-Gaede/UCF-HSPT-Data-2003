/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Maddog                                                  *
 * Problem Author:  Greg                                                    *
 * Solution Author: Ben                                                     *
 * Data Author:     Glenn                                                   *
 ***************************************************************************/
#include <stdio.h>
#include <math.h>

int main()
{
  FILE* fin;
  int coils;
  int diameter;
  double distance;

  //Open the input file
  fin = fopen("maddog.in","r");

  //Loop indefinitely
  while(1)
  {
    //Read in all the information
    fscanf(fin,"%d %d %lf",&coils,&diameter,&distance);
		
    //We are done if the number of coils is zero
    if(coils == 0)
      return 0;
		
    //If you are too close, you get eaten!
    double rope = coils * diameter * 3.14;
    if(rope < distance && !( fabs( rope - distance ) < 1e-6 ) )
      printf("MAD DOG GOES HUNGRY!!!\n");
    //If you are far enough away, you escape
    else
      printf("MAD DOG FOOD!!!\n");
  }

  return 0;
}
