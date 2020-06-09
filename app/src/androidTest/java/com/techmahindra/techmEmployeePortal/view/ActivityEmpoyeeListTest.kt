package com.techmahindra.techmEmployeePortal.view


import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.techmahidra.optustest.ui.userinfo.CustomMatchers
import com.techmahindra.techmEmployeePortal.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.internal.matchers.TypeSafeMatcher
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class TechmEmployeeListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TechmEmployeeListActivity::class.java)

    @Test
    fun testEmployeeListStartAddEmployeeActivity() {
        onView(ViewMatchers.withId(R.id.action_add)).perform(click())
        onView(ViewMatchers.withId(R.id.constraintlayoutEmployeelist)).perform(click())
        Assert.assertEquals(getActivityInstance(), AddTechmEmployeeActivity::class.java)
    }

    @Test
    fun getActivityInstance(): Activity {
        val currentActivity = arrayOf<Activity>()
        InstrumentationRegistry.getInstrumentation().runOnMainSync(object : Runnable {
            public override fun run() {
                val resumedActivities =
                    ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(
                        Stage.RESUMED
                    )
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity[0] = resumedActivities.iterator().next() as Activity
                }
            }
        })
        return currentActivity[0]
    }

    @Test
    fun techmEmployeeListActivityTest() {


        val addButtonTextViewDiplay = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.action_add), ViewMatchers.withText("ADD"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        addButtonTextViewDiplay.check(matches(withText("ADD")))

        val addEmployeeButton = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.action_add), ViewMatchers.withText("Add"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        addEmployeeButton.perform(ViewActions.click())


        val linearLayout = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.search_bar),
                childAtPosition(
                    allOf(
                        ViewMatchers.withId(R.id.searchViewEmployee),
                        childAtPosition(
                            ViewMatchers.withId(R.id.constraintlayoutEmployeelist),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

        val imageView = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.search_button),
                ViewMatchers.withContentDescription("Search"),
                childAtPosition(
                    allOf(
                        ViewMatchers.withId(R.id.search_bar),
                        childAtPosition(
                            ViewMatchers.withId(R.id.searchViewEmployee),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val employeeListItemEmpId = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.textViewEmpyoyeeId), withText("480911"),
                childAtPosition(
                    allOf(
                        ViewMatchers.withId(R.id.constraintLayEmpInfo),
                        childAtPosition(
                            ViewMatchers.withId(R.id.employeeList),
                            0
                        )
                    ),
                    4
                ),
                ViewMatchers.isDisplayed()
            )
        )
        employeeListItemEmpId.check(matches(withText("480911")))

        val employeeListItemEmpNameBand = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.textViewEmployeeNameBand), withText("poonam  patil(p1)"),
                childAtPosition(
                    allOf(
                        ViewMatchers.withId(R.id.constraintLayEmpInfo),
                        childAtPosition(
                            ViewMatchers.withId(R.id.employeeList),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        employeeListItemEmpNameBand.check(matches(withText("poonam  patil(p1)")))

        val employeeListItemEmpDesignation = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.textViewEmpDesignation), withText("project manager"),
                childAtPosition(
                    allOf(
                        ViewMatchers.withId(R.id.constraintLayEmpInfo),
                        childAtPosition(
                            ViewMatchers.withId(R.id.employeeList),
                            0
                        )
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        employeeListItemEmpDesignation.check(matches(withText("project manager")))

        val employeeListItemEmpCompetency = Espresso.onView(
            allOf(
                ViewMatchers.withId(R.id.textViewEmployeeCompetency), withText("iOS"),
                childAtPosition(
                    allOf(
                        ViewMatchers.withId(R.id.constraintLayEmpInfo),
                        childAtPosition(
                            ViewMatchers.withId(R.id.employeeList),
                            0
                        )
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        employeeListItemEmpCompetency.check(matches(withText("iOS")))


        val activityLableTextView = Espresso.onView(
            allOf(
                ViewMatchers.withText("Employee Information"),
                childAtPosition(
                    allOf(
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
        activityLableTextView.check(ViewAssertions.matches(ViewMatchers.withText("Employee Information")))

        @Test
        fun recyclerViewTestScrolling() {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val viewGroup1 = Espresso.onView(
                allOf(
                    ViewMatchers.withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            ViewMatchers.withId(R.id.swipeToRefresh),
                            childAtPosition(
                                IsInstanceOf.instanceOf(FrameLayout::class.java),
                                0
                            )
                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.check(matches(ViewMatchers.isDisplayed()))
        }

        @Test
        fun recyclerViewTestScrollingToPositionEndIndex() {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val viewGroup1 = Espresso.onView(
                allOf(
                    ViewMatchers.withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            ViewMatchers.withId(R.id.swipeToRefresh),
                            childAtPosition(
                                ViewMatchers.withId(R.id.constraintlayoutEmployeelist),
                                1
                            )
                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.perform(ViewActions.swipeUp())
        }

        @Test
        fun recyclerViewTestScrollingToPositionTop() {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val viewGroup1 = Espresso.onView(
                allOf(
                    ViewMatchers.withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            ViewMatchers.withId(R.id.swipeToRefresh),
                            childAtPosition(
                                ViewMatchers.withId(R.id.constraintlayoutEmployeelist),
                                1
                            )
                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.perform(ViewActions.swipeDown())
        }

        @Test
        fun recyclerViewClick() {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val viewGroup1 = Espresso.onView(
                allOf(
                    ViewMatchers.withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            ViewMatchers.withId(R.id.swipeToRefresh),
                            childAtPosition(
                                ViewMatchers.withId(R.id.constraintlayoutEmployeelist),
                                1
                            )
                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    2,
                    click()
                )
            )
        }

        @Test
        fun countPrograms() {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val viewGroup1 = Espresso.onView(
                allOf(
                    ViewMatchers.withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            ViewMatchers.withId(R.id.swipeToRefresh),
                            childAtPosition(
                                ViewMatchers.withId(R.id.constraintlayoutEmployeelist),
                                1
                            )
                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.check(matches(CustomMatchers.withItemCount(10)))
        }

        Espresso.pressBack()
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
