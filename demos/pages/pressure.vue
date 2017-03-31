<template>
  <div class="page">
    <navbar back="true" title="pressure"></navbar>
    <scroller class="main">
      <text class="desc">this is simple pressure test </text>
      <div class="item">
        <svg class="item-shape">
          <circle cx="300" cy="200" v-for="item in circles" fill="none" :key="item.r" :r="item.r" :stroke="item.color"  stroke-width="2"/>
        </svg>
        <text class="desc">more than 100 cricles</text>
      </div>
      <div class="item">
        <svg class="item-shape2">
          <rect v-for="item in randomShapes.rects" :key="item.x" fill="none" :x="item.x" :y="item.y" :width="item.w" :height="item.h" stroke="#222" stroke-width="2" />
        </svg>
        <div class="btn" @click="genRandomShapes">
          <text class="btn-text1">随机添加形状</text>
        </div>
      </div>
      <div class="item">
        <svg class="item-shape2">
          <polygon :points="points" fill="#9b59b6" stroke="#222222" stroke-width="2"/>
        </svg>
        <div class="btn" @click="changePath">
          <text class="btn-text1">随机改变形状</text>
        </div>
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
  .item-shape2{
    width: 600px;
    height: 600px;
  }
  .btn{
    padding: 20px;
    background-color: #1ba1e2;
  }
  .btn-text1{
    padding-left: 30px;
    padding-right: 30px;
    color: #fff;
  }
</style>

<script>
  import navbar from '../include/navbar.vue';
  const circleArr = [];
  for(var i=4; i < 150; i+=4) {
    circleArr.push({r: i, color: ['#', i, i, i, i, i, i].join('').substr(0,7)});   
  }
  module.exports = {
    components: {
      navbar  
    },
    
    data() {
      return {
        circles: circleArr,
        randomShapes: {
          circles: [],
          rects: [],
          polygon: [],
        },
        points: '100,100 200,200',
      }
    },
    
    methods: {
      
      genRandomShapes() {
         function genRect() {
           return {
             x: Math.floor(Math.random() * 300),
             y: Math.floor(Math.random() * 300),
             w: Math.floor(Math.random() * 300),
             h: Math.floor(Math.random() * 300),
           }
         }
          console.log(this.randomShapes.rects);
         this.randomShapes.rects.push(genRect());
        
      },
      
      changePath() {
        let dots = 3 + Math.floor(Math.random() * 10);
        let path = [];
        for(let i=0; i<dots; i++) {
          path.push(Math.floor(Math.random() * 600) + ',' + Math.floor(Math.random() * 600));
        }
        this.points = path.join(' ');
      }
      
    }
    
    
  };
  
  
</script>