
package org.example.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new IllegalStateException(
                        "Could not find config/config.properties on the classpath."
                );
            }

            PROPERTIES.load(input);

        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConfigReader() {
        // Prevent object creation.
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing or empty configuration property: " + key
            );
        }

        return value.trim();
    }

    public static boolean getBoolean(String key) {
        String value = get(key);

        if (!value.equalsIgnoreCase("true")
                && !value.equalsIgnoreCase("false")) {
            throw new IllegalArgumentException(
                    "Configuration property '" + key
                            + "' must be true or false."
            );
        }

        return Boolean.parseBoolean(value);
    }

    public static int getInt(String key) {
        try {
            return Integer.parseInt(get(key));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Configuration property '" + key
                            + "' must be a valid integer.",
                    e
            );
        }
    }
}
