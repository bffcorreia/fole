package io.github.bffcorreia.fole;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

public class Config {

  private final Fole fole;

  private String text;
  private int maxLines;
  private int maxChars;
  private String ellipsisPlaceholder;
  private View toggleView;
  private FoleCallback callback;

  private boolean isTextViewExpanded;

  public Config(Fole fole) {
    this.fole = fole;
    this.text = null;
    this.ellipsisPlaceholder = "...";
  }

  public Config text(String text) {
    this.text = text;
    return this;
  }

  public Config maxLines(int maxLines) {
    Preconditions.checkArgument(maxLines > 0, "MaxLines must not be 0.");
    Preconditions.checkState(maxChars == 0, "MaxChars and MaxLines can not be used at same time.");
    this.maxLines = maxLines;
    return this;
  }

  public Config maxChars(int maxChars) {
    Preconditions.checkArgument(maxChars > 0, "MaxChars must not be 0.");
    Preconditions.checkState(maxLines == 0, "MaxLines and MaxChars can not be used at same time.");
    this.maxChars = maxChars;
    return this;
  }

  public Config ellipsisPlaceholder(String ellipsizePlaceholder) {
    Preconditions.checkArgument(ellipsizePlaceholder != null, "Placeholder must not be null.");
    this.ellipsisPlaceholder = ellipsizePlaceholder;
    return this;
  }

  public void toggleView(View toggleView) {
    Preconditions.checkArgument(toggleView != null, "Toggle view must not be null.");
    toggleView(toggleView, null);
  }

  public void toggleView(View toggleView, FoleCallback callback) {
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
    this.toggleView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        onActionPerformed();
      }
    });
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
    Preconditions.checkState(maxLines > 0 || maxChars > 0, "You need to add MaxLines or MaxChars.");
    if (maxLines > 0) {
      addListener(new MaxLinesStateHandler(fole.textView, ellipsisPlaceholder, maxLines));
    } else {
      addListener(new MaxCharsStateHandler(fole.textView, ellipsisPlaceholder, maxChars));
    }
  }

  private void addListener(final TextViewStateHandler textViewStateHandler) {
    ViewTreeObserver treeObserver = fole.textView.getViewTreeObserver();

    treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @SuppressWarnings("deprecation") @Override public void onGlobalLayout() {
        if (textViewStateHandler.isTextExpanded()) {
          fole.textView.setText(textViewStateHandler.ellipsizedText());
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
