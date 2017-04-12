<template>
  <div class="page">
    <navbar back="true" title="circle"></navbar>
    <scroller class="main">
      <text class="desc">The circle element is an SVG basic shape, used to create circles based on a center point and a radius</text>
      <div class="item">
        <svg class="item-shape">
          <circle cx="300" cy="200" r="100" fill="#ea6153"/>
        </svg>
        <text class="desc">a simple circle componnet</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <circle cx="55" cy="55" r="50" fill="#ea6153"/>
          <circle cx="160" cy="160" r="50" fill="#ea6153"/>
          <circle cx="275" cy="275" r="50" fill="#ea6153"/>
        </svg>
        <text class="desc">use "cx" and "cy" to set the center position of the component</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <circle cx="55" cy="55" r="50" fill="#ea6153"/>
          <circle cx="200" cy="200" r="100" fill="#ea6153"/>
          <circle cx="330" cy="330" r="30" fill="#ea6153"/>
        </svg>
        <text class="desc">"r" defines the radius of the circle. </text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <circle cx="105" cy="200" r="100" fill="#ea6153"/>  
          <circle cx="155" cy="200" r="100" fill="#3498db"/>  
          <circle cx="195" cy="200" r="100" fill="#9b59b6"/>
          <circle cx="410" cy="200" r="100" fill="#fff" stroke-width="4" stroke="#9b59b6"/> 
        </svg>
        <text class="desc">use fill and stroke to set the color and outline of the component</text>
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