package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static hexlet.code.TestUtils.getFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    @Test
    void testStylishDiffWithJsonInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.json");
        final File file2 = getFile("NestedSecondFile.json");
        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        String actual = Differ.generate(file1, file2, OutputFormat.STYLISH);
        assertEquals(expected, actual);
    }

    @Test
    void testPlainDiffWithJsonInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.json");
        final File file2 = getFile("NestedSecondFile.json");
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'checked' was updated. From false to true
                Property 'default' was updated. From null to [complex value]
                Property 'id' was updated. From 45 to null
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                Property 'setting2' was updated. From 200 to 300
                Property 'setting3' was updated. From true to 'none'
                """;

        String actual = Differ.generate(file1, file2, OutputFormat.PLAIN);
        assertEquals(expected, actual);
    }

    @Test
    void testJsonDiffWithJsonInput() throws IOException {
        final File file1 = getFile("DifferTestLeft.json");
        final File file2 = getFile("DifferTestRight.json");

        final String actual = Differ.generate(file1, file2, OutputFormat.JSON);
        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, Object> actualData = objectMapper
                .readValue(actual, new TypeReference<Map<String, Object>>() {});
        final Map <String, Object> expected = Map.of(
              "key_integer", Map.of("type", "REMOVED"),
              "key_string", Map.of("type", "EQUALS"),
                "key_double", Map.of("type" ,"ADDED"),
                "key_boolean", Map.of("type" , "CHANGED")
        );
        assertEquals(actualData, expected);
    }

    @Test
    void testStylishDiffWithYMLInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.yaml");
        final File file2 = getFile("NestedSecondFile.yaml");
        String expected = """
                {
                  - chars2: [d, e, f]
                  + chars2: false
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                    obj2: {key=value}
                  - obj3: {key=value-old}
                  + obj3: {key=value-new}
                  - setting1: Some value
                  + setting1: Another value
                }""";

        String actual = Differ.generate(file1, file2, OutputFormat.STYLISH);
        assertEquals(expected, actual);
    }

    @Test
    void testPlainDiffWithYMLInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.yaml");
        final File file2 = getFile("NestedSecondFile.yaml");
        String expected = """
                Property 'chars2' was updated. From [complex value] to false
                Property 'key1' was removed
                Property 'key2' was added with value: 'value2'
                Property 'numbers2' was updated. From [complex value] to [complex value]
                Property 'numbers3' was removed
                Property 'numbers4' was added with value: [complex value]
                Property 'obj1' was added with value: [complex value]
                Property 'obj3' was updated. From [complex value] to [complex value]
                Property 'setting1' was updated. From 'Some value' to 'Another value'
                """;

        String actual = Differ.generate(file1, file2, OutputFormat.PLAIN);
        assertEquals(expected, actual);
    }

    @Test
    void testJsonDiffWithYMLInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.yaml");
        final File file2 = getFile("NestedSecondFile.yaml");

        final String actual = Differ.generate(file1, file2, OutputFormat.JSON);
        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, Object> actualData = objectMapper
                .readValue(actual, new TypeReference<Map<String, Object>>() {});
        final Map <String, Object> expected = new HashMap<>();
        expected.put("key1", Map.of("type", "REMOVED"));
        expected.put("key2", Map.of("type", "ADDED"));
        expected.put("chars2", Map.of("type" ,"CHANGED"));
        expected.put("setting1", Map.of("type" , "CHANGED"));
        expected.put("numbers4", Map.of("type" , "ADDED"));
        expected.put("numbers3", Map.of("type" , "REMOVED"));
        expected.put("numbers2", Map.of("type" , "CHANGED"));
        expected.put("numbers1", Map.of("type" , "EQUALS"));
        expected.put("obj2", Map.of("type" , "EQUALS"));
        expected.put("obj1", Map.of("type" , "ADDED"));
        expected.put("obj3", Map.of("type" , "CHANGED"));
        assertEquals(actualData, expected);
    }
}