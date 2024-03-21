package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.EqualityCheckResult;
import hexlet.code.model.KeyDifference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getFormatted(final List<KeyDifference> differences) {

        final Map<String, Object> differenceMap = new HashMap<>();

        for (KeyDifference difference : differences) {
            if (difference.getDifference().equals(EqualityCheckResult.EQUAL)) {
                differenceMap.put(difference.getKey(), Map.of("type", "EQUALS"));
            } else if(difference.getDifference().equals(EqualityCheckResult.ADDED)) {
                differenceMap.put(difference.getKey(), Map.of("type", "ADDED"));
            } else if (difference.getDifference().equals(EqualityCheckResult.REMOVED)) {
                differenceMap.put(difference.getKey(), Map.of("type", "REMOVED"));
            } else if (difference.getDifference().equals(EqualityCheckResult.CHANGED)) {
                differenceMap.put(difference.getKey(), Map.of("type", "CHANGED"));
            }
        }
        try {
            return objectMapper.writeValueAsString(differenceMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
