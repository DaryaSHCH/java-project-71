package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static hexlet.code.TestUtils.getFile;
import static org.junit.jupiter.api.Assertions.*;
import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {
    @Test
    void testGenerate() throws IOException {
        final File file1 = getFile("DifferTestLeft.json");
        final File file2 = getFile("DifferTestRight.json");
        String expected = "{\n" +
                "- key_boolean: true\n" +
                "+ key_boolean: false\n" +
                "+ key_double: 1.2\n" +
                "- key_integer: 1\n" +
                "  key_string: value\n" +
                "}";
        String actual = Differ.generate(file1, file2, null);
        assertEquals(expected, actual);
    }
}