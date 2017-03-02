<template>
  <rect :width="width" :height="height" :x="x" :y="y" :rx="rx" :ry="ry" :fill="fill" :stroke="stroke"></rect>
</template>

<script>
import registerComponent from '../mixins/register-component';
export default {
  props: [
    'width',
    'height',
    'fill',
    'stroke',
    'strokeWidth',
    'x',
    'y',
    'rx',
    'ry'
  ],
  mixins: registerComponent,
  
  
}; 
</script>