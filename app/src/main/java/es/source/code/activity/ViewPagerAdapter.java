package es.source.code.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by zhoutao on 2017/10/12.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public String[] mTitle= {"冷菜","热菜","海鲜","酒水"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment baseFragment = FragmentFactory.createFragment(position);
        return baseFragment;
    }


}
