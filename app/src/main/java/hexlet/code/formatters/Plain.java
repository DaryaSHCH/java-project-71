package hexlet.code.formatters;

import hexlet.code.EqualityCheckResult;
import hexlet.code.model.KeyDifference;

import java.util.List;
import java.util.Map;

public class Plain {
    public static String formatPlain(
            final String key,
            final Object leftValue,
            final Object rightValue,
            final EqualityCheckResult checkResult) {

            final Object leftValuePrepared = getStringValueForPlainFormat(leftValue);
            final Object rightValuePrepared = getStringValueForPlainFormat(rightValue);
            final StringBuilder stringBuilder = new StringBuilder();

            switch (checkResult) {
                //Property 'obj1' was added with value: [complex value]
                case ADDED -> stringBuilder.append("Property '" + key + "' was added with value: " + rightValuePrepared + "\n");
                //Property 'numbers3' was removed
                case REMOVED -> stringBuilder.append("Property '" + key + "' was removed\n");
                case EQUAL -> {}
                //Property 'chars2' was updated. From [complex value] to false
                case CHANGED -> {
                    stringBuilder.append("Property '" + key + "' was updated. From " + leftValuePrepared + " to "
                            + rightValuePrepared + "\n");
                }
            }
        return stringBuilder.toString();
    }
    public static String getStringValueForPlainFormat(Object o) {
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

    public static String getFormatted(
            final List<KeyDifference> differences) {
        final StringBuilder sb = new StringBuilder();

        sb.append(getHeaderLine());

        for (final KeyDifference difference : differences) {
            final String stylishString = formatPlain(
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
    public static String getHeaderLine() {
        return "";

    }
    public static String getTrailingLine() {
        return  "";
    }
}

