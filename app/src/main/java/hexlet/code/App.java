package hexlet.code;
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
public class App implements Callable<String>{

    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File left;

    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File right;

    @CommandLine.Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]")
    private String outputFormat;

    public static void main(String[] args) {
        final int exitCode = new CommandLine(new App()).execute(args);

        System.exit(exitCode);
    }

    @Override
    public String call() throws Exception {
        System.out.println(generate(left, right, outputFormat));
        return null;
    }
}