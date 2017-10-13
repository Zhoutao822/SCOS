package es.source.code.activity;

import android.content.Intent;
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

public class MainScreen extends AppCompatActivity {


    //RETURN and SUCCESS are resultCode for onActivityResult
    private static final int RETURN = 228;
    private static final int SUCCESS = 229;
    //FLAG is to distinguish 4 and 2 bottom function
    private static int FLAG = 4;

//    @BindView(R.id.bottom_navigation_bar)
//    BottomNavigationBar bottomNavigationBar;
//
//    private BottomNavigationItem orderItem = new BottomNavigationItem(R.drawable.order, "点菜");
//    private BottomNavigationItem checkItem = new BottomNavigationItem(R.drawable.check, "查看订单");
//    private BottomNavigationItem loginItem = new BottomNavigationItem(R.drawable.login, "登录/注册");
//    private BottomNavigationItem helpItem = new BottomNavigationItem(R.drawable.help, "系统帮助");

    @BindView(R.id.gridview)
    GridView myGridView;

    private List<Map<String, Object>> dataList;
    private SimpleAdapter mySimpleAdapter;
    private int[] image = {R.drawable.order, R.drawable.check, R.drawable.login, R.drawable.help};
    private String[] imageName = {"点菜", "查看订单", "登录/注册", "系统帮助"};

    private int[] image2 = {R.drawable.login, R.drawable.help};
    private String[] imageName2 = {"登录/注册", "系统帮助"};

    private String[] from = {"image", "imageName"};
    private int[] to = {R.id.item_image, R.id.item_name};

    private String strFromEntry = "check";
    private User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_screen);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //gridview relative parameters
        dataList = new ArrayList<Map<String, Object>>();

        mySimpleAdapter = new SimpleAdapter(this, getData(), R.layout.gridview_item, from, to);
//        addFourItems();

        try {
            Intent intent = getIntent();
            strFromEntry = intent.getStringExtra("fromEntry");
            if (!strFromEntry.equals("FromEntry")) {
//                bottomNavigationBar.removeItem(orderItem)
//                        .removeItem(checkItem)
//                        .initialise();
                mySimpleAdapter = new SimpleAdapter(this, getData2(),
                        R.layout.gridview_item, from, to);
                FLAG = 2;
            }
        } catch (Exception e) {
            Log.i("intent error", "str is:" + strFromEntry);
        }
        myGridView.setAdapter(mySimpleAdapter);
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (FLAG == 4) {
                    switch (position) {
                        case 0:
                            startActivity(new Intent(MainScreen.this,FoodView.class));
                            break;
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
        });
//        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
//            @Override
//            //未选中 -> 选中
//            public void onTabSelected(int position) {
//                itemSelected(position);
//            }
//
//            //选中 -> 未选中
//            @Override
//            public void onTabUnselected(int position) {
//            }
//
//            //选中 -> 选中
//            @Override
//            public void onTabReselected(int position) {
//                itemSelected(position);
//            }
//        });
    }

    //getData() is to put image and imageName into dataList
    private List<Map<String, Object>> getData() {
        dataList.clear();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", image[i]);
            map.put("imageName", imageName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    private List<Map<String, Object>> getData2() {
        dataList.clear();
        for (int i = 0; i < image2.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", image2[i]);
            map.put("imageName", imageName2[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == SUCCESS) {
                    String returnData = data.getStringExtra("fromLogin");
                    Log.i("fromlogin", returnData+user.getUserName());
                    if (returnData.equals("LoginSuccess")) {
//                        addFourItems();
                        mySimpleAdapter = new SimpleAdapter(this, getData(),
                                R.layout.gridview_item, from, to);
                        myGridView.setAdapter(mySimpleAdapter);
                        FLAG = 4;
                        user = (User) data.getSerializableExtra("userModel");
                    }else if (returnData.equals("RegisterSuccess")){
                        mySimpleAdapter = new SimpleAdapter(this, getData(),
                                R.layout.gridview_item, from, to);
                        myGridView.setAdapter(mySimpleAdapter);
                        FLAG = 4;
                        user = (User) data.getSerializableExtra("userModel");
                        Toast.makeText(this,"欢迎您成为SCOS新用户",Toast.LENGTH_SHORT).show();
                    }else {
                        user = null;
                    }

                } else if (resultCode == RETURN) {
                    String returnData = data.getStringExtra("fromLogin");
                    Log.i("fromlogin", returnData);
                }
        }
    }

//    private void addFourItems() {
//        bottomNavigationBar.clearAll();
//        bottomNavigationBar.addItem(orderItem)
//                .addItem(checkItem)
//                .addItem(loginItem)
//                .addItem(helpItem)
//                .initialise();
//        FLAG = 4;
//    }

//    private void itemSelected(int position) {
//        if (FLAG == 4) {
//            switch (position) {
//                case 2:
//                    Intent intent2 = new Intent(MainScreen.this, LoginOrRegister.class);
//                    startActivityForResult(intent2, 1);
//                    break;
//                default:
//                    break;
//            }
//        } else if (FLAG == 2) {
//            switch (position) {
//                case 0:
//                    Intent intent2 = new Intent(MainScreen.this, LoginOrRegister.class);
//                    startActivityForResult(intent2, 1);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }

}
