package com.daolion.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.daolion.BindTools;
import com.daolion.BindViewAnnotation;
import com.daolion.HelloAnnotation;
import com.daolion.R;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/7/6
       Time     :  16:53
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */

@HelloAnnotation
public class MainActivity extends AppCompatActivity {

    @BindViewAnnotation(R.id.tv_first) TextView tvFirst;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindTools.bind(this);
        tvFirst.setText("hello This is inject by mySelf");
    }
}
