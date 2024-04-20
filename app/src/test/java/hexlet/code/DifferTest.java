package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static hexlet.code.TestUtils.getFile;
import static hexlet.code.TestUtils.getFileContent;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {

    @Test
    void testGenerateDefaultFormatWithJsonInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.json");
        final File file2 = getFile("NestedSecondFile.json");
        final String filePath1 = file1.getPath();
        final String filePath2 = file2.getPath();
        final String expected = getFileContent("resultForStylishDiffWithJsonInput.txt").trim();
        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(expected, actual);
    }

    @Test
    void testGenerateDefaultFormatWithYAMLInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.yaml");
        final File file2 = getFile("NestedSecondFile.yaml");
        final String filePath1 = file1.getPath();
        final String filePath2 = file2.getPath();
        final String expected = getFileContent("resultForStylishDiffWithYMLInput.txt").trim();
        String actual = Differ.generate(filePath1, filePath2);
        assertEquals(expected, actual);
    }

    @Test
    void testStylishDiffWithJsonInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.json");
        final File file2 = getFile("NestedSecondFile.json");
        final String expected = getFileContent("resultForStylishDiffWithJsonInput.txt").trim();
        String actual = Differ.generate(file1, file2, OutputFormat.STYLISH);
        assertEquals(expected, actual);
    }

    @Test
    void testPlainDiffWithJsonInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.json");
        final File file2 = getFile("NestedSecondFile.json");
        final String expected = getFileContent("resultForPlainDiffWithJsonInput.txt");

        String actual = Differ.generate(file1, file2, OutputFormat.PLAIN);
        assertEquals(expected, actual);
    }

    @Test
    void testJsonDiffWithJsonInput() throws IOException {
        final File file1 = getFile("DifferTestLeft.json");
        final File file2 = getFile("DifferTestRight.json");
        final File file3 = getFile("resultForJsonDiffWithJsonInput.json");

        final String actual = Differ.generate(file1, file2, OutputFormat.JSON);
        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, Object> actualData = objectMapper
                .readValue(actual, new TypeReference<Map<String, Object>>() {
                });
        Map<String, Object> expectedMap = objectMapper.readValue(file3, new TypeReference<Map<String, Object>>() {
        });
        assertEquals(actualData, expectedMap);
    }

    @Test
    void testStylishDiffWithYMLInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.yaml");
        final File file2 = getFile("NestedSecondFile.yaml");
        final String expected = getFileContent("resultForStylishDiffWithYMLInput.txt").trim();

        String actual = Differ.generate(file1, file2, OutputFormat.STYLISH);
        assertEquals(expected, actual);
    }

    @Test
    void testPlainDiffWithYMLInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.yaml");
        final File file2 = getFile("NestedSecondFile.yaml");
        final String expected = getFileContent("resultForPlainDiffWithYMLInput.txt");

        String actual = Differ.generate(file1, file2, OutputFormat.PLAIN);
        assertEquals(expected, actual);
    }

    @Test
    void testJsonDiffWithYMLInput() throws IOException {
        final File file1 = getFile("NestedFirstFile.yaml");
        final File file2 = getFile("NestedSecondFile.yaml");
        final File file3 = getFile("resultForJsonDiffWithYMLInput.json");

        final String actual = Differ.generate(file1, file2, OutputFormat.JSON);

        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, Object> actualData = objectMapper
                .readValue(actual, new TypeReference<Map<String, Object>>() {
                });
        Map<String, Object> expectedMap = objectMapper.readValue(file3, new TypeReference<Map<String, Object>>() {
        });
        assertEquals(actualData, expectedMap);
    }
}
