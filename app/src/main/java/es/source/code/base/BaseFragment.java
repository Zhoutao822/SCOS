package es.source.code.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.model.Food;

/**
 * Created by zhoutao on 2017/10/12.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public List<Food> loadData(List<Food> foodList, String[] foodName,
                                              int[] foodPrice, int[] foodQuantity,
                                              int[] imageID, String[] foodInfo) {
        foodList = new ArrayList<Food>();
        for (int i = 0; i < foodName.length; i++) {
            Food food = new Food();
            food.setFoodName(foodName[i]);
            food.setFoodPrice(foodPrice[i]);
            food.setFoodQuantity(foodQuantity[i]);
            food.setImageID(imageID[i]);
            food.setFoodInfo(foodInfo[i]);
            foodList.add(food);
        }
        return foodList;
    }

}
