package com.alibaba.weex.svg.component;

import android.util.Log;

import com.alibaba.weex.svg.SvgParser;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

import javax.annotation.Nullable;

/**
 * Created by budao on 2017/3/2.
 */

public class WXSvgPolyLine extends WXSvgPath {
  private static final String TAG = "WXSvgPolygon";

  public WXSvgPolyLine(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
    super(instance, dom, parent);
  }

  @WXComponentProp(name = "points")
  public void setPoints(@Nullable String points) {
    mD = SvgParser.parsePolyLine(points);
    Log.v(TAG, "svg point is " + SvgParser.parsePolyLine(points));
    setupPath();
  }
}
