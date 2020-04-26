package d3ifcool.org;

import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ShareTest {

    FirebaseUser cUser;
    FirebaseAuth mFirebaseAuth;

    @Rule
    public ActivityTestRule<WelcomeActivity> mActivityTestRule = new ActivityTestRule<>(WelcomeActivity.class);
    public IntentsTestRule<WelcomeActivity> intentsTestRule = new IntentsTestRule<>(WelcomeActivity.class);

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

    public void sleep7S() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    @Test
    public void ShareTesting(){

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Espresso.onView(withId(R.id.btn_login)).perform(click());
        sleep3S();
//        onView(withId(R.id.edt_email)).perform(typeText("testingMe@gmail.com"));
//        sleep1S();
//        onView(withId(R.id.edt_password)).perform(typeText("adereskita12"));
        onView(withId(R.id.edt_email)).perform(typeText("ade.reskita@yahoo.com"));
        sleep1S();
        onView(withId(R.id.edt_password)).perform(typeText("adereskita12"));
        sleep1S();
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        sleep7S();
        onView(withId(R.id.rv_history)).perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.ivb_share)));
        sleep3S();
//        intended(toPackage("org.telegram.messenger"));
//        intended(toPackage("com.android.mms"));
    }

}