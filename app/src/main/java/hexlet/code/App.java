package hexlet.code;
import hexlet.code.picocli.OutputFormatConverter;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.concurrent.Callable;

import static hexlet.code.Differ.generate;

@Command(
        name = "gendiff",
        version = "gendiff 0.1",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    private static final int SUCCESS_EXIT_CODE = 0;
    private static final int ERROR_EXIT_CODE = 1;

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File left;

    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File right;

    @CommandLine.Option(names = {"-f", "--format"},
            paramLabel = "format",
            defaultValue = "stylish",
            converter = OutputFormatConverter.class,
            description = "output format [default: stylish]")
    private OutputFormat outputFormat;

    public static void main(String[] args) {
        final int exitCode = new CommandLine(new App()).execute(args);

        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        try {
            String formattedDiff = Differ.generate(left, right, outputFormat);
            System.out.println(formattedDiff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return ERROR_EXIT_CODE;
        }

        return SUCCESS_EXIT_CODE;
    }
}