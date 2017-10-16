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

import es.source.code.activity.R;
import es.source.code.model.Food;

/**
 * Created by zhoutao on 2017/10/16.
 */

public class UnorderListAdapter extends BaseAdapter {
    private List<Food> data;
    private LayoutInflater layoutInflater;
    private Context context;


    public UnorderListAdapter(Context context, List<Food> data) {
        this.data = data;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    private final class FoodView {
        private TextView foodName;
        private TextView foodPrice;
        private TextView foodInfo;
        private TextView foodQuantity;
        private Button btnOrder;

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
        FoodView foodView = null;
        if (convertView == null) {
            foodView = new FoodView();
            convertView = layoutInflater.inflate(R.layout.unorder_list_food_item, null);
            foodView.foodName = (TextView) convertView.findViewById(R.id.unorder_food_item_name);
            foodView.foodPrice = (TextView) convertView.findViewById(R.id.unorder_food_item_price);
            foodView.foodQuantity = (TextView) convertView.findViewById(R.id.unorder_food_item_quantity);
            foodView.foodInfo = (TextView) convertView.findViewById(R.id.unorder_food_item_info);
            foodView.btnOrder = (Button) convertView.findViewById(R.id.unorder_food_item_select_btn);
            convertView.setTag(foodView);
        } else {
            foodView = (FoodView) convertView.getTag();
        }
        foodView.foodName.setText(data.get(position).getFoodName());
        foodView.foodPrice.setText(String.valueOf(data.get(position).getFoodPrice()));
        foodView.foodQuantity.setText(String.valueOf(data.get(position).getFoodQuantity()));
        foodView.foodInfo.setText(data.get(position).getFoodInfo());
        final FoodView finalFoodView = foodView;
        foodView.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalFoodView.btnOrder.getText().equals("点菜")) {
                    Toast.makeText(context, "点菜成功", Toast.LENGTH_SHORT).show();
                    finalFoodView.btnOrder.setText("退点");
                    //todo add food into orderlist

                }else if (finalFoodView.btnOrder.getText().equals("退点")){
                    Toast.makeText(context, "退点成功", Toast.LENGTH_SHORT).show();
                    finalFoodView.btnOrder.setText("点菜");
                }

            }
        });
        return convertView;
    }
}
