package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static hexlet.code.TestUtils.getFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void shouldReturnExpectedData() throws IOException {
        final File file = getFile("parser_input.json");
        InputStream fileStream = java.nio.file.Files.newInputStream(file.toPath());
        final Map<String, Object> actual = Parser.parse(fileStream, "json");
        final Map<String, Object> expected = Map.of(
                "key_string", "value",
                "key_boolean", true,
                "key_integer", 1,
                "key_double", 1.2d
        );

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyMap() throws IOException {
        final File file = getFile("parser_input_empty.json");
        InputStream emptyFileStream = java.nio.file.Files.newInputStream(file.toPath());
        final Map<String, Object> actual = Parser.parse(emptyFileStream, "json");
        final Map<String, Object> expected = Map.of();

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionOnCorruptedJson() throws IOException {
        final File file = getFile("parser_input_corrupted.json");
        InputStream fileStream = java.nio.file.Files.newInputStream(file.toPath());
        assertThrows(
                IOException.class,
                () -> Parser.parse(fileStream, "json"));
    }

}