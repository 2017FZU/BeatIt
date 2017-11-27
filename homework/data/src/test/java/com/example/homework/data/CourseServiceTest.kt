package com.example.homework.data

import com.example.homework.data.test.TestService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by 59800 on 2017/11/14.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)

class CourseServiceTest {

    @Before
    fun setUp() {
        DataLayer.init(RuntimeEnvironment.application)
    }
}