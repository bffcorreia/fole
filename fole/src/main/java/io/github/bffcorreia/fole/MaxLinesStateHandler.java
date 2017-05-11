package io.github.bffcorreia.fole;

import android.widget.TextView;

public class MaxLinesStateHandler implements TextViewStateHandler {

  private final TextView textView;
  private final String ellipsisPlaceholder;
  private final int maxLines;

  public MaxLinesStateHandler(TextView textView, String ellipsisPlaceholder, int maxLines) {
    this.textView = textView;
    this.ellipsisPlaceholder = ellipsisPlaceholder;
    this.maxLines = maxLines;
  }

  @Override public boolean isTextExpanded() {
    return textView.getLineCount() > maxLines;
  }

  @Override public String ellipsizedText() {
    int lineEndIndex = textView.getLayout().getLineEnd(maxLines - 1);
    return textView.getText()
        .subSequence(0, lineEndIndex - ellipsisPlaceholder.length())
        .toString()
        .trim() + ellipsisPlaceholder;
  }
}
