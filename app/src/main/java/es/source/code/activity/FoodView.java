package es.source.code.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodView extends AppCompatActivity {

    //set resultCode for onActivityResult() for MainScreen.java
    private static final int RETURN = 228;
    private static final int SUCCESS = 229;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.food_view_tab)
    TabLayout mTab;

    @BindView(R.id.food_view_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.back)
    TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
        ButterKnife.bind(this);
        toolbarTitle.setText("点菜");
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
    }

    private void initData() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
//        setOffscreenPageLimit() is the reason why ViewPagerAdapter.getItem() run twice
//        fragment will be loaded before it is put on screen
//        mViewPager.setOffscreenPageLimit(3);
        mTab.setupWithViewPager(mViewPager);
    }


}
