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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.source.code.activity.FoodDetailed;
import es.source.code.activity.R;
import es.source.code.adapter.HasOrderedListAdapter;
import es.source.code.adapter.ListViewAdapter;
import es.source.code.base.BaseFragment;
import es.source.code.model.Food;

/**
 * A simple {@link Fragment} subclass.
 */
public class HasOrderFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String[] foodName = {"蔬菜沙拉", "水煮鱼", "水煮肉片", "啤酒"};
    private int[] foodPrice = {10, 40, 40, 8};
    private int[] foodQuantity = {1, 1, 1, 3};
    private int[] imageID = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    private String[] foodInfo = {"不要番茄", "不要香菜", "多加辣", "冰的"};
    private List<Food> foodList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        foodList = loadData(foodList, foodName, foodPrice, foodQuantity, imageID, foodInfo);
        listView.setAdapter(new HasOrderedListAdapter(getActivity(), foodList));
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
