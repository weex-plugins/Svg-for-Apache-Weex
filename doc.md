### weex-svg 介绍

近几年 SVG 逐渐成为前端开发的热门的技术。我们可以在我们的业务场景中构建我们的图标，以及替代部分位图，随着动画交互的普及，SVG 也成为了大家的选择之一。
今天我们给大家带来如何在 weex 项目中通过 [weex-svg](https://github.com/weex-plugins/weex-svg)  来实现 svg 的一些图形信息的展示。

我们查阅 [weex-svg](https://github.com/weex-plugins/weex-svg) 的文档，可以了解它目前支持的一些组件和属性有:

#### 基本的图形

+ `rect` 矩形
+ `circle` 圆形
+ `path` 路径
+ `polyline` 折线
+ `polygon` 几何形
+ `line` 直线
+ `linear-gradient` 线性渐变
+ `radial-gradient` 径向渐变

#### 通用属性

+ fill 颜色填充
+ stroke 描边的颜色
+ stroke-width 描边的宽度


### 快速开始
 
weex-svg 在插件市场已经发布了，我们可以通过 [weex-toolkit](https://github.com/weexteam/weex-toolkit) 来添加插件。
首先我们创建一个项目：

``` bash
weex create svgapp
```

然后进入创建的项目，添加插件。

``` bash 
cd svgapp && npm install
# 添加 Android 应用模板，你也可以修改为iOS
weex platform add android 
weex plugin add weex-svg
```

然后我们编辑src/index.vue 文件，如果没有，就自行创建 `index.vue`。

``` bash
<template>
  <div class="page">
    <svg style="width:300px;height:300px;">
      <rect x="20" y="20" width="100" height="45" fill="#ea6153"/> 
    </svg>
  </div>
</template

<style>
  .page{
    flex: 1;
  }
</style>

```

 这个时候我们就可以看到一个简单红色的矩形在界面上。接下来，我们修改 `index.vue`,添加一些复杂的图形
 
 ``` html
 <template>
  <div class="page">
    <scroller class="main">
      <div class="item">
        <svg style="width:300px;height:300px"
           viewBox="0 0 300 300">
          <path d="M155.3,39.7c26.9,1.1,51.2,11.9,69.7,29l0,0c-18.5,20.2-30.2,46.8-31.4,76.1h-38.3C155.3,144.7,155.3,39.7,155.3,39.7z
           M145.7,39.7v105.1h-38.3c-1.2-29.3-12.8-55.9-31.4-76.1l0,0C94.5,51.6,118.9,40.8,145.7,39.7z M155.3,259.3V154.3h38.3
          c1.2,29.3,12.8,55.9,31.4,76.1l0,0C206.5,247.4,182.1,258.2,155.3,259.3z M145.7,259.3c-26.9-1.1-51.2-11.9-69.7-29
          c18.5-20.2,30.2-46.8,31.4-76.1h38.3C145.7,154.3,145.7,259.3,145.7,259.3z M231.8,75.4c16.8,18.4,27.4,42.6,28.6,69.3l0,0h-57.2
          C204.3,118.1,214.9,93.9,231.8,75.4L231.8,75.4L231.8,75.4L231.8,75.4z M231.8,223.6L231.8,223.6L231.8,223.6
          c-16.8-18.4-27.4-42.6-28.6-69.3h57.2C259.2,180.9,248.6,205.1,231.8,223.6L231.8,223.6z M40.7,144.7c1.1-26.6,11.8-50.8,28.6-69.3
          l0,0c16.8,18.4,27.4,42.6,28.6,69.3H40.7L40.7,144.7z M40.7,154.3h57.2c-1.1,26.6-11.8,50.8-28.6,69.3
          C52.4,205.1,41.8,180.9,40.7,154.3L40.7,154.3z M150.5,269c66,0,119.5-53.5,119.5-119.5S216.5,30,150.5,30S31,83.5,31,149.5
          S84.5,269,150.5,269L150.5,269z" fill="#000000"/>
        </svg>
        <svg style="width:120px; height:120px;" viewBox="0 0 120 120">
          <path fill="#f1c40f"  d="M29.2,31.2V10h61.5L69.6,31.2l21.2,21.2H33.1V110h-3.8V31.2z M33.1,13.8v34.6h48.1L63.8,31.2l17.3-17.3H33.1z" />
        </svg>
      </div>
      
    </scroller>
    
  </div>
   
</template>

<style>
  .page{
    flex: 1;
    padding-top: 20px;
    background-color: #fff;
  }
  .main{
    padding-top: 88px;
  }
  .desc{
    margin: 20px;
    font-size: 28px;
    text-align: left;
    color: #444;
  }
  .item{
    align-items: center;
    margin: 20px;
    padding: 10px;
    border-bottom: 2px solid #ddd;
  }
</style>

 ```
 
 预览效果:
 
 <img width="320" src="http://img1.vued.vanthink.cn/vuedf348778b4712be475850bc37095b0f17.png" />
 
 ### 组件使用
 
 Weex-svg 目前已经对 `rect` ，`circle`，`path`，`polyline`，`polygon`，`linear-gradient`，`radial-gradient` 的支持，考虑在下一版本继续完善，因此在使用的时候，目前只能解析上面的那些元素。
 
 #### rect

绘制矩形，你可以通过 `x`，`y` 来设置矩形的起点，你可以通过 `width` 和 `height` 来设置矩形的大小，你可以通过fill 和 stroke 来进行填充和描边。

``` html
<svg style="width:300px;height:300px;">
  <rect x="20" y="20" width="160" height="160" fill="#f39c12"/> 
</svg>

```
<img src="http://img1.vued.vanthink.cn/vued1d57e449aba15c3fe8778f7a05541825.png" />


#### circle

绘制圆形， 其中 `cx` 和 `cy` 表示圆心， `r` 表示半径。

``` html
<svg style="width:300px;height:300px;">
  <circle cx="150" cy="50" r="45" fill="none" stroke-width="2" stroke="#ea6153"/>
</svg>
```

<img src="http://img1.vued.vanthink.cn/vued385a377a46e040ab275773d538c88e82.png" />

#### line

你可以通过 line 标签来进行直线的绘制，其中 `x1`  和 `y1` 表示起点，`x2` 和 `y2` 表示终点。

``` html
<svg style="width:300px;height:300px;">
  <line x1="10" y1="70" x2="120" y2="70" stroke="#ea6153" stroke-width="2" />
  <line x1="70" y1="10" x2="70" y2="120" stroke="#ea6153" stroke-width="2" />
</svg>
```

<img src="http://img1.vued.vanthink.cn/vued7f0da5c30bde89cf80d0cabf475529c1.png" />

#### polyline

绘制折线，你可以通过points 来进行路径设置，每一组数据的坐标由逗号分隔。

``` html
<svg style="width:300px;height:300px;">
  <polyline points="0,0 100,0 100,100" fill="#ea6153"></polyline>            
</svg>
```

<img src="http://img1.vued.vanthink.cn/vued63089137ee5fe4ad7f8698ef29eb0cc0.png" />



#### path
你可以使用 path 来定义任何形状，无论是直线还是曲线， path 也是 svg 中最强大的工具。你可以通过 d 命令来进行设置, 它可以绘制直线，曲线，以及复杂的贝塞尔曲线。 你可以 阅读[MDN -D](https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/d) 来了解它的具体使用。一般情况下，比较复杂的 SVG 图形，我们都是通过第三方工具来完成的比如 [Adobe Illustrator](https://helpx.adobe.com/illustrator/how-to/what-is-illustrator.html) 来进行适量图形的制作。

``` html  
<svg style="width:300px;height:300px;">
  <path d="M50,50 A50,50 0 0,1 150,80" stroke="#e84c3d" fill="none" />           
</svg>
```

<img src="http://img1.vued.vanthink.cn/vued574ca3cc6d13119caf55684e983ca521.png" />




#### fill 

fill 主要用于填充不同的颜色。

``` bash 
<svg style="width:300px;height:300px;">
  <circle cx="50" cy="50" r="45" style="fill:#ea6153;"/>
  <circle cx="100" cy="50" r="45" style="fill:#9b59b6;"/> 
  <circle cx="150" cy="50" r="45" style="fill:#2ecc71;"/>  
</svg>
```

<img src="http://img1.vued.vanthink.cn/vued3d31af635b3a2a5873822e0e8ba372c8.png" />

#### stroke

而 stroke 主要用于设置描边的颜色，而描边的宽度可以通过 `stroke-width`来设置。
``` bash
<svg style="width:300px;height:300px;">
  <circle cx="50" cy="50" r="20" fill="none" stroke="#ea6153"/>
  <circle cx="100" cy="50" r="20" fill="none" stroke="#9b59b6"/> 
  <circle cx="150" cy="50" r="20" fill="none" stroke="#2ecc71" stroke-width="2"/>  
</svg>
```

<img src="http://img1.vued.vanthink.cn/vuedf4145cf4cbf4835001b4de2fb63520d4.png" />
 
 
 
 
 
 ### 使用场景对比
 
 svg 是矢量图形，相对于位图，它很好地解决多机型分辨率的问题。而我们在Android开发的时候，我们需要生成多套资源文件，去克服不同分辨率下图片可能失真的情况。我们可以对比同样一个图标，(*测试为同为300px 高宽的图像，svg 和 png的对比*) 
 
 <img width="300" src="http://img1.vued.vanthink.cn/vuedd1f830f2184145d6151fd85cff2eeb53.png"/>
 
 我们可以感知到下面图片有些模糊。 我们常用的svg的场景，主要就是**图标的显示**和一些**动画**的使用。对于一些复杂的图形，首先我们的工具不一定可以导出svg，第二svg导出的体积可能会比优化后的位图还大。而小图标对于设计师而言，他们可以方便的导出svg代码，相对而言，svg的代码可读性强，有利于工程师进行代码使用和编辑。我们对比了常见的页面布局下的图标渲染视频:
 
 <video controls style="width:320px;height:640px;" src="http://cloud.video.taobao.com/play/u/481286803/p/1/e/6/t/1/54127540.mp4"></video>
 
 
 透过 [weex debug](https://github.com/weexteam/weex-toolkit) 的功能我们可以查看手机(测试机型 HUAWEI Honor7) 的log 信息，了解到我们渲染的时间。下面我们通过一组简单的测试来看下渲染时间对比:
 
 下面是一组常见底部icon的对比效果:
 
 <img width="320" src="http://img1.vued.vanthink.cn/vued33c84e67a635fc0fcb60551718ecf4d7.png" />
 
 [文件源码](https://github.com/JackPu/weex-svg-performance-test/blob/master/src/testsvg.vue)
 
 渲染的时间对比如下图:
 
 <table style="width:600px;">
  <tr>
    <th>单位(ms)</th>
    <th>JS Bundle Networking Time</th>
    <th>Render Time</th>
    <th>Resource Networking Time</th>
  </tr>
  <tr>
    <td>SVG</td>
    <td>67</td>
    <td>179</td>
    <td>0</td>
  </tr>
  <tr>
    <td>PNG</td>
    <td>46</td>
    <td>122</td>
    <td>233</td>
  </tr>
 </table>
 
 <img src="http://img1.vued.vanthink.cn/vueda5d14964721eea36db2495a43a17325f.png" />
 
 接下来我们测试比较复杂的图形，显示效果如下:
 
 <img width="320" src="http://img1.vued.vanthink.cn/vuedd02a90b42ccb56d270a4d7c55cf5093f.png" />
 
 
 
 [文件源码](https://github.com/JackPu/weex-svg-performance-test/blob/master/src/testsvg-icon.vue)
 
 渲染时间对比:
 
 <table style="width:600px;">
  <tr>
    <th>单位(ms)</th>
    <th>JS Bundle Networking Time</th>
    <th>Render Time</th>
    <th>Resource Networking Time</th>
  </tr>
  <tr>
    <td>SVG</td>
    <td>47</td>
    <td>235</td>
    <td>0</td>
  </tr>
  <tr>
    <td>PNG</td>
    <td>29</td>
    <td>119</td>
    <td>798</td>
  </tr>
 </table>
 
 <img src="http://img1.vued.vanthink.cn/vuedce5e5f5cdb29538f21f5ebb94cf2fed3.png" />
 
 从图中我们可以直观的看到，SVG的优点在于节省了资源的请求，在 render 上时间则不占优势，原因在于我们需要花时间去进行DOM的解析然后按照命令去进行图形的绘制。
 使用 `weex compile` 进行文件的编译后文件大小对比如图:

<img src="http://img1.vued.vanthink.cn/vued5bccb21cf015853ae62c1b790dba285a.png" />

由此也发现了使用 svg 一些需要注意的点，比如我们的 JS Bundle 的文件会相对增大。因此也不太建议特别复杂的图形使用svg去进行绘制，这会造成性能的开销。对于动态类数据的图形展示，我们也可是尝试使用SVG，比如我们前端非常使用的 [d3.js](https://d3js.org/) ，可以生成很多漂亮的图表:

<img width="320" src="http://img1.vued.vanthink.cn/vued2ede28df2537bc6e9b81302487e730e7.png" />

*目前D3还不能直接引入到 weex 中，需要我们一些 hack 技巧, 如果你有什么好的想法也可以提出issue*


 
### Demo 使用
 
 [Demo](https://github.com/weex-plugins/weex-svg/tree/master/demos) 里面包含了目前所支持的特性的的案例。你只需要 
 
 ``` bash 
 
 git clone https://github.com/weex-plugins/weex-svg.git
 
 ```
 然后进行项目的demo 文件拷贝到 src目录下, 运行 `weex run android` 即可。
 
 <video controls style="width:320px;height:640px;" src="http://cloud.video.taobao.com/play/u/481286803/p/1/e/6/t/1/54127586.mp4"></video>
 
 
 
 ### 小结
 
 本文只是粗浅的介绍了weex-svg的基本用法，我们可以尝试在我们的 weex  项目中引进 svg ，它可以帮助我们去完成一些图标的展示和基本图形的页面展示，它还可以实现一些简单的图形动画。当然在实际使用过程中，你有事吗想法和建议也可以反馈到 [项目issue](https://github.com/weex-plugins/weex-svg/issues) ，从而帮助开发者进一步完善。
 

 
 
 ### 扩展阅读
 
 + [SVG Tutorial-Jakob Jenkov](http://tutorials.jenkov.com/svg/index.html) 
 + [Android 关于SVG矢量图支持](http://blog.csdn.net/a1961613299/article/details/50512729)
 + [5 reasons you should be using SVG’s over PNG’s](https://watb.co.uk/5-reasons-you-should-be-using-svgs-over-pngs/)
 + [MDN - SVG](https://developer.mozilla.org/en-US/docs/Web/SVG)















