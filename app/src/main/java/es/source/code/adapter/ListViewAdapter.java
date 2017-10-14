package es.source.code.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import es.source.code.activity.R;

/**
 * Created by zhoutao on 2017/10/13.
 */

public class ListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;


    public ListViewAdapter(Context context, List<Map<String, Object>> data) {
        this.data = data;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public final class FoodInfo {
        public TextView foodName;
        public TextView foodPrice;
        public Button btnOrder;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodInfo foodInfo = null;
        if (convertView == null) {
            foodInfo = new FoodInfo();
            convertView = layoutInflater.inflate(R.layout.list_food_item,null);
            foodInfo.foodName = (TextView) convertView.findViewById(R.id.food_item_name);
            foodInfo.foodPrice = (TextView) convertView.findViewById(R.id.food_item_price);
            foodInfo.btnOrder = (Button) convertView.findViewById(R.id.food_item_select_btn);
            convertView.setTag(foodInfo);
        }else {
            foodInfo= (FoodInfo) convertView.getTag();
        }
        foodInfo.foodName.setText((String) data.get(position).get("foodName"));
        foodInfo.foodPrice.setText((String) data.get(position).get("foodPrice"));
        final FoodInfo finalFoodInfo = foodInfo;
        foodInfo.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点菜成功",Toast.LENGTH_SHORT).show();
                finalFoodInfo.btnOrder.setText("退点");
            }
        });
        return convertView;
    }
}
