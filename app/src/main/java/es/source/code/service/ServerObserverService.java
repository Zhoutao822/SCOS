package es.source.code.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by zhoutao on 2017/10/29.
 */

public class ServerObserverService extends Service {

    private boolean stopThread = false;
    private Messenger SCOSMessenger = null;
    private Handler cMessageHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SCOSMessenger = msg.replyTo;
            switch (msg.what) {
                case 1:
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (!stopThread) {
                                try {
                                    Log.i("service", "run: success");
                                    if (true) {
                                        if (SCOSMessenger != null) {
                                            Message messageToSCOS = Message.obtain();
                                            messageToSCOS.what = 10;
                                            Bundle bundle = new Bundle();
                                            bundle.putString("foodName", foodName);
                                            bundle.putInt("foodInventory", foodInventory);
                                            messageToSCOS.setData(bundle);
                                            try {
                                                SCOSMessenger.send(messageToSCOS);
                                            } catch (RemoteException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                    break;
                                }
                            }
                        }
                    });
                    thread.start();
                    break;
                case 0:
                    stopThread = true;
                    break;
                default:
                    break;
            }
        }
    };
    private Messenger ServiceMessenger = new Messenger(cMessageHandler);

    String foodName = "酱牛肉";
    int foodInventory = 8;

    public ServerObserverService() {
    }

    private boolean isRunning() {
        ActivityManager activityManager = (ActivityManager)
                getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager
                    .RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("service", "onCreate: service");
    }

    @Override
    public void onDestroy() {
        SCOSMessenger = null;
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("service", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("service", "onBind: service");
        return ServiceMessenger.getBinder();
    }
}
