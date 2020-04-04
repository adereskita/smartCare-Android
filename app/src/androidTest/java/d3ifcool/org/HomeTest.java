package d3ifcool.org;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeTest {

    FirebaseUser cUser;
    FirebaseAuth mFirebaseAuth;

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);

    public void sleep1S() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void sleep3S() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void StepTesting(){

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.btn_signUp)).perform(click());
        sleep3S();
        onView(withId(R.id.edt_nama)).perform(typeText("Testing Me"));
        sleep1S();
        onView(withId(R.id.edt_email)).perform(typeText("testingMe@gmail.com"));
        sleep1S();
        onView(withId(R.id.edt_password)).perform(typeText("adereskita12"));
        sleep1S();
        onView(withId(R.id.btn_signUp)).perform(click());
        sleep3S();

    }

}