package com.tetris.presentation;

import java.text.ParseException;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dgm.classicPuzzlerGame.R;
import com.tetris.game.EnumActionTetris;
import com.tetris.game.Tetris;
import com.tetris.scoreManager.ScoreController;

public class GameActivity extends Activity implements Observer {

	// Sensitivity buttons
	private long speedActions; // move right,left,down with buttons (milisecons) -> sensitivity 5 (medium)
	private static final long SPEED_ACTION_ROTATE = 300; // move rotate with buttons (milisecons)
	private static final long DELAY_ACTIONS = 6; // delay move right,left,down with buttons (milisecons)
	
	// --------------------------------------------------------
	
	// Views of activity
	private ImageButton buttonRight, buttonLeft, buttonDown,buttonDownFull, buttonRotate, buttonPause;
	private TextView textViewLevel,textViewScore,textViewLines;
	private Tetris tetris;
	private ViewBoardTetris viewBoard;
	private ViewNextPieceTetris nextPiece;
	private Timer timer, timerButtons;
	private TetrisTimerTask timerTask;
	private ButtonTimerTask timerTaskButton;
	private PopupWindow popupGameOver,popupPause;
	private MediaPlayer mediaPlayer;
	private int widthPopupGameOver, heightPopupGameOver, widthPopupPause, heightPopupPause;

	// Options game
	private boolean showNextPiece;
	private boolean increaseLevel;
	private boolean enablePieceShadow;
	private boolean sound;
	private int levelInit;
	private int sensitivity;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		// Preferences of game
		SharedPreferences preferences = getSharedPreferences("Options",Context.MODE_PRIVATE);
		
		levelInit = preferences.getInt("Level", 0);
		enablePieceShadow = preferences.getBoolean("ShadowPiece", true);
		showNextPiece = preferences.getBoolean("NextPiece", true);
		increaseLevel = preferences.getBoolean("IncreaseLevel", true); 
		sound = preferences.getBoolean("Sound", true);
		sensitivity = preferences.getInt("Sensitivity", 8);
		
		tetris = new Tetris(levelInit,enablePieceShadow, increaseLevel);
		tetris.addObserver(this);

		buttonRight = (ImageButton) findViewById(R.id.buttonRight);
		buttonLeft = (ImageButton) findViewById(R.id.buttonLeft);
		buttonDown = (ImageButton) findViewById(R.id.buttonDown);
		buttonDownFull = (ImageButton) findViewById(R.id.buttonDownFull);
		buttonRotate = (ImageButton) findViewById(R.id.buttonRotation);
		buttonPause = (ImageButton) findViewById(R.id.buttonPause);

		textViewLevel = (TextView) findViewById(R.id.textViewLevel);
		textViewScore = (TextView) findViewById(R.id.textViewScore);
		textViewLines = (TextView) findViewById(R.id.textViewLines);
		
		textViewLevel.setText(tetris.getLevel()+"");
		textViewLines.setText(tetris.getLines()+"");
		textViewScore.setText(tetris.getScore()+"");
		
		viewBoard = (ViewBoardTetris) findViewById(R.id.viewBoardTetris);
		viewBoard.setBoard(tetris);
		viewBoard.invalidate();
		
		nextPiece = (ViewNextPieceTetris)findViewById(R.id.viewNextPiece);
		
		if(showNextPiece){
			nextPiece.setPiece(tetris.getNextPiece());
		} else {
			nextPiece.setVisibility(View.GONE);
			((TextView)findViewById(R.id.textViewLabelNextPiece)).setVisibility(View.GONE);
		}
		
		if(sound){
			mediaPlayer = MediaPlayer.create(GameActivity.this,R.raw.tetris);
			mediaPlayer.setLooping(true);
			mediaPlayer.start();
		}
		
		speedActions = 99;
		if(sensitivity < 5){ // Minor sensitivity of buttons -> More miliseconds
			speedActions = speedActions + (sensitivity * 3);
		} else if(sensitivity > 5){ // More sensitivity of buttons -> Less miliseconds
			speedActions = speedActions - ((sensitivity - 5) * 3);
		}
		
		events();
		
