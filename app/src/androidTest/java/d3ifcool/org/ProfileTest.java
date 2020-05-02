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
public class ProfileTest {

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
    public void HomeTesting(){

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.btn_login)).perform(click());
        sleep3S();
        onView(withId(R.id.edt_email)).perform(typeText("ade.reskita@yahoo.com"));
        sleep1S();
        onView(withId(R.id.edt_password)).perform(typeText("adereskita12"));
        sleep1S();
        onView(withId(R.id.btn_login)).perform(click());
        sleep3S();
        onView(withId(R.id.const_profile)).perform(click());
        sleep1S();
        onView(withId(R.id.btn_editProfile)).perform(click());
        sleep1S();
        onView(withId(R.id.edt_nama)).perform(typeText("ade reskita"));
        sleep1S();
        onView(withId(R.id.edt_age)).perform(typeText("21"));
        sleep1S();
        onView(withId(R.id.edt_weight)).perform(typeText("80"));
        sleep1S();
        onView(withId(R.id.edt_height)).perform(typeText("167"));
        sleep1S();
        onView(withId(R.id.btn_edit)).perform(click());

    }

}