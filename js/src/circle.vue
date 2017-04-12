<template>
  <circle :cx="cx" :cy="cy" :r="r" :fill="fill" :stroke="stroke"></circle>
</template>

<script>
import registerComponent from '../mixins/register-component';
export default {
  props: [
    'fill',
    'stroke',
    'strokeWidth',
    'cx',
    'cy',
    'r'
  ],
  mixins: registerComponent,
  
  
}; 
</script>