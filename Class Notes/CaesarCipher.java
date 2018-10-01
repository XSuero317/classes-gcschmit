import java.util.Scanner;

/**
 * This class encrypts strings with a keyphrase version of the classic
 *      Caesar Cipher.
 *      (as described in The Code Book by Simon Singh)
 *
 * @author gcschmit
 * @version 21 September 2018
 */
public class CaesarCipher
{
    /*
     * static: one value for the variable for all objects of the class;
     *      can be accessed directly through the class
     *      (e.g., CaesarCipher.ALPHABET)
     *      This is like class attributes in Python.
     *      
     *  "ABCDEFGHIJKLMNOPQRSTUVWXYZ" is a String literal
     *  It is equivalent to:
     *      new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
     */
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static void main(String[] args)
    {
        /*
         * A scanner object parses primitive types and Strings from a stream.
         * 
         *  A stream is a sequence of characters from a file, String,
         *      keyboard, network connection, etc.
         *  
         *  Parsing is separating a sequence of characters into tokens based
         *      on delimiters.
         *      A token is a meaningful sequence of characters (e.g., word).
         *      Delimiters are characters that separate tokens (by default,
         *          whitespace (space, tab, newline) is the delimiter).
         *  
         *  When we create a Scanner object, we have to specify the input
         *      stream (e.g., System.in which is the keyboard via the terminal).
         */
        Scanner s = new Scanner(System.in);

        /*
         * Best practices:
         *      1. prompt the user for what you want them to input
         *      2. use print, not println, then the user input immediately
         *          follows the colon
         *      3. leave a space after the final colon
         */
        System.out.print("Enter the text to encrypt: ");

        /*
         * The nextLine method returns all characters up to the end of line
         *      (i.e., where the user typed enter)
         */
        String text = s.nextLine();
        text = text.toUpperCase();
        
        System.out.print("Enter the keyphrase (no spaces): ");
        
        /*
         * The next method returns the next token in the stream as a String
         */
        String keyphrase = s.next();
        keyphrase = keyphrase.toUpperCase();
        
        System.out.print("Enter the number of seconds to test a guessed keyphrase: ");
        /*
         * The nextInt method attempts to convert the next token in the
         *      stream to an int and returns the value. If the next token
         *      cannot be converted, an exception is generated.
         *      
         *  The nextDouble method behaves in the same way for doubles.
         */
         int secondsPerGuess = s.nextInt();
         
         
         // prepare the keyphrase by removing duplicate letters
         keyphrase = CaesarCipher.compressKeyphrase(keyphrase);
         
         long averageTimeToCrack = CaesarCipher.calculateAverageTimeToCrack(
                keyphrase.length(), secondsPerGuess);
         
         CaesarCipher.printAverageTimeToCrack(averageTimeToCrack);
         
         String encryptedText = CaesarCipher.encrypt(text, keyphrase);
         System.out.println("Encrypted: " + encryptedText);
                
         /*
          * Generate a random number between 1 and 26 using th
          *     Math.random method.
          * 
          *  We could use the Random class:
          *     int letterIndex = generator.nextInt(26) + 1;
          *     
          *  int randomValue = (int)((Math.random() * (max-min+1)) + min);
          */
         int letterIndex = (int)(((Math.random() * 26) + 1));
    }
    
    
    /**
     * Formats the average time to crack the cipher based on the
     *      specified number of seconds and displaysit via System.out
     *      in several formats.
     *  
     *  This method is static and is independent of the state of a
     *      CasesarCipher object. As a result, this method may be
     *      invoked on the class instead of on a variable that
     *      references an object.
     *      (e.g., CaesarCipher.printAverageTimeToCrack())
     *  
     *  Static methods cannot access any instance variables. They can
     *      only access static class variables.
     *      
     *  @param  totalSeconds    the average number of seconds to crack
     *                          the cipher
     */
    public static void printAverageTimeToCrack(long totalSeconds)
    {
        /*
         * Instead of using a "magic number" (e.g., 3.14159),use
         *      constants defined by us or the Java Standard Library.
         *  
         *  For example, in the Math class is defined:
         *  
         *      public static final double PI = 3.141592654;
         *  
         *  Declare a constant with the final keyword.
         *      If we try to change the value, a compiler error will be
         *      generated.
         *  
         *  By convention, constants are in all caps with underscores.
         */
        final int SECONDS_FOR_EVERY_MINUTE = 60;
        final int MINUTES_FOR_EVERY_HOUR = 60;
        final int HOURS_FOR_EVERY_DAY = 24;
        final int DAYS_FOR_EVERY_YEAR = 365;
        
        /*
         * Use integer division to calculate how many whole minutes
         *      based on the specified number of seconds.
         *      
         *  Integer division (like // Python) discards the remainder
         *      (truncates). For example: (3 / 4) evalues to 0;
         *      (3.0 / 4) evalues to 0.75.
         */
        long totalMinutes = totalSeconds / SECONDS_FOR_EVERY_MINUTE;
        
        /*
         * Use the modulo (mod, remainder) operator to calculate how many
         *      seconds remain.
         *  
         *  The mod operator (%) returns the remainder of the divsion
         *      operation.
         *      
         *  It can be very useful when paired with integer division.
         *  
         *  Examples:
         *      7 % 2 = 1
         *      11 % 3 = 2
         *      6 % 2 = 0
         */
        long seconds = totalSeconds % SECONDS_FOR_EVERY_MINUTE;
        
        long totalHours = totalMinutes / MINUTES_FOR_EVERY_HOUR;
        long minutes = totalMinutes % MINUTES_FOR_EVERY_HOUR;
        
        long totalDays = totalHours / HOURS_FOR_EVERY_DAY;
        long hours = totalHours % HOURS_FOR_EVERY_DAY;

        long years = totalDays / DAYS_FOR_EVERY_YEAR;
        long days = totalDays % DAYS_FOR_EVERY_YEAR;
        
        System.out.println("Average time to crack: " + years + " years, " +
                days + " days, " + hours + " hours, " + minutes +
                " minutes, " + seconds + " seconds");
        
        /*
         * A conversion is when a data value is converted from one type
         *      to another (e.g., int to a double, double to int,
         *      int to a long).
         *      
         *  Widening: preserves information (e.g., int to double,
         *      int to long)
         *  Narrowing (lossy): may lose information (e.g., double to int)
         *  
         *  Java only automatically performs widening conversions.
         *  
         *  This is a widening conversion (i.e., long to a double):
         */
        double yearsAsDecimal = totalSeconds;
        
        /*
         * Arthimetic Promotion
         * 
         *  If the two operands are of different types, Java attempts to
         *      promote one of the types (widening conversion) and then
         *      performs the operation.
         *  
         *  In this case, both SECONDS_FOR_EVERY_MINUTE and 
         *      MINUTES_FOR_EVERY_HOUR are ints; so, Java doesn't
         *      perform any promotion and, instead, performs
         *      integer multiplication.
         */
        final long SECONDS_FOR_EVERY_YEAR = SECONDS_FOR_EVERY_MINUTE *
                MINUTES_FOR_EVERY_HOUR * HOURS_FOR_EVERY_DAY *
                DAYS_FOR_EVERY_YEAR;
        
        /*
         * In this example, the value of SECONDS_FOR_EVERY_YEAR is
         *      promoted to a double and then floating-point division is
         *      performed and the result is assigned to yearsAsDecimal.
         *      The local variable SECONDS_FOR_EVERY_YEAR is still
         *      a long and has the same value.
         */
        yearsAsDecimal = yearsAsDecimal / SECONDS_FOR_EVERY_YEAR;
        
        System.out.println("or " + yearsAsDecimal + " years");
        
        /*
         * To force a narrowing conversion, use the cast operator. A
         *      cast is the "I know what I'm doing; trust me" conversion.
         *  
         *  (int)(84.69) => truncates to an int (84)
         *  
         *  If we want to round a double to the nearest int value, use
         *      the Math.round static method:
         *      
         *      public static long round(double value);
         *      public static int round(float value);
         *  
         *  The following divides yearsAsDecimal by 10, rounds the
         *      resulting value to the nearest decade and then casts
         *      the resulting long to an int.
         */
        int decades = (int)(Math.round(yearsAsDecimal / 10));
        
        System.out.println("or about " + decades + " decades");
    }
    
