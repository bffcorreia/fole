package io.github.bffcorreia.fole;

import android.widget.TextView;

public class Fole {

  static volatile Fole singleton = null;

  final TextView textView;

  public Fole(TextView textView) {
    this.textView = textView;
  }

  public static Config with(TextView textView) {
    if (singleton == null) {
      synchronized (Fole.class) {
        if (singleton == null) {
          singleton = new Builder(textView).build();
        }
      }
    }
    return new Config(singleton);
  }
}
