package es.source.code.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import es.source.code.activity.BaseFragment;

/**
 *
 */
public class ColdFoodFragment extends BaseFragment {
    @Override
    protected void loadData() {
        Toast.makeText(mContext,"冷菜",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View initView() {
        TextView mView = new TextView(mContext);
        mView.setText("Fragment冷菜");
        mView.setGravity(Gravity.CENTER);
        mView.setTextSize(18);
        mView.setTextColor(Color.BLACK);
        return mView;
    }
}
