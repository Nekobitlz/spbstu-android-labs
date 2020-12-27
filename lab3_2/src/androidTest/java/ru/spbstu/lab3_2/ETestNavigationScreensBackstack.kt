package ru.spbstu.lab3_2

import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

class ETestNavigationScreensBackstack {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testNavigationScreensBackstack() {
        FirstScreen().clickToSecondButton()
            .pressBack()
            .clickToSecondButton()
            .clickToThirdButton()
            .pressBack()
            .pressBack()
            .clickToSecondButton()
            .clickToThirdButton()
            .clickToFirstButton()
            .pressBack()
    }
}