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
        longPressGesture(peopleNamesElement)

        val txt = driver.findElement(AppiumBy.id("android:id/title")).text
        Assert.assertEquals(txt, "Sample menu")
        Assert.assertTrue(
            driver.findElement(AppiumBy.id("android:id/title")).isDisplayed
        )
    }

    @Test
    fun scrollGestureTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click()
        scrollToEnd()
    }

    @Test
    fun swipeGestureTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click()
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click()
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click()

        val imageElement = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"))
        Assert.assertEquals(imageElement.getAttribute("focusable"), "true")
        swipeLeft(imageElement)
        Assert.assertEquals(imageElement.getAttribute("focusable"), "false")
    }
}
