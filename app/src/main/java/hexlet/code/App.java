package hexlet.code;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;


public class App {

    public static void main(String[] args) {
        new CommandLine(new Differ()).execute(args);
    }


}