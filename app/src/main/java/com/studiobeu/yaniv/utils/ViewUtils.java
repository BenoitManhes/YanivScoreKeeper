package com.studiobeu.yaniv.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.utils.font.TypefaceHelper;


public final class ViewUtils {

    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

//    public static void applyFontForToolbarTitle(Activity context, int resId) {
//        Toolbar toolbar = (Toolbar) context.findViewById(resId);
//        for (int i = 0; i < toolbar.getChildCount(); i++) {
//            View view = toolbar.getChildAt(i);
//            if (view instanceof TextView) {
//                TextView tv = (TextView) view;
//                Typeface titleFont = TypefaceHelper.get(context.getAssets(), "fonts/OpenSans-SemiBold.ttf");
//                if (tv.getText().equals(toolbar.getTitle())) {
//                    tv.setTypeface(titleFont);
//                    tv.setTextColor(Color.WHITE);
//                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimension(R.dimen.toolbar_title));
//                    break;
//                }
//            }
//        }
//    }

}