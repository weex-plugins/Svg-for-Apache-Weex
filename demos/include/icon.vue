<template>
  <image class="icon" :src="src"></image>
</template>

<style>
  .icon{
    width: 54px;
    height: 54px;
  }
</style>

<script>
export default {
  props: {
    src: {
      default: '',
    }
  }
};

</script>