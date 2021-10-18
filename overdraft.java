/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Overdraft                                               *
 * Problem Author:  Greg                                                    *
 * Solution Author: Richard                                                 *
 * Data Author:     Glenn                                                   *
 ***************************************************************************/
// import declarations of the input-output classes
import java.io.*;

class overdraft
{
  // declares our input stream member
  static BufferedReader in;
  
  // a little helper method to read the next line
  // and convert it to an int
  static int readInt()
  {
    // check for exceptions
    try
    {
      // read the line
      String line = in.readLine();
      // trim the line and parse the integer
      return Integer.parseInt( line.trim() );
    }
    catch( Exception e )
    {
      return -1;
    }
  }

  // this is where our program begins
  public static void main( String[] args )
  {
    // open the input file
    // we use a BufferedReader so we have access to the readLine method
    try
    {
      in = new BufferedReader( new FileReader( "overdraft.in" ) );
    }
    catch( Exception e )
    {
      System.err.println( "input file missing!" );
    }

    // used to store the initial checking account balance,
    // savings account balance, and number of transactions
    int checking, savings, count;

    // read the beginning of the first case
    checking = readInt();
    savings = readInt();
    count = readInt();
    // continue looping while the variables aren't all zero
    while( checking != 0 || savings != 0 || count != 0 )
    {
      // keeps track of whether there has been an overdraft or not
      boolean over = false;

      for( int i = 0; i < count; i++ )
      {
        int transaction = readInt();
        if( transaction > checking )
        {
          over = true;
          // add the leftovers in checking to savings,
          // then subtract the transaction from that sum
          savings = savings + checking - transaction;
          // nothing left in checking
          checking = 0;
        }
        else
        {
          // simply deduct the transaction from checking
          checking -= transaction;
        }
      }

      // produce output
      System.out.println( checking );
      System.out.println( savings );
      if( over )
        System.out.println( "OVERDRAFT" );
      else
        System.out.println( "CLEAR" );
      // print the blank line
      System.out.println();

      // read the beginning of the next case
      checking = readInt();
      savings = readInt();
      count = readInt();
    }

    try
    {
      in.close();
    }
    catch( Exception e )
    {
    }
  } // main
} // class overdraft
