/**
 * Copyright (c) 2015-present, Horcrux.
 * All rights reserved.
 * <p>
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */


package com.alibaba.weex.svg;

import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

/**
 * Contains static helper methods for accessing props.
 */
public class PropHelper {


  public static
  @Nullable
  float[] toFloatArray(@Nullable JSONArray value) {
    if (value != null) {
      float[] result = new float[value.length()];
      toFloatArray(value, result);
      return result;
    }
    return null;
  }

  public static int toFloatArray(JSONArray value, float[] into) {
    int length = value.length() > into.length ? into.length : value.length();
    for (int i = 0; i < length; i++) {
      try {
        into[i] = (float) value.getDouble(i);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    return value.length();

  }

  /**
   * Converts percentage string into actual based on a relative number
   *
   * @param percentage percentage string
   * @param relative   relative number
   * @param offset     offset number
   * @return actual float based on relative number
   */
  public static float fromPercentageToFloat(String percentage, float relative, float offset, float scale) {
    Matcher matched = Pattern.compile("^(\\-?\\d+(?:\\.\\d+)?)%$").matcher(percentage);
    if (matched.matches()) {
      return Float.valueOf(matched.group(1)) / 100 * relative + offset;
    } else {
      return Float.valueOf(percentage) * scale;
    }
  }

  /**
   * Judge given string is a percentage-like string or not.
   *
   * @param string percentage string
   * @return string is percentage-like or not.
   */

  public static boolean isPercentage(String string) {
    Pattern pattern = Pattern.compile("^(\\-?\\d+(?:\\.\\d+)?)%$");
    return pattern.matcher(string).matches();
  }

  public static class RNSVGBrush {

    private GradientType mType = GradientType.LINEAR_GRADIENT;
    private ArrayList<String> mPoints;
    private float[] mStops;
    private int[] mStopColors;

    public RNSVGBrush(GradientType type, ArrayList<String> points, float[] colors) {
      mType = type;
      mPoints = points;
      mStops = colors;
    }

    public RNSVGBrush(GradientType type, ArrayList<String> points, float[] stops, int[] stopColors) {
      mType = type;
      mPoints = points;
      mStops = stops;
      mStopColors = stopColors;
    }

    public enum GradientType {
      LINEAR_GRADIENT(0),
      RADIAL_GRADIENT(1);

      GradientType(int ni) {
        nativeInt = ni;
      }

      final int nativeInt;
    }

    public void setupPaint(Paint paint, RectF box, float scale, float opacity) {
      float height = box.height();
      float width = box.width();
      float midX = box.centerX();
      float midY = box.centerY();
      float offsetX = (midX - width / 2);
      float offsetY = (midY - height / 2);


      int[] stopsColors = mStopColors;
      float[] stops = mStops;
      //parseGradientStops(mColors, stopsCount, stops, stopsColors, opacity);

      if (mType == GradientType.LINEAR_GRADIENT) {
        float x1 = PropHelper.fromPercentageToFloat(mPoints.get(0), width, offsetX, scale);
        float y1 = PropHelper.fromPercentageToFloat(mPoints.get(1), height, offsetY, scale);
        float x2 = PropHelper.fromPercentageToFloat(mPoints.get(2), width, offsetX, scale);
        float y2 = PropHelper.fromPercentageToFloat(mPoints.get(3), height, offsetY, scale);
        paint.setShader(
            new LinearGradient(
                x1,
                y1,
                x2,
                y2,
                stopsColors,
                stops,
                Shader.TileMode.CLAMP));
      } else {
        float rx = PropHelper.fromPercentageToFloat(mPoints.get(2), width, 0f, scale);
        float ry = PropHelper.fromPercentageToFloat(mPoints.get(3), height, 0f, scale);
        float cx = PropHelper.fromPercentageToFloat(mPoints.get(4), width, offsetX, scale);
        float cy = PropHelper.fromPercentageToFloat(mPoints.get(5), height, offsetY, scale) / (ry / rx);
        // TODO: support focus point.
        //float fx = PropHelper.fromPercentageToFloat(mPoints.getString(0), width, offsetX) * scale;
        //float fy = PropHelper.fromPercentageToFloat(mPoints.getString(1), height, offsetY) * scale / (ry / rx);
        Shader radialGradient = new RadialGradient(
            cx,
            cy,
            rx,
            stopsColors,
            stops,
            Shader.TileMode.CLAMP
        );

        Matrix radialMatrix = new Matrix();
        radialMatrix.preScale(1f, ry / rx);
        radialGradient.setLocalMatrix(radialMatrix);
        paint.setShader(radialGradient);
      }
    }
  }
}
