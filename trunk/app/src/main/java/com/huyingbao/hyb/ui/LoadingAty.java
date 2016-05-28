package com.huyingbao.hyb.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.huyingbao.hyb.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoadingAty extends AppCompatActivity {

    @Bind(R.id.fullscreen_content)
    TextView fullscreenContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_loading);
        ButterKnife.bind(this);

        fullscreenContent.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 300);
    }
}
