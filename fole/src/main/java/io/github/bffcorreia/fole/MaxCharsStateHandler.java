package io.github.bffcorreia.fole;

import android.widget.TextView;

class MaxCharsStateHandler implements TextViewStateHandler {

  private final TextView textView;
  private final String ellipsisPlaceholder;
  private final int maxChars;

  MaxCharsStateHandler(TextView textView, String ellipsisPlaceholder, int maxChars) {
    this.textView = textView;
    this.ellipsisPlaceholder = ellipsisPlaceholder;
    this.maxChars = maxChars;
  }

  @Override public boolean isTextExpanded() {
    return textView.length() - ellipsisPlaceholder.length() > maxChars;
  }

  @Override public String ellipsizedText() {
    return textView.getText().subSequence(0, maxChars) + ellipsisPlaceholder;
  }
}
