<template>
  <div class="navbar">
    <div v-if="leftCorner" class="btn-icon-left" @click="goHome">
      <image class="btn-arrow-left" :src="leftCorner.src"></image>
      <text class="btn-text">{{leftCorner.text}}</text>
    </div>
    <div v-if="back" class="btn-icon-left" @click="goHome">
      <image class="btn-arrow-left" src="http://img1.vued.vanthink.cn/vued9c8e7e738ff94abee23eb69d7f1401e9.png"></image>
      <text class="btn-text">返回</text>
    </div>
    <div v-if="rightCorner" class="btn-icon-left" @click="goHome">
      <image class="btn-arrow-left" src="rightCorner.src"></image>
      <text class="btn-text">{{rightCorner.text}}</text>
    </div>
    <text class="title">{{title}}</text>
  </div>
</template>
<style>
  .navbar{
    z-index: 200;
    position: fixed;
    left:0;
    right: 0;
    top:0;
    display:flex;
    flex-direction: row;
    height: 88px;
    background-color: #fff;
    align-items: center;
    border-bottom-width: 2px;
    border-bottom-style: solid;
    border-bottom-color: #ddd;
    
  }
  .btn-icon-left{
    z-index:10;
    flex-direction: row;
    align-items: center;
    width: 180px;
  }
  .btn-arrow-left{
    width: 60px;
    height: 60px;
    margin-left: 10px;
  }
  .btn-text{
    color: #0f89f5;
  }
  .title{
    position: absolute;
    left:0;
    right:0;
    top:0;
    height: 88px;
    line-height: 88px;
    font-size: 34px;
    text-align: center;
  }
</style>
<script>
  const navigator = weex.requireModule('navigator');
  module.exports = {
    props: {
      title: {
        default: 'Title',
        type: String
      },
      back: {
        default: false,
        type: Boolean 
     }
    },
    methods: {
      goHome() {
        navigator.pop();
      }
    }   
  }
</script>