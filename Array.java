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
    * @param quantity the number of integers in the array
    * @return the mean of the integers
    */
    public static double mean(final Integer[] arrayOfIntegers,
        final Integer quantity) {
        double mean = 0;

        for (int meanCounter = quantity - 1; meanCounter != -1; meanCounter--) {
            mean = mean + arrayOfIntegers[meanCounter];

        }

        mean = mean / quantity;

        return mean;
    }

    /**
    * The median() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @param quantity the number of integers in the array
    * @return the median of the integers
    */
    public static double median(final Integer[] arrayOfIntegers,
        final double quantity) {
        double median = 0;
        final double extra = 0.5;
        Arrays.sort(arrayOfIntegers);

        if (quantity % 2 == 0) {
            median = arrayOfIntegers[(int) (quantity / 2.0)];
        } else {
            median = ((arrayOfIntegers[(int) (quantity / 2 - extra)])
                + (arrayOfIntegers[(int) (quantity / 2 + extra)])) / 2;
        }

        return median;
    }

    /**
    * The mode() function.
    *
    * @param arrayOfIntegers the collection of integers
    * @param quantity the number of integers in the array
    * @return the mode of the integers
    */
    public static ArrayList<Integer> mode(final Integer[] arrayOfIntegers,
        final double quantity) {
        // Constants.
        final ArrayList<Integer> maxValue = new ArrayList<Integer>();
        // Variables.
        int currentMaxCount = 0;
        int counterOne;
        int counterTwo;
        int counterThree;
        int counterOneRepeat;
        int counterTwoRepeat;

        /*
        * This has a one because of the temprorary 0 that is put into
        * the arraylist.
        */
        int valuesInMaxValue = 1;
        int addMode = 0;
        maxValue.add(0);

        for (counterOne = 0; counterOne < quantity; ++counterOne) {
            int count = 0;
            for (counterTwo = 0; counterTwo < quantity; ++counterTwo) {
                if (arrayOfIntegers[counterTwo]
                    == arrayOfIntegers[counterOne]) {
                    ++count;
                }
            }

            if (count > currentMaxCount) {
                currentMaxCount = count;
            }
        }
        for (counterOneRepeat = 0; counterOneRepeat < quantity;
            ++counterOneRepeat) {
            int countTwo = 0;
            for (counterTwoRepeat = 0; counterTwoRepeat < quantity;
                ++counterTwoRepeat) {
                if (arrayOfIntegers[counterTwoRepeat]
                    == arrayOfIntegers[counterOneRepeat]) {
                    ++countTwo;
                }
                if (countTwo == currentMaxCount) {
                    for (counterThree = 0; counterThree < valuesInMaxValue;
                        ++counterThree) {
                        if ((int) (arrayOfIntegers[counterOneRepeat])
                            == (int) (maxValue.get(counterThree))) {
                            addMode = 1;
                        }
                    }
                    if (addMode == 0) {
                        ++valuesInMaxValue;
                        maxValue.add(arrayOfIntegers[counterOneRepeat]);
                    }
                    addMode = 0;
                }
            }
        }
        // Remove the 0 added at the beggining of the funciton.
        maxValue.remove(0);
        return maxValue;
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
        final Path filePath = Paths.get("./", args[0]);
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
        Arrays.sort(arrayOfNumbers);
        System.out.println(Arrays.toString(arrayOfNumbers));

        System.out.println("\nCalculating stats...");
        final double mean = mean(arrayOfNumbers, numberOfValues);
        final double median = median(arrayOfNumbers, numberOfValues);
        final ArrayList<Integer> mode = mode(arrayOfNumbers, numberOfValues);

        System.out.println("The mean is: " + mean);
        System.out.println("The median is: " + median);
        System.out.println("The mode is: " + mode);

        System.out.println("\nDone.");
    }
}
