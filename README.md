# fole

Fole is a simple library that handles a toggle for you, to expand and collapse a TextView.
This is only a Beta version, not tested yet!

## Installing

TODO: Publish to a public repository...

## Basic Usage

``` java
    Fole.with(yourTextView).text("Your text...").maxLines(4).toggle(toggleView);
```

### Ellipsis Placeholder

``` java
    Fole.with(yourTextView).maxLines(4).ellipsisPlaceholder("###").toggle(toggleView);
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
´´´

``` java
    Fole.with(yourTextView).maxLines(4).toggle(toggleView, callback);
```

## TODO

- Tests
- Publish to public repository
- More...