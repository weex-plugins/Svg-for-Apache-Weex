package com.alibaba.weex.svg.component;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.weex.svg.ISvgDrawable;
import com.alibaba.weex.svg.PropHelper;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.larvalabs.svgandroid.SVGParser;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

/**
 * Created by budao on 2017/2/22.
 */

public class WXSvgPath extends WXSvgAbsComponent {
  private static final String TAG = "WXSvgPath";

  private static final int CAP_BUTT = 0;
  private static final int CAP_ROUND = 1;
  private static final int CAP_SQUARE = 2;

  private static final int JOIN_BEVEL = 2;
  private static final int JOIN_MITER = 0;
  private static final int JOIN_ROUND = 1;

  private static final int FILL_RULE_EVENODD = 0;
  private static final int FILL_RULE_NONZERO = 1;

  JSONArray mFill = new JSONArray();
  JSONArray mStroke = new JSONArray();
  float[] mStrokeDasharray;
  public float mStrokeWidth = 1;
  public float mStrokeOpacity = 1;
  public float mStrokeMiterlimit = 4;
  public float mStrokeDashoffset = 0;
  public Paint.Cap mStrokeLinecap = Paint.Cap.ROUND;
  public Paint.Join mStrokeLinejoin = Paint.Join.ROUND;

  public float mFillOpacity = 1.0f;
  public Path.FillType mFillRule = Path.FillType.WINDING;
  private boolean mFillRuleSet;

  protected Path mPath;
  private float[] mD;
  protected ReadableArray mPropList;// = new WritableNativeArray();

  protected WritableArray mOwnedPropList;// = new WritableNativeArray();

  private ArrayList<String> mChangedList;
  private ArrayList<Object> mOriginProperties;

  public WXSvgPath(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
    super(instance, dom, parent);
  }

//  public void parseFromJson(JSONObject map) {
//    super.parseFromJson(map);
//    if (map != null) {
//      JSONObject attrs = map.getJSONObject("attr");
//      String stroke = attrs.getString("stroke");
//      if (!TextUtils.isEmpty(stroke)) {
//        try {
//          mStroke.put(0, 0);
//          mStroke.put(1, 255);
//          mStroke.put(2, 255);
//          mStroke.put(3, 0);
//        } catch (JSONException e) {
//          e.printStackTrace();
//        }
//
//      }
//
//      String fill = attrs.getString("fill");
//      if (!TextUtils.isEmpty(stroke)) {
//        try {
//          mFill = new JSONArray();
//          mFill.put(0, 0);
//          mFill.put(1, 255);
//          mFill.put(2, 0);
//          mFill.put(3, 0);
//        } catch (JSONException e) {
//          e.printStackTrace();
//        }
//      }
//
//    }
//    Log.v(TAG, "WXSvgPathDomNode " + map);
//  }

  @WXComponentProp(name = "d")
  public void setPath(@Nullable String shapePath) {
    JSONArray jsonArray = null;
    mPath = SVGParser.parsePath(shapePath);
//    try {
//      jsonArray = new JSONArray(shapePath);
//    } catch (JSONException e) {
//      e.printStackTrace();
//    }
//    mD = PropHelper.toFloatArray(jsonArray);
    setupPath();
  }

  @WXComponentProp(name = "d")
  public void setPath(@Nullable ReadableArray shapePath) {
    mD = PropHelper.toFloatArray(shapePath);
    setupPath();
    
  }

//  @WXComponentProp(name = "fill")
//  public void setFill(@Nullable String fill) {
//    try {
//      mFill = new JSONArray(fill);
//    } catch (JSONException e) {
//      e.printStackTrace();
//    }
//  }

  @WXComponentProp(name = "fillOpacity")
  public void setFillOpacity(float fillOpacity) {
    mFillOpacity = fillOpacity;
    
  }

  @WXComponentProp(name = "fillRule")
  public void setFillRule(int fillRule) {
    switch (fillRule) {
      case FILL_RULE_EVENODD:
        mFillRule = Path.FillType.EVEN_ODD;
        break;
      case FILL_RULE_NONZERO:
        break;
      default:
        throw new JSApplicationIllegalArgumentException(
            "fillRule " + mFillRule + " unrecognized");
    }

    mFillRuleSet = true;
    setupPath();
    
  }

//  @WXComponentProp(name = "stroke")
//  public void setStroke(String strokeColors) {
//    try {
//      mStroke = new JSONArray(strokeColors);
//    } catch (JSONException e) {
//      e.printStackTrace();
//    }
//  }

