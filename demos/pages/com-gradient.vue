<template>
  <div class="page">
    <navbar back="true" title="gradient"></navbar>
    <scroller class="main">
      <text class="desc">The linearGradient element lets users define linear gradients to fill or stroke graphical elements</text>
      <div class="item">
        <svg class="item-shape">
          <defs>
            <linearGradient id="myLinearGradient1"
                            x1="0%" y1="0%"
                            x2="100%" y2="0%"
                            spreadMethod="pad">
              <stop offset="0%" stop-color="#ee0979" stop-opacity="1"/>
              <stop offset="100%" stop-color="#ff6a00" stop-opacity="1"/>

            </linearGradient>
          </defs>
          <rect x="100" y="50" width="300" height="300" fill="url(#myLinearGradient1)" />
        </svg>
        <text class="desc">linearGradient</text>
      </div>
      <text class="desc">Radial gradients are gradients in which the colors change circularly rather than linearly</text>
      <div class="item">
        <svg class="item-shape">
          <defs>
            <radialGradient id="myRadialGradient"
               cx="20%" cy="50%" r="45%"
               spreadMethod="pad">
              <stop offset="0%"   stop-color="#4776E6" stop-opacity="1"/>
              <stop offset="100%" stop-color="#8E54E9" stop-opacity="1" />
            </radialGradient>
          </defs>
          <circle  cx="300" cy="200" r="150" fill="url(#myRadialGradient)" />
        </svg>
        <text class="desc">radialGradient</text>
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