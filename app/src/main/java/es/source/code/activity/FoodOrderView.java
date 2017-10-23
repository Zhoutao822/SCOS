package es.source.code.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.source.code.adapter.ViewPagerAdapter;
import es.source.code.model.User;

public class FoodOrderView extends AppCompatActivity {

    //set resultCode for onActivityResult() in MainScreen.java
    private static final int RETURN = 228;
    private static final int SUCCESS = 229;

    private static final String[] TITLE = {"未下单菜", "已下单菜"};

    private CheckoutTask checkoutTask = null;
    private User user = null;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.food_order_view_tab)
    TabLayout mTab;

    @BindView(R.id.food_order_view_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.back)
    ImageView mBack;

    @BindView(R.id.total_food_price)
    TextView totalPrice;

    @BindView(R.id.total_food_quantity)
    TextView totalQuantity;

    @BindView(R.id.total_food_pay)
    Button btnPay;

    @BindView(R.id.order_progressbar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_view);
        ButterKnife.bind(this);
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        toolbarTitle.setText("查看订单");
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent();
                intentBack.putExtra("fromFoodView", "Return");
                setResult(RETURN, intentBack);
                finish();
            }
        });
        initData();
        if (getIntent() != null) {
            user = (User) getIntent().getSerializableExtra("userFromMainScreen");
        }
//// TODO: 2017/10/23 不可以点击两次结账，会报错

        totalPrice.setText("总计:" + String.valueOf(114));
        totalQuantity.setText("菜品总数:" + String.valueOf(6));
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user != null && user.getOldUser()) {
                    Toast.makeText(FoodOrderView.this, "您好，老顾客，本次你可享受7折优惠",
                            Toast.LENGTH_SHORT).show();
                }
                mProgressBar.setVisibility(ProgressBar.VISIBLE);
                new Thread() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (i < 100) {
                            i++;
                            try {
                                Thread.sleep(60);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            mProgressBar.setProgress(i);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                            }
                        });
                    }
                }.start();
                checkoutTask = new CheckoutTask(114 * 0.7);
                checkoutTask.execute((Void) null);
                btnPay.setEnabled(false);
                btnPay.setBackgroundColor(Color.GRAY);
            }
        });
    }

    private void initData() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), TITLE);
        mViewPager.setAdapter(adapter);
        mTab.setupWithViewPager(mViewPager);
    }

    private class CheckoutTask extends AsyncTask<Void, Void, Boolean> {

        private final double totalAmount;

        CheckoutTask(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            boolean flag = false;

            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                return false;
            }

            flag = true;
            return flag;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            checkoutTask = null;
            if (aBoolean) {
                Toast.makeText(FoodOrderView.this, "总计金额：" + String.valueOf(114 * 0.7), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            checkoutTask = null;
        }
    }

//    private int getTotalPrice() {
//        int sum = 0;
//        for (int i = 0; i < foodName.length; i++) {
//            sum += foodPrice[i] * foodQuantity[i];
//        }
//        return sum;
//    }
//
//
//    private int getTotalQuantity() {
//        int sum = 0;
//        for (int i = 0; i < foodName.length; i++) {
//            sum += foodQuantity[i];
//        }
//        return sum;
//    }
}