		//Popup game over and pause width and height
		//Converts dp into its equivalent px
		widthPopupGameOver = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)225, getResources().getDisplayMetrics());
		heightPopupGameOver = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)140, getResources().getDisplayMetrics());
		widthPopupPause = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)225, getResources().getDisplayMetrics());
		heightPopupPause = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)160, getResources().getDisplayMetrics());
		
		
		timerButtons = new Timer();
		
		timer = new Timer();
		timerTask = new TetrisTimerTask();
		timer.schedule(timerTask, 1000, tetris.getSpeed());
		
	}
	
	
	@Override
	public void onPause() {
		
		pauseGame();
		
		super.onPause();
	}

	@Override
	public void onDestroy() {
		
		if(mediaPlayer != null){
			mediaPlayer.release();
		}
		
		super.onDestroy();
	}

	private void events() {
		
		// BUTTON RIGHT
		buttonRight.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				if (event.getAction() == MotionEvent.ACTION_DOWN ) {
					
					if(timerTaskButton != null){
						timerTaskButton.cancel();
						timerTaskButton = null;
					}
					
					timerTaskButton = new ButtonTimerTask(ButtonTimerTask.ACTION_RIGHT);
					timerButtons.schedule(timerTaskButton, DELAY_ACTIONS, speedActions);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                	if(timerTaskButton != null){
                		timerTaskButton.cancel();
                		timerTaskButton = null;
					}
                }

				return false;
			}
		});
		
		
		// BUTTON LEFT
		buttonLeft.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					
					if(timerTaskButton != null){
						timerTaskButton.cancel();
	                	timerTaskButton = null;
					}
					
					timerTaskButton = new ButtonTimerTask(ButtonTimerTask.ACTION_LEFT);
					timerButtons.schedule(timerTaskButton, DELAY_ACTIONS, speedActions);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                	if(timerTaskButton != null){
						timerTaskButton.cancel();
	                	timerTaskButton = null;
					}
                }

				return false;
			}
		});


		// BUTTON DOWN
		buttonDown.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					
					if(timerTaskButton != null){
						timerTaskButton.cancel();
						timerTaskButton = null;
					}
					
					timerTaskButton = new ButtonTimerTask(ButtonTimerTask.ACTION_DOWN);
					timerButtons.schedule(timerTaskButton, DELAY_ACTIONS, speedActions);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                	if(timerTaskButton != null){
                		timerTaskButton.cancel();
                		timerTaskButton = null;
					}
                }

				return false;
			}
		});
		
		
		// BUTTON ROTATE
		buttonRotate.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					
					if(timerTaskButton != null){
						timerTaskButton.cancel();
						timerTaskButton = null;
					}
					
					timerTaskButton = new ButtonTimerTask(ButtonTimerTask.ACTION_ROTATE);
					timerButtons.schedule(timerTaskButton, DELAY_ACTIONS, SPEED_ACTION_ROTATE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                	if(timerTaskButton != null){
                		timerTaskButton.cancel();
                		timerTaskButton = null;
					}
                }

				return false;
			}
		});
		
		// BUTTON DOWN FULL
		buttonDownFull.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(timerTaskButton != null){
            		timerTaskButton.cancel();
            		timerTaskButton = null;
				}
				
				tetris.movePieceDownFull();
			}
		});
		
		// BUTTON PAUSE
		buttonPause.setOnTouchListener(new OnTouchListener() {
			
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					pauseGame();
                } 

				return false;
			}
		});
		
	}
	
	private void pauseGame(){
		timerTask.cancel();
		
		if(sound && mediaPlayer != null){
			if (mediaPlayer.isPlaying()){
				mediaPlayer.pause();
			}
		}
		
		if(popupPause == null){
			showPopupPause();
		}
	}
	
	private void showPopupGameOver(){
		
		try {
			ScoreController.createScore(GameActivity.this, tetris.getScore(), levelInit, tetris.getLevel(), tetris.getLines());
		} catch (ParseException e) {
		}
		
		// Inflate the popup_gameover.xml
		LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout viewGroup = (LinearLayout) findViewById(R.id.popupGameOver);
		View layout = layoutInflater.inflate(R.layout.popup_gameover, viewGroup, true);
		
		// Creating the PopupWindow
		popupGameOver = new PopupWindow(layout, widthPopupGameOver, heightPopupGameOver, true);

		// Show popup in the center of screen
		popupGameOver.showAtLocation(layout, Gravity.CENTER, 0, 0);

		Button buttonExit,buttonPlayAgain;
		buttonExit = (Button) layout.findViewById(R.id.buttonGameOverExit);
		buttonPlayAgain = (Button) layout.findViewById(R.id.buttonGameOverPlayAgain);

		buttonExit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupGameOver.dismiss();
				closeActivity();
				popupGameOver = null;
			}
		});
	   
		buttonPlayAgain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupGameOver.dismiss();
				playAgain();
				popupGameOver = null;
			}
		});
		
	}
	
	private void showPopupPause(){
		
		// Inflate the popup_gameover.xml
		LayoutInflater layoutInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout viewGroup = (LinearLayout) findViewById(R.id.popupPause);
		View layout = layoutInflater.inflate(R.layout.popup_pause, viewGroup, true);
		
		// Creating the PopupWindow
		popupPause = new PopupWindow(layout, widthPopupPause, heightPopupPause, true);

		// Show popup in the center of screen
		popupPause.showAtLocation(layout, Gravity.CENTER, 0, 0);

		Button buttonExit,buttonRestart,buttonResume;
		buttonExit = (Button) layout.findViewById(R.id.buttonPauseExit);
		buttonRestart = (Button) layout.findViewById(R.id.buttonPauseRestart);
		buttonResume = (Button) layout.findViewById(R.id.buttonPauseResume);

		buttonExit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupPause.dismiss();
				closeActivity();
				popupPause = null;
			}
		});
	   
		buttonRestart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popupPause.dismiss();
				
				if(sound){
					mediaPlayer.seekTo(0);
				}
				
				playAgain();
				popupPause = null;
			}
		});
		
		buttonResume.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				popupPause.dismiss();
				
				if(sound){
					mediaPlayer.start();
				}
				
				timerTask = new TetrisTimerTask();
				timer.schedule(timerTask,tetris.getSpeed(), tetris.getSpeed());
				popupPause = null;
			}
		});
	}
	
	private void closeActivity(){
		this.finish();
	}
	
	private void playAgain(){
		tetris = new Tetris(levelInit,enablePieceShadow,increaseLevel);
		tetris.addObserver(this);
		viewBoard.setBoard(tetris);
		viewBoard.invalidate();
		textViewLevel.setText(tetris.getLevel()+"");
		textViewLines.setText(tetris.getLines()+"");
		textViewScore.setText(tetris.getScore()+"");
		
		if(showNextPiece){
			nextPiece.setPiece(tetris.getNextPiece());
		}
		
		if(sound){
			mediaPlayer.start();
		}
		
		timerTask = new TetrisTimerTask();
		timer.schedule(timerTask, 1000, tetris.getSpeed());
	}

	@Override
	public void update(Observable observable, Object data) {
		
		EnumActionTetris action = (EnumActionTetris)data;
		if(action == EnumActionTetris.MoveDownPiece || action == EnumActionTetris.MoveRightPiece || action == EnumActionTetris.MoveLeftPiece || action == EnumActionTetris.RotatePiece || action == EnumActionTetris.RemoveRow){
			viewBoard.invalidate();
		} else if (action == EnumActionTetris.NextPiece && showNextPiece){
			nextPiece.setPiece(tetris.getNextPiece());
		} else if (action == EnumActionTetris.LevelChange){
			timerTask.cancel();
			timerTask = new TetrisTimerTask();
			timer.schedule(timerTask, 0, tetris.getSpeed());
			textViewLevel.setText(tetris.getLevel()+"");
		} else if (action == EnumActionTetris.ScoreChange){
			textViewScore.setText(tetris.getScore()+"");
		} else if (action == EnumActionTetris.LinesChange){
			textViewLines.setText(tetris.getLines()+"");
		} else if (action == EnumActionTetris.EndGame){
			timerTask.cancel();
			
			if(sound){
				mediaPlayer.pause();
				mediaPlayer.seekTo(0);
			}
			
			if (popupGameOver == null){
				showPopupGameOver();
			}
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        return true;
	    } 

	    return super.onKeyDown(keyCode, event);
	}

	
	// ------------------------------------------------------------------------
	
	/*
	 *    TetrisTimerTask
	 */
	
	private class TetrisTimerTask extends TimerTask{

        @Override
        public void run() {        
            runOnUiThread(new Runnable() {              
                @Override
                public void run() {
                	tetris.movePieceDown();
                }
            });
        }       
    }
	
	// ------------------------------------------------------------------------ 

	/*
	 *    ButtonTimerTask
	 */
	
	private class ButtonTimerTask extends TimerTask{

		public static final int ACTION_DOWN = 0;
		public static final int ACTION_RIGHT = 1; 
		public static final int ACTION_LEFT = 2; 
		public static final int ACTION_ROTATE = 3; 
		
		
		private int action;
		public ButtonTimerTask(int action){
			this.action = action;
		}
		
        @Override
        public void run() {        
            runOnUiThread(new Runnable() {              
                @Override
                public void run() {
                	
                	if(action == ACTION_DOWN){
                		tetris.movePieceDown();
                	} else if(action == ACTION_LEFT){
                		tetris.movePieceLeft();
                	}else if(action == ACTION_RIGHT){
                		tetris.movePieceRight();
                	}else if(action == ACTION_ROTATE){
                		tetris.rotatePiece();
                	}
                	
                	
                }
            });
        }       
    }
	
	// ------------------------------------------------------------------------ 
	
	
}
