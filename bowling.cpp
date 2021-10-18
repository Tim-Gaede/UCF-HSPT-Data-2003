/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Bowling                                                 *
 * Problem Author:  Phil                                                    *
 * Solution Author: Adam                                                    *
 * Data Author:     Mike B                                                  *
 ***************************************************************************/
/***********************
 *
 * 2003 High School Competition
 *
 * Problem name: bowling
 *
 ***********************/

#include <stdio.h>
#include <stdlib.h>
#include "string.h"

#define NUM_FRAMES 10

int main(int argc, char* argv[]){

  int i, j, k, curFrame, curBowler;

  /* open the input file
  **/
  FILE *inputFile;
  inputFile = fopen("bowling.in", "r");

  /* lane       : needed for output
   * numBowlers : the number of bowlers in the current game
   **/
  int lane = 1;
  int numBowlers;

  /* compute the scores of each data set until numBowlers read in is zero
  **/
  fscanf(inputFile, "%d", &numBowlers);

  while(numBowlers != 0){

    /* bowlerNames  : needed for output
     * bowlerScores : the scores of each bowler, in the same order as bowlerNames
     * completeGame : an easy way of storing the game data
     *                  rows are the bowlers
     *                  columns are the balls thrown
     *                  two columns equal a frame, except for 10th frame which has
     *                  three balls
     **/
    char bowlerNames[6][31];
    int bowlerScores[6];
    int completeGame[6][10][2];
    int frame10Ball3[6];

    for(i = 0; i < 6; i++){
      bowlerScores[i] = 0;
      frame10Ball3[i] = 0;
      for(j = 0; j < 10; j++)
        for (k = 0; k < 2; k++)
          completeGame[i][j][k] = 0;
    }

    /* read in the bowlers' names
    **/
    for(i = 0; i < numBowlers; i++)
      fscanf(inputFile, "%s", bowlerNames[i]);

    /* fill completeGame matrix with input
    **/
    for(curFrame = 0; curFrame < NUM_FRAMES; curFrame++){

      for(curBowler = 0; curBowler < numBowlers; curBowler++){

        /* curFrame * 2 is used throughout this program because two balls
         *   are used per frame
         **/
        fscanf(inputFile, "%d", &completeGame[curBowler][curFrame][0]);

        /* Actually 10th frame, remember arrays are zero-indexed
         * Handle the 10th frame differently, because it could use three balls
         **/
        if(curFrame == 9){

          fscanf(inputFile, "%d", &completeGame[curBowler][curFrame][1]);

          if(completeGame[curBowler][curFrame][0] + completeGame[curBowler][curFrame][1] >= 10){
            fscanf(inputFile, "%d", &frame10Ball3[curBowler]);
          }

        }else{

          if(completeGame[curBowler][curFrame][0] < 10)
            fscanf(inputFile, "%d", &completeGame[curBowler][curFrame][1]);
          else
            completeGame[curBowler][curFrame][1] = 0;

        }

      }//end for curBowler

    }//end for curFrame

    /* calculate the scores of each bowler one frame at a time
    **/
    for(curFrame = 0; curFrame < NUM_FRAMES; curFrame++){

      for(curBowler = 0; curBowler < numBowlers; curBowler++){

        int firstBall, secondBall;

        /* obtain the values of the players first and second balls thrown in the frame
        **/
        firstBall = completeGame[curBowler][curFrame][0];
        secondBall = completeGame[curBowler][curFrame][1];

        /* calculate curBowler's score if he got a strike, or if it's 10th frame
        **/
        if(curFrame == 8){ // Actually 9th frame

          /* if curBowler threw a strike in the 9th frame, then add
           *   10 to the first and second ball of the 10th frame
           **/
          if(firstBall == 10) {
            bowlerScores[curBowler] += 10 + completeGame[curBowler][curFrame + 1][0] + completeGame[curBowler][curFrame + 1][1];
          }

        }else if(curFrame == 9){ // Actually 10th frame

          /* always add the first, second, and third balls to the 
           *   curBowler's score in the 10th frame
           **/
          bowlerScores[curBowler] += firstBall + secondBall + frame10Ball3[curBowler];

          /* curFrame < 8 */
        }else{

          /* if curFrame is a strike
          **/
          if(firstBall == 10){

            /* if curFrame + 1 is strike, then add 20 + first ball in curFrame + 2
             * else add 10 + first and second balls in curFrame + 1
             **/
            if(completeGame[curBowler][curFrame + 1][0] == 10)
              bowlerScores[curBowler] += 20 + completeGame[curBowler][curFrame + 2][0];
            else
              bowlerScores[curBowler] += 10 + completeGame[curBowler][curFrame + 1][0] + completeGame[curBowler][curFrame + 1][1];

          }

        }

        /* if game is not in 10th frame
        **/
        if(curFrame < 9){

          /* if curFrame is a spare, then add 10 + first ball in curFrame + 1
           * else add firstBall + secondBall to score
           **/
          if(firstBall + secondBall == 10 && firstBall != 10)
            bowlerScores[curBowler] += 10 + completeGame[curBowler][curFrame + 1][0];
          else if(firstBall + secondBall < 10)
            bowlerScores[curBowler] += firstBall + secondBall;

        }
      }//end for curBowler

    }//end for curFrame

    /* sort the scores in descending order
    **/
    for(int index = 1; index < numBowlers; index++){

      char keyName[31];

      int keyScore   = bowlerScores[index];
      strcpy(keyName, bowlerNames[index]);
      int position   = index;

      /* shift small values to right
      **/
      while(position > 0 && bowlerScores[position-1] < keyScore){
        bowlerScores[position] = bowlerScores[position-1];
        strcpy(bowlerNames[position], bowlerNames[position-1]);
        position--;
      }

      bowlerScores[position] = keyScore;
      strcpy(bowlerNames[position], keyName);

    }


    /* output the data to the screen
    **/
    printf("Lane #%d:\n", lane);
    for(i = 0; i < numBowlers; i++)
      printf(" %s %d\n", bowlerNames[i], bowlerScores[i]);
    printf("\n");

    /* increment the lane
    **/
    lane++;

    fscanf(inputFile, "%d", &numBowlers);
  }

  fclose(inputFile);
  return 0;
}

