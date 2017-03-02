package com.alibaba.weex.svg.component;

import com.alibaba.weex.svg.PropHelper;
import com.alibaba.weex.svg.SvgParser;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.ImmutableDomObject;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import java.util.ArrayList;

/**
 * Created by budao on 2017/3/2.
 */

public class WXSvgRadialGradient extends WXSvgDefs {
  public WXSvgRadialGradient(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
    super(instance, dom, parent);
  }
  private String mCx = "0";

  private String mCy = "0";

  private String mRx = "1";

  private String mRy = "0";

  private String mFx = "1";

  private String mFy = "0";

  @WXComponentProp(name = "name")
  public void setName(String id) {
    mName = id;
  }

  @WXComponentProp(name = "cx")
  public void setCx(String cx) {
    mCx = cx;
  }

  @WXComponentProp(name = "cy")
  public void setCy(String cy) {
    mCy = cy;
  }

  @WXComponentProp(name = "fx")
  public void setFx(String fx) {
    mFx = fx;
  }

  @WXComponentProp(name = "fy")
  public void setFy(String y2) {
    mFy = y2;
  }

  @WXComponentProp(name = "rx")
  public void setRx(String fx) {
    mRx = fx;
  }

  @WXComponentProp(name = "ry")
  public void setRy(String ry) {
    mRy = ry;
  }

  @Override
  protected void saveDefinition() {
    if (mName != null) {
      ArrayList<String> points = new ArrayList<String>();
      points.add(mFx);
      points.add(mFy);
      points.add(mRx);
      points.add(mRy);
      points.add(mCx);
      points.add(mCy);

      ArrayList<Integer> stopColors = new ArrayList<>();
      ArrayList<Float> stops = new ArrayList<>();
      for (int i = 0; i < childCount(); i++) {
        if (getChild(i) instanceof WXSvgStop) {
          ImmutableDomObject domObject = getChild(i).getDomObject();
          int color = SvgParser.parseColor((String) domObject.getAttrs().get("stopColor"));
          float offset = Float.parseFloat((String) domObject.getAttrs().get("offset"));
          stops.add(offset);
          stopColors.add(color);
        }
      }

      int[] colors = new int[stopColors.size()];
      for (int i = 0; i < colors.length; i++) {
        colors[i] = stopColors.get(i);
      }
      float[] positions = new float[stops.size()];
      for (int i = 0; i < positions.length; i++) {
        positions[i] = stops.get(i);
      }

      PropHelper.RNSVGBrush brush = new PropHelper.RNSVGBrush(PropHelper.RNSVGBrush.GradientType
          .RADIAL_GRADIENT, points, positions, colors);
      getSvgComponent().defineBrush(brush, mName);
    }
  }
}
