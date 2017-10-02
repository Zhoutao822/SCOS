package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainScreen extends AppCompatActivity {

    private static final int RETURN = 228;
    private static final int SUCCESS = 229;
    private static int FLAG = 4;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private BottomNavigationItem orderItem = new BottomNavigationItem(R.drawable.order, "点菜");
    private BottomNavigationItem checkItem = new BottomNavigationItem(R.drawable.check, "查看订单");
    private BottomNavigationItem loginItem = new BottomNavigationItem(R.drawable.login, "登录/注册");
    private BottomNavigationItem helpItem = new BottomNavigationItem(R.drawable.help, "系统帮助");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        ButterKnife.bind(this);
        String strFromEntry = "check";

        addFourItems();

        try {
            Intent intent = getIntent();
            strFromEntry = intent.getStringExtra("fromEntry");
            if (!strFromEntry.equals("FromEntry")) {
                bottomNavigationBar.removeItem(orderItem)
                        .removeItem(checkItem)
                        .initialise();
                FLAG = 2;
            }
        } catch (Exception e) {
            Log.i("intent error", "str is:" + strFromEntry);
        }






        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            //未选中 -> 选中
            public void onTabSelected(int position) {
                itemSelected(position);
            }

            //选中 -> 未选中
            @Override
            public void onTabUnselected(int position) {

            }

            //选中 -> 选中
            @Override
            public void onTabReselected(int position) {
                itemSelected(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == SUCCESS) {
                    String returnData = data.getStringExtra("fromLogin");
                    Log.i("fromlogin", returnData);
                    if (returnData.equals("LoginSuccess")) {
                        addFourItems();
                    }


                } else if (resultCode == RETURN) {
                    String returnData = data.getStringExtra("fromLogin");
                    Log.i("fromlogin", returnData);
                }
        }
    }

    private void addFourItems() {
        bottomNavigationBar.clearAll();
        bottomNavigationBar.addItem(orderItem)
                .addItem(checkItem)
                .addItem(loginItem)
                .addItem(helpItem)
                .initialise();
        FLAG = 4;
    }

    private void itemSelected(int position) {
        if (FLAG == 4) {
            switch (position) {
                case 2:
                    Intent intent2 = new Intent(MainScreen.this, LoginOrRegister.class);
                    startActivityForResult(intent2, 1);
                    break;
                default:
                    break;
            }
        } else if (FLAG == 2) {
            switch (position) {
                case 0:
                    Intent intent2 = new Intent(MainScreen.this, LoginOrRegister.class);
                    startActivityForResult(intent2, 1);
                    break;
                default:
                    break;
            }
        }
    }

}
