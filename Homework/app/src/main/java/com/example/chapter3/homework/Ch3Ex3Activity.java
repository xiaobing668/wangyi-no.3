package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

/**
 * 使用 ViewPager 和 Fragment 做一个简单版的好友列表界面
 * 1. 使用 ViewPager 和 Fragment 做个可滑动界面
 * 2. 使用 TabLayout 添加 Tab 支持
 * 3. 对于好友列表 Fragment，使用 Lottie 实现 Loading 效果，在 5s 后展示实际的列表，要求这里的动效是淡入淡出
 */
public class Ch3Ex3Activity extends AppCompatActivity {
    private LottieAnimationView animation;
    private AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex3);
        final ViewPager pager=findViewById(R.id.pager);
        TabLayout tabLayout =  findViewById(R.id.tablayout);
        animation = findViewById(R.id.animation_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Ch3Ex3Activity.this,R.layout.support_simple_spinner_dropdown_item);//适配器
         final ListView listView = (ListView) findViewById(R.id.listview);
         //找到ListView布局
        listView.setAdapter(adapter);
        for(int i = 0; i < 20; i++){
            adapter.add("item " + String.valueOf(i));
        }
        listView.setAlpha(0.0f);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator alphaanimation = ObjectAnimator.ofFloat(animation,"alpha",1.0f,0.0f);
                alphaanimation.setRepeatCount(0);
                alphaanimation.setInterpolator(new LinearInterpolator());
                alphaanimation.setDuration(500);
                alphaanimation.setRepeatMode(ObjectAnimator.REVERSE);

                ObjectAnimator alphapager = ObjectAnimator.ofFloat(listView,"alpha",0.0f,1.0f);
                alphapager.setRepeatCount(0);
                alphapager.setInterpolator(new LinearInterpolator());
                alphapager.setDuration(500);
                alphapager.setRepeatMode(ObjectAnimator.REVERSE);

                animatorSet = new AnimatorSet();
                animatorSet.playTogether(alphaanimation,alphapager);
                animatorSet.start();
            }
        },5000);



        // TODO: ex3-1. 添加 ViewPager 和 Fragment 做可滑动界面
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(position == 0)
                {
                    return new Fragment();
                }
                else{
                    return  new Fragment();
                }

            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                if (position==0)
                    return "好友列表";
                else
                    return "我的好友";
            }

        });


        // TODO: ex3-2, 添加 TabLayout 支持 Tab

        tabLayout.setupWithViewPager(pager);


    }
}
