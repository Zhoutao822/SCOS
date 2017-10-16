package es.source.code.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 *
 */
public class ColdFoodFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String[] foodName = {"酱牛肉", "口水鸡", "拍黄瓜", "凉拌苦瓜", "花生米", "蔬菜沙拉"};
    private int[] foodPrice = {30, 25, 8, 8, 8, 10};
    private int[] foodQuantity = {1, 1, 1, 1, 1, 1};
    private int[] imageID = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private String[] foodInfo = {"味道偏咸", "凉菜", "爽口", "清热解火", "脆", "富含纤维素"};
    private List< Food> foodList;

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
        Log.i("item click","position:"+position+" view:"+view.getId()+" id:"+id);
    }
}
