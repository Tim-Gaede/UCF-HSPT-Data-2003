/****************************************************************************
 *          17th Annual UCF High School Programming Tournament              *
 *                              May 2, 2003                                 *
 *                     University of Central Florida                        *
 *                                                                          *
 *                                                                          *
 * Special thanks to our sponsors: UPE, SAIC, ACM, and HARRIS               *
 *                                                                          *
 *                                                                          *
 * Problem:         Candy                                                   *
 * Problem Author:  Jason                                                   *
 * Solution Author: Ben                                                     *
 * Data Author:     Greg                                                    *
 ***************************************************************************/
import java.io.*;
import java.util.*;

public class candy
{
  public static void main(String[] argv)
  {
    String[] candyNames = {
      "Unknown candy bar!",	// 0000
      "Planters",				// 0001
      "Reeses_Pieces",		// 0010
      "Unknown candy bar!",	// 0011
      "Sugar_Babies",			// 0100
      "Pay_Day",				// 0101
      "Unknown candy bar!",	// 0110
      "Unknown candy bar!",	// 0111
      "Hersheys",				// 1000
      "Goobers",				// 1001
      "Reeses_Cups",			// 1010
      "Nutrageous",			// 1011
      "Caramello",			// 1100
      "Baby_Ruth",			// 1101
      "Milky_Way",			// 1110
      "Snickers"				// 1111
    };
		
    BufferedReader stdin;	// File reader for input		
    String inp;				// One line of input
    StringTokenizer stk;	// Input Tokenizer		
	
    int numSets;
    int curSet;		
    int lvalue=0;   // Keeps track of intermediate value during calculation
    int avalue=0;   // Keeps track of the result of the current & operation
    int tvalue=0;   // Keeps track of value of current token
    boolean gotNot;
    boolean tokType; // Which type of token to expect: false-operand, true-operator		
    int i;	
		
    String token;
		
    // Open the input file.
    try { 
      stdin = new BufferedReader(
                                 new FileReader("candy.in")
                                 );
    } catch (IOException e)
    { 
      System.out.println("Error reading input.");
      return;
    }
		
    // Get a line of input
    try { inp = stdin.readLine().trim(); } catch (IOException e) { return; }
		
    numSets = Integer.parseInt(inp);
		
    for(curSet = 0; curSet < numSets; curSet++) // Loop through each set
    {
	
      // Get a line of input
      try { inp = stdin.readLine().trim(); } catch (IOException e) { return; }
      gotNot = false;
      lvalue = 0; //Set it to the '|' identity
      avalue = 0xF; //Set it to the '&' identity
      stk = new StringTokenizer(inp, " \n\r\t");
      token = stk.nextToken();
      while(token.charAt(0) == '~') //Process ~s
      {
        gotNot = !gotNot;
        token = token.substring(1); // Advance past the ~
      }
      for(i=0; i <= 0xf; i++) // Identify which candy we've found
      {
        if( token.equals(candyNames[i]))
          tvalue = i;
      }
      if(gotNot) // Negate the candy if needed
        tvalue = not(tvalue);
      tokType = true; //Next token should be an operator
			
      while(stk.hasMoreTokens())
      {
        token = stk.nextToken();
        if(tokType) //If we expect an operator			
        {
          avalue = avalue & tvalue;
          if(token.charAt(0) == '|')
          {
            lvalue = lvalue | avalue;
            avalue = 0xf;
          }
          tokType = false;
        }
        else //If we expect a candy
        {
          gotNot = false;
          while(token.charAt(0) == '~') //Process ~s
          {
            gotNot = !gotNot;
            token=token.substring(1); // Advance past the ~
          }
          for(i=0; i <= 0xf; i++) // Identify the candy 
          {
            if( token.equals(candyNames[i]) )
              tvalue = i;
          }
          if(gotNot) // Negate the candy if needed
            tvalue = not(tvalue);
	
          tokType = true;
        }
							
      }
      avalue = avalue & tvalue;
      lvalue = lvalue | avalue;
      System.out.println(candyNames[lvalue]); // Print the result							
    }
  } // end of main()

  static int not(int value)
  {
    return (~value) & 0xF; // We need to mask out all higher order bits
  }

} // end of class
