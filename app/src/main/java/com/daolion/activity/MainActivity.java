package com.daolion.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.daolion.HelloAnnotation;
import com.daolion.R;
import com.daolion.my.HelloWorld;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2018/7/6
       Time     :  16:53
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */

@HelloAnnotation
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv_first);




        String firstStr = HelloWorld.hello1();
        String secondStr = HelloWorld.hello2();
        String thirdStr = HelloWorld.hello3();

    }
}
