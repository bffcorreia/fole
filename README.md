[![Release](https://jitpack.io/v/bffcorreia/fole.svg)](https://jitpack.io/#bffcorreia/fole)

# fole

Fole is a simple library that handles a toggle for you, to expand and collapse a TextView.

This is only the Alpha version, not tested yet!

## Installing

``` groovy
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        compile 'com.github.bffcorreia:fole:v0.4.4-alpha'
    }
```

## Basic Usage

``` java
    Fole.with(yourTextView).text("Your text...").maxLines(4).toggleView(yourToggleView);
```

### Ellipsis Placeholder

``` java
    Fole.with(yourTextView).maxLines(4).ellipsisPlaceholder("###").toggleView(yourToggleView);
```

### Callback

If you want to know when the TextView expand or collapse just add a FoleCallback.

``` java
    FoleCallback callback = new FoleCallback() {
          @Override public void onTextExpand() {
            // Handle onTextExpand!
          }
    
          @Override public void onTextCollapse() {
             // Handle onTextCollapse!
          }
        };
```

``` java
    Fole.with(yourTextView).maxLines(4).toggleView(yourToggleView, callback);
```

## TODO

- Tests
- More...