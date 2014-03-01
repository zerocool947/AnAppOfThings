package com.poorfellow.agameofthings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by David on 3/1/14.
 */
public class MainGameScreenPagerAdapter extends FragmentPagerAdapter {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public MainGameScreenPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("The position is", Integer.toString(position));

        switch (position) {
            case 0:
                Log.d("POSITION", "0");
                Fragment submitThingFragment = new SubmitThingFragment();
                return submitThingFragment;
            case 1:
                Log.d("POSITION", "1");
                Fragment listThingsFragment = new ListThingsFragment();
                return listThingsFragment;
            case 2:
                Log.d("POSITION", "1");
                Fragment listNamesFragment = new ListNamesFragment();
                return listNamesFragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
