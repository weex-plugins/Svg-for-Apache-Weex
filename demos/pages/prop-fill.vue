<template>
  <div class="page">
    <navbar back="true" title="fill"></navbar>
    <scroller class="main">
      <text class="desc">For shapes and text, the fill attribute is a presentation attribute that define the color of the interior of the given graphical element</text>
      <div class="item">
        <svg class="item-shape">
          <circle cx="200" cy="240" r="150" fill="#ea6153"/>
          <circle cx="250" cy="240" r="150" fill="#9b59b6"/> 
          <circle cx="300" cy="240" r="150" fill="#2ecc71"/>
          <circle cx="350" cy="240" r="150" fill="#3498db"/>
          <circle cx="400" cy="240" r="150" fill="#1abc9c"/>
        </svg>
        <text class="warnning">You can only set hex color to the attribute.We'll support more int future.</text>
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
  .warnning{
    background-color: #f1c40f;
    color: #000;
    padding: 10px;
    font-size: 20px;
    border:2px solid #f39c12;
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