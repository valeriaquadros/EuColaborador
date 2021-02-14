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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditarPerfilTest {

    private int TEMPO_REQUEST = 5000;
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void editarPerfilTest() {
        onView(withId(R.id.expanded_menu)).perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.title), withText("Editar perfil pessoal"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        textView.perform(click());

        onView(withId(R.id.cpf)).perform(replaceText("558.686.491-68"));
        onView(withId(R.id.password_field)).perform(replaceText("1234567"));
        onView(withId(R.id.button_login)).perform(click());

        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.perfil_btn_editar)).perform(click());

        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.perfil_et_nome)).perform(replaceText("Danielle Lima Maidana"));
        onView(withId(R.id.perfil_et_data_nascimento)).perform(replaceText("18/09/1989"));

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.perfil_edit_sexo_m), withText("Masculino"),
                        childAtPosition(
                                allOf(withId(R.id.perfil_rg_sexo),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1)));
        radioButton.perform(scrollTo(), click());

        onView(withId(R.id.perfil_edit_salvar)).perform(click());
        try {
            sleep(TEMPO_REQUEST);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.sucesso_titulo), withText("Salvo com sucesso"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("Salvo com sucesso")));

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
