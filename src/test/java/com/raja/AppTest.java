package com.raja;


import io.appium.java_client.AppiumBy;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws MalformedURLException, URISyntaxException
    {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();

    }
}
