package Day3.Solutions;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int num1 = scanner.nextInt();
        System.out.print("Enter second number: ");
        int num2 = scanner.nextInt();

        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("Enter operation (1-4): ");
        int operation = scanner.nextInt();

        int result = 0;
        if (operation == 1) {
            result = add(num1, num2);
        } else if (operation == 2) {
            result = subtract(num1, num2);
        } else if (operation == 3) {
            result = multiply(num1, num2);
        } else if (operation == 4) {
            result = divide(num1, num2);
        } else {
            System.out.println("Invalid operation!");
            return;
        }
        System.out.println("Result: " + result);
    }

    static int add(int a, int b) {
        return a + b;
    }

    static int subtract(int a, int b) {
        return a - b;
    }

    static int multiply(int a, int b) {
        return a * b;
    }

    static int divide(int a, int b) {
        return a / b;
    }
}
