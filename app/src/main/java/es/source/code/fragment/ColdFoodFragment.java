package es.source.code.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
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
 *
 */
public class ColdFoodFragment extends BaseFragment {

    private ListView listView;
    private String[] foodName = {"酱牛肉","口水鸡","拍黄瓜","凉拌苦瓜","花生米","蔬菜沙拉","泡椒凤爪"};
    private String[] foodPrice = {"30","25","8","8","8","10","20"};

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
