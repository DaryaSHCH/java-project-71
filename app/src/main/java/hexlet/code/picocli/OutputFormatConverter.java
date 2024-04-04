package hexlet.code.picocli;

import hexlet.code.OutputFormat;
import picocli.CommandLine;

public final class OutputFormatConverter implements CommandLine.ITypeConverter<OutputFormat> {
    @Override
    public OutputFormat convert(String value) throws Exception {
        return OutputFormat.valueOf(value.toUpperCase());
    }
}
