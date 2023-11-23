package hexlet.code.formatters;

import hexlet.code.Formatter;
import hexlet.code.OutputFormat;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String formatPlain(
            final String key,
            final Object leftValue,
            final Object rightValue,
            final Formatter.EqualityCheckResult checkResult,
            final OutputFormat format) {
            final Object leftValuePrepared = getStringValueForPlainFormat(leftValue);
            final Object rightValuePrepared = getStringValueForPlainFormat(rightValue);
            final StringBuilder stringBuilder = new StringBuilder();

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
    public static String getHeaderLine(final OutputFormat outputFormat) {
        return "";

    }

    public static String getTrailingLine(final OutputFormat outputFormat) {
        return  "";
    }
}

