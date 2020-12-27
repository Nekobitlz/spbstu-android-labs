package ru.spbstu.lab3_2

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.allOf

class AboutScreen : BaseScreen<AboutScreen>() {

    override fun validate() {
        onView(
            allOf(withId(R.id.tv_about_title), isDisplayed())
        ).check(
            matches(withText(R.string.about_title))
        )
    }

    fun pressBack() {
        Espresso.pressBack()
    }
}