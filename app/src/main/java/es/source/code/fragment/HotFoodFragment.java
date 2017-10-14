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

/**
 * A simple {@link BaseFragment} subclass.
 */
public class HotFoodFragment extends BaseFragment {

    private ListView listView;
    private String[] foodName = {"糖醋排骨","鱼香肉丝","水煮肉片","水煮鱼","宫保鸡丁","回锅肉"};
    private String[] foodPrice = {"40","30","40","40","35","25"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        List<Map<String, Object>> list = getData();
        listView.setAdapter(new ListViewAdapter(getActivity(), list));
        return view;
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < foodName.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("foodName", foodName[i]);
            map.put("foodPrice", foodPrice[i]);
            list.add(map);
        }
        return list;
    }
}
