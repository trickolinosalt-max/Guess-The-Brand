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
    org.junit.Assert.assertEquals(55, brands.size)
    val requestedNames = listOf(
        "APPLE", "AQUAFINA", "BACARDI", "ARIEL", "ARSENAL",
        "ARTEGA", "AIR ASIA", "ASICS", "ASTON MARTIN", "ASUS"
    )
    for (name in requestedNames) {
        val item = brands.find { it.name == name }
        org.junit.Assert.assertNotNull("Brand $name should exist in catalog", item)
    }
  }
}
