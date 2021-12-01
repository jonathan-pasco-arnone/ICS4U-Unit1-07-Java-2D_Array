/**
* This program generates marks
* after reading in 2 text files.
*
* @author  Jonathan Pasco-Arnone
* @version 1.0
* @since   2021-11-28
*/

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* This is the marks program.
*/
final class Marks {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Marks() {
        throw new IllegalStateException("Cannot be instantiated");
    }


    public static String mergeArrays(final ArrayList<String> arrayOfStudents, final ArrayList<String> arrayOfAssignments,
        final int amountOfStudents, final int amountOfAssignments) {

        ArrayList<ArrayList<Object>> combinedArray = new ArrayList<ArrayList<Object>>();
        int counterOne;
        int counterTwo;
        int counterThree;
        int counterFour;
        int counterFive;
        StringBuilder sb = new StringBuilder();
        String returnString;        

        
        for (counterOne = 0; counterOne < amountOfStudents; ++counterOne) {
            combinedArray.add(new ArrayList<Object>());
            combinedArray.get(counterOne).add(arrayOfStudents.get(counterOne));
        }

        for (counterTwo = 0; counterTwo < amountOfStudents; ++counterTwo) {

            for (counterThree = 0; counterThree < amountOfAssignments; ++counterThree) {

                Random random = new Random();
                // Generates a random number and adds it to the array
                int mark = (int)Math.floor(random.nextGaussian()*10+75);
                combinedArray.get(counterTwo).add(mark);

            }

        }

        for (counterFour = 0; counterFour < amountOfStudents; ++counterFour) {

            sb.append(combinedArray.get(counterFour));
            sb.append("\n");

        }

        returnString = sb.toString();
        returnString = returnString.replaceAll("\\p{Punct}", "");
        returnString = returnString.replaceAll(" ", ", ");

        return returnString;
    }


    /**
    * The generateMarks() function.
    *
    * @param arrayOfStudents the collection of students
    * @param arrayOfAssignments the collection of assignments
    * @return the generated marks
    */
    public static String[][] generateMarks(final Integer[] arrayOfStudents, 
                                       final Integer[] arrayOfAssignments) {

        // this is just a place holder!
        String[][] markArray = { { "", "Ass #1", "Ass #2" }, 
                           { "Sue", "76%", "88%" },
                           { "Bob", "46%", "81%" } };

        return markArray;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {

        // Variables/Constants.
        final ArrayList<String> listOfStudents = new ArrayList<String>();
        final ArrayList<String> listOfAssingments = new ArrayList<String>();
        final Path studentFilePath = Paths.get("./", args[0]);
        final Path assignmentFilePath = Paths.get("./", args[1]);
        final Charset charset = Charset.forName("UTF-8");

        // For removing specific parts of the array.
        final String frontSquareBrace = "[";
        final String backSquareBrace = "]";
        final String sameDirectory = "./";
        final String newLine = "\n";

        try (BufferedReader readerStudent = Files.newBufferedReader(
                                     studentFilePath, charset)) {
            String lineStudent = null;
            while ((lineStudent = readerStudent.readLine()) != null) {
                listOfStudents.add(lineStudent);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        try (BufferedReader readerAssignment = Files.newBufferedReader(
                                     assignmentFilePath, charset)) {
            String lineAssignment = null;
            while ((lineAssignment = readerAssignment.readLine()) != null) {
                listOfAssingments.add(lineAssignment);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer quantityStudents = listOfStudents.size();
        final Integer quantityAssignments = listOfAssingments.size();

        final String mergedArray = mergeArrays(listOfStudents, listOfAssingments, quantityStudents, quantityAssignments);

        System.out.println(mergedArray);

        try {

        // Writing the new CSV file.
        final FileWriter csvMaker = new FileWriter("./marks.csv");
        csvMaker.append(mergedArray); /*(", " + Arrays.toString(listOfAssignments)
                    .replace(frontSquareBrace, "")
                    .replace(backSquareBrace, "") + newLine);*/

        /*for (ArrayList<ArrayList<Object>> array : mergedArray) {
                csvMaker.append(Arrays.deepToString(array)
                        .replace(frontSquareBrace, "")
                        .replace(backSquareBrace, "") + newLine);
        }*/

        csvMaker.close();
        } catch (IOException exception) {
            System.out.println("Failed to output to out.csv");
        }

        System.out.println("\nDone.");
    }
}
