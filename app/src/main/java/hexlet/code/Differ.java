package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Differ {
    public static String generate(File file1, File file2, OutputFormat outputFormat) throws IOException {
        Map<String, Object> leftData = Parser.parse(file1);
        Map<String, Object> rightData = Parser.parse(file2);

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(leftData.keySet());
        allKeys.addAll(rightData.keySet());

        List<String> sortedKeys = new ArrayList<>(allKeys);
        Collections.sort(sortedKeys);

        final List<KeyDifference> differences = new LinkedList<>();

        for (String key : sortedKeys) {
            final Object l = leftData.get(key);
            final Object r = rightData.get(key);

            final Formatter.EqualityCheckResult checkResult;
            if (l == null && !leftData.containsKey(key)) {
                checkResult = Formatter.EqualityCheckResult.ADDED;
            } else {
                if (r == null && !rightData.containsKey(key)) {
                    checkResult = Formatter.EqualityCheckResult.REMOVED;
                } else if (Objects.equals(l, r)) {
                    checkResult = Formatter.EqualityCheckResult.EQUAL;
                } else {
                    checkResult = Formatter.EqualityCheckResult.CHANGED;
                }
            }

            differences.add(new KeyDifference(
                    key,
                    l, r, checkResult
            ));
        }

        return Formatter.getFormatted(differences, outputFormat);
    }

    public static class KeyDifference {

        private final String key;
        private final Object leftValue;
        private final Object rightValue;
        private final Formatter.EqualityCheckResult difference;

        KeyDifference(String key, Object leftValue, Object rightValue, Formatter.EqualityCheckResult difference) {
            this.key = key;
            this.leftValue = leftValue;
            this.rightValue = rightValue;
            this.difference = difference;
        }

        public String getKey() {
            return key;
        }

        public Object getLeftValue() {
            return leftValue;
        }

        public Object getRightValue() {
            return rightValue;
        }

        public Formatter.EqualityCheckResult getDifference() {
            return difference;
        }
    }

}
