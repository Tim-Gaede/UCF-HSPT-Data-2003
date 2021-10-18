/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Darts                                                   *
 * Problem Author:  Glenn                                                   *
 * Solution Author: Richard                                                 *
 * Data Author:     Jason                                                   *
 ***************************************************************************/
import java.io.*;
import java.util.*;

class darts
{

  // table of scores for the wedges, in counter-clockwise order, starting
  // from the +x axis
  static final int wedge_scores[] = 
  { 6, 13, 4, 18, 1, 20, 5, 12, 9, 14, 11, 8, 16,
    7, 19, 3, 17, 2, 15, 10 };

  static final double two_pi = 2.0 * Math.PI;
  static final double wedge_width = 2.0 * Math.PI / 20;

  // canonicalize the angle to be between 0 and 2pi
  static double angle_fix( double angle )
  {
    while( angle > two_pi )
      angle -= two_pi;
    while( angle < 0.0 )
      angle += two_pi;
    return angle;
  }

  // convert the angle to an index into the score table above
  static int angle_to_idx( double angle )
  {
    double ratio = angle_fix( angle + wedge_width / 2.0 ) / two_pi;
    return (int)( ratio * 20 );
  }

  // returns the hypotenuse of a right triangle with sides x and y
  static double hypot( double x, double y )
  {
    return Math.sqrt( x * x + y * y );
  }

  // returns true if the point is inside the dartboard
  static boolean is_valid( double x, double y )
  {
    return hypot( x, y ) < 170.0;
  }

  // returns true if the point is in the bullseye
  static boolean is_bullseye( double x, double y )
  {
    return hypot( x, y ) < 7.0;
  }

  // returns true if the point is in the "outer center" circle
  static boolean is_outercenter( double x, double y )
  {
    return hypot( x, y ) < 16.0 && !is_bullseye( x, y );
  }

  // returns true if the score should be tripled, assuing it
  // is *not* in the bullseye or "outer center"
  static boolean is_triple( double x, double y )
  {
    return 99.0 < hypot( x, y ) && hypot( x, y ) < 107.0;
  }

  // returns true if the score should be doubled, assuing it
  // is *not* in the bullseye or "outer center"
  static boolean is_double( double x, double y )
  {
    return 162.0 < hypot( x, y ) && hypot( x, y ) < 170.0;
  }

  // returns the score that the point earns
  static int score( double x, double y )
  {
    if( is_bullseye( x, y ) )
      return 50;
    else if( is_outercenter( x, y ) )
      return 25;
    else if( is_valid( x, y ) )
    {
      int base = wedge_scores[ angle_to_idx( Math.atan2( y, x ) ) ];
      if( is_triple( x, y ) )
        return base * 3;
      else if( is_double( x, y ) )
        return base * 2;
      else
        return base;
    }
    else
      return 0;
  }

  public static void main( String[] args )
    throws Exception
  {
    // create a buffered reader for its readLine() method
    BufferedReader in = new BufferedReader( new FileReader( "darts.in" ) );

    // the number of cases
    int count = Integer.parseInt( in.readLine().trim() );

    for( int i = 0; i < count; ++i )
    {
      // read a line
      String line = in.readLine();
      StringTokenizer tok = new StringTokenizer( line );

      // parse the point out of the line
      double x, y;
      x = Double.parseDouble( tok.nextToken() );
      y = Double.parseDouble( tok.nextToken() );

      // produce output
      System.out.println( score( x, y ) );
    }
  } // main
} // class darts
