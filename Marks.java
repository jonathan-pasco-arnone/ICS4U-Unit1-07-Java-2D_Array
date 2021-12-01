/*
* This program generates marks
* after reading in 2 text files.
*
* @author  Jonathan Pasco-Arnone
* @version 1.0
* @since   2021-11-28
*/

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    /**
    * This function recieves two arrays, changes them into a 2D array,
    * and then outputs it as a string (for simplicity and convenience).
    *
    * @param arrayOfStudents array of every student name.
    * @param arrayOfAssignments array of every assignment.
    * @param amountOfStudents amount of students in the array.
    * @param amountOfAssignments amount of assignmetns in the array.
    * @return returns the merged array as a string (for simplicity).
    */
    public static String mergeArrays(final ArrayList<String> arrayOfStudents,
        final ArrayList<String> arrayOfAssignments,
        final int amountOfStudents, final int amountOfAssignments) {

        final ArrayList<ArrayList<Object>> combinedArray =
            new ArrayList<ArrayList<Object>>();
        int counterOne;
        int counterTwo;
        int counterThree;
        int counterFour;
        final StringBuilder createString = new StringBuilder();
        final String returnString;

        for (counterOne = 0; counterOne < amountOfStudents; ++counterOne) {
            combinedArray.add(new ArrayList<Object>());
            combinedArray.get(counterOne).add(arrayOfStudents.get(counterOne));
        }

        for (counterTwo = 0; counterTwo < amountOfStudents; ++counterTwo) {

            for (counterThree = 0; counterThree < amountOfAssignments; ++counterThree) {

                final Random random = new Random();
                // Generates a random number and adds it to the array
                final int mark = (int) Math.floor(random.nextGaussian() * 10 + 75);
                combinedArray.get(counterTwo).add(mark);

            }

        }

        for (counterFour = 0; counterFour < amountOfStudents; ++counterFour) {

            createString.append(combinedArray.get(counterFour));
            createString.append(newLine);

        }

        returnString = createString.toString();
        returnString = returnString.replaceAll("\\p{Punct}", "");
        returnString = returnString.replaceAll(" ", ", ");

        return returnString;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {

        // Variables/Constants.
        // For removing specific parts of the array.
        final String frontSquareBrace = "[";
        final String backSquareBrace = "]";
        final String sameDirectory = "./";
        final String newline = "\n";

        final ArrayList<String> listOfStudents = new ArrayList<String>();
        final ArrayList<String> listOfAssingments = new ArrayList<String>();
        final Path studentFilePath = Paths.get(sameDirectory, args[0]);
        final Path assignmentFilePath = Paths.get(sameDirectory, args[1]);
        final Charset charset = Charset.forName("UTF-8");

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

        final String mergedArray = mergeArrays(listOfStudents,
            listOfAssingments, quantityStudents, quantityAssignments);

        System.out.println(mergedArray);

        try {

            // Writing the new CSV file.
            final FileWriter csvMaker = new FileWriter("./marks.csv");
            csvMaker.append(mergedArray);

            csvMaker.close();
        } catch (IOException exception) {
            System.out.println("Failed to output to out.csv");
        }

        System.out.println("\nDone.");
    }
}
