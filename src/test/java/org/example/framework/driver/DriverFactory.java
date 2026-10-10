
package org.example.framework.driver;

import org.example.framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent object creation.
    }

    public static WebDriver createDriver() {
        String browser = ConfigReader.get("browser")
                .trim()
                .toLowerCase();

        WebDriver driver;

        switch (browser) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();

            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
                            + ". Supported browsers: chrome, firefox, edge."
            );
        }

        driver.manage().timeouts().pageLoadTimeout(
                java.time.Duration.ofSeconds(
                        ConfigReader.getInt("pageLoadTimeout")
                )
        );

        return driver;
    }
}
