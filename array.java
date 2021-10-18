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
import java.io.*;
import java.util.*;

public class array
{
  /**
   *  Processes input for array.
   *  @throws IOException if problem reading input.
   */
  public static void main(String[] args) throws IOException
  {
    BufferedReader in;  // input reader
    int num;            // number in array
    StringTokenizer st; // tokenizer
        
    in = new BufferedReader(new FileReader("array.in")); // open input
        
    // data causes break;
    while(true)
    {
      // Read the size of the case.
      num = Integer.parseInt(in.readLine().trim());
            
      // terminal case.
      if(num == 0) break;
            
      // read input data
      st = new StringTokenizer(in.readLine(), " ");
            
      //Loop through all the numbers in the array
      for(int i=0;i<num;i++)
      {
        //We're looking for '1'. If found, print position and end.
        if(Integer.parseInt(st.nextToken())==1)
        {
          System.out.println("The Unity is at position " + i + ".");
          break;
        }
      }                        
      System.out.println();
    }
    in.close();
  }
}