  @WXComponentProp(name = "strokeOpacity")
  public void setStrokeOpacity(float strokeOpacity) {
    mStrokeOpacity = strokeOpacity;
    
  }

  @WXComponentProp(name = "strokeDasharray")
  public void setStrokeDasharray(@Nullable ReadableArray strokeDasharray) {

    mStrokeDasharray = PropHelper.toFloatArray(strokeDasharray);
    if (mStrokeDasharray != null && mStrokeDasharray.length > 0) {
      for (int i = 0; i < mStrokeDasharray.length; i++) {
        mStrokeDasharray[i] = mStrokeDasharray[i] * mScale;
      }
    }
    
  }

  @WXComponentProp(name = "strokeDashoffset")
  public void setStrokeDashoffset(float strokeWidth) {
    mStrokeDashoffset = strokeWidth * mScale;
    
  }

  @WXComponentProp(name = "strokeWidth")
  public void setStrokeWidth(float strokeWidth) {
    mStrokeWidth = strokeWidth;
  }

  @WXComponentProp(name = "strokeMiterlimit")
  public void setStrokeMiterlimit(float strokeMiterlimit) {
    mStrokeMiterlimit = strokeMiterlimit;
    
  }

  @WXComponentProp(name = "strokeLinecap")
  public void setStrokeLinecap(int strokeLinecap) {
    switch (strokeLinecap) {
      case CAP_BUTT:
        mStrokeLinecap = Paint.Cap.BUTT;
        break;
      case CAP_SQUARE:
        mStrokeLinecap = Paint.Cap.SQUARE;
        break;
      case CAP_ROUND:
        mStrokeLinecap = Paint.Cap.ROUND;
        break;
      default:
        throw new JSApplicationIllegalArgumentException(
            "strokeLinecap " + mStrokeLinecap + " unrecognized");
    }
    
  }

  @WXComponentProp(name = "strokelinejoin")
  public void setStrokeLinejoin(int strokeLinejoin) {
    switch (strokeLinejoin) {
      case JOIN_MITER:
        mStrokeLinejoin = Paint.Join.MITER;
        break;
      case JOIN_BEVEL:
        mStrokeLinejoin = Paint.Join.BEVEL;
        break;
      case JOIN_ROUND:
        mStrokeLinejoin = Paint.Join.ROUND;
        break;
      default:
        throw new JSApplicationIllegalArgumentException(
            "strokeLinejoin " + mStrokeLinejoin + " unrecognized");
    }
    
  }

