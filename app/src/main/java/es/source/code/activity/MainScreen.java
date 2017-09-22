package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainScreen extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String strGet = intent.getStringExtra("fromEntry");
        if (strGet.equals("FromEntry")){
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.order, "点菜"))
                .addItem(new BottomNavigationItem(R.drawable.checkorder, "查看订单"))
                .addItem(new BottomNavigationItem(R.drawable.login, "登录/注册"))
                .addItem(new BottomNavigationItem(R.drawable.help, "系统帮助"))
                .initialise();
        }else {
            bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.login, "登录/注册"))
                    .addItem(new BottomNavigationItem(R.drawable.help, "系统帮助"))
                    .initialise();
        }

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            //未选中 -> 选中
            public void onTabSelected(int position) {
            switch(position){
                case 2:
                    startActivity(new Intent(MainScreen.this,LoginOrRegister.class));
                    break;
                default:
                    break;
            }
            }
            //选中 -> 未选中
            @Override
            public void onTabUnselected(int position) {

            }
            //选中 -> 选中
            @Override
            public void onTabReselected(int position) {

            }
        });

    }
}