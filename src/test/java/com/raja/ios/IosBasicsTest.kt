package com.raja.ios

import io.appium.java_client.AppiumBy
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
}