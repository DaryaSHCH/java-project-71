package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.*;


public class Differ {

    public static String generate(File file1, File file2, OutputFormat outputFormat) throws IOException {
        StringBuilder diff = new StringBuilder(Formatter.getHeaderLine(outputFormat));
        Map<String, Object> leftData = Parser.parse(file1);
        Map<String, Object> rightData = Parser.parse(file2);

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(leftData.keySet());
        allKeys.addAll(rightData.keySet());

        List<String> sortedKeys = new ArrayList<>(allKeys);
        Collections.sort(sortedKeys);

        for (String key : sortedKeys) {
            final Object l = leftData.get(key);
            final Object r = rightData.get(key);

            final Formatter.EqualityCheckResult checkResult;
            if (l == null) {
                checkResult = Formatter.EqualityCheckResult.ADDED;
            } else {
                if (r == null) {
                    checkResult = Formatter.EqualityCheckResult.REMOVED;
                } else if (l.equals(r)) {
                    checkResult = Formatter.EqualityCheckResult.EQUAL;
                } else {
                    checkResult = Formatter.EqualityCheckResult.CHANGED;
                }
            }

            final String formattedStringToDisplay = Formatter.format(
                    key,
                    l, r,
                    checkResult,
                    outputFormat
            );

            diff.append(formattedStringToDisplay);
        }

        diff.append(Formatter.getTrailingLine(outputFormat));

        return diff.toString();
    }

}
