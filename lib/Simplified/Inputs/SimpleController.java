package lib.Simplified.Inputs;

public class SimpleController {
    private double leftX;
    private double leftY;
    private double rightX;
    private double rightY;

    private boolean aButton;
    private boolean bButton;
    private boolean xButton;
    private boolean yButton;

    private boolean leftBumper;
    private boolean rightBumper;
    private boolean leftTrigger;
    private boolean rightTrigger;

    public SimpleController(int portNumber) {
        randomize();
    }

    public void set(double leftX, double leftY, double rightX, double rightY,
            boolean aButton, boolean bButton, boolean xButton, boolean yButton,
            boolean leftBumper, boolean rightBumper,
            boolean leftTrigger, boolean rightTrigger) {
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
        this.rightY = rightY;
        this.aButton = aButton;
        this.bButton = bButton;
        this.xButton = xButton;
        this.yButton = yButton;
        this.leftBumper = leftBumper;
        this.rightBumper = rightBumper;
        this.leftTrigger = leftTrigger;
        this.rightTrigger = rightTrigger;
    }

    public void randomize() {
        // Random from -1 to 1
        this.leftX = Math.random() * 2 - 1;
        this.leftY = Math.random() * 2 - 1;
        this.rightX = Math.random() * 2 - 1;
        this.rightY = Math.random() * 2 - 1;

        // 50/50 chance of being true or false
        this.aButton = Math.random() < 0.5;
        this.bButton = Math.random() < 0.5;
        this.xButton = Math.random() < 0.5;
        this.yButton = Math.random() < 0.5;
        this.leftBumper = Math.random() < 0.5;
        this.rightBumper = Math.random() < 0.5;
        this.leftTrigger = Math.random() < 0.5;
        this.rightTrigger = Math.random() < 0.5;
    }

    public double getLeftX() {
        return leftX;
    }

    public double getLeftY() {
        return leftY;
    }

    public double getRightX() {
        return rightX;
    }

    public double getRightY() {
        return rightY;
    }

    public boolean getAButton() {
        return aButton;
    }

    public boolean getBButton() {
        return bButton;
    }

    public boolean getXButton() {
        return xButton;
    }

    public boolean getYButton() {
        return yButton;
    }

    public boolean getLeftBumper() {
        return leftBumper;
    }

    public boolean getRightBumper() {
        return rightBumper;
    }

    public boolean getLeftTrigger() {
        return leftTrigger;
    }

    public boolean getRightTrigger() {
        return rightTrigger;
    }

}
