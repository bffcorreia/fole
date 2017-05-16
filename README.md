[![BuddyBuild](https://dashboard.buddybuild.com/api/statusImage?appID=59197efc523ddd0001b5c558&branch=master&build=latest)](https://dashboard.buddybuild.com/apps/59197efc523ddd0001b5c558/build/latest?branch=master) [![Release](https://jitpack.io/v/bffcorreia/fole.svg)](https://jitpack.io/#bffcorreia/fole)

# fole

Fole is a simple library that handles a toggle for you, to expand and collapse a TextView.

## Demo
<p>
   <img src="https://raw.githubusercontent.com/bffcorreia/fole/master/fole-sample.gif"/>
</p>

## Installing

``` groovy
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }

    dependencies {
        compile 'com.github.bffcorreia:fole:1.0.0'
    }
```

## Basic Usage

``` java
    Fole.with(yourTextView).text("Your text...").maxLines(4).toggleView(yourToggleView);
```

``` java
    Fole.with(yourTextView).text("Your text...").maxChars(50).toggleView(yourToggleView);
```

### Ellipsis Placeholder

``` java
    Fole.with(yourTextView).maxLines(4).ellipsisPlaceholder("###").toggleView(yourToggleView);
```

### Callback

If you want to know when the TextView expands or collapses just add a FoleCallback.

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

## License

    Copyright 2016 Bruno Correia

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
