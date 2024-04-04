package hexlet.code.formatters;

import hexlet.code.EqualityCheckResult;
import hexlet.code.model.KeyDifference;

import java.util.Iterator;
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

        return switch (checkResult) {
            //Property 'obj1' was added with value: [complex value]
            case ADDED -> "Property '" + key + "' was added with value: " + rightValuePrepared;
            //Property 'numbers3' was removed
            case REMOVED -> "Property '" + key + "' was removed";
            case EQUAL -> "";
            //Property 'chars2' was updated. From [complex value] to false
            case CHANGED -> "Property '" + key + "' was updated. From " + leftValuePrepared + " to "
                    + rightValuePrepared;
        };
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

        for (final Iterator<KeyDifference> iterator = differences.iterator(); iterator.hasNext(); ) {
            KeyDifference difference = iterator.next();

            final String stylishString = formatPlain(
                    difference.getKey(),
                    difference.getLeftValue(),
                    difference.getRightValue(),
                    difference.getDifference()
            );

            sb.append(stylishString);

            if (iterator.hasNext() && !stylishString.isBlank()) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}

