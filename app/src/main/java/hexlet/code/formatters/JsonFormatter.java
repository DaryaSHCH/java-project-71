package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import hexlet.code.Formatter;
import hexlet.code.OutputFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String formatJson(
            final String key,
            final Object leftValue,
            final Object rightValue,
            final Formatter.EqualityCheckResult checkResult) throws JsonProcessingException {
        final String diffJson;

        switch (checkResult) {
            case ADDED -> diffJson = objectMapper.writeValueAsString(Map.of(
                    "type", "ADDED",
                    "new_value", rightValue
            ));
            case REMOVED -> diffJson = objectMapper.writeValueAsString(Map.of(
                    "type", "REMOVED"
            ));
            case EQUAL -> diffJson = objectMapper.writeValueAsString(Map.of(
                    "type", "EQUAL",
                    "value", rightValue
            ));
            case CHANGED -> diffJson = objectMapper.writeValueAsString(Map.of(
                    "type", "CHANGED",
                    "old_value", leftValue,
                    "new_value", rightValue
            ));
            default -> throw new IllegalArgumentException("Unknown check result type: " + checkResult);
        }

        return "\"" + key + "\": " + diffJson + "\n";
    }

    public static String getHeaderLine() {
        return "{\n";
    }

    public static String getTrailingLine() {
        return  "}";

    }

    public static String getFormatted(final List<Differ.KeyDifference> differences) {
        final Map<String, Object> differenceMap = new HashMap<>();
        Map<String, Object> diffInfoMap = new HashMap<>();
        final StringBuilder sb = new StringBuilder();
        sb.append(getHeaderLine());

        for (Differ.KeyDifference difference : differences) {
            if (difference.getDifference().equals(Formatter.EqualityCheckResult.EQUAL)) {
                diffInfoMap = Map.of("type", "EQUALS");
            }

            differenceMap.put(difference.getKey(), diffInfoMap);
        }
        try {
            sb.append(objectMapper.writeValueAsString(differenceMap));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        sb.append(getTrailingLine());
        return sb.toString();
    }
}
