package es.source.code.base;

import java.util.HashMap;

import es.source.code.base.BaseFragment;
import es.source.code.fragment.ColdFoodFragment;
import es.source.code.fragment.DrinkFragment;
import es.source.code.fragment.HasOrderFragment;
import es.source.code.fragment.HotFoodFragment;
import es.source.code.fragment.SeaFoodFragment;
import es.source.code.fragment.UnOrderFragment;

/**
 * fragment 工厂类
 * Created by zhoutao on 2017/10/12.
 */

public class FragmentFactory {
    private static HashMap<Integer, BaseFragment> mBaseFragments = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int position) {
        BaseFragment baseFragment = mBaseFragments.get(position);

        if (baseFragment == null) {
            switch (position) {
                case 0:
                    baseFragment = new ColdFoodFragment();
                    break;
                case 1:
                    baseFragment = new HotFoodFragment();
                    break;
                case 2:
                    baseFragment = new SeaFoodFragment();
                    break;
                case 3:
                    baseFragment = new DrinkFragment();
                    break;
                case 4:
                    baseFragment = new UnOrderFragment();
                    break;
                case 5:
                    baseFragment = new HasOrderFragment();
                    break;
            }
            mBaseFragments.put(position, baseFragment);
        }
        return baseFragment;
    }


}
