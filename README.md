# weex-plugin-svg



<img src="http://img1.vued.vanthink.cn/vued0366b8d467968db7b4e9055288972c4e.png" width="240" />

a weex plugin to support svg for Web/iOS/Andoid



### preview demo

``` bash
// if you didn't install weex-toolkit
npm install weex-toolkit@beta -g 

git clone https://github.com/weex-plugins/weex-plugin-svg.git

cd weex-plugin-svg

weex ./demos/index.vue
```

### how to use

``` bash
<template>
  <div class="page">
    <svg width="200" height="200">
      <rect x="20" y="20" rx="22.5" ry="22.5" width="100" height="45" style="fill:#ea6153"/> 
    </svg>
  </div>
</template

<style>
  .page{
    flex: 1;
  }
</style>

```

### svg elements

#### rect




### svg  props

| props  | exmaple | Description  |
| ------ |:---------|:-------------|
| fill  | #1ba1e2 | For shapes and text, the fill attribute is a presentation attribute that define the color of the interior of the given graphical element |
| fillOpacity  | 0.5 | This attribute specifies the opacity of the color or the content the current object is filled with |
| stroke  | green | The stroke attribute defines the color of the outline on a given graphical element. The default value for the stroke attribute is none |
| strokeWidth  | 2 | The strokeWidth attribute specifies the width of the outline on the current object|
| x  | 20 | Translate distance on x-axis.|
| y  | 20 | Translate distance on y-axis|


If you want to learn more about SVG , we suggest you read [SVG Tutorial-Jakob Jenkov](http://tutorials.jenkov.com/svg/index.html) .

