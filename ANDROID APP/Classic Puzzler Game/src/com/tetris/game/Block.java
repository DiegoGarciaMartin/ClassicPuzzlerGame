package com.tetris.game;

/**
 *
 * Class that represent a block of tetris's board. Each piece of tetris occupies four blocks.
 *
 * @author Diego García Martín
 */
public class Block {

    public static final String freeColor = "#000000"; 		// color when block is free -> black
    public static final String marginFreeColor = "#111111"; // margin's color when block is free -> white
    
    private int row;
    private int column;
    private String color;
    private String marginColor;
    private boolean busy;

    /**
     * 
     * Constructs a Block object with the specified arguments. This object represent a block of tetris's board.
     * 
     * @param row block's row
     * @param column block's column
     * @param color block's color
     * @param marginColor block's margin color
     * @param busy is block busy
     */
    public Block(int row, int column, String color, String marginColor, boolean busy) {
        this.row = row;
        this.column = column;
        this.color = color;
        this.marginColor = marginColor;
        this.busy = busy;
    }

    /**
     * 
     * Constructs a Block object with the specified arguments. This object represent a block of tetris's board.
     * 
     * @param row block's row
     * @param column block's column
     */
    public Block(int row, int column) {
        this.row = row;
        this.column = column;
        this.color = freeColor; 
        this.marginColor = marginFreeColor; 
        this.busy = false;
    }

    /**
     * 
     * Fills the block:
     * <ul>
     * 	<li>Sets busy to true</li>
     * 	<li>Sets the color of block</li>
     * 	<li>Sets the margins's color of block</li>
     * </ul>
     * 
     * @param color block's color
     * @param marginColor block's margin color
     */
    public void fillBlock(String color, String marginColor) {
        this.busy = true;
        this.color = color;
        this.marginColor = marginColor;
    }
    
    /**
     * 
     * Fills the block:
     * <ul>
     * 	<li>Sets busy of block</li>
     * 	<li>Sets the color of block</li>
     * 	<li>Sets the margins's color of block</li>
     * </ul>
     * 
     * @param busy is block busy
     * @param color block's color
     * @param marginColor block's margin color
     */
    public void fillBlock(boolean busy, String color, String marginColor) {
        this.busy = busy;
        this.color = color;
        this.marginColor = marginColor;
    }
    
    /**
     * 
     * Frees the block:
     * <ul>
     * 	<li>Marks busy false</li>
     * 	<li>Sets the color to free color</li>
     * 	<li>Sets the margins's color to free margin's color</li>
     * </ul>
     */
    public void freeBlock() {
        this.busy = false;
        this.marginColor = marginFreeColor;
        this.color = freeColor;
    }
    
    /**
     * 
     * Frees the block:
     * <ul>
     * 	<li>Marks busy false</li>
     * 	<li>Sets the color and the margin's color</li>
     * </ul>
     * 
     * @param color block's color
     * @param marginColor block's margin color
     */
    public void freeBlock(String color, String marginColor) {
        this.busy = false;
        this.marginColor = marginColor;
        this.color = color;
    }

	/**
	 * 
	 * Returns the block's row
	 * 
	 * @return block's row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 * Sets the block's row
	 * 
	 * @param row block's row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * 
	 * Returns the block's column
	 * 
	 * @return block's column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 
	 * Sets the block's column
	 * 
	 * @param column block's column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * 
	 * Returns the color of the block in hexadecimal in format '#XXXXXX'
	 * 
	 * @return string with the color in hexadecimal
	 */
	public String getColor() {
		return color;
	}

	/**
	 * 
	 * Returns the margin's color of the block in hexadecimal in format '#XXXXXX'
	 * 
	 * @return string with the margin's color in hexadecimal
	 */
	public String getMarginColor() {
		return marginColor;
	}

	/**
	 * 
	 * Method that indicates if the block is busy
	 * 
	 * @return true if block is busy and false otherwise
	 */
	public boolean isBusy() {
		return busy;
	}

}
