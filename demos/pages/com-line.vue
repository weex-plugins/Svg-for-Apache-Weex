<template>
  <div class="page">
    <navbar back="true" title="line"></navbar>
    <scroller class="main">
      <text class="desc">The line element is an SVG basic shape used to create a line connecting two points</text>
      <div class="item">
        <svg class="item-shape">
          <line x1="10" y1="70" x2="500" y2="70" stroke="#ea6153" stroke-width="2" />
          <line x1="10" y1="120" x2="500" y2="120" stroke="#ea6153" stroke-width="2" />
        </svg>
        <text class="desc">a simple line componnet</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <line x1="10" y1="70" x2="200" y2="70" stroke="#ea6153" stroke-width="2" />
          <line x1="220" y1="120" x2="420" y2="120" stroke="#ea6153" stroke-width="2" />
          <line x1="220" y1="130" x2="220" y2="320" stroke="#ea6153" stroke-width="2" />
        </svg>
        <text class="desc">use x1 y1 defaine the start of line. x2 and y2 defines the end of line</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <line x1="10" y1="70" x2="500" y2="70" stroke="#3498db" stroke-width="2" />
          <line x1="10" y1="120" x2="500" y2="120" stroke="#2ecc71" stroke-width="2" />
          <line x1="10" y1="170" x2="500" y2="170" stroke="#e74c3c" stroke-width="2" />
          <line x1="10" y1="220" x2="500" y2="220" stroke="#34495e" stroke-width="4" />
          <line x1="10" y1="270" x2="500" y2="270" stroke="#95a5a6" stroke-width="8" />
        </svg>
        <text class="desc">stroke define the color of line. stroke-width define the width of line</text>
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