package com.example.toshiba.footballmatch.view


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.toshiba.footballmatch.R.id.*

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun homeActivityTest() {
        onView(withId(rv_prev))
                .check(matches(isDisplayed()))
        onView(withId(rv_prev)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(favorite))
                .check(matches(isDisplayed()))
        onView(withId(favorite)).perform(click())
        onView(withText("Add to Favorite"))
                .check(matches(isDisplayed()))

        pressBack()

        onView(withId(navigation))
                .check(matches(isDisplayed()))
        onView(withId(nav_next)).perform(click())

        onView(withId(rv_next))
                .check(matches(isDisplayed()))
        onView(withId(rv_next)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(favorite))
                .check(matches(isDisplayed()))
        onView(withId(favorite)).perform(click())
        onView(withText("Add to Favorite"))
                .check(matches(isDisplayed()))

        onView(withId(favorite)).perform(click())
        onView(withText("Remove from Favorite"))
                .check(matches(isDisplayed()))

        pressBack()
    }
}
