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
#include <string.h>
#include <stdio.h>

int not(int value)
{
	return (~value) & 0xF; // We need to mask out all higher order bits
}

void main(void)
{
  char candyNames[16][32] = {
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
  char instr[1024];
  char* token;
  int tokType; // Which type of token to expect: 0-operand, 1-operator
  int numSets;
  int curSet;
  int lvalue;   // Keeps track of intermediate value during calculation
  int avalue;   // Keeps track of the result of the current & operation
  int tvalue;   // Keeps track of value of current token
  int gotNot;
  int i;
  FILE* fin;

  fin = fopen("candy.in", "r");
	

  fgets(instr, 1024, fin);

  sscanf(instr,"%d", &numSets); // Collect the number of sets
	
  for(curSet = 0; curSet < numSets; curSet++) // Loop through each set
  {
    fgets(instr, 1024, fin); // Read current set
		
    gotNot = 0;
    lvalue = 0; //Set it to the '|' identity
    avalue = 0xF; //Set it to the '&' identity
    token = strtok(instr," \n\r\t"); // Get first token
    while(token[0] == '~') //Process ~s
    {
      gotNot = !gotNot;
      token++; // Advance pointer past the ~
    }
    for(i=0; i <= 0xf; i++) // Identify which candy we've found
    {
      if( !strcmp(token, candyNames[i]) )
        tvalue = i;
    }
    if(gotNot) // Negate the candy if needed
      tvalue = not(tvalue);
		
    tokType = 1; //Next token should be an operator
    token = strtok(NULL, " \n\r\t"); //Continue reading tokens
    while(token != NULL) //Process tokens until finished
    { 
      if(tokType) //If we expect an operator			
      {
        avalue = avalue & tvalue;
        if(token[0] == '|')
        {
          lvalue = lvalue | avalue;
          avalue = 0xf;
        }
					
        tokType = 0;
      }
      else //If we expect a candy
      {
        gotNot = 0;
        while(token[0] == '~') //Process ~s
        {
          gotNot = !gotNot;
          token++; // Advance pointer past the ~
        }
        for(i=0; i <= 0xf; i++) // Identify the candy 
        {
          if( !strcmp(token, candyNames[i]) )
            tvalue = i;
        }
        if(gotNot) // Negate the candy if needed
          tvalue = not(tvalue);

        tokType = 1;
      }
      token = strtok(NULL," \t\n\r"); // Get next token
    }
    avalue = avalue & tvalue;
    lvalue = lvalue | avalue;
    printf("%s\n",candyNames[lvalue]); // Print the result
  }
}
