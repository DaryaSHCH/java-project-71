package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Differ {

    public static String generate(File file1, File file2, String outputFormat) throws IOException {
        StringBuilder diff = new StringBuilder("{\n");
        Map<String, Object> leftData = Parser.parseJson(file1);
        Map<String, Object> rightData = Parser.parseJson(file2);

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(leftData.keySet());
        allKeys.addAll(rightData.keySet());

        List<String> sortedKeys = new ArrayList<>(allKeys);
        Collections.sort(sortedKeys);

        for (String key : sortedKeys) {
            final Object l = leftData.get(key);
            final Object r = rightData.get(key);

            if (l == null) {
                diff.append("+ " + key + ": " + r + "\n");
            } else {
                if (r == null) {
                    diff.append("- " + key + ": " + l + "\n");
                } else if (l.equals(r)) {
                    diff.append("  " + key + ": " + l + "\n");
                } else {
                    diff.append("- " + key + ": " + l + "\n");
                    diff.append("+ " + key + ": " + r + "\n");
                }
            }
        }

        diff.append("}");
        String result = diff.toString();

        return result;
    }

}
