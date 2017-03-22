<template>
  <div class="page">
    <navbar back="true" title="polyline and polygon"></navbar>
    <scroller class="main">
      <text class="desc">The polyline element is an SVG basic shape that creates straight lines connecting several points.</text>
      <div class="item">
        <svg class="item-shape">
          <polyline points="0,0 500,0 500,400" fill="#ea6153"></polyline> 
        </svg>
        <text class="desc">a simple polyline componnet</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <polyline points="300,40 450,200 150,200 300,40" stroke="#ea6153" fill="none" stroke-width="4"></polyline>
          <polyline points="150,100 450,100 300,260 150,100" stroke="#ea6153" fill="none" stroke-width="4"></polyline>
        </svg>
        <text class="desc">The points attribute defines a list of points required to draw a polyline or polygon component</text>
      </div>
      <text class="desc">The polygon element defines a closed shape consisting of a set of connected straight line segments</text>
      <div class="item">
        <svg class="item-shape">
          <polygon  points="0,30 50,0 70,30 70,60 50,80 0,60" fill="#ea6153" /> 
        </svg>
        <text class="desc">a simple polygon componnet </text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <polygon  points="300,40 450,200 150,200" fill="#9b59b6" />
          <polyline points="150,100 450,100 300,260 150,100" fill="#2ecc71"></polyline>
        </svg>
        <text class="desc">As the some to polyline ,the points attribute defines a list of points required to draw a polyline or polygon component</text>
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
    height: 480px;
  }
</style>

<script>
  import navbar from '../include/navbar.vue';
  module.exports = {
    components: {
      navbar  
    },
    
    
  };
  
  
</script>