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

#include <string>
#include <vector>
#include <fstream>
#include <iostream>
#include <algorithm>
using namespace std;

class backup 
{
public:
  int row;			// the row (x) position of this backup
  int col;			// the col (y) position of this backup
  int height;			// the height (i) of this backup
  int width;			// the width (j) of this backup	
  int time;			// the time (t) of this backup
  vector<string> data;
	
  // constructor/destructor
  backup(int x,int y,int i,int j,int t);
  ~backup() {};

  // compare operator for STL sort()
  bool operator<(const backup &rhs) const;

};

// Function declaration
void apply(vector<string> &target, backup &source);

backup::backup(int x,int y,int i,int j,int t)
{
	// Set members
	row = x;
	col = y;
	width = j;
	height = i;
	time = t;
}

bool backup::operator<(const backup &rhs)const
{
	if (time < rhs.time) return true;	// this is less than rhs
	else return false;					// this is not less than rhs
}

int main(char *argv)
{
	ifstream inp;		// input stream
	
	int n,m,k,x,y,i,j,t; 	// Input vars specified in statement
	vector<backup> backups;		// array of backups
	vector<string> tehArray;		// The Array!!!!@#!@# Whoa! (c)
	
	int iter=1;				// current set iteration (Set [iter]:)
	int cur,r,c;			// loop indexes
	char inc;				// input character
				
	// Open the input file.
	inp.open("reloaded.in", ifstream::in);
	if (!inp.is_open())
	{
		cout << "You must have taken the wrong pill..." << endl;
		cout << "The Array doesn't exist!!!! (input)." << endl;
		return 0;
	} 

	inp >> n >> m >> k;

	// The terminal case is 0 0 0
	while (n != 0 || m != 0 || k != 0)
	{		
		tehArray.clear();

		for (r = 0; r < n; r++)
		{	
			// create an empty string for this row of the array
			tehArray.push_back(string());
			for (c = 0; c < m; c++)
			{
				tehArray[r] += '0';	// initialize this position to character '0',
									// not 0 (NULL).
			}
		}		
		
		backups.clear();

		// Loop through the number of backups (k).											
		for (cur = 0; cur < k; cur++)
		{
			// Get a line of input.
			inp >> x >> y >> i >> j >> t;
			
			// create a new backup, and add to vector.
			backups.push_back(backup(x,y,i,j,t));
			
			for (r = 0; r < i; r++)
			{
				// create an empty string for this row of the
				// backup
				backups[cur].data.push_back(string());
				for (c = 0; c < j; c++)
				{
					// input is guaranteed to be one character.
					inp >> inc;
					// add it to the row
					backups[cur].data[r] += inc;
				}
			}
		}
		
		// This will sort the backups in time order,
		// using the STL unstable sort.  
		sort(backups.begin(), backups.end());
				
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
		cout << "Set " << iter << ":" << endl;
		for (r = 0; r < n && m != 0; r++)
		{
			for (c = 0; c < m; c++)
			{
				cout << tehArray[r][c] << " ";
			}
			cout << endl;
		}
		cout << endl;
				
		iter++;		// increment data set number
		
		// Prime input for next case.
		inp >> n >> m >> k;
	} // end of main while loop

  return 0;

} // end of main()

// Apply data in a backup to passed Array.
void apply(vector<string> &target, backup &source)
{
	int r,c;
	
	for (r = source.row; r < source.row+source.height; r++)
	for (c = source.col; c < source.col+source.width ; c++)
	{
		target[r][c] = source.data[r-source.row][c-source.col];
	}
}
