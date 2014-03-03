package com.poorfellow.agameofthings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by David on 3/2/14.
 */
public class CreateGameActivity extends android.app.Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        Button createButton = (Button) findViewById(R.id.createGameButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateGameActivity.this, HostGameLobbyActivity.class);
                Log.d("STATUS", "Changing activities");
                startActivity(i);
            }
        });
    }


}