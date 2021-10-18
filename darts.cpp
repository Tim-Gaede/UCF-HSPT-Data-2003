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
#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;

// table of scores for the wedges, in counter-clockwise order, starting
// from the +x axis
int wedge_scores[] = 
{ 6, 13, 4, 18, 1, 20, 5, 12, 9, 14, 11, 8, 16,
  7, 19, 3, 17, 2, 15, 10 };

#ifndef M_PI
# define M_PI 3.14159265358979323846264
#endif // M_PI

const double two_pi = 2.0 * M_PI;
const double wedge_width = 2.0 * M_PI / 20;

// canonicalize the angle to be between 0 and 2pi
double angle_fix( double angle )
{
  while( angle > two_pi )
    angle -= two_pi;
  while( angle < 0.0 )
    angle += two_pi;
  return angle;
}

// convert the angle to an index into the score table above
int angle_to_idx( double angle )
{
  double ratio = angle_fix( angle + wedge_width / 2.0 ) / two_pi;
  return (int)( ratio * 20 );
}

// returns the hypotenuse of a right triangle with sides x and y
double hypot( double x, double y )
{
  return sqrt( x * x + y * y );
}

// returns true if the point is inside the dartboard
bool is_valid( double x, double y )
{
  return hypot( x, y ) < 170.0;
}

// returns true if the point is in the bullseye
bool is_bullseye( double x, double y )
{
  return hypot( x, y ) < 7.0;
}

// returns true if the point is in the "outer center" circle
bool is_outercenter( double x, double y )
{
  return hypot( x, y ) < 16.0 && !is_bullseye( x, y );
}

// returns true if the score should be tripled, assuing it
// is *not* in the bullseye or "outer center"
bool is_triple( double x, double y )
{
  return 99.0 < hypot( x, y ) && hypot( x, y ) < 107.0;
}

// returns true if the score should be doubled, assuing it
// is *not* in the bullseye or "outer center"
bool is_double( double x, double y )
{
  return 162.0 < hypot( x, y ) && hypot( x, y ) < 170.0;
}

// returns the score that the point earns
int score( double x, double y )
{
  if( is_bullseye( x, y ) )
    return 50;
  else if( is_outercenter( x, y ) )
    return 25;
  else if( is_valid( x, y ) )
  {
    int base = wedge_scores[ angle_to_idx( atan2( y, x ) ) ];
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

int main( void )
{
  // create an input file stream
  ifstream in( "darts.in" );

  // the number of cases
  int count;
  in >> count;

  for( int i = 0; i < count; ++i )
  {
    // read a case
    double x, y;
    in >> x >> y;
    // produce output
    cout << score( x, y ) << endl;
  }
  return 0;
}
