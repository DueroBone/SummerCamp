package Day3;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello, World! : " + i);
        }

        Calculator calc = new Calculator();
        // ask the calc object to add two numbers,
        // and then print the result
        Scanner input = new Scanner(System.in);

        System.out.println("What mode do you want:");
        System.out.println("1: +");
        System.out.println("2: -");
        System.out.println("3: *");
        System.out.println("4: /");

        System.out.print("Enter the mode: ");
        int mode = input.nextInt();
        System.out.print("Enter the first number: ");
        double x = input.nextDouble();
        System.out.print("Enter the second number: ");
        double y = input.nextDouble();

        System.out.print("The result is: ");
        if (mode == 1) {
            System.out.println(calc.add(x, y));
        } else if (mode == 2) {
            System.out.println(calc.subtract(x, y));
        } else if (mode == 3) {
            System.out.println(calc.multiply(x, y));
        } else if (mode == 4) {
            System.out.println(calc.divide(x, y));
        } else {
            System.out.println("Invalid mode");
        }
    }

    double add(double x, double y) {
        return x + y;
    }

    double subtract(double x, double y) {
        return x - y;
    }

    double multiply(double x, double y) {
        return x * y;
    }

    double divide(double x, double y) {
        return x / y;
    }
}
