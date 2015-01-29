package com.tetris.game;


/**
 *
 * Class that represent the square piece of the tetris's game
 *    ____ ____
 *   |b1  |b2  |
 *   |____|____|
 *   |b3  |b4  |
 *   |____|____|
 *   
 *   Rot0Degrees
 * 
 * 
 * @author Diego García Martín
 * 
 */
public class PieceSquare extends Piece {

	public static String colorSquare = "#FFFF00";		// yellow
	public static String marginColorSquare = "#000000";	// black
    
	/**
	 * 
	 * Constructs a Piece_Square object with the specified arguments. This object represent the square piece of tetris's game.
	 * It uses the default colors: yellow of filler and black for margin.
	 * 
	 * @param b1 Block 1
	 * @param b2 Block 2
	 * @param b3 Block 3
	 * @param b4 Block 4
	 * @param board Tetris's game
	 */
    public PieceSquare(Block b1, Block b2, Block b3, Block b4, Tetris board) {
        super(b1, b2, b3, b4,colorSquare,marginColorSquare, board);
    }
    
    /**
	 * 
	 * Constructs a Piece_Square object with the specified arguments. This object represent the square piece of tetris's game.
	 * 
	 * @param b1 Block 1
	 * @param b2 Block 2
	 * @param b3 Block 3
	 * @param b4 Block 4
	 * @param color Color of piece
	 * @param marginColor Margin's color of piece
	 * @param board Tetris's game
	 */
    public PieceSquare(Block b1, Block b2, Block b3, Block b4, Tetris board, String color, String marginColor) {
        super(b1, b2, b3, b4,color,marginColor, board);
    }

    @Override
    public synchronized boolean rotate() {
        // This piece is equals in all rotations -> not actions here
    	generateShadow();
    	return true;
    }

    @Override
    public synchronized boolean moveRight() {
        
        // Controls that the cells of the right of c2 and c4 are not busy
        if(!board.isBlockBusy(b2.getRow(), b2.getColumn()+1) && !board.isBlockBusy(b4.getRow(), b4.getColumn()+1)){
            b1.freeBlock();
            b3.freeBlock();
            b1 = b2;
            b3 = b4;
            b2 = board.getBlock(b2.getRow(), b2.getColumn()+1);
            b4 = board.getBlock(b4.getRow(), b4.getColumn()+1);
            b2.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
            generateShadow();
            return true;
        } else {
        	return false;
        }
    }

    @Override
    public synchronized boolean moveLeft() {
        
        // Controls that the cells of the right of c1 and c3 are not busy
        if(!board.isBlockBusy(b1.getRow(), b1.getColumn()-1) && !board.isBlockBusy(b3.getRow(), b3.getColumn()- 1)){
            b2.freeBlock();
            b4.freeBlock();
            b2 = b1;
            b4 = b3;
            b1 = board.getBlock(b2.getRow(), b2.getColumn()-1);
            b3 = board.getBlock(b4.getRow(), b4.getColumn()-1);
            b1.fillBlock(color, marginColor);
            b3.fillBlock(color, marginColor);
            generateShadow();
            return true;
        } else {
        	return false;
        }
        
    }

    @Override
    public synchronized boolean moveDown() {
    	
        // Controls that the cells down of c3 and c4 are not busy
        if(!board.isBlockBusy(b3.getRow()+1, b3.getColumn()) && !board.isBlockBusy(b4.getRow()+1, b4.getColumn())){
        	b1.freeBlock();
            b2.freeBlock();
            b1 = b3;
            b2 = b4;
            b3 = board.getBlock(b3.getRow()+1, b3.getColumn());
            b4 = board.getBlock(b4.getRow()+1, b4.getColumn());
            b3.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
            generateShadow();
            return true;
        } else {
        	return false;
        }
    
    }

	@Override
	protected boolean moveDownShadow() {
        if(!board.isBlockBusy(b3Shadow.getRow()+1, b3Shadow.getColumn()) && !board.isBlockBusy(b4Shadow.getRow()+1, b4Shadow.getColumn())){
            b1Shadow = b3Shadow;
            b2Shadow = b4Shadow;
            b3Shadow = board.getBlock(b3Shadow.getRow()+1, b3Shadow.getColumn());
            b4Shadow = board.getBlock(b4Shadow.getRow()+1, b4Shadow.getColumn());
            return true;
        } else {
        	return false;
        }
	}
    
}
