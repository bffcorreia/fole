# Fole [![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=Check%20Fole!&url=https%3A%2F%2Fgithub.com%2Fbffcorreia%2Ffole&via=bffcorreia&hashtags=android,androiddev,opensource)

Fole is a simple library that handles a toggle for you, to expand and collapse a TextView.

> Please, star this repo if you find it useful. 🙃

-------
<p align="center">
    <a href="#demo">Demo</a> &bull;
    <a href="#installing">Installing</a> &bull;
    <a href="#basic-usage">Basic Usage</a> &bull;
    <a href="#Callback">Callback</a>
</p>

-------

<p align="center">
  <a href="https://jitpack.io/#bffcorreia/fole">
    <img src="https://jitpack.io/v/bffcorreia/fole.svg">
  </a>
  <a href="https://circleci.com/gh/bffcorreia/fole/tree/master">
      <img src="https://circleci.com/gh/bffcorreia/fole/tree/master.svg?style=svg">
    </a>
  <a href="https://github.com/bffcorreia/fole/blob/master/LICENSE.txt">
    <img src="https://img.shields.io/github/license/bffcorreia/fole.svg">
  </a>
</p>

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
        implementation 'com.github.bffcorreia:fole:1.0.0'
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
