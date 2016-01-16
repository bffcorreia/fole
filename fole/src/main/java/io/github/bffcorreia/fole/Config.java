package io.github.bffcorreia.fole;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

public class Config {

  private final Fole fole;

  private String text;
  private int maxLines;
  private String ellipsisPlaceholder;
  private View expandableView;
  private FoleCallback callback;

  private boolean isTextViewExpanded;

  public Config(Fole fole) {
    this.fole = fole;
    this.text = null;
    this.maxLines = 1;
    this.ellipsisPlaceholder = "...";
    this.isTextViewExpanded = false;
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

  public void toggle(View expandableView) {
    Preconditions.checkArgument(expandableView != null, "View must not be null.");
    toggle(expandableView, null);
  }

  public void toggle(View expandableView, FoleCallback callback) {
    this.expandableView = expandableView;
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
    this.expandableView.setClickable(true);

    this.expandableView.setOnClickListener(view -> {
      onActionPerformed();
    });
  }

  private void onActionPerformed() {
    this.fole.textView.setText(this.text);

    if (this.isTextViewExpanded) {
      handleViewState();
      this.isTextViewExpanded = false;
      addActionInfoIfCallbackIsSet(false);
    } else {
      this.isTextViewExpanded = true;
      addActionInfoIfCallbackIsSet(true);
    }
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

          expandableView.setVisibility(View.VISIBLE);
        } else {
          expandableView.setVisibility(View.GONE);
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
