package io.github.bffcorreia.fole;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

public class Config {

  private final Fole fole;

  private String text;
  private int maxLines;
  private String ellipsisPlaceholder;
  private View toggleView;
  private FoleCallback callback;

  private boolean isTextViewExpanded;

  public Config(Fole fole) {
    this.fole = fole;
    this.text = null;
    this.maxLines = 1;
    this.ellipsisPlaceholder = "...";
  }

  public Config text(String text) {
    this.text = text;
    return this;
  }

  public Config maxLines(int maxLines) {
    Preconditions.checkArgument(maxLines > 0, "MaxLines must not be 0.");
    this.maxLines = maxLines;
    return this;
  }

  public Config ellipsisPlaceholder(String ellipsizePlaceholder) {
    Preconditions.checkArgument(ellipsizePlaceholder != null, "Placeholder must not be null.");
    this.ellipsisPlaceholder = ellipsizePlaceholder;
    return this;
  }

  public void toggleView(View toggleView) {
    Preconditions.checkArgument(toggleView != null, "Toggle view must not be null.");
    toggle(toggleView, null);
  }

  public void toggle(View toggleView, FoleCallback callback) {
    this.toggleView = toggleView;
    this.callback = callback;

    addOnExpandableViewClickListener();
    handleViewState();
    initText();
  }

  private void initText() {
    if (this.text == null) {
      this.text = this.fole.textView.getText().toString();
    }
    this.fole.textView.setText(this.text);
  }

  private void addOnExpandableViewClickListener() {
    this.toggleView.setClickable(true);
    this.toggleView.setOnClickListener(view -> onActionPerformed());
  }

  private void onActionPerformed() {
    if (this.isTextViewExpanded) {
      handleViewState();
    } else {
      this.isTextViewExpanded = true;
      addActionInfoIfCallbackIsSet(true);
    }
    this.fole.textView.setText(this.text);
  }

  private void addActionInfoIfCallbackIsSet(boolean wasExpanded) {
    if (this.callback != null) {
      if (wasExpanded) {
        this.callback.onTextExpand();
      } else {
        this.callback.onTextCollapse();
      }
    }
  }

  private void handleViewState() {
    ViewTreeObserver treeObserver = fole.textView.getViewTreeObserver();

    treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @SuppressWarnings("deprecation") @Override public void onGlobalLayout() {
        if (fole.textView.getLineCount() > maxLines) {
          int lineEndIndex = fole.textView.getLayout().getLineEnd(maxLines - 1);

          String ellipsizedText =
              fole.textView.getText().subSequence(0, lineEndIndex - ellipsisPlaceholder.length())
                  + ellipsisPlaceholder;
          fole.textView.setText(ellipsizedText);

          toggleView.setVisibility(View.VISIBLE);
          isTextViewExpanded = false;
          addActionInfoIfCallbackIsSet(false);
        } else {
          toggleView.setVisibility(View.GONE);
          isTextViewExpanded = true;
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
          fole.textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        } else {
          fole.textView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
      }
    });
  }
}
