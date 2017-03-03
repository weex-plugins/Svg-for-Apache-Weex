<template>
  <div class="page">
    <navbar back="true" title="stroke"></navbar>
    <scroller class="main">
      <text class="desc">The stroke attribute defines the color of the outline on a given graphical element. The default value for the stroke attribute is none</text>
      <div class="item">
        <svg class="item-shape">
          <circle cx="200" cy="240" r="150" fill="#fff" stroke="#ea6153" stroke-width="2"/>
          <circle cx="220" cy="240" r="150" fill="#fff" stroke="#9b59b6" stroke-width="2"/> 
          <circle cx="240" cy="240" r="150" fill="#fff" stroke="#2ecc71" stroke-width="2"/>
          <circle cx="260" cy="240" r="150"  fill="#fff" stroke="#3498db" stroke-width="2"/>
          <circle cx="280" cy="240" r="150" fill="#fff" stroke="#1abc9c" stroke-width="2"/>
        </svg>
        <text class="warnning">You can only set hex color to the attribute.We'll support more int future.</text>
      </div>
      <text class="desc">The strokeWidth attribute specifies the width of the outline on the current object</text>
      <div class="item">
        <svg class="item-shape">
          <line x1="10" y1="70" x2="500" y2="70" stroke="#999999" stroke-width="2" />
          <line x1="10" y1="120" x2="500" y2="120" stroke="#888888" stroke-width="4" />
          <line x1="10" y1="170" x2="500" y2="170" stroke="#777777" stroke-width="6" />
          <line x1="10" y1="220" x2="500" y2="220" stroke="#666666" stroke-width="8" />
          <line x1="10" y1="270" x2="500" y2="270" stroke="#555555" stroke-width="10" />
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