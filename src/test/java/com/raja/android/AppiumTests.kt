package com.raja.android

import io.appium.java_client.AppiumBy
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent
import org.openqa.selenium.By
import org.openqa.selenium.DeviceRotation
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

    @Test
    fun dragAndDropGestureTest() {
        driver.findElement(AppiumBy.accessibilityId("Views")).click()
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click()

        val source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"))
        drag(source, 800.0, 800.0)

        val confirmText = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_result_text")).text
        Assert.assertEquals(confirmText, "Dropped!")
    }

    @Test
    fun miscTests() {
        driver.findElement(AppiumBy.accessibilityId("Preference")).click()
        driver
            .findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]"))
            .click()
        driver.findElement(AppiumBy.id("android:id/checkbox")).click()

        // Landscape
        val landscape = DeviceRotation(0, 0, 90)
        driver.rotate(landscape)

        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click()

        val alertTitle = driver.findElement(By.id("android:id/alertTitle")).text
        Assert.assertEquals(alertTitle, "WiFi settings")

        // Clipboard
        driver.clipboardText = "RajaWifi"
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys(driver.clipboardText)

        driver.pressKey(KeyEvent(AndroidKey.ENTER))
        driver.findElements(AppiumBy.className("android.widget.Button"))[1].click()

        driver.pressKey(KeyEvent(AndroidKey.BACK))
        driver.pressKey(KeyEvent(AndroidKey.HOME))
    }

    @Test
    fun activityTest() {
        startActivity("io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies")

        driver.findElement(AppiumBy.id("android:id/checkbox")).click()
        driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]")).click()

        val alertTitle = driver.findElement(By.id("android:id/alertTitle")).text
        Assert.assertEquals(alertTitle, "WiFi settings")

        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("RajaWifi")
        driver.findElements(AppiumBy.className("android.widget.Button"))[1].click()

    }
}
