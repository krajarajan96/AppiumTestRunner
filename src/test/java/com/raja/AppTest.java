package com.raja;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.Test;
import java.io.File;
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
        // Programmatically start Appium server
        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone API 36 (2)");
        options.setApp("/Users/rajark/LeisureWork/MyAppium/src/test/resources/ApiDemos-debug.apk");

        URL url =  new URI("http://127.0.0.1:4723").toURL();
        AndroidDriver androidDriver = new AndroidDriver(url, options);

        // Actual automation


        androidDriver.quit();
        service.stop();

    }
}
