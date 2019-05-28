# Page-indicator
A simple and customisable Android library to indicate number of items in a recycler view.

### Installation
Add this in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {  
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
  
Add the dependancy to your gradle file
```
dependencies {
  implementation 'com.github.jami4n:page-indicator:0.1.1'
}
```
### Latest Version
[![](https://jitpack.io/v/jami4n/page-indicator.svg)](https://jitpack.io/#jami4n/page-indicator)


### How to use
**Add the indicator View to your xml file**

```
<com.example.indicator.indicator.Indicator
    android:id="@+id/indicator"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```

**Create an IndicatorItem object using the IndicatorItem Builder**

```
var indicatorItem = IndicatorItem.Builder(this)
                    .setIndicatorColors(resources.getColor(R.color.yellow),resources.getColor(R.color.grey))
                    .build()
```

You can keep adding functions to the builder method and modify the indicator colors, height, width, margin, corners and  gravity. Please refer to this link to see more of these functions used.

**Attach your horizontal recyclerview to the Indicator and pass in the indicatorItem**

```
indicator.attachToRecyclerView(rvBanner,indicatorItem)
```
And just like that you have a lightweight page indicator ready to customise and use.


### Author 
Jamian Gomes


