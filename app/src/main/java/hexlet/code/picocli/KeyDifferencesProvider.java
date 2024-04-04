package hexlet.code.picocli;

import hexlet.code.EqualityCheckResult;
import hexlet.code.model.KeyDifference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

            final EqualityCheckResult checkResult;
            if (l == null && !leftData.containsKey(key)) {
                checkResult = EqualityCheckResult.ADDED;
            } else if (r == null && !rightData.containsKey(key)) {
                checkResult = EqualityCheckResult.REMOVED;
            } else if (Objects.equals(l, r)) {
                checkResult = EqualityCheckResult.EQUAL;
            } else {
                checkResult = EqualityCheckResult.CHANGED;
            }

            differences.add(new KeyDifference(
                    key,
                    l, r, checkResult
            ));
        }

        return differences;
    }
}
