package com.example.fole;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
