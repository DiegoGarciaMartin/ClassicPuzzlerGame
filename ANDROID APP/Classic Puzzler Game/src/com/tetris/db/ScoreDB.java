package com.tetris.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
/**
 * 
 * Class that represent the database of game's scores of tetris.
 * 
 * @author Diego García Martín
 *
 */
public class ScoreDB extends SQLiteOpenHelper {
 
	private static final String databaseName = "ScoresTetris";
	private static final int databaseVersion = 1;
	
	/**
	 * 
	 * Returns the database's object with the game's score of tetris
	 * 
	 * @param context Context of activity
	 * @return Object SQLiteDatabase
	 */
	public static SQLiteDatabase getInstaceScoreDb(Context context){
		ScoreDB db = new ScoreDB(context);
		return db.getWritableDatabase();
	}
	
    private ScoreDB(Context context) {
        super(context, databaseName, null, databaseVersion);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
    	//Query SQL to creation the table Scores
        String sqlTableScores = 
		  "CREATE TABLE Scores ( "
		+ "		idScore INTEGER PRIMARY KEY, "
		+ "		date INTEGER NOT NULL, "
		+ "		score INTEGER NOT NULL, "
		+ "		levelBegin INTEGER NOT NULL, "
		+ "		levelEnd INTEGER NOT NULL, "
		+ "		lines INTEGER NOT NULL "
		+ "); ";
    	
    	
        //creation of the db
        db.execSQL(sqlTableScores);
        
        
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//detele table scores
		db.execSQL("DROP TABLE IF EXISTS Scores");
		
		//Query SQL to creation the table Scores
        String sqlTableScores = 
		  "CREATE TABLE Scores ( "
		+ "		idScore INTEGER PRIMARY KEY, "
		+ "		date INTEGER NOT NULL, "
		+ "		score INTEGER NOT NULL, "
		+ "		levelBegin INTEGER NOT NULL, "
		+ "		levelEnd INTEGER NOT NULL, "
		+ "		lines INTEGER NOT NULL "
		+ "); ";
    	
    	
        //creation of the db
        db.execSQL(sqlTableScores);
		
	}
 

    
}
