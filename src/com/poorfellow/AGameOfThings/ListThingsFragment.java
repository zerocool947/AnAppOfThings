package com.poorfellow.agameofthings;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;
import android.widget.RadioButton;


/**
 * Created by David on 3/1/14.
 */
public class ListThingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioGroup rootView = (RadioGroup) inflater.inflate(R.layout.list_things_fragment_layout, container, false);

        RadioButton response1 = new RadioButton(this.getActivity());
        response1.setText("Porn");
        response1.setGravity(Gravity.RIGHT);

        RadioButton response2 = new RadioButton(this.getActivity());
        response2.setText("misbehave");
        response2.setGravity(Gravity.RIGHT);

        RadioButton response3 = new RadioButton(this.getActivity());
        response3.setText("Incest");
        response3.setGravity(Gravity.RIGHT);

        RadioButton response4 = new RadioButton(this.getActivity());
        response4.setText("I hope I don't accidentally show this to someone at work");
        response4.setGravity(Gravity.RIGHT);

        rootView.addView(response1);
        rootView.addView(response2);
        rootView.addView(response3);
        rootView.addView(response4);
        return rootView;
    }
}