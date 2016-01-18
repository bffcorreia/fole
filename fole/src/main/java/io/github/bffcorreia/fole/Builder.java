package io.github.bffcorreia.fole;

import android.widget.TextView;

public class Builder {

  private final TextView textView;

  public Builder(TextView textView) {
    Preconditions.checkArgument(textView != null, "TextView must not be bull.");
    this.textView = textView;
  }

  public Fole build() {
    return new Fole(this.textView);
  }
}
