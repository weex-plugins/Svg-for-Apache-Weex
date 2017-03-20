package com.alibaba.weex.svg.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;

import com.alibaba.weex.svg.ISvgDrawable;
import com.alibaba.weex.svg.PropHelper;
import com.alibaba.weex.svg.view.WXSvgView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXVContainer;

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
  private WXSvgView mSvgView;

  public WXSvgContainer(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
    super(instance, dom, parent);
    mDom = dom;
  }

  @Override
  protected WXSvgView initComponentHostView(@NonNull Context context) {
    WXSvgView host = new WXSvgView(context);
    host.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
    host.setSvgDrawable(this);
    return host;
  }

  @WXComponentProp(name = Constants.Name.WIDTH)
  public void setWidth(int width) {
    if (mDom != null) {
      mDom.getStyles().put(Constants.Name.WIDTH, width);
    }
  }

  @WXComponentProp(name = Constants.Name.HEIGHT)
  public void setR(int height) {
    if (mDom != null) {
      mDom.getStyles().put(Constants.Name.HEIGHT, height);
    }
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

  private void drawElements(Canvas canvas) {
    Paint paint = new Paint();
    processChildren(canvas, paint);
  }

  public synchronized void processChildren(Canvas canvas, Paint paint) {
    for (int i = 0; i < getChildCount(); i++) {
      if (!(getChild(i) instanceof ISvgDrawable)) {
        continue;
      }

      ISvgDrawable child = (ISvgDrawable) getChild(i);
      child.draw(canvas, paint, 1f);
    }
  }

  @Override
  protected void onFinishLayout() {
    super.onFinishLayout();
//    Bitmap bitmap = drawBitmap();
//    if (bitmap != null) {
//      getHostView().setBitmap(bitmap);
//    }
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

//  public void setSvgView(WXSvgView svgView) {
//    mSvgView = svgView;
//  }
//
//  protected void invalidateView() {
//    mSvgView.invalidate();
//  }

  @Override
  public void draw(Canvas canvas, Paint paint, float opacity) {
    drawElements(canvas);
  }

  @Override
  protected boolean setProperty(String key, Object param) {
    switch (key) {
      case Constants.Name.WIDTH:
      case Constants.Name.HEIGHT:
        return false;
      default:
        return super.setProperty(key, param);
    }
  }
}
