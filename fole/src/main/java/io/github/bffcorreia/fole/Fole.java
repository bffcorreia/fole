package io.github.bffcorreia.fole;

import android.widget.TextView;

public class Fole {

  final TextView textView;

  public Fole(TextView textView) {
    this.textView = textView;
  }

  public static Config with(TextView textView) {
    Preconditions.checkArgument(textView != null, "TextView must not be null.");
    return new Config(new Fole(textView));
  }
}
