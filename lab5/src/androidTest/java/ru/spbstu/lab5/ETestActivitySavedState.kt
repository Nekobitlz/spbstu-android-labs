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

        Log.i(javaClass.simpleName, "Trying to type text")
        onView(
            allOf(withId(R.id.edit_text), isDisplayed())
        ).perform(
            clearText(),
            typeText(text),
            closeSoftKeyboard()
        ).check(
            matches(withText(text))
        )
        Log.i(javaClass.simpleName, "Text typed successfully")

        Log.i(javaClass.simpleName, "Trying to click a button")
        onView(
            allOf(withId(R.id.button), isDisplayed())
        ).check(
            matches(withText(R.string.button_text))
        ).perform(
            click()
        ).check(
            matches(withText(R.string.button_text_pressed))
        )
        Log.i(javaClass.simpleName, "Button is successfully clicked")

        Log.i(javaClass.simpleName, "Rotate screen")
        activityRule.scenario.onActivity { activity ->
            val currentOrientation = activity.resources.configuration.orientation
            val newOrientation = if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            activity.requestedOrientation = newOrientation
        }
        Log.i(javaClass.simpleName, "Screen is successfully rotated")

        Log.i(javaClass.simpleName, "Check typed text is saved")
        onView(
            allOf(withId(R.id.edit_text), isDisplayed())
        ).check(
            matches(withText(text))
        )
        Log.i(javaClass.simpleName, "Typed text is saved")

        Log.i(javaClass.simpleName, "Check button text")
        onView(
            allOf(withId(R.id.button), isDisplayed())
        ).check(
            matches(withText(R.string.button_text))
        )
        Log.i(javaClass.simpleName, "Button text is correct")
    }
}