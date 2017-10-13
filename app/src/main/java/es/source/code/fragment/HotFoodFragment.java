package es.source.code.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import es.source.code.activity.BaseFragment;
import es.source.code.activity.R;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class HotFoodFragment extends BaseFragment {

    @Override
    protected void loadData() {
        Toast.makeText(mContext,"热菜",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View initView() {
        TextView mView = new TextView(mContext);
        mView.setText("Fragment热菜");
        mView.setGravity(Gravity.CENTER);
        mView.setTextSize(18);
        mView.setTextColor(Color.BLACK);
        return mView;
    }




}