  @WXComponentProp(name = "proplist")
  public void setPropList(@Nullable ReadableArray propList) {
    WritableArray copy = new WritableNativeArray();
    if (propList != null) {
      for (int i = 0; i < propList.size(); i++) {
        String fieldName = propertyNameToFieldName(propList.getString(i));
        copy.pushString(fieldName);
        mOwnedPropList.pushString(fieldName);
      }

    }

    mPropList = copy;
    
  }
  @WXComponentProp(name = "stroke")
  public void setStroke(String stroke) {
    if (!TextUtils.isEmpty(stroke)) {
      try {
        JSONArray array = new JSONArray(stroke);
        mStroke.put(0, array.opt(0));
        mStroke.put(1, array.opt(1));
        mStroke.put(2, array.opt(2));
        mStroke.put(3, array.opt(3));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  @WXComponentProp(name = "fill")
  public void setFill(String fill) {
    if (!TextUtils.isEmpty(fill)) {
      try {
        JSONArray array = new JSONArray(fill);
        // mFill = new JSONArray();
        mFill.put(0, array.opt(0));
        mFill.put(1, array.opt(1));
        mFill.put(2, array.opt(2));
        mFill.put(3, array.opt(3));
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void draw(Canvas canvas, Paint paint, float opacity) {
    opacity *= mOpacity;

    if (opacity > MIN_OPACITY_FOR_DRAW) {
      int count = saveAndSetupCanvas(canvas);
      if (mPath == null) {
        throw new JSApplicationIllegalArgumentException(
            "Paths should have a valid path (d) prop");
      }

      clip(canvas, paint);

      if (setupFillPaint(paint, opacity * mFillOpacity, null)) {
        canvas.drawPath(mPath, paint);
      }
      if (setupStrokePaint(paint, opacity * mStrokeOpacity, null)) {
        canvas.drawPath(mPath, paint);
      }

      restoreCanvas(canvas, count);
      //markUpdateSeen();
    }
  }

  @Override
  protected Path getPath(Canvas canvas, Paint paint) {
    return mPath;
  }

  private void setupPath() {
    // init path after both fillRule and path have been set
    if (mFillRuleSet && mD != null) {
      mPath = new Path();
      mPath.setFillType(mFillRule);
      super.createPath(mD, mPath);
    }
  }
  /**
   * Sets up paint according to the props set on a shadow view. Returns {@code true}
   * if the fill should be drawn, {@code false} if not.
   */
  protected boolean setupFillPaint(Paint paint, float opacity, @Nullable RectF box) {
    if (mFill != null && mFill.length() > 0) {
      paint.reset();
      paint.setFlags(Paint.ANTI_ALIAS_FLAG);
      paint.setStyle(Paint.Style.FILL);
      setupPaint(paint, opacity, mFill, box);
      return true;
    }
    return false;
  }

  /**
   * Sets up paint according to the props set on a shadow view. Returns {@code true}
   * if the stroke should be drawn, {@code false} if not.
   */
  protected boolean setupStrokePaint(Paint paint, float opacity, @Nullable RectF box) {
    paint.reset();
    if (mStrokeWidth == 0 || mStroke == null || mStroke.length() == 0) {
      return false;
    }

    paint.setFlags(Paint.ANTI_ALIAS_FLAG);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeCap(mStrokeLinecap);
    paint.setStrokeJoin(mStrokeLinejoin);
    paint.setStrokeMiter(mStrokeMiterlimit * mScale);
    paint.setStrokeWidth(mStrokeWidth * mScale);
    setupPaint(paint, opacity, mStroke, box);

    if (mStrokeDasharray != null && mStrokeDasharray.length > 0) {
      paint.setPathEffect(new DashPathEffect(mStrokeDasharray, mStrokeDashoffset));
    }

    return true;
  }

  // convert propertyName something like fillOpacity to fieldName like mFillOpacity
  private String propertyNameToFieldName(String fieldName) {
    Pattern pattern = Pattern.compile("^(\\w)");
    Matcher matched = pattern.matcher(fieldName);
    StringBuffer sb = new StringBuffer("m");
    while (matched.find()) {
      matched.appendReplacement(sb, matched.group(1).toUpperCase());
    }
    matched.appendTail(sb);
    return sb.toString();
  }

  private void setupPaint(Paint paint, float opacity, JSONArray colors, @Nullable RectF box) {
    int colorType = colors.optInt(0);
    if (colorType == 0) {
      // solid color
      paint.setARGB(
          (int) (colors.length() > 4 ? colors.optDouble(4) * opacity * 255 : opacity * 255),
          (int) (colors.optDouble(1) * 255),
          (int) (colors.optDouble(2) * 255),
          (int) (colors.optDouble(3) * 255));
    } else if (colorType == 1) {
      if (box == null) {
        box = new RectF();
        mPath.computeBounds(box, true);
      }
      PropHelper.RNSVGBrush brush = getSvgComponent().getDefinedBrush(colors.optString(1));
      if (brush != null) {
        brush.setupPaint(paint, box, mScale, opacity);
      }
    } else {
      // TODO: Support pattern.
      Log.w(TAG, "RNSVG: Color type " + colorType + " not supported!");
    }

  }

  private static class NumberParse {
    private ArrayList<Float> numbers;
    private int nextCmd;

    public NumberParse(ArrayList<Float> numbers, int nextCmd) {
      this.numbers = numbers;
      this.nextCmd = nextCmd;
    }

    public int getNextCmd() {
      return nextCmd;
    }

    public float getNumber(int index) {
      return numbers.get(index);
    }

  }
}
