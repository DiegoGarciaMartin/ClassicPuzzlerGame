package com.tetris.scoreManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tetris.db.ScoreDB;

/**
 * 
 * Class that interacts with the database to create, delete and obtain game's score of tetris. 
 * It implements a DAO pattern.
 * 
 * @author Diego García Martín
 *
 */
public class ScoreDAO {

	
	/**
	 * 
	 * Creates the game's score in the database
	 * 
	 * @param context Context of activity
	 * @param score Object ScoreTetris
	 * @return Identifier of the score
	 */
	public static int createScore(Context context, ScoreTetris score){
		
		String sqlQuery = "INSERT INTO Scores (date, score, levelBegin, levelEnd, lines) "
						+ "VALUES ( "
						+ "		strftime('%Y%m%d%H%M','now','localtime'), "
						+ 		score.getScore() + ", "
						+		score.getLevelBegin() + ", "
						+		score.getLevelEnd() + ", "
						+		score.getLines() + " "
						+ "); ";
		
		SQLiteDatabase db = ScoreDB.getInstaceScoreDb(context);
		
		db.execSQL(sqlQuery);
		Cursor id = db.rawQuery("SELECT last_insert_rowid(); ", null);
		
		int idReg;
		if (id.moveToFirst()){
			idReg = id.getInt(0);
		} else {
			idReg = -1;
		}

		db.close();
		
		return idReg;
		
	}
	
	
	/**
	 * 
	 * Deletes the game's score of the database
	 * 
	 * @param context Context of activity
	 * @param score Object ScoreTetris
	 */
	public static void deleteScore(Context context, ScoreTetris score){
		
		String sqlQuery = "DELETE FROM Scores WHERE idScore = " + score.getIdScore() + "; ";
		
		SQLiteDatabase db = ScoreDB.getInstaceScoreDb(context);
		db.execSQL(sqlQuery);
		db.close();
		
	}
	
	
	/**
	 * 
	 * Returns a list of game's scores order by score
	 * 
	 * @param context Context of activity
	 * @return List of objects ScoreTetris
	 * @throws ParseException
	 */
	public static List<ScoreTetris> getScores(Context context) throws ParseException{
		
		List<ScoreTetris> scores = new ArrayList<ScoreTetris>();
		String sqlQuery = "SELECT * FROM Scores ORDER BY score DESC; ";
		
		SQLiteDatabase db = ScoreDB.getInstaceScoreDb(context);
		Cursor c = db.rawQuery(sqlQuery, null);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		
		if (c.moveToFirst()){
			do {
		          scores.add(new ScoreTetris(
		        		  c.getInt(0), 
		        		  sdf.parse(c.getString(1)), 
		        		  c.getInt(2), 
		        		  c.getInt(3), 
		        		  c.getInt(4), 
		        		  c.getInt(5)
	        		  )
		          );
		     } while(c.moveToNext());
		} 

		db.close();
		
		return scores;
		
	}
	
	
	/**
	 * 
	 * Returns the game's score with the minimum score. If there are two game's score 
	 * with the same score that is the minimum, returns the first stored.
	 * 
	 * @param context Context of activity
	 * @return Object ScoreTetris
	 * @throws ParseException
	 */
	public static ScoreTetris getMinimumScore(Context context) throws ParseException{
		
		ScoreTetris score = null;
		String sqlQuery = "SELECT * FROM Scores WHERE Score = (SELECT Min(Score) FROM Scores) LIMIT 1; ";
		
		SQLiteDatabase db = ScoreDB.getInstaceScoreDb(context);
		Cursor c = db.rawQuery(sqlQuery, null);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
		
		if (c.moveToFirst()){
          score = new ScoreTetris(
        		  c.getInt(0), 
        		  sdf.parse(c.getString(1)), 
        		  c.getInt(2), 
        		  c.getInt(3), 
        		  c.getInt(4), 
        		  c.getInt(5)
          );
		} 

		db.close();
		
		return score;
		
	}
	
	/**
	 * 
	 * Returns the count of game's scores stored in the database.
	 * 
	 * @param context Context of activity
	 * @return Count of scores
	 */
	public static int getCountScores(Context context){
		
		String sqlQuery = "SELECT COUNT(*) FROM Scores; ";
		
		SQLiteDatabase db = ScoreDB.getInstaceScoreDb(context);
		Cursor c = db.rawQuery(sqlQuery, null);
		
		int count = 0;
		if (c.moveToFirst()){
          count = c.getInt(0);
		} 

		db.close();
		
		return count;
		
	}
	
	
	
}
