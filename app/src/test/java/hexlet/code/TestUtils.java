package hexlet.code;

import org.junit.platform.commons.util.ClassLoaderUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestUtils {
    public static File getFile(String name) {
        final String testFilePath = ClassLoaderUtils.getDefaultClassLoader()
                .getResource(name)
                .getFile();
        return new File(testFilePath);
    }
    public static String getFileContent(String name) throws IOException {
        File file = getFile(name);
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!content.isEmpty()) {
                    content.append("\n");
                }

                content.append(line);
            }
        }
        return content.toString();
    }
}
