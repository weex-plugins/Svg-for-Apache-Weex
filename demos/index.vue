<template>
  <div class="page">
    <image class="logo" src="http://img1.vued.vanthink.cn/vued0366b8d467968db7b4e9055288972c4e.png"></image>
    <p>line</p>
    <div class="item">
      <svg width="200" height="200">
        <line x1="10" y1="70" x2="120" y2="70" stroke="#ea6153" stroke-width="2" />
        <line x1="70" y1="10" x2="70" y2="120" stroke="#ea6153" stroke-width="2" />
      </svg>
    </div>
  </div> 
</template>

<style>
  .page{
    flex: 1;
    padding-top: 20px;
    text-align: center;
    justify-content: center;
    align-items: center;
  }
  .logo{
    width: 240px;
    height: 120px;
  }
  .item{
    width: 400px;
    height: 300px;
    margin: 20px auto;
  }
  svg{
    margin-top: 20px;
  }
  p{
    text-align: left;
  }
</style>

<script>
 // require('../web/index');
  module.exports = {
     
    
  };
  
  
</script>
