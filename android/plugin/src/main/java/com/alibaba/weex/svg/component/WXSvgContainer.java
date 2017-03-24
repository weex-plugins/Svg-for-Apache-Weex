package com.alibaba.weex.svg.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.alibaba.weex.svg.ISvgDrawable;
import com.alibaba.weex.svg.PropHelper;
import com.alibaba.weex.svg.view.WXSvgView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXCircleIndicator;
import com.taobao.weex.utils.WXViewUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by budao on 2017/2/21.
 */

public class WXSvgContainer extends WXVContainer<WXSvgView> implements ISvgDrawable {
  private static final Map<String, WXSvgAbsComponent> mDefinedClipPaths = new HashMap<>();
  private static final Map<String, WXSvgAbsComponent> mDefinedTemplates = new HashMap<>();
  private static final Map<String, PropHelper.RNSVGBrush> mDefinedBrushes = new HashMap<>();

  private WXDomObject mDom;

  public WXSvgContainer(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
    super(instance, dom, parent);
    mDom = dom;
    Log.v("WXSvgContainer", mDom.getAttrs().values().toString());
    if (mDom.getStyles().get(Constants.Name.HEIGHT) == null) {
      mDom.getStyles().put(Constants.Name.HEIGHT, "0");
    }
    if (mDom.getStyles().get(Constants.Name.WIDTH) == null) {
      mDom.getStyles().put(Constants.Name.WIDTH, "0");
    }
  }

  @Override
  protected WXSvgView initComponentHostView(@NonNull Context context) {
    WXSvgView host = new WXSvgView(context);
    host.setWillNotDraw(false);
    host.setSvgDrawable(this);
    host.setTag(getDomObject().getRef());
    return host;
  }

  @WXComponentProp(name = Constants.Name.WIDTH)
  public void setWidth(int width) {
    getHostView().getLayoutParams().width = (int)WXViewUtils.getRealPxByWidth(width, 750);
  }

  @WXComponentProp(name = Constants.Name.HEIGHT)
  public void setHeight(int height) {
    getHostView().getLayoutParams().height = (int)WXViewUtils.getRealPxByWidth(height, 750);
  }

  private void drawElements(Canvas canvas) {
    Paint paint = new Paint();
    Log.v("WXSvgAbsComponent"," =========  start drawElements " + getDomObject().getRef() +
        "， child count is " + getChildCount());
    processChildren(canvas, paint);
    Log.v("WXSvgAbsComponent"," =========  end drawElements " + getDomObject().getRef() +
        "， child count is " + getChildCount());
  }

  public synchronized void processChildren(Canvas canvas, Paint paint) {
    Log.v("WXSvgAbsComponent", "processChildren ");
    for (int i = 0; i < getChildCount(); i++) {
      if (!(getChild(i) instanceof ISvgDrawable)) {
        continue;
      }

      ISvgDrawable child = (ISvgDrawable) getChild(i);
      child.draw(canvas, paint, 1f);
      Log.v("WXSvgAbsComponent", "processChildren " + i + " id is " + getChild(i).getRef());
    }
  }

  @Override
  protected void onFinishLayout() {
    super.onFinishLayout();
    //getHostView().invalidate();
  }

  protected Path getPath(Canvas canvas, Paint paint) {
    return null;
  }

  public void defineClipPath(WXSvgAbsComponent clipPath, String clipPathRef) {
    mDefinedClipPaths.put(clipPathRef, clipPath);
  }

  public WXSvgAbsComponent getDefinedClipPath(String clipPathRef) {
    return mDefinedClipPaths.get(clipPathRef);
  }

  public void defineTemplate(WXSvgAbsComponent template, String templateRef) {
    mDefinedTemplates.put(templateRef, template);
  }

  public WXSvgAbsComponent getDefinedTemplate(String templateRef) {
    return mDefinedTemplates.get(templateRef);
  }

  public void defineBrush(PropHelper.RNSVGBrush brush, String brushRef) {
    mDefinedBrushes.put(brushRef, brush);
  }

  public PropHelper.RNSVGBrush getDefinedBrush(String brushRef) {
    return mDefinedBrushes.get(brushRef);
  }


  @Override
  public void draw(Canvas canvas, Paint paint, float opacity) {
    drawElements(canvas);
  }

  @Override
  protected boolean setProperty(String key, Object param) {
    switch (key) {
      case Constants.Name.WIDTH:
      case Constants.Name.HEIGHT:
        if (mDom != null) {
          mDom.getStyles().put(key, param.toString());
        }
        return false;
      default:
        return super.setProperty(key, param);
    }
  }

  public void addChild(WXComponent child, int index) {
    super.addChild(child, index);
    Log.v("WXSvgAbsComponent", "addChild force invalidate");
    getHostView().postInvalidate();
  }

  private Bitmap drawBitmap() {
    WXSvgView host = getHostView();
    if (host != null) {
      int width = host.getWidth();
      int height = host.getHeight();
      Log.v("WXSvgAbsComponent", "width is " + width + ", height is " + height);
      Bitmap bitmap = Bitmap.createBitmap(
          Math.max(1, width),
          Math.max(1, height),
          Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(bitmap);
      Paint paint = new Paint();
      processChildren(canvas, paint);
      return bitmap;
    }
    return null;
  }

  public void invalidate() {
    getHostView().setWillNotDraw(false);
    getHostView().forceLayout();
    Log.v("WXSvgAbsComponent", "invalidate " + getRef());
  }
}
