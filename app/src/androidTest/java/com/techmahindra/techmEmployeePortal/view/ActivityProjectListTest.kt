package com.techmahindra.techmEmployeePortal.view

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.techmahindra.techmEmployeePortal.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class ActivityProjectListTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TechmEmployeeListActivity::class.java)

    @Test
    fun addEditEmployeeActivityTest() {

        val projectNameEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editTextProjectNam),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        projectNameEditText.perform(replaceText("ATT"), closeSoftKeyboard())

        val addProjectButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.buttonProjectAdd),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withClassName(Matchers.`is`("android.widget.LinearLayout")),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        addProjectButton.perform(click())

        val projectNameListItemTextView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.textViewProjectInfo), ViewMatchers.withText("bell canda "),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.linearLayoutProjectItem),
                        childAtPosition(
                            ViewMatchers.withId(R.id.projectInfoList),
                            0
                        )
                    ),
                    5
                ),
                ViewMatchers.isDisplayed()
            )
        )
        projectNameListItemTextView.check(ViewAssertions.matches(ViewMatchers.withText("bell canda ")))

        val ActivityTitleTextView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withText("Add Project"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.action_bar),
                        childAtPosition(
                            ViewMatchers.withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        ActivityTitleTextView.check(ViewAssertions.matches(ViewMatchers.withText("Add Project")))

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
