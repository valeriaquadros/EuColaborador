package br.com.eucolaborador;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginTest {
    private int TEMPO_REQUEST = 5000;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginTest() {
        onView(withId(R.id.expanded_menu)).perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.title), withText("Fazer verificaçāo de residência"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        onView(withId(R.id.cpf)).perform(replaceText("558.686.491-68"));
        onView(withId(R.id.password_field)).perform(replaceText("1234567"));

        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.button_login)).perform(click());

        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.expanded_menu)).perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.title), withText("Sair"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView4.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
