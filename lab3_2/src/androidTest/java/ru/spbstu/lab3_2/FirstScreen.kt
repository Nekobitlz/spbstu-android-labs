package ru.spbstu.lab3_2

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf

class FirstScreen : BaseScreen<FirstScreen>() {

    override fun validate() {
        log("Validating the first screen")
        onView(withId(R.id.btn_to_first)).check(doesNotExist())
        onView(withId(R.id.btn_to_third)).check(doesNotExist())
        onView(
            allOf(withId(R.id.btn_to_second), isDisplayed())
        ).check(
            matches(withText(R.string.to_second))
        )
    }

    fun pressBack() {
        Espresso.pressBackUnconditionally()
    }
}

class SecondScreen : BaseScreen<SecondScreen>() {

    override fun validate() {
        log("Validating the second screen")
        onView(withId(R.id.btn_to_second)).check(doesNotExist())
        onView(
            allOf(withId(R.id.btn_to_first), isDisplayed())
        ).check(
            matches(withText(R.string.to_first))
        )
        onView(
            allOf(withId(R.id.btn_to_third), isDisplayed())
        ).check(
            matches(withText(R.string.to_third))
        )
    }

    fun pressBack(): FirstScreen {
        Espresso.pressBack()
        return FirstScreen()
    }
}

class ThirdScreen : BaseScreen<ThirdScreen>() {

    override fun validate() {
        log("Validating the third screen")
        onView(withId(R.id.btn_to_third)).check(doesNotExist())
        onView(
            allOf(withId(R.id.btn_to_first), isDisplayed())
        ).check(
            matches(withText(R.string.to_first))
        )
        onView(
            allOf(withId(R.id.btn_to_second), isDisplayed())
        ).check(
            matches(withText(R.string.to_second))
        )
    }

    fun pressBack(): SecondScreen {
        Espresso.pressBack()
        return SecondScreen()
    }
}