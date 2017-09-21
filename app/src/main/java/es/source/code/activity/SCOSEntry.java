package es.source.code.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SCOSEntry extends AppCompatActivity {

    @BindView(R.id.myLogo)
    ImageView myLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.scoslogo).fit().centerCrop().into(myLogo);
        myLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SCOSEntry.this,MainScreen.class));
            }
        });
    }
}
