package hexlet.code.picocli;

import hexlet.code.Formatter;
import hexlet.code.model.KeyDifference;

import java.util.*;

public class KeyDifferencesProvider {
    public static List<KeyDifference> getKeyDifferences(Map<String, Object> leftData, Map<String, Object> rightData) {
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(leftData.keySet());
        allKeys.addAll(rightData.keySet());

        List<String> sortedKeys = new ArrayList<>(allKeys);
        Collections.sort(sortedKeys);

        final List<KeyDifference> differences = new LinkedList<>();

        for (String key : sortedKeys) {
            final Object l = leftData.get(key);
            final Object r = rightData.get(key);

            final hexlet.code.Formatter.EqualityCheckResult checkResult;
            if (l == null && !leftData.containsKey(key)) {
                checkResult = hexlet.code.Formatter.EqualityCheckResult.ADDED;
            } else {
                if (r == null && !rightData.containsKey(key)) {
                    checkResult = hexlet.code.Formatter.EqualityCheckResult.REMOVED;
                } else if (Objects.equals(l, r)) {
                    checkResult = hexlet.code.Formatter.EqualityCheckResult.EQUAL;
                } else {
                    checkResult = Formatter.EqualityCheckResult.CHANGED;
                }
            }

            differences.add(new KeyDifference(
                    key,
                    l, r, checkResult
            ));
        }

        return differences;
    }
}
