/**
 * 
 */
package com.widgets;

import com.dgm.classicPuzzlerGame.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * 
 * Android's view that represent a switch. It has two states: on and off. 
 * 
 * @author Diego García Martín
 *
 */
public class SwitchButton extends LinearLayout {

	private Button btOn, btOff;
	private boolean stateOn;
	private String textButtonOn, textButtonOff;
	private OnChangeStateListener listener;
	private SwitchButton sb;

	public SwitchButton(Context context) {
		super(context);
		initialize();
	}

	public SwitchButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
		
		// Obtain atributes of component
		TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.SwitchButton);
		
		boolean state = a.getBoolean(R.styleable.SwitchButton_switch_state, true);
		setStateOn(state);
		
		String btOnText = a.getString(R.styleable.SwitchButton_button_on_text);
		if(btOnText != null)btOn.setText(btOnText);
		
		String btOffText = a.getString(R.styleable.SwitchButton_button_off_text);
		if(btOffText != null)btOff.setText(btOffText);
		
		a.recycle();
	}

	private void initialize() {

		// Save reference to own object, necesary to throw event onChangeState
		sb = this;
		
		// Inflate layout
		LayoutInflater layoutInflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.widget_switch_button, this, true);

		// Obtain references to buttons
		btOn = (Button) findViewById(R.id.buttonOn);
		btOff = (Button) findViewById(R.id.buttonOff);
		
		setStateOn(true);

		events();

	}

	private void events() {

		// Button ON click
		this.btOn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!isStateOn()) {
					setStateOn(true);
					
					if(listener !=null){
						listener.onChangeState(sb);
					}
					
				}
			}
		});

		// Button OFF click
		this.btOff.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isStateOn()) {
					setStateOn(false);
					
					if(listener !=null){
						listener.onChangeState(sb);
					}
					
				}

			}
		});

	}

	public boolean isStateOn() {
		return stateOn;
	}

	public void setStateOn(boolean stateOn) {
		this.stateOn = stateOn;

		if (this.stateOn) {
			btOn.setBackgroundColor(getResources().getColor(R.color.DarkCyan));
			btOn.setTextColor(getResources().getColor(R.color.White));
			btOn.setTypeface(Typeface.create("", Typeface.BOLD));

			btOff.setBackgroundColor(getResources().getColor(R.color.DarkGray));
			btOff.setTextColor(getResources().getColor(R.color.Black));
			btOff.setTypeface(Typeface.create("", Typeface.NORMAL));

		} else {
			btOn.setBackgroundColor(getResources().getColor(R.color.DarkGray));
			btOn.setTextColor(getResources().getColor(R.color.Black));
			btOn.setTypeface(Typeface.create("", Typeface.NORMAL));

			btOff.setBackgroundColor(getResources().getColor(R.color.DarkCyan));
			btOff.setTextColor(getResources().getColor(R.color.White));
			btOff.setTypeface(Typeface.create("", Typeface.BOLD));
		}

	}

	public String getTextButtonOn() {
		return textButtonOn;
	}

	public void setTextButtonOn(String textButtonOn) {
		this.textButtonOn = textButtonOn;
	}

	public String getTextButtonOff() {
		return textButtonOff;
	}

	public void setTextButtonOff(String textButtonOff) {
		this.textButtonOff = textButtonOff;
	}

	public void setOnChangeStateListener(OnChangeStateListener listener) {
		this.listener = listener;
	}

}
