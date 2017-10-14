package es.source.code.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import es.source.code.base.BaseFragment;
import es.source.code.base.FragmentFactory;

/**
 * Created by zhoutao on 2017/10/12.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public String[] mTitle;

    public ViewPagerAdapter(FragmentManager fm, String[] title) {
        super(fm);
        mTitle = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment baseFragment = null;
        if (mTitle.length == 4) {
            baseFragment = FragmentFactory.createFragment(position);
        } else if (mTitle.length == 2) {
            baseFragment = FragmentFactory.createFragment(position+4);
        }
        return baseFragment;
    }


}
