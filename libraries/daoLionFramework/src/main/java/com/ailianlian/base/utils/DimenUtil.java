package com.ailianlian.base.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class DimenUtil {
    
    public static int dpToPx(Context context, float dp) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }
}
