<template>
  <div class="page">
    <navbar back="true" title="path"></navbar>
    <scroller class="main">
      <text class="desc">The rect component is an SVG basic shape, used to create rectangles based on the position of a corner and their width and height</text>
      <div class="item">
        <svg class="item-shape">
          <path d="M300,240 A50,50 0 0,1 150,80" stroke="#e84c3d" stroke-width="4" fill="none" />  
        </svg>
        <text class="desc">a simple path componnent</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <path d="M25,100 C25,150 75,150 75,100 S100,25 150,75" stroke="#3598dc" fill="none" stroke-width="2"/>
        </svg>
        <text class="desc">path command with C and S</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <path d="M20,20 L420,20" stroke="#e84c3d" stroke-width="4" fill="none" />  
          <path d="M120,240
                   A50,50 0 0,0 120,80" stroke="#3598dc" fill="none" stroke-width="2"/>
        <path d="M50,50
                   A50,50 0 1,0 120,80" stroke="#2fcc71" fill="none" stroke-width="2" />
        <path d="M50,50
                   A50,50 0 0,1 120,80" stroke="#ea6153" fill="none" stroke-width="2" />
        </svg>
        <text class="desc">The attribute d defines a path to follow</text>
      </div>
      <div class="item">
        <svg style="width:300px;height:300px;" viewBox="0 0 300 300">
          <path fill="#9b59b6" d="M63.9,128.2l39.7,16.9l14,48.2l-27.6,28.6l-45.3,2.5c-13.8-19.5-22.3-43.1-23.4-68.6L63.9,128.2L63.9,128.2z
	 M64.9,116.4L47.7,71.8C62.3,52.7,82,37.9,104.9,29.3l39.5,32.1v42.7l-37.1,30.2L64.9,116.4L64.9,116.4z M101,226.6l25.3-26.2h46.1
	l26.3,27.3l-12.2,45.9l0,0c-11.6,3.4-23.9,5.3-36.6,5.3s-25-1.8-36.6-5.3L101,226.6L101,226.6z M208.6,221.7L182,194.2l14.4-49.7
	l39.2-16.6l43,27.8c-1.1,25.5-9.6,49.1-23.4,68.6L208.6,221.7L208.6,221.7z M235.3,115.9l-42.8,18.2l-36.8-30V61.4l39.5-32.1l0,0
	c22.9,8.5,42.7,23.4,57.3,42.5L235.3,115.9L235.3,115.9z M39.8,83.2l14.5,37.7l-32.9,21.3C22.7,120.7,29.3,100.6,39.8,83.2
	L39.8,83.2z M100.4,268.9c-18.1-7.5-34-19.1-46.8-33.5l37.3-2L100.4,268.9L100.4,268.9z M199.6,268.9l9.5-35.5l37.3,2
	C233.7,249.8,217.7,261.3,199.6,268.9z M260.2,83.2c10.6,17.4,17.1,37.5,18.4,59l-32.9-21.3L260.2,83.2L260.2,83.2z M182.2,25.3
	L150,51.4l-32.2-26.2c10.3-2.7,21.1-4.1,32.2-4.1C161.1,21.2,171.9,22.6,182.2,25.3z M150,290c77.3,0,140-62.7,140-140
	S227.3,10,150,10S10,72.7,10,150S72.7,290,150,290L150,290z M150,114.2l35.8,29.1l-14.6,45.9h-42.6l-14.6-45.9L150,114.2L150,114.2z
	"/></path>
        </svg>
        <text class="desc">use path to draw a soccer</text>
      </div>
      <div class="item">
        <svg style="width:300px;height:300px;" viewBox="0 0 1024 1024">
         <path fill="#f1c40f" d="M210.1,129.3L210.1,129.3c1.1-4.4,1.8-8.9,2-13.5c-5.8,2-12.1,3.2-18.6,3.2c-30.9,0-55.9-25-55.9-55.9
	c0-6.5,1.1-12.8,3.2-18.6c-4.7,0.2-9.2,0.9-13.5,2c-29.7,7.4-51.7,34.3-51.7,66.3c0,37.7,30.6,68.3,68.3,68.3
	C175.8,181,202.7,159,210.1,129.3L210.1,129.3z M87.9,112.7c0-24.4,15.6-45.1,37.3-52.7c0,1-0.1,2-0.1,3.1
	c0,37.7,30.6,68.3,68.3,68.3c1,0,2,0,3.1-0.1c-7.7,21.7-28.4,37.3-52.7,37.3C112.9,168.6,87.9,143.5,87.9,112.7L87.9,112.7
	L87.9,112.7z M38.3,218.3c-3.5,0-6.3-2.9-6.3-6.2c0-3.4,2.8-6.2,6.3-6.2h75.5l36.2,31.1l36.2-31.1h75.5c3.5,0,6.3,2.9,6.3,6.2
	c0,3.4-2.8,6.2-6.3,6.2h-68.2L150,255.6l-43.5-37.3H38.3z"></path>
        </svg>
        <text class="desc">use path to draw a moon</text>
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