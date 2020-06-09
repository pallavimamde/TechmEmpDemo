package com.techmahindra.techmEmployeePortal.view

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.techmahindra.techmEmployeePortal.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class ActivityAddEditEmployeeTest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TechmEmployeeListActivity::class.java)
    @Test
    fun testStartProjectListActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.img_btn_add_project)).perform(click())
        Assert.assertEquals(getProjectActivityInstance(), ProjectActivity::class.java)
    }

    @Test
    fun getProjectActivityInstance(): Activity {
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
    fun addEditEmployeeActivityTest() {

        val employeeIdEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_id),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    2
                )
            )
        )
        employeeIdEditText.perform(scrollTo(), replaceText("480819"), closeSoftKeyboard())

        val employeeNameEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_name),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    3
                )
            )
        )
        employeeNameEditText.perform(scrollTo(), replaceText("pooja patil "), closeSoftKeyboard())

        val bandEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_band),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    4
                )
            )
        )
        bandEditText.perform(scrollTo(), replaceText("u4"), closeSoftKeyboard())

        val designationEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_designation),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    5
                )
            )
        )
        designationEditText.perform(scrollTo(), replaceText("project manager "), closeSoftKeyboard())

        val competencyRadioButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.radio_tester), ViewMatchers.withText("Tester"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.rg_emp_competency),
                        childAtPosition(
                            ViewMatchers.withId(R.id.const_layout_emp_list),
                            7
                        )
                    ),
                    3
                )
            )
        )
        competencyRadioButton.perform(scrollTo(), click())

        val addProjectImageButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.img_btn_add_project),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    10
                )
            )
        )
        addProjectImageButton.perform(scrollTo(), click())

        val projectEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.editTextProjectNam), ViewMatchers.withText("Enter Project"),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(LinearLayout::class.java),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        projectEditText.check(ViewAssertions.matches(ViewMatchers.withText("Enter Project")))

        val projectNameTextView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.textViewProjectInfo), ViewMatchers.withText("ATT"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.projectInfoList),
                        0
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        projectNameTextView.check(ViewAssertions.matches(ViewMatchers.withText("ATT")))

        val projectAddButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.buttonProjectAdd),
                childAtPosition(
                    childAtPosition(
                        IsInstanceOf.instanceOf(LinearLayout::class.java),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        projectAddButton.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val idEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_id), ViewMatchers.withText("ID"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            IsInstanceOf.instanceOf(ScrollView::class.java),
                            0
                        )
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        idEditText.check(matches(withText("ID")))

        val empIdEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_id),
                ViewMatchers.withText("Please enter your employee id"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            IsInstanceOf.instanceOf(ScrollView::class.java),
                            0
                        )
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        empIdEditText.check(matches(withText("Please enter your employee id")))

        val empNameEditText = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_name),
                ViewMatchers.withText("Please enter your full name"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            IsInstanceOf.instanceOf(ScrollView::class.java),
                            0
                        )
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        empNameEditText.check(matches(withText("Please enter your full name")))

        val bandEditTextDisplay = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_band),
                ViewMatchers.withText("Please enter your band"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            IsInstanceOf.instanceOf(ScrollView::class.java),
                            0
                        )
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bandEditTextDisplay.check(matches(withText("Please enter your band")))

        val designationEditTextDisplay = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_designation),
                ViewMatchers.withText("Please enter your designation"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            IsInstanceOf.instanceOf(ScrollView::class.java),
                            0
                        )
                    ),
                    4
                ),
                ViewMatchers.isDisplayed()
            )
        )
        designationEditTextDisplay.check(matches(withText("Please enter your designation")))

        val competencyRadioButtonDisplay = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.radio_android),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.rg_emp_competency),
                        childAtPosition(
                            ViewMatchers.withId(R.id.const_layout_emp_list),
                            6
                        )
                    ),
                    0
                ),
                ViewMatchers.isDisplayed()
            )
        )
        competencyRadioButtonDisplay.check(matches(isDisplayed()))




        val androidRadioButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.radio_tester),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.rg_emp_competency),
                        childAtPosition(
                            ViewMatchers.withId(R.id.const_layout_emp_list),
                            6
                        )
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        androidRadioButton.check(matches(isDisplayed()))

        val projectLabelTextView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.tv_emp_project_label), ViewMatchers.withText("Project :"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            IsInstanceOf.instanceOf(ScrollView::class.java),
                            0
                        )
                    ),
                    7
                ),
                ViewMatchers.isDisplayed()
            )
        )
        projectLabelTextView.check(matches(withText("Project :")))

        val projectAddImageButtonDisplay = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.img_btn_add_project),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            IsInstanceOf.instanceOf(ScrollView::class.java),
                            0
                        )
                    ),
                    8
                ),
                ViewMatchers.isDisplayed()
            )
        )
        projectAddImageButtonDisplay.check(matches(isDisplayed()))

        val projectListSpinner = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.spinner_emp_project),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            IsInstanceOf.instanceOf(ScrollView::class.java),
                            0
                        )
                    ),
                    9
                ),
                ViewMatchers.isDisplayed()
            )
        )
        projectListSpinner.check(matches(isDisplayed()))

        val activityTitleTextView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withText("Add Employee"),
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
        activityTitleTextView.check(matches(withText("Add Employee")))

        val employeeIdEditTextTextReplace = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_id),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    2
                )
            )
        )
        employeeIdEditTextTextReplace.perform(scrollTo(), replaceText("Poonam Rao "), closeSoftKeyboard())

        val employeeIdEditTextContainerItemClick = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.etv_add_emp_id),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    2
                )
            )
        )
        employeeIdEditTextContainerItemClick.perform(scrollTo(), click())


        val projectListSpiinerOnClick = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.spinner_emp_project),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    9
                )
            )
        )
        projectListSpiinerOnClick.perform(scrollTo(), click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        val submitButtonClick = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.btn_add_emp_submit), ViewMatchers.withText("Submit"),
                childAtPosition(
                    Matchers.allOf(
                        ViewMatchers.withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            ViewMatchers.withClassName(Matchers.`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    12
                )
            )
        )
        submitButtonClick.perform(scrollTo(), click())
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
