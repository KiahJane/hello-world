// Mini application -- offers two options: random or numerical order

//import classes & packages

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

import static java.lang.System.out;

public class Driver {
    /**
     * The Driver program is a mini application that displays a graph of randomised numbers either in numerical or
     * randomised order as defined by the user.
     *
     * @since 2023-02-28
     */
    public enum RandomiserEnum {
        /**
         * userInputs.add(0, arrayLength);
         * userInputs.add(1, startValue);
         * userInputs.add(2, endValue);
         * userInputs.add(3, numOrder);
         */
        ARRAY_LENGTH(0),
        START_VALUE(1),
        END_VALUE(2),
        NUM_ORDER(3);

        final int val;

        RandomiserEnum(int val) {
            this.val = val;
        }
    }

    public static int askInt(String prompt) {
        /* helper method -- asks & validates user input */
        Scanner sc = new Scanner(System.in);
        int arrayLength = 0;
        Boolean flag;
        do {
            try {
                System.out.println(prompt);
                arrayLength = Integer.parseInt(sc.next());
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer number:");
                System.out.println(prompt);
                flag = false;
            }
        } while (!flag);

        return arrayLength;
    }

    public static int askInt(String prompt, int startValue) {
        /* helper method -- asks & validates user input */
        Scanner sc = new Scanner(System.in);
        int arrayLength = 0;
        boolean flag;
        boolean print_flag = true;
        do {
            try {
                System.out.println(prompt);
                arrayLength = Integer.parseInt(sc.next());
                if(startValue > arrayLength){
                    print_flag = false;
                    System.out.println("Please enter an integer larger than the start value:");
                    throw new Exception();
                }
                flag = true;
            } catch (Exception e) {
                if(print_flag){
                    System.out.println("Please enter an integer number:");
                    System.out.println(prompt);
                }
                flag = false;
            }
        } while (!flag);

        return arrayLength;
    }

    public static ArrayList<Integer> getUserInput() {
        int arrayLength = askInt("Enter Array Length:");
        int startValue = askInt("Enter Start Value (minimum):");
        int endValue = askInt("EEnter End Value (maximum):", startValue);
        int numOrder = askInt("""
                Visualization:\s
                1. randomly ordered data
                2. numerically ordered data""");

        ArrayList<Integer> userInputs = new ArrayList<>(4);
        userInputs.add(RandomiserEnum.ARRAY_LENGTH.val, arrayLength);
        userInputs.add(RandomiserEnum.START_VALUE.val, startValue);
        userInputs.add(RandomiserEnum.END_VALUE.val, endValue);
        userInputs.add(RandomiserEnum.NUM_ORDER.val, numOrder);

        return userInputs;
    }

    public static ArrayList<Integer> randomise(ArrayList<Integer> userInputs) {
        // creates array of randomised integers
        // params: array: array of 3 integers
        // returns: array list of integers

        int arrayLength = userInputs.get(RandomiserEnum.ARRAY_LENGTH.val);
        int startValue = userInputs.get(RandomiserEnum.START_VALUE.val);
        int endValue = userInputs.get(RandomiserEnum.END_VALUE.val);

        //int[] numArrayList = new Random().ints(arrayLength, startValue, endValue).toArray();

        ArrayList<Integer> numArrayList = new ArrayList<>(arrayLength);
        for (int number = 0; number < arrayLength; number++) {
            int num = (int) (Math.random() * (endValue - startValue + 1) + startValue);
            numArrayList.add(num);
        }
        return numArrayList;
    }

    public void visualiseArray(ArrayList<Integer> numbers, int numOrder) {
        // visualizes array of integers in the terminal
        // params: numbers: array of integers
        //         numOrder: integer
        // numbers remain randomly organized
        if (numOrder == 2) {
            // make numbers numerically organized
            Collections.sort(numbers);
        }

        int max = Collections.max(numbers);

        for (int y = max; y >= 0; y--) {
            out.print(y + "\t");

            for (int x = 0; x < numbers.size(); x++) {

                if (y == 0) {
                    out.print("#\t");
                    continue;
                }

                if (numbers.get(x) == y) {
                    out.print("^");
                } else if (numbers.get(x) > y) {
                    out.print("|");
                } else {
                    out.print(" ");
                }

                out.print("\t");
            }
            out.println();
        }
    }


    public static void main(String[] args) {
        // runs the code in the appropriate oder
        Driver driver = new Driver();

        ArrayList<Integer> userInput = getUserInput();
        int numOrder = userInput.get(RandomiserEnum.NUM_ORDER.val);

        ArrayList<Integer> numbers = randomise(userInput);

        driver.visualiseArray(numbers, numOrder);

    }
}