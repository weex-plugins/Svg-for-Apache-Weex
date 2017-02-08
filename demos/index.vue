<template>
  <div class="page">
    <image class="logo" src="http://img1.vued.vanthink.cn/vued0366b8d467968db7b4e9055288972c4e.png"></image>
    <p>rect</p>
    <div class="item">
      <svg width="300" height="300">
        <rect x="20" y="20" width="160" height="160" fill="#f39c12"/> 
        <rect x="20" y="200" rx="22.5" ry="22.5" width="100" height="45" style="fill:#ea6153"/> 
      </svg>
    </div>
    <p>circle</p>
    <div class="item">
      <svg width="200" height="200">
       <circle cx="50" cy="50" r="45" style="fill:#ea6153;"/>
       <circle cx="150" cy="50" r="45" style="stroke:#ea6153; stroke-width:2; fill:none;"/>
       <circle cx="250" cy="50" r="45" style="stroke:#ea6153; stroke-width:1; stroke-dasharray:5; fill:none;" />
       <circle cx="350" cy="50" r="45" style="fill:#ea6153;fill-opacity:0.6;" />
    </svg>
    </div>
    <p>line</p>
    <div class="item">
      <svg width="200" height="200">
        <line x1="10" y1="10" x2="120" y2="10" style="stroke:#333" />            
      </svg>
    </div>
    <p>polyline</p>
    <div class="item">
      <svg width="200" height="200">
          <polyline points="0,0 100,0 100,100" style="fill:#ea6153"></polyline>            
      </svg>
    </div>
    <p>polygon</p>
    <div class="item">
      <svg width="200" height="200">
        <polygon  points="0,30 50,0 70,30 70,60 50,80 0,60" />           
      </svg>
    </div>
    <p>path</p>
    <div class="item">
      <svg width="200" height="200">
          <path d="M50,50 A50,50 0 0,1 150,80" style="stroke:#e84c3d;fill:none;" />           
      </svg>
    </div>
    <p>text</p>
    <div class="item">
      <svg width="200" height="200">
        <text x="20"  y="40">Example SVG text 1</text>          
      </svg>
    </div>
    <p>linear gradient</p>
    <div class="item">
      <svg width="200" height="200">
        <defs>
          <linearGradient id="myLinearGradient1"
                          x1="0%" y1="0%"
                          x2="0%" y2="100%"
                          spreadMethod="pad">
            <stop offset="0%"   stop-color="#3498db" stop-opacity="1"/>
            <stop offset="100%" stop-color="#2980b9" stop-opacity="1"/>
          </linearGradient>
        </defs>

        <rect x="10" y="10" width="75" height="100" rx="10" ry="10"
           style="fill:url(#myLinearGradient1);" />          
      </svg>
    </div>
    <p>radial gradient</p>
    <div class="item">
      <svg width="200" height="200">
        <defs>
          <radialGradient id="myRadialGradient4"
             fx="5%" fy="5%" r="65%"
             spreadMethod="pad">
            <stop offset="0%"   stop-color="#9b59b6" stop-opacity="1"/>
            <stop offset="100%" stop-color="#8e44ad" stop-opacity="1" />
          </radialGradient>
        </defs>
        <rect x="20" y="10" width="100" height="100" rx="10" ry="10" fill="url(#myRadialGradient4)" />         
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