package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Guess The Brand", appName)
  }

  @Test
  fun `verify brand catalog has uploaded brands`() {
    val brands = com.example.model.BrandCatalog.allBrands
    org.junit.Assert.assertEquals(75, brands.size)
    val requestedNames = listOf(
        // First batch
        "APPLE", "AQUAFINA", "ARCADE", "ARIEL", "ARSENAL",
        "ARTEGA", "AIR ASIA", "ASICS", "ASTON MARTIN", "ASUS",
        // New 20 brands
        "ATARI", "ATI", "ATOMIC", "ATT", "AUDI",
        "AUDIOSLAVE", "AUNTIE ANNES", "AVG", "AVIVA", "AVON",
        "AXA", "AXN", "AXE", "AXIAM", "BABOLAT",
        "BABOR", "BACARDI", "BAIDU", "BAD ROBOT", "BAD PIGGIES"
    )
    for (name in requestedNames) {
        val item = brands.find { it.name == name }
        org.junit.Assert.assertNotNull("Brand $name should exist in catalog", item)
    }
  }

  @Test
  fun `verify initial screen is MainMenu splash and can navigate to LevelSelect`() {
    val app = ApplicationProvider.getApplicationContext<android.app.Application>()
    val vm = com.example.viewmodel.QuizViewModel(app)
    org.junit.Assert.assertEquals(com.example.viewmodel.Screen.MainMenu, vm.currentScreen.value)
    vm.navigateTo(com.example.viewmodel.Screen.LevelSelect)
    org.junit.Assert.assertEquals(com.example.viewmodel.Screen.LevelSelect, vm.currentScreen.value)
  }
}
