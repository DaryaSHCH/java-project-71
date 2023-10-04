package hexlet.code;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Parser {

    public static Map<String, Object> parse(File file) throws IOException {
        if (file.getName().endsWith("json")) {
            return parseJson(file);
        } else {
           return parseYml(file);
        }
    }
    private static Map<String,Object> parseJson(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(file, new TypeReference<Map<String,Object>>(){});
    }
    private static Map<String, Object> parseYml(File file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(file, new TypeReference<TreeMap<String, Object>>() {
        });

    }
}
