<template>
  <line :x1="x1" :y1="y1" :x2="x2" :y2="y2" :stroke="stroke"></line>
</template>
<script>
import registerComponent from '../mixins/register-component';
export default {
  props: [
    'fill',
    'stroke',
    'strokeWidth',
    'x1',
    'y1',
    'x2',
    'y2'
  ],
  mixins: registerComponent,
  
  
}; 
</script>