// Espresso
// Core library
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.androidhomework.R
import com.androidhomework.ui.splash.SplashActivity
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailViewTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun splashActivityTest() {
        //Todo Wait For splash time 3 second
        onView(ViewMatchers.isRoot()).perform(waitFor(3000))

        //Todo Wait For Api data loading approx time 5 second
        onView(ViewMatchers.isRoot()).perform(waitFor(5000))
        val recyclerView = onView(withId(R.id.recyclerView))
        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        //Todo Wait For Opening Details Screen
        onView(ViewMatchers.isRoot()).perform(waitFor(1000))
        val appCompatEditText = onView(withId(R.id.etNote))
        appCompatEditText.perform(ViewActions.scrollTo(), ViewActions.replaceText("test"), ViewActions.closeSoftKeyboard())

        //Todo Click on Submit button for save notes in database
        val appCompatButton = onView(withId(R.id.btnSubmit))
        appCompatButton.perform(ViewActions.scrollTo(), ViewActions.click())

        //Todo Wait For Reflect data from database
        onView(ViewMatchers.isRoot()).perform(waitFor(1000))
    }

    private fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}