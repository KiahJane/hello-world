// prints randomized integers between a given Min & Max
import java.lang.Math;

public class randomNumber {
    public static void main(String args[])
    {
        int min = 200;
        int max = 400;
// Generating random numbers
        System.out.println("1st Random Number: " + (int)(Math.random()*(max-min+1)+min));
        System.out.println("2nd Random Number: " + (int)(Math.random()*(max-min+1)+min));
        System.out.println("3rd Random Number: " + (int)(Math.random()*(max-min+1)+min));
        System.out.println("4th Random Number: " + (int)(Math.random()*(max-min+1)+min));
    }
}
