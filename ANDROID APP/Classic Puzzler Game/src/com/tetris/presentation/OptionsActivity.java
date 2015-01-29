package com.tetris.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.dgm.classicPuzzlerGame.R;
import com.widgets.OnChangeStateListener;
import com.widgets.SwitchButton;

public class OptionsActivity extends Activity {

	private SwitchButton switchButtonSound, switchButtonIncreasedLevel, switchButtonNextPiece, switchButtonShadowPiece;
	private TextView textViewLevel, textViewButtonSensitivity;
	private SeekBar seekBarLevel,seekBarButtonSensitivity;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editorPreferences; 
	private String textSensitivity, textLevel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		preferences = getSharedPreferences("Options",Context.MODE_PRIVATE);
		editorPreferences = preferences.edit();
		
		boolean sound = preferences.getBoolean("Sound", true);
		boolean increasedLevel = preferences.getBoolean("IncreaseLevel", true); 
		boolean nextPiece = preferences.getBoolean("NextPiece", true);
		boolean shadowPiece = preferences.getBoolean("ShadowPiece", true);
		int level = preferences.getInt("Level", 0);
		int sensitivity = preferences.getInt("Sensitivity", 8);
		
		switchButtonSound = (SwitchButton)this.findViewById(R.id.switchButtonSound);
		switchButtonIncreasedLevel = (SwitchButton)this.findViewById(R.id.switchButtonIncreaseLevel);
		switchButtonNextPiece = (SwitchButton)this.findViewById(R.id.switchButtonShowNextPiece);
		switchButtonShadowPiece = (SwitchButton)this.findViewById(R.id.switchButtonShadowPiece);
		textViewLevel = (TextView)this.findViewById(R.id.textViewInitialLevel);
		seekBarLevel = (SeekBar)this.findViewById(R.id.seekBarInitialLevel);
		textViewButtonSensitivity = (TextView)this.findViewById(R.id.textViewButtonSensitivity);
		seekBarButtonSensitivity = (SeekBar)this.findViewById(R.id.seekBarButtonSensitivity);
		
		switchButtonSound.setStateOn(sound);
		switchButtonIncreasedLevel.setStateOn(increasedLevel);
		switchButtonNextPiece.setStateOn(nextPiece);
		switchButtonShadowPiece.setStateOn(shadowPiece);
		seekBarLevel.setProgress(level);
		textLevel = getString(R.string.textViewLevel);
		textViewLevel.setText(textLevel + level);
		seekBarButtonSensitivity.setProgress((sensitivity-1));
		textSensitivity = getString(R.string.textViewButtonSensitivity);
		textViewButtonSensitivity.setText(textSensitivity + sensitivity);
		
		events();
		
	}


	public void events(){
		
		switchButtonSound.setOnChangeStateListener(new OnChangeStateListener() {
			@Override
			public void onChangeState(SwitchButton sb) {
				editorPreferences.putBoolean("Sound", sb.isStateOn());
				editorPreferences.commit();
			}
		});
		
		switchButtonIncreasedLevel.setOnChangeStateListener(new OnChangeStateListener() {
			@Override
			public void onChangeState(SwitchButton sb) {
				editorPreferences.putBoolean("IncreaseLevel", sb.isStateOn());
				editorPreferences.commit();
			}
		});

		switchButtonNextPiece.setOnChangeStateListener(new OnChangeStateListener() {
			@Override
			public void onChangeState(SwitchButton sb) {
				editorPreferences.putBoolean("NextPiece", sb.isStateOn());
				editorPreferences.commit();
			}
		});
		
		switchButtonShadowPiece.setOnChangeStateListener(new OnChangeStateListener() {
			@Override
			public void onChangeState(SwitchButton sb) {
				editorPreferences.putBoolean("ShadowPiece", sb.isStateOn());
				editorPreferences.commit();
			}
		});
		
		seekBarLevel.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				editorPreferences.putInt("Level", seekBar.getProgress());
				editorPreferences.commit();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				textViewLevel.setText(textLevel + seekBar.getProgress());
			}
		});
		
		seekBarButtonSensitivity.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				editorPreferences.putInt("Sensitivity", (seekBar.getProgress()+1));
				editorPreferences.commit();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				textViewButtonSensitivity.setText(textSensitivity + (seekBar.getProgress() + 1));
			}
		});
		
	}
	
}
