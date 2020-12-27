package ru.spbstu.lab3_2

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import org.hamcrest.Matcher
import org.hamcrest.Matchers

class ChangeOrientationAction : ViewAction {

    override fun getConstraints(): Matcher<View> = Matchers.any(View::class.java)

    override fun getDescription(): String = "Change screen orientation"

    override fun perform(uiController: UiController, view: View) {
        val currentOrientation = view.resources.configuration.orientation
        val newOrientation = if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        val resumedActivities = ActivityLifecycleMonitorRegistry
            .getInstance()
            .getActivitiesInStage(Stage.RESUMED)

        if (resumedActivities.isEmpty()) {
            throw RuntimeException("There are no currently resumed activities")
        }
        if (resumedActivities.size > 1) {
            throw RuntimeException("There are too many currently resumed activities: $resumedActivities")
        }

        for (activity in resumedActivities) {
            activity.requestedOrientation = newOrientation
        }
    }
}