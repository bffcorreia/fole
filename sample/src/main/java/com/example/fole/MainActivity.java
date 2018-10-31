package com.example.fole;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import io.github.bffcorreia.fole.Fole;
import io.github.bffcorreia.fole.FoleCallback;

public class MainActivity extends AppCompatActivity {

  public static final String TEXT =
      "\"Warning: If you are reading this then this warning is for you. "
          + "Every word you read of this useless fine print is another second off your life. "
          + "Don't you have other things to do? Is your life so empty that you honestly can't think "
          + "of a better way to spend these moments? Or are you so impressed with authority that you "
          + "give respect and credence to all that claim it? Do you read everything you're supposed "
          + "to read? Do you think every thing you're supposed to think? Buy what you're told to "
          + "want? Get out of your apartment. Meet a member of the opposite sex. Stop the excessive "
          + "shopping and masturbation. Quit your job. Start a fight. Prove you're alive. "
          + "If you don't claim your humanity you will become a statistic. "
          + "You have been warned.\"";

  private TextView textView, toggleView;
  private boolean isInMaxLinesMode;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textView = findViewById(R.id.text_view);
    toggleView = findViewById(R.id.toggle_view);

    initState(savedInstanceState);
    initView();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.max_lines:
        isInMaxLinesMode = true;
        break;
      case R.id.max_chars:
        isInMaxLinesMode = false;
        break;
      default:
        return super.onOptionsItemSelected(item);
    }
    recreate();
    return true;
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    outState.putBoolean("isInMaxLinesMode", isInMaxLinesMode);
    super.onSaveInstanceState(outState);
  }

  private void initState(Bundle savedInstanceState) {
    if (savedInstanceState != null) {
      isInMaxLinesMode = savedInstanceState.getBoolean("isInMaxLinesMode");
    } else {
      isInMaxLinesMode = true;
    }
  }

  private void initView() {
    if (isInMaxLinesMode) {
      toggleView.setVisibility(View.VISIBLE);

      Fole.with(textView)
          .text(TEXT)
          .maxLines(2)
          .expandAnimation(loadAnimation(android.R.anim.fade_in))
          .collapseAnimation(loadAnimation(android.R.anim.slide_in_left))
          .toggleView(toggleView, buildMaxLinesFoleCallback());
    } else {
      toggleView.setVisibility(View.GONE);
      Fole.with(textView)
          .text(TEXT)
          .maxChars(62)
          .animation(loadAnimation(android.R.anim.slide_in_left))
          .toggleView(textView, buildMaxCharsFoleCallback());
    }
  }

  private FoleCallback buildMaxLinesFoleCallback() {
    return new FoleCallback() {
      @Override public void onTextExpand() {
        toggleView.setText(R.string.show_less);
      }

      @Override public void onTextCollapse() {
        toggleView.setText(R.string.show_more);
      }
    };
  }

  private FoleCallback buildMaxCharsFoleCallback() {
    return new FoleCallback() {
      @Override public void onTextExpand() {
        appendToggleTextToTextView(getString(R.string.show_less).toLowerCase());
      }

      @Override public void onTextCollapse() {
        appendToggleTextToTextView(getString(R.string.show_more).toLowerCase());
      }
    };
  }

  private void appendToggleTextToTextView(String toggleText) {
    int color = ContextCompat.getColor(this, R.color.colorToggle);
    ForegroundColorSpan span = new ForegroundColorSpan(color);
    Spannable wordToSpan = new SpannableString(textView.getText() + " " + toggleText);
    wordToSpan.setSpan(span, textView.length(), wordToSpan.length(),
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    textView.setText(wordToSpan);
  }

  private Animation loadAnimation(@AnimRes int res) {
    return AnimationUtils.loadAnimation(this, res);
  }
}
