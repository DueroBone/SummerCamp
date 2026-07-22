package Day2;

public class TimesTable {
    public static void main(String[] args) {
        // for (int i = 1; i <= 12; i++) {
        // for (int j = 1; j <= 12; System.out.print((i * j++) + " ")) {
        // }
        // System.out.println();
        // }

        for (int i = 1; i <= 12; i++) {
            for (int j = 1; j <= 12; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }
            System.out.println();
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println("Fibonacci of " + i + " is " + fibonacci(i));
        }
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
