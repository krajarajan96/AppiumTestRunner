package com.raja.android

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import org.testng.annotations.BeforeClass
import java.io.File
import java.net.URI
import java.time.Duration

open class BrowserBaseTest {
    lateinit var service: AppiumDriverLocalService
    lateinit var driver: AndroidDriver

    @BeforeClass
    fun configure() {
        // Programmatically start Appium server
        service = AppiumServiceBuilder()
            .withAppiumJS(File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .build()
        service.start()

        val options = UiAutomator2Options()
        // For automating webviews inside android app
        options.setDeviceName("Pixel 6 Pro")
         options.setChromedriverExecutableDir("/Users/rajark/Desktop/")
        options.setCapability("browserName", "Chrome")

        val url = URI("http://127.0.0.1:4723").toURL()
        driver = AndroidDriver(url, options)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
    }
}