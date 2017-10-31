package es.source.code.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import es.source.code.activity.FoodDetailed;

/**
 * Created by zhoutao on 2017/10/31.
 */

public class UpdateService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private String updateInfo;

    public UpdateService() {
        super("UpdateService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (foodUpdate()) {
            Intent intentDetail = new Intent(UpdateService.this, FoodDetailed.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(UpdateService.this, 0, intentDetail, 0);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(getApplication())
                    .setContentTitle("title")
                    .setContentText(updateInfo)
                    .setWhen(System.currentTimeMillis()).setContentIntent(pendingIntent).build();
            notificationManager.notify(1, notification);
        }
    }

    private boolean foodUpdate() {
        updateInfo = "新品上架：狮子头，50，热菜";
        return true;
    }
}
