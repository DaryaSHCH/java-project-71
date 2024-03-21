package hexlet.code;

import hexlet.code.model.KeyDifference;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import static hexlet.code.picocli.KeyDifferencesProvider.getKeyDifferences;


public class Differ {

    public static String generate(File file1, File file2, OutputFormat outputFormat) throws IOException {
        try (InputStream file1Stream = Files.newInputStream(file1.toPath());
             InputStream file2Stream = Files.newInputStream(file2.toPath())) {
            Map<String, Object> leftData = Parser.parse(file1Stream, getFileExtension(file1));
            Map<String, Object> rightData = Parser.parse(file2Stream, getFileExtension(file2));

            final List<KeyDifference> differences = getKeyDifferences(leftData, rightData);

            return Formatter.getFormatted(differences, outputFormat);
        }
    }

    private static String getFileExtension(File file) {
        if (file.getName().endsWith("json")) {
            return "json";
        } else if (file.getName().endsWith("yaml")) {
            return "yaml";
        } else {
            return "yml";
        }
    }
}
