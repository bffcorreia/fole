package io.github.bffcorreia.fole;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import io.bloco.faker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class) public class ConfigTest {

  private TextView textView;
  private Faker faker = new Faker();

  @Before public void setUp() {
    Context context = InstrumentationRegistry.getTargetContext();
    textView = new TextView(context);
  }

  @Test(expected = IllegalStateException.class)
  public void testExceptionIfMaxLinesAndMaxCharsUsedAtSameTime() {
    Fole.with(textView).maxChars(10).maxLines(2);
  }

  @Test(expected = IllegalStateException.class) public void testSpecifyMaxLinesOrMaxChars() {
    Fole.with(textView).toggleView(textView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEllipsisPlaceholderCannotBeNull() {
    Fole.with(textView).ellipsisPlaceholder(null);
  }

  @Test(expected = IllegalArgumentException.class) public void testToggleViewCannotBeNull() {
    Fole.with(textView).toggleView(null);
  }

  @Test(expected = IllegalArgumentException.class) public void testAnimationCannotBeNull() {
    Fole.with(textView).animation(null);
  }

  @Test(expected = IllegalArgumentException.class) public void testExpandAnimationCannotBeNull() {
    Fole.with(textView).expandAnimation(null);
  }

  @Test(expected = IllegalArgumentException.class) public void testCollapseAnimationCannotBeNull() {
    Fole.with(textView).collapseAnimation(null);
  }

  @Test public void testSetNewTextToTextView() {
    textView.setText(faker.lorem.sentence());
    String newText = faker.lorem.sentence();
    Fole.with(textView).text(newText).maxLines(1).toggleView(textView);
    assertThat(textView.getText().toString(), equalTo(newText));
  }

  @Test public void testAnimationSetsTheCorrectAnimation() {
    String text = faker.lorem.sentence();
    Animation animation = loadAnimation(android.R.anim.fade_in);
    Animation otherAnimation = loadAnimation(android.R.anim.fade_out);
    Fole.with(textView).text(text).maxLines(1).animation(animation).toggleView(textView);

    assertThat(textView.getAnimation(), equalTo(null));
    textView.performClick();
    assertThat(textView.getAnimation(), equalTo(animation));
    assertThat(textView.getAnimation(), not(otherAnimation));
    textView.performClick();
    assertThat(textView.getAnimation(), equalTo(animation));
  }

  @Test public void testExpandedAnimationSetsTheCorrectAnimation() {
    String text = faker.lorem.sentence();
    Animation expandedAnimation = loadAnimation(android.R.anim.fade_in);
    Animation otherAnimation = loadAnimation(android.R.anim.fade_out);
    Fole.with(textView)
        .text(text)
        .maxLines(1)
        .expandAnimation(expandedAnimation)
        .toggleView(textView);

    assertThat(textView.getAnimation(), equalTo(null));
    textView.performClick();
    assertThat(textView.getAnimation(), equalTo(expandedAnimation));
    assertThat(textView.getAnimation(), not(otherAnimation));
  }

  @Test public void testCollapseAnimationSetsTheCorrectAnimation() {
    String text = faker.lorem.sentence();
    Animation collapseAnimation = loadAnimation(android.R.anim.fade_in);
    Animation otherAnimation = loadAnimation(android.R.anim.fade_out);
    Fole.with(textView)
        .text(text)
        .maxLines(1)
        .collapseAnimation(collapseAnimation)
        .toggleView(textView);

    assertThat(textView.getAnimation(), equalTo(null));
    textView.performClick();
    assertThat(textView.getAnimation(), equalTo(null));
    textView.performClick();
    assertThat(textView.getAnimation(), equalTo(collapseAnimation));
    assertThat(textView.getAnimation(), not(otherAnimation));
  }

  private Animation loadAnimation(@AnimRes int res) {
    return AnimationUtils.loadAnimation(InstrumentationRegistry.getTargetContext(), res);
  }
}
