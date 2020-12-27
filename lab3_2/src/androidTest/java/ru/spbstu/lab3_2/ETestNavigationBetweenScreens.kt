package ru.spbstu.lab3_2

import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

class ETestNavigationBetweenScreens {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testNavigationBetweenScreens() {
        FirstScreen().clickToSecondButton()
            .clickToFirstButton()
            .clickToSecondButton()
            .clickToThirdButton()
            .clickToSecondButton()
            .clickToThirdButton()
            .clickToFirstButton()
    }
}