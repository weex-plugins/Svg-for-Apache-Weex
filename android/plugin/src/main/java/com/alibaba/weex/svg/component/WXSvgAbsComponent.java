/**
 * Copyright (c) 2015-present, Horcrux.
 * All rights reserved.
 * <p>
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */


package com.alibaba.weex.svg.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.annotation.NonNull;
import android.util.Log;

import com.alibaba.weex.svg.ISvgDrawable;
import com.alibaba.weex.svg.PropHelper;
import com.alibaba.weex.svg.SvgParser;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXException;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXComponentProp;
import com.taobao.weex.ui.component.WXDiv;
import com.taobao.weex.ui.component.WXVContainer;
import com.taobao.weex.ui.view.WXFrameLayout;
import com.taobao.weex.utils.WXViewUtils;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Nullable;

public class WXSvgAbsComponent extends WXDiv implements ISvgDrawable {
  private static final String TAG = "WXSvgAbsComponent";

  protected static final float MIN_OPACITY_FOR_DRAW = 0.01f;

  private static final float[] sMatrixData = new float[9];
  private static final float[] sRawMatrix = new float[9];
  private static final String PATH_TYPE_CLOSE = "Z";
  private static final String PATH_TYPE_CURVETO = "C";
  private static final String PATH_TYPE_LINETO = "L";
  private static final String PATH_TYPE_MOVETO = "M";

  private static final String PATH_TYPE_CLOSE_LOWERCASE = "z";
  private static final String PATH_TYPE_CURVETO_LOWERCASE = "c";
  private static final String PATH_TYPE_LINETO_LOWERCASE = "l";
  private static final String PATH_TYPE_MOVETO_LOWERCASE = "m";

  private static final int CLIP_RULE_EVENODD = 0;
  private static final int CLIP_RULE_NONZERO = 1;
  protected final float mScale;// = 1.0f;
  protected float mOpacity = 1f;
  protected Matrix mMatrix = new Matrix();
  protected
  @Nullable
  Path mClipPath;
  protected
  @Nullable
  String mClipPathRef;
  protected boolean mResponsible = false;
  protected int mCanvasX;
  protected int mCanvasY;
  protected int mCanvasWidth;
  protected int mCanvasHeight;
  protected String mName;
  private ArrayList<SvgParser.PathCmd> mClipData;
  private int mClipRule = CLIP_RULE_NONZERO;
  private boolean mClipRuleSet;
  private boolean mClipDataSet;
  private WXSvgContainer mSvgShadowNode;

  public WXSvgAbsComponent(WXSDKInstance instance, WXDomObject dom, WXVContainer parent) {
    super(instance, dom, parent);
    mScale = (float) (WXViewUtils.getScreenWidth(parent.getContext()) * 1.0 / dom.getViewPortWidth());
  }

  @Override
  protected WXFrameLayout initComponentHostView(@NonNull Context context) {
    return new WXFrameLayout(context);
  }

  public void draw(Canvas canvas, Paint paint, float opacity) {

  }

  /**
   * Sets up the transform matrix on the canvas before an element is drawn.
   * <p>
   * NB: for perf reasons this does not apply opacity, as that would mean creating a new canvas
   * layer (which allocates an offscreen bitmap) and having it composited afterwards. Instead, the
   * drawing code should apply opacity recursively.
   *
   * @param canvas the canvas to set up
   */
  protected final int saveAndSetupCanvas(Canvas canvas) {
    final int count = canvas.save();
    canvas.concat(mMatrix);
    return count;
  }

  /**
   * Restore the canvas after an element was drawn. This is always called in mirror with
   * {@link #saveAndSetupCanvas}.
   *
   * @param canvas the canvas to restore
   */
  protected void restoreCanvas(Canvas canvas, int count) {
    canvas.restoreToCount(count);
  }

  @WXComponentProp(name = "clipPath")
  public void setClipPath(@Nullable String clipPath) {
    mClipData = SvgParser.parserPath(clipPath);
    mClipDataSet = true;
    setupClip();
  }

  @WXComponentProp(name = "name")
  public void setName(String name) {
    mName = name;
  }


  @WXComponentProp(name = "clipPathRef")
  public void setClipPathRef(String clipPathRef) {
    mClipPathRef = clipPathRef;

  }

  @WXComponentProp(name = "clipRule")
  public void setClipRule(int clipRule) {
    mClipRule = clipRule;
    mClipRuleSet = true;
    setupClip();

  }

  @WXComponentProp(name = "opacity")
  public void setOpacity(float opacity) {
    mOpacity = opacity;

  }

//  @WXComponentProp(name = "matrix")
//  public void setMatrix(@Nullable ReadableArray matrixArray) {
//    if (matrixArray != null) {
//      int matrixSize = PropHelper.toFloatArray(matrixArray, sMatrixData);
//      if (matrixSize == 6) {
//        setupMatrix();
//      } else if (matrixSize != -1) {
//        throw new JSApplicationIllegalArgumentException("Transform matrices must be of size 6");
//      }
//    } else {
//      mMatrix = null;
//    }
//
//  }

