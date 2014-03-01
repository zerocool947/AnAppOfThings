package com.poorfellow.agameofthings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by David on 3/1/14.
 */
public class SubmitThingFragment extends Fragment {

    public SubmitThingFragment () {

        Log.d("STATUS", "Inside create view");


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.submit_thing_fragment_layout, container, false);

        Log.d("STATUS", "Inside create view");
        TextView currentThingView = (TextView) rootView.findViewById(R.id.currentThingTextView);
        currentThingView.setText("THINGS you shouldn't teach your children.");
        Log.d("STATUS", "THINGS text set");

        Button submitButton = (Button) rootView.findViewById(R.id.submitThingButton);
        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hook up later
            }
        });

        return rootView;
    }
}
