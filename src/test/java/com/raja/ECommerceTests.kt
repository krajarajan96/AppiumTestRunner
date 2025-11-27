package com.raja

import io.appium.java_client.AppiumBy
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.Test
import java.time.Duration


class ECommerceTests: BaseTest() {

    @Test
    fun testPersonalInfoScreen() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField"))
            .sendKeys("Rajarajan K")
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale"))
            .click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry"))
            .click()

        scrollToElement("Chad")
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Chad\"]"))
            .click()
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click()
        Thread.sleep(3000)
    }

    @Test
    fun testEnterNameToast() {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop"))
            .click()

        val toastLocator = AppiumBy.xpath("//android.widget.Toast[@text='Please enter your name']")
        val waitForToast = WebDriverWait(driver, Duration.ofMillis(10))
        waitForToast.until(ExpectedConditions.presenceOfElementLocated(toastLocator))

        val toast = driver.findElement(toastLocator)
        Assert.assertTrue(toast.isDisplayed)
        val toastMsg = toast.getAttribute("name")
        Assert.assertEquals(toastMsg, "Please enter your name")
    }
}