package com.poorfellow.AGameOfThings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;

/**
 * Created by David on 3/1/14.
 */
public class SubmitThingFragment extends Fragment {

    public SubmitThingFragment () {


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInastanceState) {
        View rootView = inflater.inflate(R.layout.submit_thing_fragment_layout, container, false);

        TextView currentThingView = (TextView) rootView.findViewById(R.id.currentThingTextView);
        currentThingView.setText("THINGS you shouldn't teach your children.");
        Button submitButton = (Button) rootView.findViewById(R.id.submitThingButton);
        submitButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hook up later
            }
        });

        return null;
    }
}
