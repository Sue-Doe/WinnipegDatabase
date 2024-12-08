package codeBase;
import java.util.ArrayList;
import java.util.Arrays;



//Purpose: Validate and format String
class SafeInput{

    //list of illegal chars
    private static   ArrayList<Character> illegalChars = new ArrayList<>(Arrays.asList(
         ';', '*', '\\', '\0'
    ));

    /**
     * Formats the string to be at most a length of 20
     * 
     * @param input - string to format
     * @return the string with a length of 20
     */
    public static String shrinkString(String input) {
        if (input.length() > 20) {
            return input.substring(0, 17) +"...";
        }
            return input;
    }

    /**
     * Checks the String for illegal characters that can be used for injection
     * 
     * @param input - string to check
     * @return null on success or error message if invalid
     */
    public static String validateStringUserInput(String input){

        String returnValue = null;

            for (int i = 0; i < input.length(); i++) {
                for (Character character : illegalChars) {
                    if (input.charAt(i) == character) {
                        returnValue = "Error: Invalid Input";
                    }
                }
            }
        return returnValue;
    }

    /**
     * Checks wether the given string can be converted into an int 
     * 
     * @param input - string to convert to int
     * @return the converted int or error value
     */
    public static int validateIntegerUserInput(String input) {
        int returnValue;
        try {
            returnValue = Integer.parseInt(input);
            if (returnValue < 0) {
                returnValue = Integer.MIN_VALUE;    
            }
        } catch (Exception e) {
            returnValue = Integer.MIN_VALUE;
        }
        return returnValue;
    }

}