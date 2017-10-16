package es.source.code.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.source.code.activity.R;
import es.source.code.adapter.ListViewAdapter;
import es.source.code.base.BaseFragment;
import es.source.code.model.Food;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class HotFoodFragment extends BaseFragment {

    private ListView listView;
    private String[] foodName = {"糖醋排骨","鱼香肉丝","水煮肉片","水煮鱼","宫保鸡丁","回锅肉"};
    private int[] foodPrice = {40,30,40,40,35,25};
    private int[] foodQuantity = {1,1,1,1,1,1,1};
    private int[] imageID = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,};
    private String[] foodInfo = {"味道偏咸", "凉菜", "爽口", "清热解火", "脆", "富含纤维素", "辣"};
    private List<Food> foodList;
    private Food hotFood ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        foodList= loadData(foodList,foodName,foodPrice,foodQuantity,imageID,foodInfo);

        listView.setAdapter(new ListViewAdapter(getActivity(), foodList));
        return view;
    }


}
