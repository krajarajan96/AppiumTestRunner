package com.raja;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws MalformedURLException, URISyntaxException
    {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone API 36 (2)");
        options.setApp("/Users/rajark/LeisureWork/MyAppium/src/test/resources/ApiDemos-debug.apk");

        URL url =  new URI("http://127.0.0.1:4723").toURL();
        AndroidDriver androidDriver = new AndroidDriver(url, options);
        androidDriver.quit();
    }
}
