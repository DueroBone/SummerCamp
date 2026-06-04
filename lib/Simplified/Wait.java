package lib.Simplified;

public class Wait {
    public static void waitSeconds(double seconds) {
        try {
            Thread.sleep((long)(seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
