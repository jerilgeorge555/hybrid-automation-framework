
package org.example.framework.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private DriverManager() {
        // Prevent object creation.
    }

    public static void setDriver(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(
                    "WebDriver cannot be null."
            );
        }

        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver has not been initialized for this thread."
            );
        }

        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }
}
