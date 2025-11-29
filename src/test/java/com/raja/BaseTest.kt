package com.raja

import com.google.common.collect.ImmutableMap
import io.appium.java_client.AppiumBy
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
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
        options.setDeviceName("Pixel 6 Pro")
        options.setApp("/Users/rajark/LeisureWork/MyAppium/src/test/resources/General-Store.apk")
        // For automating webviews inside android app
        // options.setChromedriverExecutableDir("")

        val url = URI("http://127.0.0.1:4723").toURL()
        driver = AndroidDriver(url, options)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
    }

    fun longPressGesture(element: WebElement) {
        (driver as JavascriptExecutor).executeScript(
            "mobile: longClickGesture",
            ImmutableMap.of(
                "elementId", (element as RemoteWebElement).id,
                "duration", 2000
            )
        )
    }

    fun scrollToElement(elementName: String) {
        driver.findElement(AppiumBy.androidUIAutomator("""
            new UiScrollable(new UiSelector()).scrollIntoView(text("$elementName"));
        """.trimIndent()))
    }

    fun scrollToEnd() {
        var canScrollMore = false
        do {
            canScrollMore = (driver as JavascriptExecutor).executeScript(
                "mobile: scrollGesture",
                ImmutableMap.of(
                    "left", 100,
                    "top", 100,
                    "width", 200,
                    "height", 200,
                    "direction", "down",
                    "percent", 10.0
                )
            ) as Boolean
        }
        while (canScrollMore)
    }

    fun scrollToTop() {
        var canScrollMore = false
        do {
            canScrollMore = (driver as JavascriptExecutor).executeScript(
                "mobile: scrollGesture",
                ImmutableMap.of(
                    "left", 100,
                    "top", 100,
                    "width", 200,
                    "height", 200,
                    "direction", "up",
                    "percent", 10.0
                )
            ) as Boolean
        }
        while (canScrollMore)
    }

    fun swipeLeft(element: WebElement, percent: Double = 0.1) {
        (driver as JavascriptExecutor).executeScript(
            "mobile: swipeGesture",
            ImmutableMap.of(
                "elementId", (element as RemoteWebElement).id,
                "direction", "left",
                "percent", percent
            )
        )
    }

    fun swipeRight(element: WebElement, percent: Double = 0.1) {
        (driver as JavascriptExecutor).executeScript(
            "mobile: swipeGesture",
            ImmutableMap.of(
                "elementId", (element as RemoteWebElement).id,
                "direction", "right",
                "percent", percent
            )
        )
    }

    fun drag(source: WebElement, endX: Double, endY: Double) {
        (driver as JavascriptExecutor).executeScript(
            "mobile: dragGesture", ImmutableMap.of(
                "elementId", (source as RemoteWebElement).getId(),
                "endX", endX,
                "endY", endY
            )
        )
    }

    fun startActivity(activity: String) {
        (driver as JavascriptExecutor).executeScript(
            "mobile: startActivity",
            ImmutableMap.of(
                "intent", activity,
            )
        )
    }

    fun getFormattedAmount(price: String): Double {
        return price.substring(1).toDouble()
    }

    @AfterClass
    fun tearDown() {
        driver.quit()
        service.stop()
    }
}