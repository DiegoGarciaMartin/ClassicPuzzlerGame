package com.tetris.scoreManager;

import java.util.Date;

/**
 * 
 * Class that represent a game's score tetris.
 * 
 * @author Diego García Martín
 *
 */
public class ScoreTetris {

	private int idScore;
	private Date date;
	private int score;
	private int levelBegin;
	private int levelEnd;
	private int lines;


	/**
	 * 
	 * Constructs a ScoreTetris object with the specified arguments. This object represent a game's score tetris.
	 * 
	 * @param idScore Identifier of score
	 * @param date Date of the game
	 * @param score Score of the game
	 * @param levelBegin Level that the game began
	 * @param levelEnd Level that the game end
	 * @param lines Lines of the game
	 */
	public ScoreTetris(int idScore, Date date, int score, int levelBegin, int levelEnd, int lines) {
		this.idScore = idScore;
		this.date = date;
		this.score = score;
		this.levelBegin = levelBegin;
		this.levelEnd = levelEnd;
		this.lines = lines;
	}
	
	
	/**
	 * 
	 * Returns the identifier of score 
	 * 
	 * @return identifier of score 
	 */
	public int getIdScore() {
		return idScore;
	}


	/**
	 * 
	 * Sets the identifier of score 
	 * 
	 * @param idScore identifier of score 
	 */
	public void setIdScore(int idScore) {
		this.idScore = idScore;
	}


	/**
	 * 
	 * Returns the date of the game
	 * 
	 * @return date of the game
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * 
	 * Sets the date of the game
	 * 
	 * @param date of the game
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * 
	 * Returns the score of the game
	 * 
	 * @return score of the game
	 */
	public int getScore() {
		return score;
	}


	/**
	 * 
	 * Sets the score of the game
	 * 
	 * @param score score of the game
	 */
	public void setScore(int score) {
		this.score = score;
	}


	/**
	 * 
	 * Returns the level that the game began 
	 * 
	 * @return level that the game began 
	 */
	public int getLevelBegin() {
		return levelBegin;
	}


	/**
	 * 
	 * Sets the level that the game began 
	 * 
	 * @param levelBegin level that the game began 
	 */
	public void setLevelBegin(int levelBegin) {
		this.levelBegin = levelBegin;
	}


	/**
	 * 
	 * Returns the level that the game end
	 * 
	 * @return level that the game end
	 */
	public int getLevelEnd() {
		return levelEnd;
	}


	/**
	 * 
	 * Sets the level that the game end
	 * 
	 * @param levelEnd level that the game end
	 */
	public void setLevelEnd(int levelEnd) {
		this.levelEnd = levelEnd;
	}


	/**
	 * 
	 * Returns the lines of the game
	 * 
	 * @return lines of the game
	 */
	public int getLines() {
		return lines;
	}


	/**
	 * 
	 * Sets the lines of the game
	 * 
	 * @param lines lines of the game
	 */
	public void setLines(int lines) {
		this.lines = lines;
	}

}
