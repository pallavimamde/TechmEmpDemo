package com.techmahindra.techmEmployeePortal.view


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.techmahidra.optustest.ui.userinfo.CustomAssertions.Companion.hasItemCount
import com.techmahidra.optustest.ui.userinfo.CustomMatchers.Companion.withItemCount
import com.techmahindra.techmEmployeePortal.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TechmEmployeeListActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(TechmEmployeeListActivity::class.java)

    @Test
    fun techmEmployeeListActivityTest() {
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.action_add), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val appCompatEditText = onView(
            allOf(
                withId(R.id.etv_add_emp_id),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    2
                )
            )
        )
        appCompatEditText.perform(scrollTo(), replaceText("4808"), closeSoftKeyboard())
        Thread.sleep(7000)
        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.etv_add_emp_id), withText("4808"),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    2
                )
            )
        )
        appCompatEditText2.perform(scrollTo(), click())
        Thread.sleep(7000)
        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.etv_add_emp_id), withText("4808"),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    2
                )
            )
        )
        appCompatEditText3.perform(scrollTo(), replaceText("480819"))
        Thread.sleep(7000)
        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.etv_add_emp_id), withText("480819"),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.etv_add_emp_name),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    3
                )
            )
        )
        appCompatEditText5.perform(scrollTo(), replaceText("Pooja"), closeSoftKeyboard())
        Thread.sleep(7000)
        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.etv_add_emp_band),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    4
                )
            )
        )
        appCompatEditText6.perform(scrollTo(), replaceText("u3"), closeSoftKeyboard())
        Thread.sleep(7000)
        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.etv_add_emp_designation),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    5
                )
            )
        )
        appCompatEditText7.perform(scrollTo(), replaceText("engineerr"), closeSoftKeyboard())

        pressBack()
        Thread.sleep(7000)
        val appCompatRadioButton = onView(
            allOf(
                withId(R.id.radio_android), withText("Android"),
                childAtPosition(
                    allOf(
                        withId(R.id.rg_emp_competency),
                        childAtPosition(
                            withId(R.id.const_layout_emp_list),
                            7
                        )
                    ),
                    0
                )
            )
        )
        appCompatRadioButton.perform(scrollTo(), click())
        Thread.sleep(7000)
        val appCompatImageButton = onView(
            allOf(
                withId(R.id.img_btn_add_project),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    10
                )
            )
        )
        appCompatImageButton.perform(scrollTo(), click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.projectName),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(replaceText("att"), closeSoftKeyboard())
        Thread.sleep(7000)
        val appCompatButton = onView(
            allOf(
                withId(R.id.buttonAdd),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton.perform(click())
        Thread.sleep(7000)
        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.projectName),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText9.perform(replaceText("transport "), closeSoftKeyboard())
        Thread.sleep(7000)
        val appCompatButton2 = onView(
            allOf(
                withId(R.id.buttonAdd),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        pressBack()

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.spinner_emp_project),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    9
                )
            )
        )
        appCompatSpinner.perform(scrollTo(), click())

        val appCompatButton3 = onView(
            allOf(
                withId(R.id.btn_add_emp_submit), withText("Submit"),
                childAtPosition(
                    allOf(
                        withId(R.id.const_layout_emp_list),
                        childAtPosition(
                            withClassName(`is`("android.widget.ScrollView")),
                            0
                        )
                    ),
                    12
                )
            )
        )
        appCompatButton3.perform(scrollTo(), click())

        pressBack()

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val appCompatImageView = onView(
            allOf(
                withId(R.id.search_button), withContentDescription("Search"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_bar),
                        childAtPosition(
                            withId(R.id.searchView),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("480819"), closeSoftKeyboard())

        val searchAutoComplete2 = onView(
            allOf(
                withId(R.id.search_src_text), withText("480819"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(pressImeActionButton())

        pressBack()
    }
}
    @Test
    fun userScrollingAction() {
        @Test
        fun recyclerViewTestScrolling() {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val viewGroup1 = onView(
                allOf(
                    withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            withId(R.id.constraintLayEmpInfo)

                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.check(ViewAssertions.matches(isDisplayed()))
        }

        @Test
        fun recyclerViewTestScrollingToPositionEndIndex() {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val viewGroup1 = onView(
                allOf(
                    withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            withId(R.id.constraintLayEmpInfo)

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
            val viewGroup1 = onView(
                allOf(
                    withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            withId(R.id.constraintLayEmpInfo)

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
            val viewGroup1 = onView(
                allOf(
                    withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            withId(R.id.constraintLayEmpInfo)

                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    8,
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
            val viewGroup1 = onView(
                allOf(
                    withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            withId(R.id.constraintLayEmpInfo)

                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.check(ViewAssertions.matches(withItemCount(10)))
        }

        @Test
        fun countProgramsWithViewAssertion() {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val viewGroup1 = onView(
                allOf(
                    withId(R.id.employeeList),
                    childAtPosition(
                        allOf(
                            withId(R.id.constraintLayEmpInfo)

                        ),
                        0
                    ),
                    isDisplayed()
                )
            )
            viewGroup1.check(hasItemCount(10))
        }

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

