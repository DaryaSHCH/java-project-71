package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        version = "gendiff 0.1",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class Differ implements Callable<String> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File left;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File right;

    @Option(names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: stylish]")
    private String outputFormat;

    public static String generate(File filePath1, File filePath2){
        String differences = "";

        return differences;
    }

    @Override
    public String call() throws Exception {
        generate(left, right);

        return "to be implemented";
    }
}
