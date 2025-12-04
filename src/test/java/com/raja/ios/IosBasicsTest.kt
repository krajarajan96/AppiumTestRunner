package com.raja.ios

import io.appium.java_client.AppiumBy
import org.openqa.selenium.remote.RemoteWebElement
import org.testng.Assert
import org.testng.annotations.Test

class IosBasicsTest: BaseTest()
{
    @Test
    fun testClickAndSendKeys() {
        driver.findElement(AppiumBy.accessibilityId("Alert Views")).click()
        driver
            .findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`name == 'Text Entry'`]"))
            .click()
        driver
            .findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeCell"))
            .sendKeys("Rajarajan")
        driver
            .findElement(AppiumBy.accessibilityId("OK"))
            .click()
        driver
            .findElement(AppiumBy.accessibilityId("Confirm / Cancel"))
            .click()

        val title = driver
            .findElement(
                AppiumBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND name BEGINSWITH[c] 'a message'"))
            .text
//            .findElement(
//            AppiumBy.iOSNsPredicateString(
//            "type == 'XCUIElementTypeStaticText' AND value BEGINSWITH[c] 'confirm'"
//            )
//            )
        println("<<< TITLE: $title")

        Assert.assertEquals(title, "A message should be a short, complete sentence.")

        driver.findElement(AppiumBy.iOSNsPredicateString("name == 'Confirm'"))
            .click()
    }

    @Test
    fun testLongPress() {
        driver
            .findElement(AppiumBy.accessibilityId("Steppers")).click()
        val buttonElement = driver
            .findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`name == 'Increment'`][3]"))
        longPress(buttonElement)
    }

    @Test
    fun testScroll() {
        val webViewElement = driver
            .findElement(AppiumBy.accessibilityId("Web View"))
        scrollBottomTo(webViewElement)
        webViewElement.click()
        driver
            .findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='BackButton']")).click()
        driver
            .findElement(AppiumBy.accessibilityId("Picker View")).click()

        val redElement =
            driver
                .findElement(AppiumBy.accessibilityId("Red color component value"))
        redElement
                .sendKeys("80")
        val greenElement =
            driver
                .findElement(AppiumBy.accessibilityId("Green color component value"))
        greenElement
                .sendKeys("120")
        val blueElement =
            driver
                .findElement(AppiumBy.accessibilityId("Blue color component value"))
        blueElement
                .sendKeys("160")

        Assert.assertEquals(redElement.text, "80")
        Assert.assertEquals(greenElement.text, "120")
        Assert.assertEquals(blueElement.text, "160")
        Thread.sleep(2000)
    }

    @Test
    fun testIOSSlider() {
        // Find slider element
        // Send keys = 0% to 1%

    }
}