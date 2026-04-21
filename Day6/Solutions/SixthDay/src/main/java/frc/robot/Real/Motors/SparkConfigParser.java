package frc.robot.Real.Motors;

import com.revrobotics.spark.config.SparkMaxConfig;

public class SparkConfigParser {
    public static class ParsedSparkConfig {
        public Integer currentLimit;
        public Double positionConversionFactor;
        public Double velocityConversionFactor;
        public Boolean isInverted;
        public Boolean isBrakeMode;
    }

    public static ParsedSparkConfig parseConfig(SparkMaxConfig config) {
        ParsedSparkConfig parsedConfig = new ParsedSparkConfig();
        String flattened = config.flatten();
        // split by lines
        String[] lines = flattened.split("\n");
        for (String line : lines) {
            // split by ,
            String[] parts = line.split(",");
            int key = Integer.parseInt(parts[0], 10);
            String value = parts[1];
            parseValue(key, value, parsedConfig);
        }
        return parsedConfig;
    }

    private static void parseValue(int key, String value, ParsedSparkConfig parsedConfig) {
        switch (key) {
            case 6:
                parsedConfig.isBrakeMode = parseBoolean(value);
                break;
            case 45:
                parsedConfig.isInverted = parseBoolean(value);
                break;
            case 59:
                parsedConfig.currentLimit = parseInt(value);
                break;
            case 112:
                parsedConfig.positionConversionFactor = parseFloat(value);
                break;
            case 113:
                parsedConfig.velocityConversionFactor = parseFloat(value);
                break;
            default:
                // Ignore other keys for now
                break;
        }
    }

    private static boolean parseBoolean(String value) {
        return value.equals("1");
    }

    private static double parseFloat(String value) {
        int intBits = (int) Long.parseLong(value, 16);
        return Float.intBitsToFloat(intBits);
    }

    private static int parseInt(String value) {
        return Integer.parseInt(value, 16);
    }
}
