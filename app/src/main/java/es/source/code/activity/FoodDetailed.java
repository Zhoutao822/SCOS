package es.source.code.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.source.code.adapter.DetailViewPagerAdapter;
import es.source.code.model.Food;

public class FoodDetailed extends AppCompatActivity {

    private List<Food> foodList;
    private int position;
//    @BindView(R.id.food_detail_image)
//    ImageView foodDetailImage;
//
//    @BindView(R.id.food_detail_name)
//    TextView foodDetailName;
//
//    @BindView(R.id.food_detail_price)
//    TextView foodDetailPrice;
//
//    @BindView(R.id.food_detail_info)
//    TextView foodDetailInfo;
//
//    @BindView(R.id.send_info)
//    Button sendInfo;

    @BindView(R.id.food_detail_viewpager)
    ViewPager foodViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detail_viewpager);
        ButterKnife.bind(this);
        foodList = (List<Food>) getIntent().getSerializableExtra("foodList");
        position = Integer.parseInt(getIntent().getStringExtra("position"));
        foodViewPager.setAdapter(new DetailViewPagerAdapter(this,foodList));
//        foodDetailName.setText(foodList.get(position).getFoodName());
//        foodDetailPrice.setText(String.valueOf(foodList.get(position).getFoodPrice()));
//        foodDetailInfo.setText(foodList.get(position).getFoodInfo());
//        foodDetailImage.setImageResource(foodList.get(position).getImageID());
    }
}
