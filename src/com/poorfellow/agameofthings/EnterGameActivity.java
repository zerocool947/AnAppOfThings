package com.poorfellow.agameofthings;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.support.v4.app.NavUtils;

public class EnterGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter_game);
		// Show the Up button in the action bar.
		Button submitButton = (Button) findViewById(R.id.submitEnterButton);
		RadioGroup gamesList = (RadioGroup) findViewById(R.id.gamesListGroup);
		
		RadioButton newGame1 = new RadioButton(this);
		newGame1.setText("RobbiesGame");
		gamesList.addView(newGame1);
		
		submitButton.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
               Intent i = new Intent(EnterGameActivity.this, MainGameScreenFragment.class);
               Log.d("STATUS","Changing activity");
               startActivity(i);		
			}
		});
		
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
