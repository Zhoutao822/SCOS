package es.source.code.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import es.source.code.service.UpdateService;

/**
 * Created by zhoutao on 2017/10/31.
 */

public class DeviceStartedListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentBoot = new Intent(context, UpdateService.class);
        context.startService(intentBoot);
        Toast.makeText(context,"boot??",Toast.LENGTH_SHORT).show();
        Log.i("receiver","boot success");
    }
}
