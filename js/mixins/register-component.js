import color from 'color';

function colorToRgba(c) {
  const colorObj = color(c);
  return colorObj.rgb().array().push(colorObj.valpha);
}

export default {
  computed: {
    fill() {
      return colorToRgba(this.fill);
    }
  }
};
