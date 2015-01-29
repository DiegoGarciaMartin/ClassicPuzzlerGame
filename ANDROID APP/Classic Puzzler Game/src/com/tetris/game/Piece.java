package com.tetris.game;

/**
 *
 * Class that represent an abstract piece of the tetris's game.
 * 
 * This class contains the properties and methods that all pieces of tetris must have.
 * 
 * 
 * @author Diego García Martín
 * 
 */
public abstract class Piece {

	protected Block b1,b2,b3,b4;
	protected Block b1Shadow,b2Shadow,b3Shadow,b4Shadow;
	protected EnumPieceRotation rotation;
	protected String marginColor;
	protected String color;
	protected Tetris board;
	protected boolean enableShowShadow;

	/**
	 * 
	 * Constructs a Piece object with the specified arguments. This object represent a piece of tetris's game.
	 * 
	 * @param b1 Block 1
	 * @param b2 Block 2
	 * @param b3 Block 3
	 * @param b4 Block 4
	 * @param color Color of piece
	 * @param marginColor Margin's color of piece
	 * @param board Tetris's game
	 */
	public Piece(Block b1, Block b2, Block b3, Block b4, String color, String marginColor, Tetris board) {
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
		this.b4 = b4;
		this.color = color;
		this.marginColor = marginColor;
		this.board = board;
		this.rotation = EnumPieceRotation.Rot0Degrees;
		this.enableShowShadow = true;
		assignBlocksShadow();
		fillBlocks();
		
	}

	
	/**
	 * 
	 * Sets if shows the piece's shadow
	 * 
	 * @param enableShowShadow true if shows the shadow and false otherwise
	 */
	public void setEnableShowShadow(boolean enableShowShadow) {
		this.enableShowShadow = enableShowShadow;
	}

	
	/**
	 * 
	 * Returns the margin color in hexadecimal with the format #XXXXXX
	 * 
	 * @return string with margin color in hexadecimal
	 */
	public String getMarginColor() {
		return marginColor;
	}

	
	/**
	 * 
	 * Returns the color in hexadecimal with the format #XXXXXX
	 * 
	 * @return string with color in hexadecimal
	 */
	public String getColor() {
		return color;
	}
	
	
	/**
	 * Method that fills the blocks of the piece with:
	 *  - the color of piece of filler 
	 * 	- the margin's color of the piece of margin 
	 */
	protected void fillBlocks(){
		b1.fillBlock(color, marginColor);
		b2.fillBlock(color, marginColor);
		b3.fillBlock(color, marginColor);
		b4.fillBlock(color, marginColor);
	}
	
	
	/**
	 * 
	 * Method that indicates if the piece is out of tetris's board (when the piece is put in the invisible rows)
	 * 
	 * @return true if piece is out of tetris's board and false in otherwise
	 */
	public boolean isPieceOutBoard(){
		if(b1.getRow() < board.getNumInvisibleRow() || b2.getRow() < board.getNumInvisibleRow() || b3.getRow() < board.getNumInvisibleRow() || b4.getRow() < board.getNumInvisibleRow()){
			return true;
		} else {
			return false;
		}
	}
	

	/**
	 * Method that rotates 90 degrees the piece
	 * 
	 * @return true if the move has been completed successfully and false
	 *         otherwise
	 */
	public abstract boolean rotate();

	
	/**
	 * Method that moves 1 position to the right the piece
	 * 
	 * @return true if the move has been completed successfully and false
	 *         otherwise
	 */
	public abstract boolean moveRight();

	
	/**
	 * Method that moves 1 position to the left the piece
	 * 
	 * @return true if the move has been completed successfully and false
	 *         otherwise
	 */
	public abstract boolean moveLeft();

	
	/**
	 * Method that moves 1 position to the down the piece
	 * 
	 * @return true if the move has been completed successfully and false
	 *         otherwise
	 */
	public abstract boolean moveDown();
	
	
	
	
	
	/*
	 * 
	 * =============================================================
	 * 
	 * METHODS TO GENERATE PIECE'S SHADOW
	 * 
	 * =============================================================
	 * 
	 */
	
	/**
	 * Method that generates the piece's shadow after any move of the piece
	 */
	protected void generateShadow(){
		freeBlocksShadow();
		if (enableShowShadow){
			assignBlocksShadow();
			moveDownShadowFull();
			fillBlocksShadow();
		}
	}
	
	
	/**
	 * Method that assigns the blocks of the piece to the blocks of the shadow 
	 */
	private void assignBlocksShadow(){
		this.b1Shadow = b1;
		this.b2Shadow = b2;
		this.b3Shadow = b3;
		this.b4Shadow = b4;
	}
	
	
	/**
	 * Method that frees the blocks of the piece's shadow if the blocks not are busy by a piece
	 */
	private void freeBlocksShadow(){
		if(b1Shadow != null && !b1Shadow.isBusy())b1Shadow.freeBlock();
		if(b2Shadow != null && !b2Shadow.isBusy())b2Shadow.freeBlock();
		if(b3Shadow != null && !b3Shadow.isBusy())b3Shadow.freeBlock();
		if(b4Shadow != null && !b4Shadow.isBusy())b4Shadow.freeBlock();
	}
	
	
	/**
	 * Method that fills the blocks of the piece's shadow with:
	 *  - the free color of piece of filler 
	 * 	- the color of the piece of margin 
	 */
	private void fillBlocksShadow(){
		if(b1Shadow != null && !b1Shadow.isBusy())b1Shadow.fillBlock(false, Block.freeColor, this.color);
		if(b2Shadow != null && !b2Shadow.isBusy())b2Shadow.fillBlock(false, Block.freeColor, this.color);
		if(b3Shadow != null && !b3Shadow.isBusy())b3Shadow.fillBlock(false, Block.freeColor, this.color);
		if(b4Shadow != null && !b4Shadow.isBusy())b4Shadow.fillBlock(false, Block.freeColor, this.color);
	}
	
	
	/**
	 * Method that moves 1 position to the down the piece's shadow
	 * 
	 * @return true if the move has been completed successfully and false
	 *         otherwise
	 */
	protected abstract boolean moveDownShadow();
	
	
	/**
	 * Method that moves to the down fully the piece's shadow
	 */
	private void moveDownShadowFull(){
		while(moveDownShadow()){}
	}
	

	/*
	 * =============================================================
	 * END METHODS TO GENERATE PIECE'S SHADOW
	 * =============================================================
	 */

}
