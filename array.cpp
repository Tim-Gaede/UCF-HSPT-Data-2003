/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Array                                                   *
 * Problem Author:  Ben                                                     *
 * Solution Author: Mike W                                                  *
 * Data Author:     Greg                                                    *
 ***************************************************************************/
#include<stdio.h>
#include<stdlib.h>

int main()
{
  FILE * in;
  int number;
  int i;
  int temp;
  char buf[30];

  in = fopen("array.in","r");

  //Process data and place a new line between each case.
  while(1)
  {
    //Scans in the number of elements in the array
    fscanf(in,"%d\n",&number);
    //if it's zero return false, because it's the ending case
    if(number==0) return 0;

    //Loop through the given array
    for(i=0;i<number;i++)
    {
      //Scan in the number
      fscanf(in,"%d",&temp);

      //If it's one print it out, but keep reading to finish line.
      if(temp==1)
      {
        printf("The Unity is at position %d.\n",i);
      }
    }

    printf("\n");
  }

  fclose(in);

  return 0;
}
