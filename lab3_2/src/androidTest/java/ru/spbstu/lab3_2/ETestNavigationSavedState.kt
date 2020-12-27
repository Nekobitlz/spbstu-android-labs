package ru.spbstu.lab3_2

import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

class ETestNavigationSavedState {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testNavigationSavedState() {
        FirstScreen().clickToSecondButton()
            .rotate()
            .clickToThirdButton()
            .rotate()
            .clickToFirstButton()
            .rotate()
    }
}