/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Reloaded                                                *
 * Problem Author:  Ben                                                     *
 * Solution Author: Greg                                                    *
 * Data Author:     Adam                                                    *
 ***************************************************************************/
/*
 *		The Array Reloaded
 *
 *		Written By: Ben
 *
 *		Solved  By: Greg
 *
 */

import java.io.*;
import java.util.*;

public class reloaded
{
	
  static class backup implements Comparable
  {
    public int row;	  // the row (x) position of this backup
    public int col;	  // the col (y) position of this backup
    public int height;	  // the height (i) of this backup
    public int width;	  // the width (j) of this backup	
    public int time;	  // the time (t) of this backup
    public char[][] data;
		
    public backup(int x,int y,int i,int j,int t)
    {
      // Set members
      row = x;
      col = y;
      width = j;
      height = i;
      time = t;
			
      // Create storage for the 
      data = new char[height][width];
    }
		
    public int compareTo(Object orhs)
    {
      // Convert basic object to compatible object type
      backup rhs = (backup) orhs;
			
			
      if (rhs.time < time) return 1;		// this is greater than rhs
      else if (rhs.time == time) return 0;// this is equal to rhs
      else return -1;						// this is less than rhs
    }
	
  }
	
  public static void main(String[] argv)
  {
    BufferedReader stdin;	// File reader for input		
    String inp;				// One line of input
    StringTokenizer stk;	// Input Tokenizer
		
    int n,m,k,x,y,i,j,t; 	// Input vars specified in statement
    backup[] backups;		// array of backups
    char[][] tehArray;		// The Array!!!! Whoa! (c)
		
    int iter=1;				// current set iteration (Set [iter]:)
    int cur,r,c;			// loop indexes
		
		
    // Open the input file.
    try { 
      stdin = new BufferedReader(
                                 new FileReader("reloaded.in")
                                 );
    } catch (IOException e)
    { 
      System.out.println("You must have taken the wrong pill...");
      System.out.println("The Array doesn't exist!!!! (input).");
      return;
    }
		
    // Get a line of input
    try { inp = stdin.readLine().trim(); } catch (IOException e) { return; }
		
    // The terminal case is 0 0 0
    while (!inp.equals("0 0 0"))
    {
      // Tokenize and retrieve variables from input
      stk = new StringTokenizer(inp, " ");
      n = Integer.parseInt(stk.nextToken());
      m = Integer.parseInt(stk.nextToken());
      k = Integer.parseInt(stk.nextToken());
			
      tehArray = new char[n][m];		// Whoa!  The Array has been loaded!
      // (with zeros at instantiation)

      for (r = 0; r < n; r++)
        for (c = 0; c < m; c++)
        {
          tehArray[r][c] = '0';		// initialize to character '0',
          // not 0 (NULL).
        }
			
      backups = new backup[k];		// Create backup array.
			
      // Loop through the number of backups.											
      for (cur = 0; cur < k; cur++)
      {
        // Get a line of input.
        try { inp = stdin.readLine().trim(); }
        catch (IOException e) { return; }
				
        // Tokenize and retrieve variables from input
        stk = new StringTokenizer(inp, " ");				
        x = Integer.parseInt(stk.nextToken());
        y = Integer.parseInt(stk.nextToken());
        i = Integer.parseInt(stk.nextToken());
        j = Integer.parseInt(stk.nextToken());
        t = Integer.parseInt(stk.nextToken());
				
        backups[cur] = new backup(x,y,i,j,t);
				
        for (r = 0; r < i; r++)
        {
          // Get a line of input.
          try { inp = stdin.readLine().trim(); }
          catch (IOException e) { return; }
					
          // Tokenize input
          stk = new StringTokenizer(inp, " ");
					
          for (c = 0; c < j; c++)
          {
            // input is guaranteed to be one character.
            backups[cur].data[r][c] = stk.nextToken().charAt(0);
          }
        }
      }
			
      // This will sort the backups in time order, using their
      // compareTo method!  Easy!
      Arrays.sort(backups);
			
      for (cur = 0; cur < k; cur++)
      {
        // Apply the backup to the Array!!
        // Since we're already sorted in time order,
        // old data will be overwritten, if there is
        // anything newer.  The last data written
        // will be the freshest (and correct) data.
        apply(tehArray, backups[cur]);
      }
			
      // Output time!!
      System.out.println("Set " + iter + ":");
      for (r = 0; r < n && m != 0; r++)
      {
        for (c = 0; c < m; c++)
        {
          System.out.print(tehArray[r][c] + " ");
        }
        System.out.println();
      }
      System.out.println();
			
      iter++;		// increment data set number

			// Prime input for next case.
      try { inp = stdin.readLine().trim(); }
      catch (IOException e) { return; }
			
    } // end of main while loop
	
  } // end of main()

  // Apply data in a backup to a 2D array.
  static void apply(char[][] target, backup source)
  {
    int r,c;
		
    for (r = source.row; r < source.row+source.height; r++)
      for (c = source.col; c < source.col+source.width ; c++)
      {
        target[r][c] = source.data[r-source.row][c-source.col];
      }
  }

} // end of class
