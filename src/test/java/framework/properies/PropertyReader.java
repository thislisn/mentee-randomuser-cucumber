package framework.properies;

import framework.exceptions.PropertyReaderException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static framework.properies.Properties.*;

public class PropertyReader {
    private final static String PROPERTY_FILE_PATH = "src/testconfig.properties";

    private static Map<String, String> readProperty(String path) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            prop.load(input);
        } catch (IOException e) {
            throw new PropertyReaderException("Cannot load configuration file " + path + ". ", e);
        }
        return prop.entrySet().stream()
                .collect(Collectors.toMap(map ->
                        (String) map.getKey(), map -> (String) map.getValue(), (a, b) -> b));
    }

    public static String getBrowserType() {
        return readProperty(PROPERTY_FILE_PATH).get(BROWSER.getFieldName());
    }
}
