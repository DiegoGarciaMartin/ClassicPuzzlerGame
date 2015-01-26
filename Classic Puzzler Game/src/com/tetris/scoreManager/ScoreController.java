package com.tetris.scoreManager;

import java.text.ParseException;
import java.util.List;

import android.content.Context;

/**
 * 
 * Class that represent the score's controller of tetris. 
 * Provides the methods to create and obtain scores.
 * 
 * @author Diego García Martín
 *
 */
public class ScoreController {

	/**
	 * 
	 * Returns a list of game's scores order by score
	 * 
	 * @param context Context of activity
	 * @return List of objects ScoreTetris
	 * @throws ParseException
	 */
	public static List<ScoreTetris> getScores(Context context) throws ParseException{
		return ScoreDAO.getScores(context);
	}
	
	
	/**
	 * 
	 * Creates the game's score in the database if exists a score in the database smaller than the new score.
	 * 
	 * @param context Context of activity
	 * @param score Score of the game
	 * @param levelBegin Level that the game began
	 * @param levelEnd Level that the game end
	 * @param lines Lines of the game
	 * 
	 * @return true if the score is stored or false otherwise
	 * @throws ParseException 
	 */
	public static boolean createScore(Context context, int score, int levelBegin, int levelEnd, int lines) throws ParseException{
		
		boolean storeScore = false;
		int countScore = ScoreDAO.getCountScores(context);
		
		if (countScore < 10){
			storeScore = true;
		} else { // there are 10 scores in the database -> delete the minimum score
			
			ScoreTetris scoreMin = ScoreDAO.getMinimumScore(context);
			
			if(scoreMin != null && scoreMin.getScore() <= score){
				ScoreDAO.deleteScore(context, scoreMin);
				storeScore = true;
			}
			
		}
		
		if(storeScore){
			ScoreTetris scoreObject = new ScoreTetris(-1, null, score, levelBegin, levelEnd, lines);
			ScoreDAO.createScore(context, scoreObject);
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
}
