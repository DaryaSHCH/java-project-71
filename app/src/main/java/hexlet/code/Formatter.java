package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {

//    public static String getHeaderLine(final OutputFormat outputFormat) {
//        return switch (outputFormat) {
//            case STYLISH -> "{\n";
//            case PLAIN -> "";
//        };
//    }
//
//    public static String getTrailingLine(final OutputFormat outputFormat) {
//        return switch (outputFormat) {
//            case STYLISH -> "}";
//            case PLAIN -> "";
//        };
//    }

    public static String format(
            final String key,
            final Object leftValue,
            final Object rightValue,
            final EqualityCheckResult checkResult,
            final OutputFormat format) throws IOException {

        if (format.equals(OutputFormat.STYLISH)) {
            //StringBuilder diff = new StringBuilder(Formatter.getHeaderLine(outputFormat));
            return Stylish.formatStylish(key, leftValue, rightValue, checkResult);
        } else if (format.equals(OutputFormat.PLAIN)) {
            return Plain.formatPlain(key, leftValue, rightValue, checkResult, format);
        } else {
            throw new IOException("Unsupported format");
        }
    }

    public enum EqualityCheckResult {

        EQUAL,
        REMOVED,
        ADDED,
        CHANGED
    }

}

