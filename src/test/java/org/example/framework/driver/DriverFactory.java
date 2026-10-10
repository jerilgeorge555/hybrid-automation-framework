
package org.example.framework.driver;

import org.example.framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Locale;

public final class DriverFactory {

    private DriverFactory() {
        // Prevent object creation.
    }

    public static WebDriver createDriver() {
        String browser = ConfigReader.get("browser")
                .trim()
                .toLowerCase(Locale.ROOT);

        boolean headless = ConfigReader.getBoolean("headless");

        WebDriver driver = switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();

                if (headless) {
                    options.addArguments("--headless=new");
                }

                yield new ChromeDriver(options);
            }

            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();

                if (headless) {
                    options.addArguments("-headless");
                }

                yield new FirefoxDriver(options);
            }

            case "edge" -> {
                EdgeOptions options = new EdgeOptions();

                if (headless) {
                    options.addArguments("--headless=new");
                }

                yield new EdgeDriver(options);
            }

            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
                            + ". Supported browsers: chrome, firefox, edge."
            );
        };

        try {
            driver.manage().timeouts().pageLoadTimeout(
                    Duration.ofSeconds(
                            ConfigReader.getInt("pageLoadTimeout")
                    )
            );

            return driver;
        } catch (RuntimeException e) {
            driver.quit();
            throw e;
        }
    }
}
