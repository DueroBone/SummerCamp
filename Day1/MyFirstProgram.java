package Day1; // Leave this line alone, it tells Java what folder the code is in

public class MyFirstProgram {
    public static void main(String[] args) {
        double aNumber = 1;
        aNumber += 1;
        aNumber += 1;
        aNumber += 1;

        double decimal = 1.5;
        int wholeNumber = 1;
        String text = "Hello World!";
        boolean isTrue = false;
        isTrue = wholeNumber > 10;
        System.out.println("Is true: " + isTrue);

        System.out.println("A number: " + aNumber);
        System.out.println("Decimal: " + decimal);
        System.out.println("Whole number: " + wholeNumber);
        System.out.println("Text: " + text);
    }
}