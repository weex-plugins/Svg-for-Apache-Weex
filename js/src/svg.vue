<template>
  <svg :width="width" :height="height"></svg>
</template>

<script>
import registerComponent from '../mixins/registerComponent';
module.export = {
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