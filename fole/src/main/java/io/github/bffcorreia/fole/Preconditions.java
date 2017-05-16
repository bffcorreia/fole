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

  static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
    if (reference == null) {
      throw new NullPointerException(String.valueOf(errorMessage));
    }
    return reference;
  }

  static <T> T checkNull(T reference, @Nullable Object errorMessage) {
    checkState(reference == null, errorMessage);
    return reference;
  }
}