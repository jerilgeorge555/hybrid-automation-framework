
package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.framework.driver.DriverFactory;
import org.example.framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
