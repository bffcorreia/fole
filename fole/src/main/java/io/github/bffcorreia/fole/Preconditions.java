package io.github.bffcorreia.fole;

import android.support.annotation.Nullable;

// Based on http://google-collections.googlecode.com/svn-history/r78/trunk/javadoc/com/google/common/base/Preconditions.html

class Preconditions {

  static void checkArgument(boolean expression, @Nullable Object errorMessage) {
    if (!expression) {
      throw new IllegalArgumentException(String.valueOf(errorMessage));
    }
  }

  static void checkState(boolean expression, @Nullable Object errorMessage) {
    if (!expression) {
      throw new IllegalStateException(String.valueOf(errorMessage));
    }
  }
}