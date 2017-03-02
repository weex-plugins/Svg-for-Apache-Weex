<template>
  <div class="group-item" @click="redirect">
    <icon :src="icon"></icon>
    <text class="group-item-text">{{name}}</text>
  </div>
</template>

<style>
  .group-item{
    flex-direction: row;
    align-items: center;
    height: 110px;
    color: #555;
  }
  .group-item-text{
    flex: 1;
    margin-left: 40px;
    padding-bottom: 20px;
    border-bottom: 2px solid rgba(0,0,0,.1);
  }
</style>

<script>

import icon from './icon.vue';
import helper from '../lib/helper.js';
const navigator = weex.requireModule('navigator');
export default {
  components: {
    icon  
  },
  props: {
    name: {
      default: '',
      type: String
    },
    icon: {
      default: '',
      type: String
    },
    url: {
      default: '',
      type: String,
    }
  },
  
  methods: {
    redirect() {
      const url = helper.setBundleUrl(this.url, weex);
      navigator.push({
        url: url
      });
    }
  }
}; 

</script>