    /**
     * Compresses the specified keyphrase by removing any duplicate letters.
     * 
     * @param   keyphrase   the keyphrase to compress
     * @return  the keyphrase with all duplicate letters removed
     */
    public static String compressKeyphrase(String keyphrase)
    {
        String compressedKeyphrase = "";
        
        /*
         * length
         *      returns the number of characters in the string
         */
        int keyPhraseLength = keyphrase.length();
        
        for(int i = 0; i < keyPhraseLength; i++)
        {
            /*
             * charAt
             *      returns the character (of type char) at the specified
             *          index (0-based)
             *  
             *  C A E S A R
             *  0 1 2 3 4 5     <- indicies
             *  
             *  length = 6
             */
            char letter = keyphrase.charAt(i);
            
            /*
             * substring
             *      returns part of the string starting at the first index
             *          up to, but not including, the second index
             *      if only one index is specified, returns part of the
             *          string starting at the index through the end
             *          of the string
             *          
             *  C A E S A R
             *  0 1 2 3 4 5     <- indicies
             *  
             *  length = 6
             */
            String restOfKeyphrase = keyphrase.substring(i+1);
            // could also be:
            // String restOfKeyphrase = keyphrase.substring(i+1,
            //                              keyphrase.length());
            
            /*
             * indexOf
             *      returns the index of the start of the first occurence
             *          of the specified string
             *      if not found, returns -1
             *      
             *  A E S A R
             *  0 1 2 3 4     <- indicies
             *  
             *  length = 5
             */
            int index = restOfKeyphrase.indexOf(letter);
            
            /*
             * String concatenation
             *      + is the string concatentation operator
             *      concatenates the second string operand to the end of
             *          the first string operand
             *      if one or both operands are Strings, + is the string
             *          concatenation operator (operands are converted to
             *          Strings); otherwise, + is the addition operator
             */
            if(index == -1)
            {
                compressedKeyphrase = compressedKeyphrase + letter;
            }
        }
        
        return compressedKeyphrase;
    }

