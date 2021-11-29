/**
* This program generates marks
* after reading in 2 text files.
*
* @author  Jonathan Pasco-Arnone
* @version 1.0
* @since   2021-11-28
*/

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


    public static ArrayList<ArrayList<Integer>> mergeArrays(final ArrayList<String> arrayOfStudents, final ArrayList<String> arrayOfAssignments,
        final int amountOfStudents, final int amountOfAssignments) {

        ArrayList<ArrayList<Integer>> combinedArray = new ArrayList<ArrayList<Integer>>();
        int counterOne;
        int counterTwo;
        int counterThree;


        /*
        for (counterOne = 0; counterOne < amountOfStudents; ++counterOne) {

            combinedArray.add();
            System.out.println(combinedArray);
        }*/

        for (counterTwo = 0; counterTwo < amountOfStudents; ++counterTwo) {

            for (counterThree = 0; counterThree < amountOfAssignments; ++counterThree) {

                if (counterThree = 0) {

                    combinedArray.get(counterThree).add(array);

                } else {

                combinedArray.add(new ArrayList<Integer>());
                combinedArray.get(counterTwo).add(counterThree);

                }

            }

        }

        return combinedArray;

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
        final ArrayList<String> listOfStudents = new ArrayList<String>();
        final ArrayList<String> listOfAssingments = new ArrayList<String>();
        final Path studentFilePath = Paths.get("./", args[0]);
        final Path assignmentFilePath = Paths.get("./", args[1]);
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader readerStudent = Files.newBufferedReader(
                                     studentFilePath, charset)) {
            String lineStudent = null;
            while ((lineStudent = readerStudent.readLine()) != null) {
                listOfStudents.add(lineStudent);
                System.out.println(lineStudent);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        try (BufferedReader readerAssignment = Files.newBufferedReader(
                                     assignmentFilePath, charset)) {
            String lineAssignment = null;
            while ((lineAssignment = readerAssignment.readLine()) != null) {
                listOfAssingments.add(lineAssignment);
                System.out.println(lineAssignment);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer quantityStudents = listOfStudents.size();
        final Integer quantityAssignments = listOfAssingments.size();

        final ArrayList<ArrayList<Integer>> mergedArray = mergeArrays(listOfStudents, listOfAssingments, quantityStudents, quantityAssignments);

        System.out.println(mergedArray);

        /* Normal Distribution numbers
        Random random = new Random();

        for (int loopCounter = 0; loopCounter < 5; loopCounter++) {
            int mark = (int)Math.floor(random.nextGaussian()*10+75);
            System.out.println(mark);
        }

        */ 

        System.out.println("\nDone.");
    }
}
