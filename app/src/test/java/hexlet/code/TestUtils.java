package hexlet.code;

import org.junit.platform.commons.util.ClassLoaderUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;

public class TestUtils {
    public static File getFile(String name) {
        final String testFilePath = ClassLoaderUtils.getDefaultClassLoader()
                .getResource(name)
                .getFile();
        return new File(testFilePath);
    }
    public static String getFileContent(String name) throws IOException {
        return Files.readString(getFile(name).toPath());
    }
}
