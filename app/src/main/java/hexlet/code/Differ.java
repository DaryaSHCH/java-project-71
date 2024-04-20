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

    public static String generate(String firstFilePath, String secondFilePath) throws IOException {
        File file1 = new File(firstFilePath);
        File file2 = new File(secondFilePath);

        return generate(file1, file2, OutputFormat.STYLISH);
    }

    public static String generate(String firstFilePath, String secondFilePath, String format) throws IOException {
        File file1 = new File(firstFilePath);
        File file2 = new File(secondFilePath);

        return generate(file1, file2, OutputFormat.valueOf(format.toUpperCase()));
    }

    public static String generate(File file1, File file2, OutputFormat outputFormat) throws IOException {

        Map<String, Object> leftData = parse(file1);
        Map<String, Object> rightData = parse(file2);

        final List<KeyDifference> differences = getKeyDifferences(leftData, rightData);

        return Formatter.getFormatted(differences, outputFormat);
    }

    private static Map<String, Object> parse(File file) throws IOException {
        FileType type = getFileExtension(file);

        try (InputStream fileStream = Files.newInputStream(file.toPath())) {
            return switch (type) {
                case JSON -> Parser.parseJson(fileStream);
                case YAML -> Parser.parseYml(fileStream);
                default -> throw new IllegalArgumentException("Unsupported format");
            };
        }
    }

    private static FileType getFileExtension(File file) {
        int index = file.getName().lastIndexOf('.');
        if (index < 0) {
            return FileType.UNKNOWN;
        }
        String type = file.getName().substring(index + 1);
        return switch (type.toLowerCase()) {
            case "json" -> FileType.JSON;
            case "yaml", "yml" -> FileType.YAML;
            default -> FileType.UNKNOWN;
        };
    }

    private enum FileType {
        JSON,
        YAML,
        UNKNOWN
    }
}
