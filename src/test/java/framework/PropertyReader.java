package framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Properties properties = new Properties();

    public PropertyReader(final String resourceName) {
        appendFromResource(resourceName);
    }

    private void appendFromResource(final String resourceName) {
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
        if (inStream != null) {
            try {
                PropertyReader.properties.load(inStream);
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.printf("Resource \"%1$s\" could not be found%n", resourceName);
        }
    }

    public static String getProperty(final String key) {
        return properties.getProperty(key);
    }
}