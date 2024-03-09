package hexlet.code;

import hexlet.code.model.KeyDifference;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static hexlet.code.picocli.KeyDifferencesProvider.getKeyDifferences;


public class Differ {

    public static String generate(String file1Path, String file2Path) throws IOException {
        return generate(new File(file1Path), new File(file2Path), null);
    }

    public static String generate(String file1Path, String file2Path, String outputFormat) throws IOException {
        OutputFormat outputFormatEnum;
        if (outputFormat == null) {
            outputFormatEnum = OutputFormat.STYLISH;
        } else {
            outputFormatEnum = OutputFormat.valueOf(outputFormat);
        }

        return generate(new File(file1Path), new File(file2Path), outputFormatEnum);
    }

    public static String generate(File file1, File file2, OutputFormat outputFormat) throws IOException {
        Map<String, Object> leftData = Parser.parse(file1);
        Map<String, Object> rightData = Parser.parse(file2);

        final List<KeyDifference> differences = getKeyDifferences(leftData, rightData);

        return Formatter.getFormatted(differences, outputFormat);
    }

}
