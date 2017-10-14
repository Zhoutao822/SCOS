package es.source.code.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SCOSEntry extends AppCompatActivity {

    @BindView(R.id.myLogo)
    ImageView myLogo;

    private GestureDetector gestureDetector;
    private GestureDetector.OnGestureListener onGestureListener = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.scoslogo).fit().centerCrop().into(myLogo);

        onGestureListener = new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                // 参数解释：
                // e1：第1个ACTION_DOWN MotionEvent
                // e2：最后一个ACTION_MOVE MotionEvent
                // velocityX：X轴上的移动速度，像素/秒
                // velocityY：Y轴上的移动速度，像素/秒
                // 触发条件 ：
                // X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
                if ((e1 == null) || (e2 == null)) {
                    return false;
                }
                int FLING_MIN_DISTANCE = 100;
                int FLING_MIN_VELOCITY = 100;
                if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                        && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                    // 向左滑动
                    String strFromEntry = "FromEntry";
                    Intent intent = new Intent(SCOSEntry.this, MainScreen.class);
                    intent.putExtra("fromEntry", strFromEntry);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);    //不重复打开多个界面
                    startActivity(intent);
                    overridePendingTransition(R.anim.move_right_in, R.anim.move_left_out);

                } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                        && Math.abs(velocityX) > FLING_MIN_VELOCITY
                        ) {
                    // 向右滑动
                }
                return false;
            }
        };
        gestureDetector = new GestureDetector(this, onGestureListener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}
