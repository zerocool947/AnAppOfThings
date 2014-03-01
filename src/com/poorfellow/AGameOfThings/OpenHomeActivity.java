package com.poorfellow.agameofthings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class OpenHomeActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button enterGameButton = (Button) findViewById(R.id.enterGameButton);

        enterGameButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OpenHomeActivity.this, MainGameScreenFragment.class);
                Log.d("STATUS","Changing activity");
                startActivity(i);
            }
        });
    }
}
