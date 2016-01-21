package com.example.fole;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import io.github.bffcorreia.fole.Fole;
import io.github.bffcorreia.fole.FoleCallback;

public class MainActivity extends AppCompatActivity {

  public static final String TEXT =
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor "
      + "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud "
      + "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute "
      + "irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla "
      + "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia "
      + "deserunt mollit anim id est laborum.";

  TextView textView, toggleView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    textView = (TextView) findViewById(R.id.text_view);
    toggleView = (TextView) findViewById(R.id.toggle_view);

    FoleCallback callback = new FoleCallback() {
      @Override public void onTextExpand() {
        toggleView.setText("Show Less");
      }

      @Override public void onTextCollapse() {
        toggleView.setText("Show More");
      }
    };

    Fole.with(textView).text(TEXT).maxLines(2).toggleView(toggleView, callback);
  }
}
