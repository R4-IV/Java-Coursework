package Pepek.Robert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args){

        //creates a file from a path in the root folder of the program
        String filename = "datafile.txt";
        String pathName = System.getProperty("user.dir") + File.separator + filename;
        File cipheredText = new File(pathName);

        //creates the output file.
        File results = new File("results.txt");


        //Character arrays are used to store the decoding tables where their index + 1 corresponds to
        //their position in the table
        char[] vowels = new String("aAeEiIoOuUyY").toCharArray();
        char[] consonants = new String("bBcCdDfFgGhHjJkKlLmMnNpPqQrRsStTvVwWxXzZ").toCharArray();

        //Regex pattern capturing 1 group which will match to every instance in the ciphered text
        //from left captures vowels followed by consonants followed by white spaces
        Pattern patternRegex = Pattern.compile("([V][1]*[0-9]|[C][1-4]*[0-9]|\\s)");

        //String containers used to hold and update output and new lines as they feed in
        String dataOutput = "";
        String currentLine;

        //creates a scanner and initialises it to null so that it can be closed in the finally block.
        Scanner fileIn = null;

        //code block responsible for cipher decryption and saving output to dataOutput variable
        //try catch to handle missing input file or incorrect file path.
        try{
            //Creates a new Scanner object that takes input from datafile.txt
            //Delimiter used to feed input from file line by line.
            fileIn = new Scanner(cipheredText);
            fileIn.useDelimiter("(\\n)");

            //Checks whether or no there is any text left in the file.
            //creates a matcher object that compares the pattern to the current line
            while(fileIn.hasNext()){
                currentLine = fileIn.next();
                Matcher matchCipher = patternRegex.matcher(currentLine);

                //checks whether or not there is match in the line
                while(matchCipher.find()){
                    //loads the string that matches the regex pattern
                    String currentLetter = matchCipher.group(1);

                    //Checks the first character of the matched pattern possible outputs 'V','C',' '
                    switch(currentLetter.charAt(0)){
                        case 'V':
                            //Updates the dataOutput string by concatenating the letter at char index -1 using
                            //substring to get the integer portion of the ciphered letter from currentLetter
                            dataOutput += vowels[Integer.parseInt(currentLetter.substring(1)) - 1];
                            break;

                        case 'C':
                            dataOutput += consonants[Integer.parseInt(currentLetter.substring(1)) - 1];
                            break;

                        default:
                            //Concatenates a white space to dataOutput as matchCipher.group(1) outputs a white space
                            dataOutput += " ";
                    }
                }
                //Concatenates a line break to dataOutput
                dataOutput += "\n";
            }
        }
        //Catches a file not found exception in the event of incorrect pathing prints the string and stacktrace to display
        //error line position
        catch (FileNotFoundException e){
            System.out.println("An error occurred, the file path may be incorrect or the file doesn't exist");
        }
        finally {
            //Closes scanner as all reading is complete
            fileIn.close();
        }

        //Code block responsible for writing the dataOutput string to results.txt
        //Closes writer once writing to file is complete
        try{
            FileWriter resultWriter = new FileWriter(results);
            resultWriter.write(dataOutput);
            resultWriter.close();
        }
        //Exception catcher in case an error prevents writing to the file
        catch (IOException e){
            System.out.println("an error occurred writing the file.");
            e.printStackTrace();
        }
        //Prints the decoded message as output to the console
        System.out.println(dataOutput);
    }
}