    /**
     * Encrypts the specified text using the specified keyphrase using a
     *      keyphrase-enhanced Caesar Cipher.
     *      
     *  @param  text        the text to encrypt
     *  @param  keyphrase   the keyphrase with which to encrypt the specified text
     *  @return             the encrypted text
     */
    public static String encrypt(String text, String keyphrase)
    {
        String encryptedText = "";

        /*
         * The keyphrase, after removing any repeated letters is used as the beginning of the
         *      jumbled cipher alphabet. The remainder of the cipher alphabet is merely the
         *      remaining letters of the alphabet, in their correct order, starting where the
         *      keyphrase ends.
         */
        String cipherAlphabet = keyphrase;
        for(int i = 0; i < CaesarCipher.ALPHABET.length(); i++)
        {
            char letter = CaesarCipher.ALPHABET.charAt(i);
            if(keyphrase.indexOf(letter) == -1)
            {
                cipherAlphabet += letter;
            }
        }

        /*
         * For each letter in the text that is a letter, find the corresponding letter
         *      at the same position in the cipher alphabet as its replacement.
         */
        for(int i = 0; i < text.length(); i++)
        {
            char letter = text.charAt(i);

            // if the letter is between A and Z
            if(letter >= 'A' && letter <= 'Z')
            {
                // 65 is the ASCII value of 'A'
                int cipherIndex = letter - 65;
                encryptedText += cipherAlphabet.charAt(cipherIndex);
            }
            else    // don't substitute the letter; just use it as is
            {
                encryptedText += letter;
            }
        }

        return encryptedText;
    }

    /**
     * Calcualtes the average time to crack the cipher, based on the
     *      specified length of the keyphrase and seconds to evaluate
     *      each attempt, using a brute force approach. This calculation
     *      assumes that the cracker knows the length of the keyphrase.
     *      If the length is not know, it will take substantially longer
     *      to crack the cipher. Regardless, this calculation is only
     *      for a brute force approach; other techniques (e.g., frequency
     *      analysis) can crack the cipher extremely quickly.
     *      
     *  @param  keyphraseLength the number of characters in the keyphrase
     *  @param  secPerGuess     the number of seconds to evaluate each attempt
     *  @return                 the average number of seconds to crack the cipher
     */
    public static long calculateAverageTimeToCrack(int keyphraseLength, int secPerGuess)
    {
        final int NUMBER_OF_LETTERS_IN_ALPHABET = 26;
        int lettersRemaining = NUMBER_OF_LETTERS_IN_ALPHABET;
        long combinations = 1;

        /*
         * Calculate the number of combintations for the specified keyphrase length.
         *  For example, if the keyphrase is six characters long:
         *      26 * 25 * 24 * 23 * 22 * 21
         *      would be the number of combinations of cipher alphabets for keyphrases
         *      that are six characters long.
         */
        for(int i = 0; i < keyphraseLength; i++)
        {
            combinations *= lettersRemaining;
            lettersRemaining -= 1;
        }

        long worstCaseTimeToCrack = combinations * secPerGuess;

        // average time is half the worst time since the best time is cracking the
        //  cipher on the first attempt
        return worstCaseTimeToCrack/2;
    }
}
