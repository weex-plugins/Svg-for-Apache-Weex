<template>
  <div class="page">
    <navbar back="true" title="D3.js"></navbar>
    <scroller class="main">
      <text class="desc"></text>
      <div class="item">
        <svg id="area" class="item-shape">
          <path :d="weatherPath" stroke="#9b59b6" fill="none" stroke-width="1"></path> 
          <path :d="xAixPath" stroke="#9b59b6" fill="none" stroke-width="1.5"></path>
          <path :d="yAixPath" stroke="#9b59b6" fill="none" stroke-width="1.5"></path>
        </svg>
        <text class="desc">a simple rect componnet</text>
      </div>
      <div class="item">
        <svg id="stock" style="width:600px; height:312px">
          <path :d="xAixPath" stroke="#333333" fill="none" stroke-width="1.5"></path>
          <path :d="yAixPath" stroke="#333333" fill="none" stroke-width="1.5"></path>
          <rect :d="item.d" :fill="item.fill" :x="item.x" :y="item.y" :width="item.width" :key="item.x" :height="item.height" v-for="item in stokcRects"></rect>
        </svg>
        <text class="desc">a simple rect componnet</text>
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
  .item-shape{
    width: 600px;
    height: 312px;
  }
</style>

<script>
  import navbar from '../include/navbar.vue';
  import d3Utils from  '../lib/d3.utils.js'
  var data = require('../data/weather.json');
 

  module.exports = {
    components: {
      navbar  
    },
    data() {
      return {
        weatherPath: '',
        xAixPath: '',
        yAixPath: '',
        stokcRects: []
        
      }
    },
    created() {
      const margin = {top: 10, right: 10, bottom: 15, left: 10};
      const width = 600 - margin.left - margin.right;  
      const height = 312 - margin.top - margin.bottom;
      this.weatherPath = d3Utils.getLineAreaData(width, height);
      this.xAixPath = d3Utils.getXAixPath(width, height + margin.top);
      this.yAixPath = d3Utils.getYAixPath(width, height + margin.top);
      this.stokcRects = d3Utils.generateStockData(width, height);
    },
    
    
    
    
    
    
    
  };
  
  
</script>