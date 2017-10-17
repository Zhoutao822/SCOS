package es.source.code.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import es.source.code.activity.R;
import es.source.code.model.Food;

/**
 * Created by zhoutao on 2017/10/16.
 */

public class DetailViewPagerAdapter extends PagerAdapter {
    private List<Food> foodList;
    private Context context;
    private LayoutInflater layoutInflater;

    private ImageView foodDetailImage;
    private TextView foodDetailName;
    private TextView foodDetailPrice;
    private TextView foodDetailInfo;
    private Button sendInfo;


    public DetailViewPagerAdapter(Context context, List<Food> foodList) {
        this.foodList = foodList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View detailView = layoutInflater.inflate(R.layout.food_detail, null);

        foodDetailImage = (ImageView) detailView.findViewById(R.id.food_detail_image);
        foodDetailName = (TextView) detailView.findViewById(R.id.food_detail_name);
        foodDetailPrice = (TextView) detailView.findViewById(R.id.food_detail_price);
        foodDetailInfo = (TextView) detailView.findViewById(R.id.food_detail_info);
        sendInfo = (Button) detailView.findViewById(R.id.send_info);
        Food foodDetail = foodList.get(position);

        foodDetailImage.setImageResource(foodDetail.getImageID());
        foodDetailName.setText(foodDetail.getFoodName());
        foodDetailPrice.setText(String.valueOf(foodDetail.getFoodPrice()));
        foodDetailInfo.setText(foodDetail.getFoodInfo());

        container.addView(detailView);

        return detailView;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
