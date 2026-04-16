package lib.Simplified.Inputs;

public class ProximitySensor {
    private boolean isObjectDetected;

    public ProximitySensor(int portNumber) {
        randomize();
    }

    public void randomize() {
        this.isObjectDetected = Math.random() < 0.5;
    }

    public boolean isObjectDetected() {
        if (Math.random() < 0.05) { // 5% chance to change state
            isObjectDetected = !isObjectDetected;
        }
        return isObjectDetected;
    }
}