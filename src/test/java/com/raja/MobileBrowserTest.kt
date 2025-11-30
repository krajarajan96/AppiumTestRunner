package com.raja

import org.testng.annotations.Test

class MobileBrowserTest: BrowserBaseTest() {

    @Test
    fun testGoogle() {
        driver.get("https://www.google.com")
        // Similar to selenium automation
        // No need of @ for CSS tags
        // CSS: a[routerlink='Products']
        // CSS- all matches of Products: a[routerlink*='Products']

    }
}