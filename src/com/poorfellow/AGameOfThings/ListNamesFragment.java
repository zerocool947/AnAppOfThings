package com.poorfellow.agameofthings;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by David on 3/1/14.
 */
public class ListNamesFragment extends Fragment {

    public ListNamesFragment () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RadioGroup rootView = (RadioGroup) inflater.inflate(R.layout.list_names_fragment_layout, container, false);
        RadioButton name1 = new RadioButton(this.getActivity());
        name1.setText("David");
        name1.setGravity(Gravity.RIGHT);

        RadioButton name2 = new RadioButton(this.getActivity());
        name2.setGravity(Gravity.RIGHT);
        name2.setText("Sean");

        RadioButton name3 = new RadioButton(this.getActivity());
        name3.setGravity(Gravity.RIGHT);
        name3.setText("Robbie");

        RadioButton name4 = new RadioButton(this.getActivity());
        name4.setGravity(Gravity.RIGHT);
        name4.setText("Kristen");

        rootView.addView(name1);
        rootView.addView(name2);
        rootView.addView(name3);
        rootView.addView(name4);
        return rootView;
    }
}