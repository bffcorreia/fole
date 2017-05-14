package io.github.bffcorreia.fole;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class) public class MaxCharsStateHandlerTest {

  private MaxCharsStateHandler handler;
  private TextView textView;
  private String ellipsisPlaceHolder = "...";
  private String text = "This is a text.";

  @Before public void setUp() throws Exception {
    Context context = InstrumentationRegistry.getTargetContext();
    textView = new TextView(context);
  }

  @Test public void testTestViewIsExpanded() throws Exception {
    textView.setText(text);
    handler = new MaxCharsStateHandler(textView, ellipsisPlaceHolder, 10);
    assertThat(handler.isTextExpanded(), is(true));
  }

  @Test public void testTestViewIsNotExpanded() throws Exception {
    textView.setText(text);
    handler = new MaxCharsStateHandler(textView, ellipsisPlaceHolder, 15);
    assertThat(handler.isTextExpanded(), is(false));
  }

  @Test public void testEllipsizedText() throws Exception {
    textView.setText(text);
    handler = new MaxCharsStateHandler(textView, ellipsisPlaceHolder, 10);
    String expectedEllipsizedText = "This is a " + ellipsisPlaceHolder;
    assertThat(handler.ellipsizedText(), equalTo(expectedEllipsizedText));
  }
}
