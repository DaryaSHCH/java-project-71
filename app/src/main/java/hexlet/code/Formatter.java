package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.model.KeyDifference;

import java.io.IOException;
import java.util.List;

public class Formatter {

    public static String getFormatted(final List<KeyDifference> differences, final OutputFormat outputFormat) throws IOException {
        return switch (outputFormat) {
            case STYLISH -> Stylish.getFormatted(differences);
            case PLAIN -> Plain.getFormatted(differences);
            case JSON -> JsonFormatter.getFormatted(differences);
        };
    }
}

