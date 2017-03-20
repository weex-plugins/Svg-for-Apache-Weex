/**
 * Copyright (c) 2015-present, Horcrux.
 * All rights reserved.
 *
 * This source code is licensed under the MIT-style license found in the
 * LICENSE file in the root directory of this source tree.
 */


package com.alibaba.weex.svg.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.weex.svg.ISvgDrawable;
import com.taobao.weex.ui.component.WXComponent;

import javax.annotation.Nullable;

/**
 * Custom {@link View} implementation that draws an RNSVGSvg React view and its \children.
 */
public class WXSvgView extends ViewGroup {

    public enum Events {
        EVENT_DATA_URL("onDataURL");

        private final String mName;

        Events(final String name) {
            mName = name;
        }

        @Override
        public String toString() {
            return mName;
        }
    }

    private @Nullable Bitmap mBitmap;
    private WXComponent mComponent;
    private ISvgDrawable mSvgDrawable;

    public WXSvgView(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    public void setBitmap(Bitmap bitmap) {
        if (mBitmap != null) {
            mBitmap.recycle();
        }
        mBitmap = bitmap;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mSvgDrawable != null) {
            mSvgDrawable.draw(canvas, null, 0);;
        }
        if (mComponent != null && mComponent instanceof ISvgDrawable) {
            ((ISvgDrawable)mComponent).draw(canvas, null, 0);
        }
//        if (mBitmap != null) {
//            canvas.drawBitmap(mBitmap, 0, 0, null);
//        }
    }

    public void setSvgDrawable(ISvgDrawable drawable) {
        mSvgDrawable = drawable;
    }



}
