package ru.spbstu.lab3_2

import androidx.test.espresso.Espresso.*
import androidx.test.ext.junit.rules.activityScenarioRule
import org.junit.Rule
import org.junit.Test

class ETestNavigationToAboutScreen {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testNavigationToAboutScreen() {
        val firstScreen = FirstScreen()
        firstScreen.openDrawer()
            .clickOpenAboutButton()
            .pressBack()

        val secondScreen = firstScreen.clickToSecondButton()
        secondScreen.openDrawer()
            .clickOpenAboutButton()
            .pressBack()

        val thirdScreen = secondScreen.clickToThirdButton()
        thirdScreen.openDrawer()
            .clickOpenAboutButton()
            .pressBack()
    }
}