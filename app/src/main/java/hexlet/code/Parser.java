package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static Map<String, Object> parse(InputStream inputStream, String fileType) throws IOException {
        if ("json".equals(fileType)) {
            return parseJson(inputStream);
        } else {
            return parseYml(inputStream);
        }
    }

    private static Map<String, Object> parseJson(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, new TypeReference<Map<String, Object>>() {});
    }

    private static Map<String, Object> parseYml(InputStream inputStream) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(inputStream, new TypeReference<Map<String, Object>>() {});
    }
}
