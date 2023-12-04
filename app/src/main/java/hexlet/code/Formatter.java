package hexlet.code;

import hexlet.code.Differ.KeyDifference;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(
            final String key,
            final Object leftValue,
            final Object rightValue,
            final EqualityCheckResult checkResult,
            final OutputFormat format) throws IOException {

        if (format.equals(OutputFormat.STYLISH)) {
            return Stylish.formatStylish(key, leftValue, rightValue, checkResult);
        } else if (format.equals(OutputFormat.PLAIN)) {
            return Plain.formatPlain(key, leftValue, rightValue, checkResult);
        } else if (format.equals(OutputFormat.JSON)){
            return JsonFormatter.formatJson(key, leftValue, rightValue, checkResult);
        } else {
            throw new IOException("Unsupported format");
        }
    }
    public static String getFormatted(final List<KeyDifference> differences, final OutputFormat outputFormat) throws IOException {
        if (outputFormat.equals(OutputFormat.STYLISH)) {
            return Stylish.getFormatted(differences);
        } else if (outputFormat.equals(OutputFormat.PLAIN)) {
            return Plain.getFormatted(differences);
        } else if (outputFormat.equals(OutputFormat.JSON)) {
            return JsonFormatter.getFormatted(differences);
        } else {
        throw new IOException("Unsupported format");}
    }

    public enum EqualityCheckResult {

        EQUAL,
        REMOVED,
        ADDED,
        CHANGED
    }
}

