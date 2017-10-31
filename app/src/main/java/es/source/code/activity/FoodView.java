package es.source.code.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.source.code.adapter.ListViewAdapter;
import es.source.code.adapter.ViewPagerAdapter;
import es.source.code.model.User;
import es.source.code.service.ServerObserverService;
import es.source.code.service.UpdateService;

public class FoodView extends AppCompatActivity implements ActionMenuView.OnMenuItemClickListener {

    //set resultCode for onActivityResult() in MainScreen.java

    private static final int RETURN = 228;
    private static final int SUCCESS = 229;

    private static final String[] TITLE = {"冷菜", "热菜", "海鲜", "酒水"};

    private User user = null;
    private Messenger serviceMessenger = null;

    private boolean isBound = false;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serviceMessenger = new Messenger(service);
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceMessenger = null;
            isBound = false;
        }
    };

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.toolbar_menu_view)
    ActionMenuView mActionMenuView;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.food_view_tab)
    TabLayout mTab;

    @BindView(R.id.food_view_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.back)
    ImageView mBack;

    Handler sMessageHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 10){
                Bundle data = msg.getData();
                String foodName = data.getString("foodName");
                String foodInventory = String .valueOf(data.getInt("foodInventory"));
                //// TODO: 更新food的信息，food信息在ColdFoodFragment中，textview在adapter中，需要重写Food类

                Toast.makeText(FoodView.this,"getdata:"+foodName +foodInventory,Toast.LENGTH_SHORT).show();
                Log.i("getdata", "handleMessage: "+foodName+" "+foodInventory);
            }
        }
    };
    private Messenger SCOSMessenger = new Messenger(sMessageHandler);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
        ButterKnife.bind(this);
        toolbarTitle.setText("点菜");

        Intent serviceStartIntent = new Intent(FoodView.this,
                ServerObserverService.class);
        bindService(serviceStartIntent,connection,BIND_AUTO_CREATE);

        mActionMenuView.getMenu().clear();
        getMenuInflater().inflate(R.menu.toolbar_menu, mActionMenuView.getMenu());
        mActionMenuView.setOnMenuItemClickListener(this);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent();
                intentBack.putExtra("fromFoodView", "Return");
                setResult(RETURN, intentBack);
                finish();
            }
        });
        initData();
        if (getIntent() != null) {
            user = (User) getIntent().getSerializableExtra("userFromMainScreen");
        }
    }

//        setOffscreenPageLimit() is the reason why ViewPagerAdapter.getItem() run twice
//        fragment will be loaded before it is put on screen

    private void initData() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), TITLE);
        mViewPager.setAdapter(adapter);

        mTab.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_hasOrdered:
                Intent intent1 = new Intent(FoodView.this,FoodOrderView.class);
                Bundle bundle1 = new Bundle();
                if (user != null) {
                    bundle1.putSerializable("userModule", user);
                }
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.item_checkOrder:
                Intent intent2 = new Intent(FoodView.this,FoodOrderView.class);
                Bundle bundle2 = new Bundle();
                if (user != null) {
                    bundle2.putSerializable("userModule", user);
                }
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.item_call:

                break;
            case R.id.item_startService:
                if (item.getTitle().equals("启动实时更新")){
                    if (isBound){
                        item.setTitle("停止实时更新");
                        Message message = Message.obtain();
                        message.what = 1;
                        message.replyTo = SCOSMessenger;
                        try {
                            serviceMessenger.send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }else if (item.getTitle().equals("停止实时更新")){
                    if (isBound){
                        item.setTitle("启动实时更新");
                        Message message = Message.obtain();
                        message.what = 0;
                        message.replyTo = SCOSMessenger;
                        try {
                            serviceMessenger.send(message);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

            default:
                break;
        }
        return false;
    }
}
