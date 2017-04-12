<template>
  <svg :width="width" :height="height"></svg>
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
  ],
  mixins: registerComponent,
  
  
}; 
</script>