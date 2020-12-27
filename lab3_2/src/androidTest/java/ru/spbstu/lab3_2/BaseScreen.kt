package ru.spbstu.lab3_2

import android.util.Log
import android.view.Gravity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions.close
import androidx.test.espresso.contrib.DrawerActions.open
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.contrib.NavigationViewActions.navigateTo
import androidx.test.espresso.matcher.ViewMatchers.withId

abstract class BaseScreen<T : BaseScreen<T>> {

    init {
        validate()
    }

    abstract fun validate()

    fun openDrawer(): T {
        onView(withId(R.id.drawer))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(open())
            .check(matches(isOpen(Gravity.LEFT)))

        return this as T
    }

    fun clickOpenAboutButton(): AboutScreen {
        onView(withId(R.id.nav_view)).perform(navigateTo(R.id.open_about_screen))
        return AboutScreen()
    }

    fun clickToFirstButton(): FirstScreen {
        onView(withId(R.id.btn_to_first)).perform(click())
        return FirstScreen()
    }

    fun clickToSecondButton(): SecondScreen {
        onView(withId(R.id.btn_to_second)).perform(click())
        return SecondScreen()
    }

    fun clickToThirdButton(): ThirdScreen {
        onView(withId(R.id.btn_to_third)).perform(click())
        return ThirdScreen()
    }

    protected fun log(text: String) {
        Log.i(javaClass.simpleName, text)
    }
}