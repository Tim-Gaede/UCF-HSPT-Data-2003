/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Maddog                                                  *
 * Problem Author:  Greg                                                    *
 * Solution Author: Ben                                                     *
 * Data Author:     Glenn                                                   *
 ***************************************************************************/
import java.io.*;
import java.util.StringTokenizer;

public class maddog
{
  public static void main(String[] args) throws Exception
  {
    BufferedReader fin;
    StringTokenizer st;
    int coils;
    int diameter;
    double distance;
      
    //Open the input file
    fin = new BufferedReader(new FileReader(new File("maddog.in")));
      
    //Loop indefinitely
    while(true)
    {
      //Read in all the information
      st = new StringTokenizer(fin.readLine());
      coils = Integer.parseInt(st.nextToken());
      diameter = Integer.parseInt(st.nextToken());
      distance = Double.parseDouble(st.nextToken());
         
      //We are done if the number of coils is zero
      if(coils == 0)
        return;
            
      //If you are too close, you get eaten!
      if(coils * diameter * 3.14 < distance) 
        System.out.println("MAD DOG GOES HUNGRY!!!");

      //If you are far enough away, you escape
      else
        System.out.println("MAD DOG FOOD!!!");
    }
      
  }
}
