package com.raja

import com.google.common.collect.ImmutableMap
import io.appium.java_client.AppiumBy
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.remote.RemoteWebElement
import org.testng.Assert
import org.testng.annotations.Test
import java.net.MalformedURLException
import java.net.URISyntaxException

/**
 * Unit test for simple App.
 */
class AppiumTests : BaseTest() {
    /**
     * Rigorous Test :-)
     */
    @Test
    @Throws(MalformedURLException::class, URISyntaxException::class)
    fun shouldAnswerWithTrue() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click()
        driver
            .findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]"))
            .click()
        driver.findElement(AppiumBy.id("android:id/checkbox")).click()
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click()

        val alertTitle = driver.findElement(By.id("android:id/alertTitle")).text
        Assert.assertEquals(alertTitle, "WiFi settings")

        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("RajaWifi")
        driver.findElements(AppiumBy.className("android.widget.Button"))[1].click()
    }

    @Test
    fun longPressGestureTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click()
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click()
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click()

        val peopleNamesElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='People Names']"))
        (driver as JavascriptExecutor).executeScript(
            "mobile: longClickGesture",
            ImmutableMap.of(
                "elementId", (peopleNamesElement as RemoteWebElement).id,
                "duration", 2000
            )
        )
        Thread.sleep(2000)
    }
}
