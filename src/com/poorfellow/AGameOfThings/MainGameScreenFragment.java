package com.poorfellow.AGameOfThings;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


/**
 * Created by David on 3/1/14.
 */
public class MainGameScreenFragment extends FragmentActivity {

    MainGameScreenPagerAdapter mGameScreenPagerAdapter;
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game_screen_layout);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        mGameScreenPagerAdapter = new MainGameScreenPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.MainGameViewPager);
        mViewPager.setAdapter(mGameScreenPagerAdapter);
    }
}