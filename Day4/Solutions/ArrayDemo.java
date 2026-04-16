package Day4.Solutions;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] arr = new int[5]; // Creates an array of integers with length 5
        arr[0] = 10; // Sets the first element to 10
        arr[1] = 20; // Sets the second element to 20
        arr[2] = 30; // Sets the third element to 30
        arr[3] = 40; // Sets the fourth element to 40
        arr[4] = 50; // Sets the fifth element to 50

        // Print out all the elements in the array using a loop
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Element at index " + i + ": " + arr[i]);
        }

        // Another way of printing out all the elements in the array using a for-each loop
        for (int element : arr) {
            System.out.println("Element: " + element);
        }

        // Student challenge: create an array containing the numbers 1-100
        int[] oneToHundred = new int[100];
        for (int i = 0; i < 100; i++) {
            oneToHundred[i] = i + 1;
        }

        // Print out the last 10 elements in the array
        for (int i = oneToHundred.length - 10; i < oneToHundred.length; i++) {
            System.out.println("Element at index " + i + ": " + oneToHundred[i]);
        }
    }
}
