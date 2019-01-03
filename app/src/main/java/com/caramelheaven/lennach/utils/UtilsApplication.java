package com.caramelheaven.lennach.utils;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

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

    public static void makeVibration(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(80, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            v.vibrate(80);
        }
    }
}
