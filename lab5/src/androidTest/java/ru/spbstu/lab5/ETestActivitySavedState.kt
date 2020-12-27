package ru.spbstu.lab5

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.util.Log
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.apache.commons.lang.RandomStringUtils
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ETestActivitySavedState {

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun testActivitySavedState() {
        val text = RandomStringUtils.randomAlphabetic(8)

        log("Trying to type text")
        onView(
            allOf(withId(R.id.edit_text), isDisplayed())
        ).perform(
            clearText(),
            typeText(text),
            closeSoftKeyboard()
        ).check(
            matches(withText(text))
        )
        log("Text typed successfully")

        log("Trying to click a button")
        onView(
            allOf(withId(R.id.button), isDisplayed())
        ).check(
            matches(withText(R.string.button_text))
        ).perform(
            click()
        ).check(
            matches(withText(R.string.button_text_pressed))
        )
        log("Button is successfully clicked")

        log("Trying to rotate screen")
        activityRule.scenario.onActivity { activity ->
            val currentOrientation = activity.resources.configuration.orientation
            val newOrientation = if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            activity.requestedOrientation = newOrientation
        }
        log("Screen is successfully rotated")

        log("Check typed text is saved")
        onView(
            allOf(withId(R.id.edit_text), isDisplayed())
        ).check(
            matches(withText(text))
        )
        log("Typed text is saved")

        log("Check button text")
        onView(
            allOf(withId(R.id.button), isDisplayed())
        ).check(
            matches(withText(R.string.button_text))
        )
        log("Button text is correct")
    }

    private fun log(text: String) {
        Log.i(javaClass.simpleName, text)
    }
}