package com.raja.ios

import com.google.common.collect.ImmutableMap
import io.appium.java_client.AppiumBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.options.XCUITestOptions
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServiceBuilder
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebElement
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.io.File
import java.net.URI
import java.time.Duration

open class BaseTest
{
    lateinit var service: AppiumDriverLocalService
    lateinit var driver: IOSDriver

    @BeforeClass
    fun configure() {
        // Programmatically start Appium server
        service = AppiumServiceBuilder()
            .withAppiumJS(File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .build()
        service.start()

        val options = XCUITestOptions()
        options.setDeviceName("iPhone 13")
//        options.setApp("/Users/rajark/LeisureWork/AppiumTestRunner/src/test/resources/UIKitCatalog.app")
        options.setApp("/Users/rajark/LeisureWork/AppiumTestRunner/src/test/resources/TestApp 3.app")
        options.setPlatformVersion("26.1")
        // Appium -> Install webdriver agent -> Interacts with iOS Apps
        options.setWdaLaunchTimeout(Duration.ofSeconds(30))
        // For automating webviews inside android app
        // options.setChromedriverExecutableDir("")

        val url = URI("http://127.0.0.1:4723").toURL()
        driver = IOSDriver(url, options)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))

        // Locators
        // xpath, id, accessiblity id, classname, iosClassChain, iosPredicateString
        // iosClassChain, iosPredicateString are faster
        // XPath -> XML -> App source: For ios, this conversion happens, so kinda slower

    }

    fun longPress(element: WebElement, duration: Int = 5) {
        val params = HashMap<String, Any>()
        params["element"] = (element as RemoteWebElement).id
        params["duration"] = duration
        driver
            .executeScript("mobile:touchAndHold", params)
    }

    fun scrollTopTo(element: WebElement) {
        val params = HashMap<String, Any>()
        params["element"] = (element as RemoteWebElement).id
        params["direction"] = "up"
        driver
            .executeScript("mobile:scroll", params)
    }

    fun scrollBottomTo(element: WebElement) {
        val params = HashMap<String, Any>()
//        params["element"] = (element as RemoteWebElement).id
        params["direction"] = "down"
//        params["toVisible"] = true
        driver
            .executeScript("mobile: scroll", params)
    }

    @AfterClass
    fun tearDown() {
        driver.quit()
        service.stop()
    }
}