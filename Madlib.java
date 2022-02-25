import java.io.*;
import java.util.*;

public class Madlib {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        intro();  
        String choice = "a";
        while (!choice.equalsIgnoreCase("q")) {
            System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
            choice = console.nextLine();
            if (choice.equalsIgnoreCase("c")) {
                Scanner input = correctFile(console);
                madLib(input, console);
            } else if (choice.equalsIgnoreCase("v")) {
                outputFile(console);
            }
            System.out.println();
        }
    }

    // Asks user for correct file. Returns the file as a scanner.
    // Scanner console - collects user input.
    public static Scanner correctFile(Scanner console) throws FileNotFoundException {
        System.out.print("Input file name: ");  
        String inFileName = console.nextLine();
        File inFile = new File(inFileName);

        while (!inFile.exists()) {
            System.out.print("File not found. Try again: ");
            inFile = new File(console.nextLine());
        }

        return new Scanner(inFile);   
    }
    
    // This method runs the intro message of the program.
    public static void intro() {
        System.out.println("Welcome to the game of Mad Libs.");
        System.out.println("I will ask you to provide various words");
        System.out.println("and phrases to fill in a story.");
        System.out.println("The result will be written to an output file.");
        System.out.println();
    }
    
    public static void madLib(Scanner input, Scanner console) throws FileNotFoundException {
        System.out.print("Output file name: ");
        String fileName = console.nextLine();
        PrintStream madlibStory = new PrintStream(new File(fileName));  
        System.out.println(); 
        
        String story = "";
        while (input.hasNext()) {
            
            String inputWord = input.next();

            if (inputWord.startsWith("<") && inputWord.endsWith(">")) {
                inputWord = inputWord.replaceFirst("<", "");
                inputWord = inputWord.replace("-", " ");
        
                inputWord = removeLastChar(inputWord);

                System.out.print("Please type ");
                if (inputWord.startsWith("a") || inputWord.startsWith("e") || inputWord.startsWith("i") || inputWord.startsWith("o") || inputWord.startsWith("u") )  {
                    System.out.print("an ");
                } else {
                    System.out.print("a ");
                }
                System.out.print(inputWord + ": ");
                String inputWord2 = console.nextLine();

                story += inputWord2 + " ";
                
            } else {
                story += inputWord + " ";
            }
            
        }
        System.out.println("Your mad-lib has been created!");
    
        madlibStory.println(story);
    }   

    public static void outputFile(Scanner console) {
        
        System.out.print("Input file? ");  
        String inFileName = console.nextLine();
        File inFile = new File(inFileName);

        while (!inFile.exists()) {
            System.out.print("File not found. Try again: ");
            inFile = new File(console.nextLine());
        }
        System.out.println(inFile);
    }


    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
    

}