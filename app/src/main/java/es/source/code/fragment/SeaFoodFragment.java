package es.source.code.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.activity.FoodDetailed;
import es.source.code.activity.R;
import es.source.code.adapter.ListViewAdapter;
import es.source.code.base.BaseFragment;
import es.source.code.model.Food;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeaFoodFragment extends BaseFragment {
    private ListView listView;
    private String[] foodName = {"蒸扇贝", "蒸鲍鱼", "生蚝", "三文鱼", "龙虾", "海参"};
    private int[] foodPrice = {50, 70, 60, 80, 70, 65};
    private int[] foodQuantity = {1, 1, 1, 1, 1, 1};
    private int[] imageID = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String[] foodInfo = {"味道偏咸", "凉菜", "爽口", "清热解火", "脆", "富含纤维素"};
    private List<Food> foodList;
    private Food seaFood;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        foodList = loadData(foodList, foodName, foodPrice, foodQuantity, imageID, foodInfo);
        listView.setAdapter(new ListViewAdapter(getActivity(), foodList));
        listView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("foodList", (Serializable) foodList);
        Intent intentDetail = new Intent(getActivity(), FoodDetailed.class);
        intentDetail.putExtras(bundle);
        intentDetail.putExtra("position",position+"");
        startActivity(intentDetail);
    }
}
