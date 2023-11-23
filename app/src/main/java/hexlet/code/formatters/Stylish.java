package hexlet.code.formatters;

import hexlet.code.Formatter;
import hexlet.code.OutputFormat;

public class Stylish {
    public static String formatStylish(
            final String key,
            final Object leftValue,
            final Object rightValue,
            final Formatter.EqualityCheckResult checkResult) {

        final StringBuilder stringBuilder = new StringBuilder();

        switch (checkResult) {
            case ADDED -> stringBuilder.append("+ " + key + ": " + rightValue + "\n");
            case REMOVED -> stringBuilder.append("- " + key + ": " + leftValue + "\n");
            case EQUAL -> stringBuilder.append("  " + key + ": " + leftValue + "\n");
            case CHANGED -> {
                stringBuilder.append("- " + key + ": " + leftValue + "\n");
                stringBuilder.append("+ " + key + ": " + rightValue + "\n");
            }
            default -> throw new IllegalArgumentException("Unknown check result type: " + checkResult);
        }
    return stringBuilder.toString();
    }
    public static String getHeaderLine(final OutputFormat outputFormat) {
        return "{\n";
    }

    public static String getTrailingLine(final OutputFormat outputFormat) {
        return  "}";

    }
}

