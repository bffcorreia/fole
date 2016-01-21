package com.example.fole;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.hamcrest.core.StringStartsWith.startsWith;

@RunWith(AndroidJUnit4.class) public class MainActivityTest {

  @Rule public ActivityTestRule<MainActivity> activityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  @Test public void testSample() throws Exception {
    int times = 2;

    for (int i = 0; i < times; i++) {
      assertExcerptMode();
      clickToggleView();
      assertFullMode();
      clickToggleView();
    }
  }

  private void clickToggleView() {
    onToggleView().perform(click());
  }

  private void assertExcerptMode() {
    assertExcerptIsVisible();
    assertFullTextIsNotVisible();
    assertEllipsisPlaceholderIsVisible();
    assertToggleViewHasShowMore();
  }

  private void assertFullMode() {
    assertFullTextIsVisible();
    assertEllipsisPlaceholderIsNotVisible();
    assertToggleViewHasShowLess();
  }

  private void assertExcerptIsVisible() {
    String textStart = MainActivity.TEXT.substring(0, 30);
    onTextView().check(matches(withText(startsWith(textStart))));
  }

  private void assertFullTextIsNotVisible() {
    String textEnd = MainActivity.TEXT.substring(MainActivity.TEXT.length() - 30);
    onTextView().check(matches(not(withText(endsWith(textEnd)))));
  }

  private void assertFullTextIsVisible() {
    onTextView().check(matches(withText(MainActivity.TEXT)));
  }

  private void assertEllipsisPlaceholderIsVisible() {
    String ellipsisPlaceholder = "...";
    onTextView().check(matches(withText(endsWith(ellipsisPlaceholder))));
  }

  private void assertEllipsisPlaceholderIsNotVisible() {
    String ellipsisPlaceholder = "...";
    onTextView().check(matches(not(withText(containsString(ellipsisPlaceholder)))));
  }

  private void assertToggleViewHasShowMore() {
    onToggleView().check(matches(withText("Show More")));
  }

  private void assertToggleViewHasShowLess() {
    onToggleView().check(matches(withText("Show Less")));
  }

  private ViewInteraction onToggleView() {
    return onView(withId(R.id.toggle_view));
  }

  private ViewInteraction onTextView() {
    return onView(withId(R.id.text_view));
  }
}
