package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import hexlet.code.Formatter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getFormatted(final List<Differ.KeyDifference> differences) {
        final Map<String, Object> differenceMap = new HashMap<>();
        Map<String, Object> diffInfoMap = new HashMap<>();
        final StringBuilder sb = new StringBuilder();

        for (Differ.KeyDifference difference : differences) {
            if (difference.getDifference().equals(Formatter.EqualityCheckResult.EQUAL)) {
                diffInfoMap = Map.of("type", "EQUALS");
            } else if(difference.getDifference().equals(Formatter.EqualityCheckResult.ADDED)) {
                diffInfoMap = Map.of("type", "ADDED");
            } else if (difference.getDifference().equals(Formatter.EqualityCheckResult.REMOVED)) {
                diffInfoMap = Map.of("type", "REMOVED");
            } else if (difference.getDifference().equals(Formatter.EqualityCheckResult.CHANGED)) {
                diffInfoMap = Map.of("type", "CHANGED");
            }
            differenceMap.put(difference.getKey(), diffInfoMap);
        }
        try {
            sb.append(objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(differenceMap));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
}
