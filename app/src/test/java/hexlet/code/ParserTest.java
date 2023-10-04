package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.commons.util.ClassLoaderUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static hexlet.code.TestUtils.getFile;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void shouldReturnExpectedData() throws IOException {
        final File file = getFile("parser_input.json");

        final Map<String, Object> actual = Parser.parse(file);
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
        final Map<String, Object> actual = Parser.parse(file);
        final Map<String, Object> expected = Map.of();

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionOnCorruptedJson() {
        final File file = getFile("parser_input_corrupted.json");

        assertThrows(
                IOException.class,
                () -> Parser.parse(file));
    }

}