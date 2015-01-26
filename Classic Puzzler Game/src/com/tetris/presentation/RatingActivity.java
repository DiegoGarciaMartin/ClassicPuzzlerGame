package com.tetris.presentation;

import java.text.ParseException;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dgm.classicPuzzlerGame.R;
import com.tetris.scoreManager.ScoreController;
import com.tetris.scoreManager.ScoreTetris;

public class RatingActivity extends Activity {

	private ListView listRating;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		
		listRating = (ListView)findViewById(R.id.listViewRating);
		List<ScoreTetris> scores;
		try {
			scores = ScoreController.getScores(RatingActivity.this);
			AdapterScores adapter = new AdapterScores(RatingActivity.this, scores);
			listRating.setAdapter(adapter);
		} catch (ParseException e) {
		}
		
		
	}

}
