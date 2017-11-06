package com.example.homework.data

import com.example.homework.data.service.GankService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class GankServiceTest {

    @Before
    fun setUp() {
        DataLayer.init(RuntimeEnvironment.application)
    }

    @Test
    fun testGetMeizis() {
        val meizis = GankService.getMeizis(10, 1).blockingFirst()
        print(meizis)
        Assert.assertTrue(meizis.isNotEmpty())
    }
}