package hexlet.code;

public class Formatter {

    public static String getHeaderLine(final OutputFormat outputFormat) {
        return switch (outputFormat) {
            case STYLISH -> "{\n";
        };
    }

    public static String getTrailingLine(final OutputFormat outputFormat) {
        return switch (outputFormat) {
            case STYLISH -> "}";
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
        }

        return stringBuilder.toString();
    }

    public enum EqualityCheckResult {

        EQUAL,
        REMOVED,
        ADDED,
        CHANGED
    }

}
