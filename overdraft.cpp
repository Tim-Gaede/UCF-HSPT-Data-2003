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
// include the header declaring the iostream library
#include <iostream>
// include the header declaring the file-based iostreams
#include <fstream>

// import the std namespace, so we don't have
// to prefix everything with std::
using namespace std;

// this is where the program starts
int main()
{
  // create an input file stream to read the input file
  ifstream in( "overdraft.in" );

  // space to hold the initial checking balance, savings
  // balance, and the number of transactions
  int checking, savings, count;
  
  in >> checking >> savings >> count;
  while( checking || savings || count )
  {
    // tracks if overflow has happened
    bool over = false;

    for( int i = 0; i < count; i++ )
    {
      // amount of the current transaction
      int transaction;
      // read the next transaction
      in >> transaction;
      if( transaction > checking )
      {
        // we overdrafted, since the transaction is
        // for more than we have in our checking account
        over = true;
        // take the money from checking, add that to our savings,
        // and subtract the transaction from our savings
        savings = savings + checking - transaction;
        // nothing left in checking
        checking = 0;
      }
      else
      {
        // simply deduct the money from the checking account
        checking -= transaction;
      }
    }

    // produce output
    cout << checking << endl;
    cout << savings << endl;
    if( over )
      cout << "OVERDRAFT" << endl;
    else
      cout << "CLEAR" << endl;
    // print the blank line
    cout << endl;

    // read the beginning of the next case before the
    // next iteration of the while loop
    in >> checking >> savings >> count;
  }
  return 0;
}
