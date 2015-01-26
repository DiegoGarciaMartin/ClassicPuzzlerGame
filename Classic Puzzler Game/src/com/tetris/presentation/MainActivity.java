package com.tetris.presentation;

import com.dgm.classicPuzzlerGame.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private static final String MY_AD_UNIT_ID = "ca-app-pub-2613003681009907/8431074671";

	private Button btStartGame, btOptions, btRating;
	private LinearLayout layoutPublicity;
	private AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		btStartGame = (Button) this.findViewById(R.id.buttonStart);
		btOptions = (Button) this.findViewById(R.id.buttonOptions);
		btRating = (Button) this.findViewById(R.id.buttonRating);
		layoutPublicity = (LinearLayout) this.findViewById(R.id.layoutPublicity);
		
		createPublicity();
		
		events(); // creates events

	}

	/**
	 * Method that implements the events of the buttons
	 */
	private void events() {

		// BUTTON StartGame (Click)
		btStartGame.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						GameActivity.class);
				startActivity(intent);
			}
		});

		// BUTTON Options (Click)
		btOptions.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						OptionsActivity.class);
				startActivity(intent);
			}
		});

		// BUTTON Rating (Click)
		btRating.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						RatingActivity.class);
				startActivity(intent);
			}
		});

		

	}

	/**
	 * Method that adds the banner of publicity in the top of window 
	 */
	private void createPublicity() {

		// Creates adView.
		adView = new AdView(this);
		adView.setAdUnitId(MY_AD_UNIT_ID);
		adView.setAdSize(AdSize.SMART_BANNER);
		
		layoutPublicity.addView(adView);

		AdRequest adRequest = new AdRequest.Builder().build();

		// test mode
		/*
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // Emulator
				.addTestDevice("157FAF53752BE37A6CF6B023518541E3") // My phone
				.build();
		*/
		
		adView.loadAd(adRequest);

	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	@Override
	public void onDestroy() {
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
	
}
