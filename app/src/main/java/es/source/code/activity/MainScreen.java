package es.source.code.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.source.code.model.User;

public class MainScreen extends AppCompatActivity implements AdapterView.OnItemClickListener {


    //RETURN and SUCCESS are resultCode for onActivityResult
    private static final int RETURN = 228;
    private static final int SUCCESS = 229;

    SharedPreferences mSharedPreference;
    int mLoginState;

    @BindView(R.id.gridview)
    GridView myGridView;

    private List<Map<String, Object>> dataList;
    private SimpleAdapter mySimpleAdapter;
    private int[] image = {R.drawable.order, R.drawable.check, R.drawable.login, R.drawable.help};
    private String[] imageName = {"点菜", "查看订单", "登录/注册", "系统帮助"};

    private String[] from = {"image", "imageName"};
    private int[] to = {R.id.item_image, R.id.item_name};

    private String strFromEntry = "check";
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //gridview relative parameters
        dataList = new ArrayList<Map<String, Object>>();
        getData();
        mySimpleAdapter = new SimpleAdapter(this, dataList, R.layout.gridview_item, from, to);
        myGridView.setAdapter(mySimpleAdapter);

        try {
            mSharedPreference = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
            mLoginState = mSharedPreference.getInt("loginState", -1);
            if (mLoginState != 1) {
                dataList.remove(1);
                dataList.remove(0);
                mySimpleAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            Log.i("intent error", "str is:" + mLoginState);
        }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        user = new User();
        mLoginState = getSharedPreferences("UserInfo",Context.MODE_PRIVATE).getInt("loginState",-1);
        switch (requestCode) {
            case 1:
                if (mLoginState == 1) {
                    String returnData = data.getStringExtra("fromLogin");
                    Log.i("fromlogin", returnData + user.getUserName());
                    if (returnData.equals("LoginSuccess")) {
                        dataList.clear();
                        getData();
                        mySimpleAdapter.notifyDataSetChanged();
                        user = (User) data.getSerializableExtra("userModel");
                    } else if (returnData.equals("RegisterSuccess")) {
                        dataList.clear();
                        getData();
                        mySimpleAdapter.notifyDataSetChanged();
                        user = (User) data.getSerializableExtra("userModel");
                        Toast.makeText(this, "欢迎您成为SCOS新用户", Toast.LENGTH_SHORT).show();
                    } else {
                        user = null;
                    }

                } else {
                    String returnData = data.getStringExtra("fromLogin");
                    Log.i("fromlogin", returnData);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (dataList.size() == 4) {
            switch (position) {
                case 0:
                    Intent intentOrder = new Intent(MainScreen.this, FoodView.class);
                    if (user != null) {
                        Bundle bundleOrder = new Bundle();
                        bundleOrder.putSerializable("userFromMainScreen", user);
                        intentOrder.putExtras(bundleOrder);
                    }
                    startActivity(intentOrder);
                    break;
                case 1:
                    Intent intentCheck = new Intent(MainScreen.this, FoodOrderView.class);
                    if (user != null) {
                        Bundle bundleCheck = new Bundle();
                        bundleCheck.putSerializable("userFromMainScreen", user);
                        intentCheck.putExtras(bundleCheck);
                    }
                    startActivity(intentCheck);
                    break;
                case 2:
                    Intent intentLogin = new Intent(MainScreen.this, LoginOrRegister.class);
                    startActivityForResult(intentLogin, 1);
                    break;
                case 3:
                    Intent intentHelp = new Intent(MainScreen.this,SCOSHelper.class);
                    startActivity(intentHelp);
                    break;
                default:
                    break;
            }
        } else if (dataList.size() == 2) {
            switch (position) {
                case 0:
                    Intent intentLogin = new Intent(MainScreen.this, LoginOrRegister.class);
                    startActivityForResult(intentLogin, 1);
                    break;
                case 1:
                    Intent intentHelp = new Intent(MainScreen.this,SCOSHelper.class);
                    startActivity(intentHelp);
                    break;
                default:
                    break;
            }
        }
    }
}
