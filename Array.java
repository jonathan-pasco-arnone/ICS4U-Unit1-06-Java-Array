/*
* This is a program that calculates mean, median and mode
* after reading in a text file into an array.
*
* @author  Jonathan Pasco-Arnone
* @version 1.0
* @since   2021-11-25
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

/**
* This is the statistics program.
*/
final class Array {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Array() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The mean() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the mean of the integers
    */
    public static double mean(final Integer[] arrayOfIntegers, Integer quantity) {
        double mean = 0;
        double originalQuantity = quantity;

        for (quantity = quantity - 1; quantity != -1; quantity--) {
            mean = mean + arrayOfIntegers[quantity];
 
        }

        mean = mean / originalQuantity;

        return mean;
    }

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @return the median of the integers
    */
    public static double median(final Integer[] arrayOfIntegers, double quantity) {
        double median = 0;
        Arrays.sort(arrayOfIntegers);

        if (quantity % 2 == 0) {
            median = arrayOfIntegers[(int)(quantity / 2.0)];
        } else {
            median = ((arrayOfIntegers[(int)(quantity / 2 - 0.5)]) + (arrayOfIntegers[(int)(quantity / 2 + 0.5)])) / 2;
        }

        return median;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {

        // Variables
        Integer tempNumber;
        Integer numberOfValues = 0;

        // Making an array.
        final ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        final Path filePath = Paths.get("./set1.txt");
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader reader = Files.newBufferedReader(
                                     filePath, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                tempNumber = Integer.parseInt(line);
                listOfNumbers.add(tempNumber);
                numberOfValues = numberOfValues + 1;
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        final Integer[] arrayOfNumbers = listOfNumbers.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        final double mean = mean(arrayOfNumbers, numberOfValues);
        final double median = median(arrayOfNumbers, numberOfValues);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);

        System.out.println("\nDone.");
    }
}
