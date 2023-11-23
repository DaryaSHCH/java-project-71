package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.*;

import static hexlet.code.OutputFormat.PLAIN;
import static hexlet.code.OutputFormat.STYLISH;


public class Differ {

    public static String generate(File file1, File file2, OutputFormat outputFormat) throws IOException {
        StringBuilder diff = new StringBuilder("");
        if (outputFormat.equals(STYLISH)) {
            diff = new StringBuilder(Stylish.getHeaderLine(outputFormat));
        } else {
            diff = new StringBuilder(Plain.getHeaderLine(outputFormat));
        }
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
        if (outputFormat.equals(STYLISH)) {
            diff.append(Stylish.getTrailingLine(outputFormat));
        } else {
            diff.append(Plain.getTrailingLine(outputFormat));
        }

        return diff.toString();
    }

}
