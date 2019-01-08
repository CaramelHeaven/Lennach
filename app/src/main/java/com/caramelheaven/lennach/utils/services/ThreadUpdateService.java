package com.caramelheaven.lennach.utils.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import timber.log.Timber;

/**
 * Created by CaramelHeaven on 21:05, 15/12/2018.
 */
public class ThreadUpdateService extends IntentService {

    private static final int REQUEST_ID = 1;
    private static final int ONE_MINUTE = 60 * 1000;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ThreadUpdateService(String name) {
        super(name);
    }

    public static void schedule(Context context) {
        Intent intent = new Intent(context, ThreadUpdateService.class);
        PendingIntent pIntent = PendingIntent.getService(context, REQUEST_ID,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + ONE_MINUTE, pIntent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        doMyWork();
    }

    private void doMyWork() {
        Timber.d("Kek every 1 minute");
    }
}
