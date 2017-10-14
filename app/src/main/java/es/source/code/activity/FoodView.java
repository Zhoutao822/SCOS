package es.source.code.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.source.code.adapter.ViewPagerAdapter;
import es.source.code.model.User;

public class FoodView extends AppCompatActivity implements ActionMenuView.OnMenuItemClickListener{

    //set resultCode for onActivityResult() in MainScreen.java
    private static final int RETURN = 228;
    private static final int SUCCESS = 229;

    private static final String[] TITLE = {"冷菜", "热菜", "海鲜", "酒水"};

    private User user = null;

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
    TextView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
        ButterKnife.bind(this);
        toolbarTitle.setText("点菜");

        mActionMenuView.getMenu().clear();
        getMenuInflater().inflate(R.menu.toolbar_menu,mActionMenuView.getMenu());
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
    private void initData() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), TITLE);
        mViewPager.setAdapter(adapter);
//        setOffscreenPageLimit() is the reason why ViewPagerAdapter.getItem() run twice
//        fragment will be loaded before it is put on screen
//        mViewPager.setOffscreenPageLimit(3);
        mTab.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_hasOrdered:

                break;
            case R.id.item_checkOrder:

                break;
            case R.id.item_call:

                break;
            default:
                break;
        }


        return false;
    }
}
