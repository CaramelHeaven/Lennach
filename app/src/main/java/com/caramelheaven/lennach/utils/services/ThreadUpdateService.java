package com.caramelheaven.lennach.utils.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by CaramelHeaven on 21:05, 15/12/2018.
 */
public class ThreadUpdateService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
