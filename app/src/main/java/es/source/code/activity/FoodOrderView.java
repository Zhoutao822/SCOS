package es.source.code.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.source.code.adapter.ViewPagerAdapter;
import es.source.code.model.User;

public class FoodOrderView extends AppCompatActivity {

    //set resultCode for onActivityResult() in MainScreen.java
    private static final int RETURN = 228;
    private static final int SUCCESS = 229;

    private static final String[] TITLE = {"未下单菜","已下单菜"};

    private User user = null;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.food_order_view_tab)
    TabLayout mTab;

    @BindView(R.id.food_order_view_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.back)
    TextView mBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_view);
        ButterKnife.bind(this);
        toolbarTitle.setText("查看订单");
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
        if (getIntent()!=null){
            user = (User) getIntent().getSerializableExtra("userFromMainScreen");
        }
    }
    private void initData(){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),TITLE);
        mViewPager.setAdapter(adapter);
//        setOffscreenPageLimit() is the reason why ViewPagerAdapter.getItem() run twice
//        fragment will be loaded before it is put on screen
//        mViewPager.setOffscreenPageLimit(3);
        mTab.setupWithViewPager(mViewPager);
    }
}
