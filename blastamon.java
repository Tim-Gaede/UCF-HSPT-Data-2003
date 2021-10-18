/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Blastamon                                               *
 * Problem Author:  Jason                                                   *
 * Solution Author: Mike W                                                  *
 * Data Author:     Nick                                                    *
 ***************************************************************************/
import java.io.*;

public class blastamon
{

  String[] values;	
  BufferedReader in;

  public void initialize()
  {
    //Makes sure that all the strings stored
    //are set to null

    values = new String[1001];
    for(int i=0;i<1001;i++)
      values[i]=null;
  }

  public void process(int numberOfEntries)
  {
    int placeholder=0;
    String readName=null;

    initialize();
		
    try{
      for(int i=0;i<numberOfEntries;i++)
      {
        //Read in the name and hold on to it
        readName=in.readLine();

        //read in the hit points (place value)
        placeholder = Integer.parseInt(in.readLine());

        //Store it
        values[placeholder]=readName;
      }
    }
    catch(IOException e){}
  }

  public void output()
  {

    //The Entries are already sorted by hit points now
    //because of the way we stored them
    //No ties means no two creatures will have the same hitpoints
    //That way no creature will overwrite another in the list of names
    //So print them starting from the beginning.

    for(int i=0;i<1001;i++)
    {
      if(values[i]!=null)
        System.out.println(values[i]);
    }
  }
	
  public boolean running()
  {
    int numberOfEntries=0;

    //This part reads in the number of entries for this dictionary

    try{
      numberOfEntries = Integer.parseInt(in.readLine());
    }catch(IOException e){}


    if(numberOfEntries==0) return false;

    process(numberOfEntries);		
    output();

    return true;
  }

  public void run()
  {


    in=null;
	
    try{
      in = new BufferedReader(new FileReader("blastamon.in"));
    }catch(IOException e){}


    //continue processing till the specified condition is met.
    while(running()) System.out.println();
  }

  public static void main(String[] args)
  {
    blastamon b = new blastamon();
    b.run();
  }
}
