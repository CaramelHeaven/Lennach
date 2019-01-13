package com.caramelheaven.lennach.utils;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;

/**
 * Created by CaramelHeaven on 02:24, 04/01/2019.
 */
public class UtilsApplication {

    private static UtilsApplication INSTANCE;

    public static UtilsApplication getInstance() {
        if (INSTANCE == null) {
            synchronized (UtilsView.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UtilsApplication();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * Make vibrate phone with 80 ms
     */
    public static void makeVibration(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(80, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(80);
        }
    }

    /**
     * Calculate size of grid layout for fill data in the BoardContainerAdapterDelegate,
     *
     * @param context    - base god object
     * @param widthChild - width size of the child view which placed inside recycler view
     * @return number of columns in the GridLayoutManager
     */
    public static int calculateNumOfColumns(Context context, int widthChild) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numOfColumns = (int) (dpWidth / widthChild);

        return numOfColumns;
    }

}
