package es.source.code.activity;

import android.*;
import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SCOSHelper extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.help_grid_view)
    GridView myGridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter mySimpleAdapter;
    private int[] image = {R.drawable.order, R.drawable.check, R.drawable.login, R.drawable.help, R.drawable.check};
    private String[] imageName = {"用户使用协议", "关于系统", "电话人工帮助", "短信帮助", "邮件帮助"};

    private String[] from = {"image", "imageName"};
    private int[] to = {R.id.help_item_image, R.id.help_item_name};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoshelper);
        ButterKnife.bind(this);
        toolbarTitle.setText("系统帮助");

        dataList = new ArrayList<Map<String, Object>>();
        getData();
        mySimpleAdapter = new SimpleAdapter(this, dataList, R.layout.help_gridview_item, from, to);
        myGridView.setAdapter(mySimpleAdapter);
        myGridView.setOnItemClickListener(this);

    }

    //getData() is to put image and imageName into dataList
    private void getData() {
        dataList.clear();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", image[i]);
            map.put("imageName", imageName[i]);
            dataList.add(map);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:

                break;
            case 1:

                break;
            case 2:
                callPhone();
                break;
            case 3:
                sendMessage();
                break;
            case 4:
                sendEmail();
                break;
            default:
                break;


        }
    }

    private void callPhone() {
        if (ActivityCompat.checkSelfPermission(SCOSHelper.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS}, 1);
        } else {
            Uri uri = Uri.parse("tel:" + String.valueOf(5554));
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            startActivity(intent);
        }
    }

    private void sendMessage() {
        if (ActivityCompat.checkSelfPermission(SCOSHelper.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS}, 1);
        } else {
            PendingIntent sendIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent("send"), 0);
            ;
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(String.valueOf(5554), null, "test scos helper", sendIntent,
                    null);
            Toast.makeText(this, "求助短信发送成功", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendEmail() {
        Thread MailSender = new Thread(){
            @Override
            public void run() {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                //emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"info@skillgun.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"标题");
                emailIntent.putExtra(Intent.EXTRA_TEXT,"HELP");
                startActivity(Intent.createChooser(emailIntent,"Choose an Email Client"));
                handler.sendEmptyMessage(1);
            }
        };
        MailSender.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                Toast.makeText(SCOSHelper.this,"求助邮件发送成功",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
