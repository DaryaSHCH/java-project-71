package hexlet.code;

import org.junit.platform.commons.util.ClassLoaderUtils;

import java.io.File;

public class TestUtils {
    public static File getFile(String name) {
        final String testFilePath = ClassLoaderUtils.getDefaultClassLoader()
                .getResource(name)
                .getFile();

        return new File(testFilePath);
    }
}
