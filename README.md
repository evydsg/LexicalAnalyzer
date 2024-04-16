# Lexical Analyzer

This project implements a simple lexical analyzer in Java named `LexicalAnalyzer`. The lexical analyzer is designed to analyze arithmetic expressions and identify tokens within them. It reads input from a file named `front.in` and produces a list of tokens along with their corresponding lexemes.

## Components

1. **Character Classes:**
   - Defines three character classes: `LETTER`, `DIGIT`, and `UNKNOWN`.

2. **Token Codes:**
   - Defines token codes for various elements found in arithmetic expressions, including integers, identifiers, and operators such as addition, subtraction, multiplication, and division.

3. **Functions:**
   - `addChar(char ch)`: Adds the given character to the current lexeme.
   - `getChar()`: Reads the next character from the input stream and determines its character class.
   - `getNonBlank()`: Skips over whitespace characters until a non-whitespace character is encountered.
   - `lex()`: Performs lexical analysis by identifying tokens based on the character classes.
   - `lookup(char ch)`: Looks up operators and parentheses and returns the corresponding token code.

## Execution

The `main()` function initializes a `FileReader` to read from the input file `front.in`. It then iterates through the input, calling the `lex()` function to perform lexical analysis until the end of the file is reached.

## Usage

To use this lexical analyzer:
1. Ensure that the input file `front.in` contains the arithmetic expressions to be analyzed.
2. Compile the `LexicalAnalyzer.java` file.
3. Execute the compiled Java program.

## Note

- This lexical analyzer is a basic implementation and may require modifications to handle more complex expressions or additional functionalities.
- Error handling is minimal in this implementation and may need enhancements for robustness in real-world applications.
