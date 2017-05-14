package io.github.bffcorreia.fole;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;
import io.bloco.faker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class) public class ConfigTest {

  private TextView textView;
  private Faker faker = new Faker();

  @Before public void setUp() throws Exception {
    Context context = InstrumentationRegistry.getTargetContext();
    textView = new TextView(context);
  }

  @Test(expected = IllegalStateException.class)
  public void testExceptionIfMaxLinesAndMaxCharsUsedAtSameTime() throws Exception {
    Fole.with(textView).maxChars(10).maxLines(2);
  }

  @Test(expected = IllegalStateException.class) public void testSpecifyMaxLinesOrMaxChars()
      throws Exception {
    Fole.with(textView).toggleView(textView);
  }

  @Test(expected = IllegalArgumentException.class) public void testEllipsisPlaceholderCannotBeNull()
      throws Exception {
    Fole.with(textView).ellipsisPlaceholder(null);
  }

  @Test(expected = IllegalArgumentException.class) public void testToggleViewCannotBeNull()
      throws Exception {
    Fole.with(textView).toggleView(null);
  }

  @Test public void testSetNewTextToTextView() throws Exception {
    textView.setText(faker.lorem.sentence());
    String newText = faker.lorem.sentence();
    Fole.with(textView).text(newText).maxLines(1).toggleView(textView);
    assertThat(textView.getText().toString(), equalTo(newText));
  }
}
