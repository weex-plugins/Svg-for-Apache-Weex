<template>
  <div class="page">
    <navbar back="true" title="rect"></navbar>
    <scroller class="main">
      <text class="desc">The rect component is an SVG basic shape, used to create rectangles based on the position of a corner and their width and height</text>
      <div class="item">
        <svg class="item-shape">
          <rect x="100" y="50" width="300" height="300" fill="#f39c12"></rect>
        </svg>
        <text class="desc">a simple rect componnet</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <rect x="5" y="5" width="50" height="50" fill="#f39c12"></rect>
          <rect x="60" y="60" width="50" height="50" fill="#f39c12"></rect>
          <rect x="115" y="115" width="50" height="50" fill="#f39c12"></rect>
          <rect x="170" y="170" width="50" height="50" fill="#f39c12"></rect>
          <rect x="220" y="225" width="50" height="50" fill="#f1c40f"></rect>
        </svg>
        <text class="desc">use x and y to set the position of shape</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <rect x="5" y="5" width="50" height="50" fill="#f39c12"></rect>
          <rect x="60" y="60" width="100" height="100" fill="#f39c12"></rect>
          <rect x="165" y="160" width="150" height="150" fill="#f39c12"></rect>
        </svg>
        <text class="desc">use width and height to set the size of shape</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <rect x="5" y="5" width="100" height="100" fill="#2ecc71"></rect>
          <rect x="120" y="5" width="100" height="100" fill="#3498db"></rect>
          <rect x="240" y="5" width="100" height="100" fill="#9b59b6"></rect>
          <rect x="5" y="120" width="100" height="100" stroke-width="2" fill="#fff" stroke="#2ecc71"></rect>
          <rect x="120" y="120" width="100" height="100" stroke-width="2" fill="#fff" stroke="#3498db"></rect>
          <rect x="240" y="120" width="100" height="100" stroke-width="4" fill="#fff" stroke="#9b59b6"></rect>
          <rect x="5" y="240" width="335" height="100" stroke-width="4" fill="#fff" stroke="#9b59b6"></rect>
        </svg>
        <text class="desc">use fill and stroke to set the color and outline of shape</text>
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