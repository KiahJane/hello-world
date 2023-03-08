// Create Number array
import java.util.*;
public final class UserInput {

    private UserInput(){}

    public static int[] getUserInput() {
        System.out.println("Enter Input N:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // create array of size N
        int numArray[] = new int[n];
        for (int i = 0; i < n; i++) {
            // fill in array with values starting from 1 to N
            numArray[i] = i + 1;
        }
        System.out.println("Array with values 1 to " + n);
        System.out.println(Arrays.toString(numArray));

        return numArray;
    }

}