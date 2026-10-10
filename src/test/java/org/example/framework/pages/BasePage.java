
package org.example.framework.pages;

import org.example.framework.config.ConfigReader;
import org.example.framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage() {
        this.driver = DriverManager.getDriver();
    }

    public void openApplication() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
