<template>
  <scroller class="page">
    <div class="hd">
      <image class="logo" src="http://img1.vued.vanthink.cn/vued50a9ba7ffdffdfe2578e0f921a741f5a.png"></image>
      <text class="desc"> A weex plugin for svg support </text>
    </div>
    <div class="main">
      <text class="h3">Components</text>
      <div class="group">
        <group-item :url="com.url" :name="com.name" :icon="com.icon" v-for="com in coms" :key="com.name"></group-item>
      </div>
      <text class="h3">Props</text>
      <div class="group">
        <group-item :url="prop.url" :name="prop.name" :icon="prop.icon" v-for="prop in props" :key="prop.name"></group-item>
      </div>
      <text class="h3">Advanced</text>
      <div class="group">
        <group-item :url="demo.url" :name="demo.name" :icon="demo.icon" v-for="demo in demos" :key="demo.name"></group-item>
      </div>
    </div>
  </scroller>
</template>
   
<style>
  .page{
    padding-top: 20px;
    text-align: center;
    background-color: #f1f1f1;
  }
  .hd{
    align-items: center;
  }
  .logo{
    width: 375px;
    height: 240px;
  }
  .main{
    margin-top: 40px;
    overflow: auto;
  }
  .h3{
    margin: 20px;
    font-size: 24px;
    text-align: left;
    color: #888;
  }
  .group{
    padding-top: 10px;
    padding-left: 20px;
    padding-right: 20px;
    background-color: #fff;
    border-top:2px solid rgba(0,0,0,.1);
    border-bottom: 2px solid rgba(0,0,0,.3);
  }
  
</style>

<script>
  import groupItem from './include/group-item.vue';
  module.exports = {
    components: {
      groupItem
    },
    data() {
      return {
        coms: [
          {
            name: 'rect',
            icon: 'http://img1.vued.vanthink.cn/vuedd801b5ac3622f3a86e1274693e0cca06.png',
            url: 'pages/com-rect.js'
          },
          {
            name: 'circle',
            icon: 'http://img1.vued.vanthink.cn/vuedc972ff2bbd382918da0d54a50abf5886.png',
            url: 'pages/com-circle.js'
          },
          {
            name: 'line',
            icon: 'http://img1.vued.vanthink.cn/vuedd34a9f03fb2cfb4e4928fd50dac7c2f0.png',
            url: 'pages/com-line.js'
          },
          {
            name: 'polyline',
            icon: 'http://img1.vued.vanthink.cn/vued636ac9019ecf3cfb37203afaf58831a1.png',
            url: 'pages/com-polyline-and-polygon.js'
          },
          {
            name: 'path',
            icon: 'http://img1.vued.vanthink.cn/vued95cc17b37133964d0700f8efe0dd464f.png',
            url: 'pages/com-path.js'
          },
          {
            name: 'gradient',
            icon: 'http://img1.vued.vanthink.cn/vued8f6b468fa26fca56c459d2dcaf5c572d.png',
            url: 'pages/com-gradient.js'
          }
        ],
        props: [
          {
            name: 'fill',
            icon: 'http://img1.vued.vanthink.cn/vued6ff56846b1d309224d1c4984b7e885be.png',
            url: 'pages/prop-fill.js'
          },
          {
            name: 'stroke',
            icon: 'http://img1.vued.vanthink.cn/vued8ad53b37ce358bbe5fa5964f46cc1309.png',
            url: 'pages/prop-stroke.js'
          }
        ],
        demos: [
          {
            name: 'complex svg shape',
            icon: 'http://img1.vued.vanthink.cn/vued1d89bd6213951e62d0bad242093c82ce.png',
            url: 'pages/complex-shape.js',
          },
          {
            name: 'Test',
            icon: 'http://img1.vued.vanthink.cn/vued1d89bd6213951e62d0bad242093c82ce.png',
            url: 'pages/pressure.js',
          }
        ]
        
      }
    }   
    
  };
  
  
</script>