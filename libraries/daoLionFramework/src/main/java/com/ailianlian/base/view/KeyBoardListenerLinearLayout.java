package com.ailianlian.base.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author miaoxiongfei@foxmail.com
 * @date 2016-11-23 14:56
 */

public class KeyBoardListenerLinearLayout extends LinearLayout {
    public static final int KEYBORAD_HIDE = 0;
    public static final int KEYBORAD_SHOW = 1;
    private static final int SOFTKEYPAD_MIN_HEIGHT = 50;

    private Handler uiHandler = new Handler();

    public KeyBoardListenerLinearLayout(Context context) {
        super(context);
    }

    public KeyBoardListenerLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, final int h, int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                if (oldh != 0) {//ignore created firstly
                    if (oldh - h > SOFTKEYPAD_MIN_HEIGHT) {
                        if (null != keyBordStateListener) {
                            keyBordStateListener.stateChange(KEYBORAD_SHOW);
                        }
                    } else {
                        if (keyBordStateListener != null) {
                            keyBordStateListener.stateChange(KEYBORAD_HIDE);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private KeyBordStateListener keyBordStateListener;

    public void setKeyBordStateListener(KeyBordStateListener keyBordStateListener) {
        this.keyBordStateListener = keyBordStateListener;
    }

    public interface KeyBordStateListener {
        void stateChange(int state);
    }
}