  private void setupClip() {
    if (mClipDataSet && mClipRuleSet) {
      mClipPath = new Path();

      switch (mClipRule) {
        case CLIP_RULE_EVENODD:
          mClipPath.setFillType(Path.FillType.EVEN_ODD);
          break;
        case CLIP_RULE_NONZERO:
          break;
        default:
          Log.v(TAG, "clipRule " + mClipRule + " unrecognized");
      }
      createPath(mClipData, mClipPath);
    }
  }

  protected void setupMatrix() {
    sRawMatrix[0] = sMatrixData[0];
    sRawMatrix[1] = sMatrixData[2];
    sRawMatrix[2] = sMatrixData[4] * mScale;
    sRawMatrix[3] = sMatrixData[1];
    sRawMatrix[4] = sMatrixData[3];
    sRawMatrix[5] = sMatrixData[5] * mScale;
    sRawMatrix[6] = 0;
    sRawMatrix[7] = 0;
    sRawMatrix[8] = 1;
    mMatrix.setValues(sRawMatrix);
  }

  /**
   * Returns the floor modulus of the float arguments. Java modulus will return a negative remainder
   * when the divisor is negative. Modulus should always be positive. This mimics the behavior of
   * Math.floorMod, introduced in Java 8.
   */
  private float modulus(float x, float y) {
    float remainder = x % y;
    float ret = remainder;
    if (remainder < 0) {
      ret += y;
    }
    return ret;
  }

  protected void createPath(ArrayList<SvgParser.PathCmd> cmds, Path path) {
    path.moveTo(0, 0);
    int i = 0;

    while (i < cmds.size()) {
      String cmd = cmds.get(i).mCmd;
      float[] data = cmds.get(i).mValue;
      switch (cmd) {
        case PATH_TYPE_MOVETO:
        case PATH_TYPE_MOVETO_LOWERCASE:
          path.moveTo(data[0] * mScale, data[1] * mScale);
          break;
        case PATH_TYPE_CLOSE:
        case PATH_TYPE_CLOSE_LOWERCASE:
          path.close();
          break;
        case PATH_TYPE_LINETO:
        case PATH_TYPE_LINETO_LOWERCASE:
          path.lineTo(data[0] * mScale, data[1] * mScale);
          break;
        case PATH_TYPE_CURVETO:
        case PATH_TYPE_CURVETO_LOWERCASE:
          path.cubicTo(
              data[0] * mScale,
              data[1] * mScale,
              data[2] * mScale,
              data[3] * mScale,
              data[4] * mScale,
              data[5] * mScale);
          break;
        default:
          Log.v(TAG, "Unrecognized drawing instruction " + cmd);
      }
      i++;
    }
  }

  protected
  @Nullable
  Path getClipPath(Canvas canvas, Paint paint) {
    Path clip = mClipPath;
    if (clip == null && mClipPathRef != null) {
      WXSvgAbsComponent node = getSvgComponent().getDefinedClipPath(mClipPathRef);
      clip = node.getPath(canvas, paint);
    }

    return clip;
  }

  protected void clip(Canvas canvas, Paint paint) {
    Path clip = getClipPath(canvas, paint);

    if (clip != null) {
      canvas.clipPath(clip, Region.Op.REPLACE);
    }
  }

  public boolean isResponsible() {
    return mResponsible;
  }

  @WXComponentProp(name = "responsible")
  public void setResponsible(boolean responsible) {
    mResponsible = responsible;

  }

  protected Path getPath(Canvas canvas, Paint paint) {
    return null;
  }

  protected WXSvgContainer getSvgComponent() {
    if (mSvgShadowNode != null) {
      return mSvgShadowNode;
    }

    WXComponent parent = getParent();

    while (!(parent instanceof WXSvgContainer)) {
      if (parent == null) {
        return null;
      } else {
        parent = parent.getParent();
      }
    }
    mSvgShadowNode = (WXSvgContainer) parent;
    return mSvgShadowNode;
  }

  protected void setupDimensions(Canvas canvas) {
    Rect mCanvasClipBounds = canvas.getClipBounds();
    mCanvasX = mCanvasClipBounds.left;
    mCanvasY = mCanvasClipBounds.top;
    mCanvasWidth = canvas.getWidth();
    mCanvasHeight = canvas.getHeight();
  }

  protected void setupDimensions(Rect rect) {
    mCanvasX = rect.left;
    mCanvasY = rect.top;
    mCanvasWidth = rect.width();
    mCanvasHeight = rect.height();
  }

  protected void saveDefinition() {
    if (mName != null) {
      Log.v(TAG, "saveDefinition " + mName);
      getSvgComponent().defineTemplate(this, mName);
    }
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
