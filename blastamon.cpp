/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Blastamon                                               *
 * Problem Author:  Jason                                                   *
 * Solution Author: Mike W                                                  *
 * Data Author:     Nick                                                    *
 ***************************************************************************/
#include<stdio.h>
#include<string.h>
#include<stdlib.h>

FILE * in;
char values[1001][21];

void initialize()
{
  int i;

  for(i=0;i<1001;i++)
  {
    //in c '\0' is the end of string marker
    //By setting the first index of the string
    // to '\0' I'm officialy setting the string to ""
    values[i][0]='\0';
  }
}

void process(int numberOfEntries)
{
  int i;
  int temp;
  char tempstr[21];

  initialize();

  for(i=0;i<numberOfEntries;i++)
  {

    //Store in this particular entry
    fscanf(in,"%s\n",tempstr);

    fscanf(in,"%d\n",&temp);


    //Copy it to its place in the list
    strcpy(values[temp],tempstr);
  }


}

void printList()
{
  int i;
  for(i=0;i<1001;i++)
  {
    //Print anything that isn't ""
    if(values[i][0]!='\0')
      printf("%s\n",values[i]);
  }
}

int running()
{
  int numberOfEntries;

  //Get the number of Entries for this case
  fscanf(in,"%d\n",&numberOfEntries);

  if(numberOfEntries==0) return 0;


  //Process them and then print them
  process(numberOfEntries);

  printList();

  return 1;
}

int main()
{
  in = fopen("blastamon.in","r");

  //Keep processing the input till specific case is found
  while(running()) printf("\n");	

  fclose(in);

  return 0;
}
