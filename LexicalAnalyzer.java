import java.io.*;

public class LexicalAnalyzer{
    // Global declarations
    private static int charClass;
    private static char[] lexeme = new char[100];
    private static char nextChar;
    private static int lexLen;
    private static int token;
    private static int nextToken;
    private static FileReader in_fp;

    //Character Classes
    public static final int LETTER = 0;
    public static final int DIGIT = 1;
    public static final int UNKNOWN = 99;

    //Token codes
    public static final int INT_LIT = 10;
    public static final int IDENT = 11;
    public static final int ASSIGN_0P = 20;
    public static final int ADD_OP= 21;
    public static final int SUB_OP= 22;
    public static final int MULT_OP= 23;
    public static final int DIV_OP = 24;
    public static final int LEFT_PAREN = 25;
    public static final int RIGHT_PAREN = 26;

    //Function declarations

    //Function to add nextChar to lexeme
    public static void addChar(char ch)
    {
        if(lexLen <= 98)
        {
            lexeme[lexLen++] = nextChar;
            lexeme[lexLen] = 0;
        }
        else
        {
            System.out.println("Error - lexeme is too long\n");
        }
    }

    //Function to get the next character of input and determine its character class
    public static void getChar()
    {
        try {
            int nextChar = in_fp.read(); // Assuming 'in_fp' is a FileReader or another appropriate input stream.
    
            if (nextChar != -1) {
                nextChar = (char)nextChar; 
                if (Character.isLetter(nextChar))
                    charClass = LETTER;
                else if (Character.isDigit(nextChar))
                    charClass = DIGIT;
                else
                    charClass = UNKNOWN;
            } else {
                charClass = -1;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the input stream: " + e.getMessage());
            System.exit(1);
        }
    }

    //Function to call getChar until it returns a non-whitespace character
    public static void getNonBlank()
    {
        while(Character.isWhitespace(nextChar))
        {
            getChar();
        }
    }

    //A simple lexical analyzer for arithmetic expressions
    public static void lex()
    {
        lexLen = 0;
        getNonBlank();
        
        switch(charClass){
            case LETTER:
                addChar(nextChar);
                getChar();

                while(charClass == LETTER || charClass ==DIGIT)
                {
                    addChar(nextChar);
                    getChar();
                }
            nextToken = IDENT;
            break;

            case DIGIT:
                addChar(nextChar);
                getChar();

                while(charClass == DIGIT)
                {
                    addChar(nextChar);
                    getChar();
                }
            nextToken = INT_LIT;
            break;

            case UNKNOWN:
                lookup(nextChar);
                getChar();
                break;
            
            case -1://If we are at the end of the file
                nextToken = -1;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = 'O';
                break;

        }
        System.out.println("Next token is: " + nextToken + " Next lexeme is " + String.valueOf(lexeme, 0, lexLen));
    }

    //Function to lookup operators and parentheses and return the token
    public static int lookup(char ch)
    {
        switch (ch) {
            case '(':
                addChar(ch);
                nextToken = LEFT_PAREN;
                break;
            case ')':
                addChar(ch);
                nextToken = RIGHT_PAREN;
                break;

            case '+':
                addChar(ch);
                nextToken = ADD_OP;
                break;

            case '-':
                addChar(ch);
                nextToken = SUB_OP;
                break;
            
            case '*':
                addChar(ch);
                nextToken = MULT_OP;
                break;

            case '/':
                addChar(ch);
                nextToken = DIV_OP;
                break;

            // Add more cases for other characters if needed
            default:
                addChar(ch);
                nextToken = -1;
                break;
                // Handle the default case here if necessary

        }
        return nextToken;
    }

    //Main Function
    public static void main(String[] args) throws IOException {
        
        try {
            in_fp = new FileReader("front.in");
        } catch (FileNotFoundException e) {
            System.out.println("ERROR - cannot open front.in");
            return; // Exit the program if the file cannot be opened
        }

        getChar(); // Get the first character

        do {
            lex();
        } while (nextToken != -1);
        // Close the file when done
        in_fp.close();
    }
}
