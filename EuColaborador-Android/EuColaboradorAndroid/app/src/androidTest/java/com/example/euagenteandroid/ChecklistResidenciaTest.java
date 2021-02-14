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
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ChecklistResidenciaTest {

    private int TEMPO_REQUEST = 5000;
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checklistResidenciaTest() {
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

        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.cpf)).perform(replaceText("558.686.491-68"));
        onView(withId(R.id.password_field)).perform(replaceText("1234567"));

        ViewInteraction button = onView(
                allOf(withId(R.id.button_login), withText("ENTRAR"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        1),
                                4),
                        isDisplayed()));
        button.perform(click());

        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.campo_cep)).perform(replaceText("79032261"));
        onView(withId(R.id.campo_numero)).perform(click(), replaceText("881"));
        onView(withId(R.id.button_address)).perform(closeSoftKeyboard(), scrollTo(), click());

        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(0).perform(click());
        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(1).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(2).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(3).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(4).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(5).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(6).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(7).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(8).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(9).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(10).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(11).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(12).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(13).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(14).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(15).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(16).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(17).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(18).perform(click());
//        onData(Matchers.anything()).inAdapterView(withId(R.id.list_view)).atPosition(19).perform(click());


        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button3 = onView(
                allOf(withId(R.id.checklist_button), withText("SALVAR"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        button3.perform(click());

        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.sucesso_titulo), withText("Lista salva com sucesso"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Lista salva com sucesso")));

        onView(withId(R.id.expanded_menu)).perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.title), withText("Sair"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView3.perform(click());
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
