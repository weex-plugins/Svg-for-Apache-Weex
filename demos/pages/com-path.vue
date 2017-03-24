<template>
  <div class="page">
    <navbar back="true" title="path"></navbar>
    <scroller class="main">
      <text class="desc">The rect component is an SVG basic shape, used to create rectangles based on the position of a corner and their width and height</text>
      <div class="item">
        <svg class="item-shape">
          <path d="M300,240 A50,50 0 0,1 150,80" stroke="#e84c3d" stroke-width="4" fill="none" />  
        </svg>
        <text class="desc">a simple path componnet</text>
      </div>
      <div class="item">
        <svg class="item-shape">
          <path d="M20,20 L420,20" stroke="#e84c3d" stroke-width="4" fill="none" />  
          <path d="M120,240
                   A50,50 0 0,0 120,80" style="stroke:#3598dc;fill:none; stroke-width:2;" />
        <path d="M50,50
                   A50,50 0 1,0 120,80" style="stroke:#2fcc71;fill:none;stroke-width:2;" />
        <path d="M50,50
                   A50,50 0 0,1 120,80" style="stroke:#ea6153;fill:none;stroke-width:2;" />
        </svg>
        <text class="desc">The attribute d defines a path to follow</text>
      </div>
      <div class="item">
        <svg style="width:300px;height:300px;" viewBox="0 0 1024 1024">
          <path fill="#9b59b6" d="M282.096 465.638l113.485 48.172 40.052 137.68-78.856 81.658-129.431 7.11c-39.496-55.842-63.826-123.172-66.993-195.993l121.743-78.626zM284.936 432.080l-49.333-127.558c41.686-54.459 98.242-96.933 163.603-121.355l112.794 91.809v122.047l-106.134 86.389-120.929-51.331zM387.908 746.977l72.404-74.977h131.769l75.234 77.907-34.756 131.027v0c-33.143 9.804-68.237 15.066-104.559 15.066s-71.416-5.262-104.559-15.066l-35.533-133.957zM695.414 732.939l-75.882-78.579 41.276-141.887 112.002-47.542 122.837 79.333c-3.167 72.821-27.497 140.151-66.993 195.993l-133.24-7.319zM771.606 430.679l-122.417 51.963-105.188-85.619v-122.047l112.794-91.809v0c65.361 24.423 121.917 66.896 163.603 121.355l-48.791 126.156zM213.234 337.249l41.532 107.667-94.108 60.896c3.655-61.467 22.398-118.872 52.576-168.563zM386.164 867.672c-51.591-21.568-97.225-54.547-133.745-95.782l106.713-5.768 27.032 101.55zM669.836 867.672l27.032-101.55 106.713 5.768c-36.521 41.235-82.154 74.214-133.745 95.782zM842.766 337.249c30.178 49.691 48.921 107.096 52.576 168.563l-94.108-60.896 41.532-107.667zM620.053 171.607l-92.053 74.793-92.053-74.793c29.421-7.577 60.266-11.607 92.053-11.607s62.632 4.030 92.053 11.607zM528 928c220.914 0 400-179.086 400-400s-179.086-400-400-400c-220.914 0-400 179.086-400 400s179.086 400 400 400v0zM528 425.6l102.4 83.2-41.601 131.2h-121.6l-41.601-131.2 102.4-83.2z"></path>
        </svg>
        <text class="desc">use path to draw a soccer</text>
      </div>
      <div class="item">
        <svg style="width:300px;height:300px;" viewBox="0 0 1024 1024">
         <path fill="#f1c40f" d="M682.768 474.595v0c2.807-11.244 4.533-22.917 5.060-34.901-14.965 5.268-31.061 8.133-47.827 8.133-79.529 0-144-64.471-144-144 0-16.766 2.865-32.863 8.133-47.827-11.984 0.527-23.656 2.253-34.901 5.060-76.535 19.106-133.232 88.316-133.232 170.768 0 97.202 78.798 176 176 176 82.452 0 151.662-56.698 170.768-133.232zM368 431.827c0-62.763 40.153-116.148 96.173-135.867-0.115 2.608-0.173 5.231-0.173 7.867 0 97.202 78.798 176 176 176 2.636 0 5.259-0.058 7.867-0.173-19.719 56.019-73.104 96.173-135.867 96.173-79.529 0-144-64.471-144-144v0zM240.263 704c-8.982 0-16.263-7.422-16.263-16 0-8.837 7.252-16 16.263-16h194.404l93.333 80 93.333-80h194.404c8.982 0 16.263 7.422 16.263 16 0 8.837-7.252 16-16.263 16h-175.737l-112 96-112-96h-175.737z"></path>
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