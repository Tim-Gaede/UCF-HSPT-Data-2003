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

/* imported for BufferedReader and FileReader classes
 **/
import java.io.*;

/* imported for StringTokenizer and Arrays classes
 **/
import java.util.*;

public class bowling{

  /* declaring the main function to throw and Exception eliminates
   *   the need for redundant try-catch clauses
   **/
  public static void main(String[] args) throws Exception{

    /* declare a new FileReader with the input file bowling.in
     * then declare 
     **/
    BufferedReader br;
    br = new BufferedReader(new FileReader("bowling.in"));

    /* NUM_FRAMES : total number of frames in a bowling game
     * lane       : needed for output
     * numBowlers : the number of bowlers in the current game
     **/
    int NUM_FRAMES = 10;
    int lane = 1;
    int numBowlers;

    /* game         : splits the game input into space-delimited strings
     * bowlerNames  : needed for output
     * bowlerScores : the scores of each bowler, in the same order as bowlerNames
     * completeGame : an easy way of storing the game data
     *                  rows are the bowlers
     *                  columns are the balls thrown
     *                  two columns equal a frame, except for 10th frame which has
     *                  three balls
     **/
    StringTokenizer game;
    String[] bowlerNames;
    int[] bowlerScores;
    int[][][] completeGame;
    int frame10Ball3[];
                

    /* compute the scores of each data set until numBowlers read in is zero
    **/
    while((numBowlers = Integer.parseInt(br.readLine().trim())) != 0){

      /* create the arrays of the appropriate size for each game
      **/
      bowlerNames = new String[numBowlers];
      bowlerScores = new int[numBowlers];

      /* read in the bowlers' names
      **/
      for(int i = 0; i < numBowlers; i++)
        bowlerNames[i] = new String(br.readLine());

      /* initialize bowlerScores to zero
      **/
      Arrays.fill(bowlerScores, 0);

      /* read in the game array
      **/
      game = new StringTokenizer(br.readLine());

      /* create 2D array of bowlers and each ball they threw
       * 21 is the maximum number of balls that could be thrown by
       *   a player during a game
       **/
      completeGame = new int[numBowlers][10][2];
      frame10Ball3 = new int[numBowlers];

      /* fill completeGame matrix with input
      **/
      for(int curFrame = 0; curFrame < NUM_FRAMES; curFrame++){

        for(int curBowler = 0; curBowler < numBowlers; curBowler++){

          /* curFrame * 2 is used throughout this program because two balls
           *   are used per frame
           **/
          completeGame[curBowler][curFrame][0] = Integer.parseInt(game.nextToken());

          /* Actually 10th frame, remember arrays are zero-indexed
           * Handle the 10th frame differently, because it could use three balls
           **/
          if(curFrame == 9){

            completeGame[curBowler][curFrame][1] = Integer.parseInt(game.nextToken());

            if(completeGame[curBowler][curFrame][0] + completeGame[curBowler][curFrame][1] >= 10){
              frame10Ball3[curBowler] = Integer.parseInt(game.nextToken());
            }

          }else{

            if(completeGame[curBowler][curFrame][0] < 10)
              completeGame[curBowler][curFrame][1] = Integer.parseInt(game.nextToken());
            else
              completeGame[curBowler][curFrame][1] = 0;

          }

        }//end for curBowler

      }//end for curFrame


      /* calculate the scores of each bowler one frame at a time
      **/
      for(int curFrame = 0; curFrame < NUM_FRAMES; curFrame++){

        for(int curBowler = 0; curBowler < numBowlers; curBowler++){

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
            if(firstBall == 10)
              bowlerScores[curBowler] += 10 + completeGame[curBowler][curFrame + 1][0] + completeGame[curBowler][curFrame + 1][1];

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
      sortBowlers(bowlerNames, bowlerScores);

      /* output the data to the screen
      **/
      System.out.println("Lane #" + lane + ":");
      for(int i = 0; i < numBowlers; i++)
        System.out.println(" " + bowlerNames[i] + " " + bowlerScores[i]);
      System.out.println();

      /* increment the lane
      **/
      lane++;

    }

  }

  /* sort the bowlers by their scores using Insertion Sort
   * remember to also swap bowlerNames when bowlerScores gets swapped
   **/
  private static void sortBowlers(String[] bowlerNames, int[] bowlerScores){

    for(int index = 1; index < bowlerNames.length; index++){

      int keyScore = bowlerScores[index];
      String keyName  = bowlerNames[index];
      int position = index;

      /* shift small values to right
      **/
      while(position > 0 && bowlerScores[position-1] < keyScore){
        bowlerScores[position] = bowlerScores[position-1];
        bowlerNames[position]  = bowlerNames[position-1];
        position--;
      }

      bowlerScores[position] = keyScore;
      bowlerNames[position] = keyName;

    }

  }

}	
