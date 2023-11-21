package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String getHeaderLine(final OutputFormat outputFormat) {
        return switch (outputFormat) {
            case STYLISH -> "{\n";
            case PLAIN -> "";
        };
    }

    public static String getTrailingLine(final OutputFormat outputFormat) {
        return switch (outputFormat) {
            case STYLISH -> "}";
            case PLAIN -> "";
        };
    }

    public static String format(
            final String key,
            final Object leftValue,
            final Object rightValue,
            final EqualityCheckResult checkResult,
            final OutputFormat format) {
        final StringBuilder stringBuilder = new StringBuilder();

        if (format.equals(OutputFormat.STYLISH)) {
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
        } else if (format.equals(OutputFormat.PLAIN)) {
            final Object leftValuePrepared = getStringValueForPlainFormat(leftValue);
            final Object rightValuePrepared = getStringValueForPlainFormat(rightValue);

            switch (checkResult) {
                case ADDED -> stringBuilder.append("Property '" + key + "' was added with value: " + rightValuePrepared + "\n");
                //Property 'obj1' was added with value: [complex value]
                case REMOVED -> stringBuilder.append("Property '" + key + "' was removed\n");
                //Property 'numbers3' was removed
                case EQUAL -> {}
                case CHANGED -> {
                    stringBuilder.append("Property '" + key + "' was updated. From " + leftValuePrepared + " to "
                                    + rightValuePrepared + "\n");
                    //Property 'chars2' was updated. From [complex value] to false
                }
                default -> throw new IllegalArgumentException("Unknown check result type: " + checkResult);
            }
        }

        return stringBuilder.toString();
    }

    private static String getStringValueForPlainFormat(Object o) {
        if (o instanceof Map) {
            return "[complex value]";
        } else if (o instanceof List) {
            return "[complex value]";
        } else if (o instanceof String) {
            return "'" + o + "'";
        } else {
           return o + "";
        }
    }

    public enum EqualityCheckResult {

        EQUAL,
        REMOVED,
        ADDED,
        CHANGED
    }

}
