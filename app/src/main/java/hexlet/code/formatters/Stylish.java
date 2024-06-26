package hexlet.code.formatters;

import hexlet.code.EqualityCheckResult;
import hexlet.code.model.KeyDifference;

import java.util.List;

public class Stylish {
    public static String formatStylish(
            final String key,
            final Object leftValue,
            final Object rightValue,
            final EqualityCheckResult checkResult) {

        final StringBuilder stringBuilder = new StringBuilder();

        switch (checkResult) {
            case ADDED -> stringBuilder.append("  + " + key + ": " + rightValue + "\n");
            case REMOVED -> stringBuilder.append("  - " + key + ": " + leftValue + "\n");
            case EQUAL -> stringBuilder.append("    " + key + ": " + leftValue + "\n");
            case CHANGED -> {
                stringBuilder.append("  - " + key + ": " + leftValue + "\n");
                stringBuilder.append("  + " + key + ": " + rightValue + "\n");
            }
            default ->  {
            }
        }
        return stringBuilder.toString();
    }

    private static String getHeaderLine() {
        return "{\n";
    }

    private static String getTrailingLine() {
        return  "}";

    }

    public static String getFormatted(
            final List<KeyDifference> differences) {
        final StringBuilder sb = new StringBuilder();

        sb.append(getHeaderLine());

        for (final KeyDifference difference : differences) {
            final String stylishString = formatStylish(
                    difference.getKey(),
                    difference.getLeftValue(),
                    difference.getRightValue(),
                    difference.getDifference()
            );

            sb.append(stylishString);
        }

        sb.append(getTrailingLine());

        return sb.toString();
    }
}

