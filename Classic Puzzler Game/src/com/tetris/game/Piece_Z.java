/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tetris.game;

/**
 *
 * Class that represent the Z piece of the tetris's game
 * 
 *       ____ ____                    ____                               ____
 *      |b1  |b2  |FREE     FREE FREE|b1  |     FREE FREE FREE      FREE|b1  |FREE
 *      |____|____|____          ____|____|     ____ ____           ____|____|
 *       FREE|b3+ |b4  |    FREE|b3+ |b2  |    |b1  |b2+ |FREE     |b3  |b2+ |FREE
 *           |____|____|        |____|____|    |____|____|____     |____|____|
 *       FREE FREE FREE     FREE|b4  |FREE      FREE|b3  |b4  |    |b4  |FREE FREE
 *                              |____|              |____|____|    |____|
 *                          
 *        Rot0Degrees         Rot90Degrees        Rot180Degrees     Rot270Degrees
 *    
 *           
 *  + -> block of rotation         
 *
 * @author Diego García Martín 
 */
public class Piece_Z extends Piece {

	public static final String colorZ = "#FF0000";			// red
	public static final String marginColorZ = "#000000";	// black
	
	/**
	 * 
	 * Constructs a Piece_Z object with the specified arguments. This object represent the Z piece of tetris's game.
	 * It uses the default colors: red of filler and black for margin.
	 * 
	 * @param b1 Block 1
	 * @param b2 Block 2
	 * @param b3 Block 3
	 * @param b4 Block 4
	 * @param board Tetris's game
	 */
    public Piece_Z(Block b1, Block b2, Block b3, Block b4, Tetris board) {
        super(b1, b2, b3, b4,colorZ,marginColorZ, board);
    }
    
    /**
	 * 
	 * Constructs a Piece_Z object with the specified arguments. This object represent the Z piece of tetris's game.
	 * 
	 * @param b1 Block 1
	 * @param b2 Block 2
	 * @param b3 Block 3
	 * @param b4 Block 4
	 * @param color Color of piece
	 * @param marginColor Margin's color of piece
	 * @param board Tetris's game
	 */
    public Piece_Z(Block b1, Block b2, Block b3, Block b4, Tetris board, String color, String marginColor) {
        super(b1, b2, b3, b4,color,marginColor, board);
    }
    
    @Override
    public synchronized boolean rotate() {

    	if(rotation == EnumPieceRotation.Rot0Degrees){
    		
    		if(!board.isBlockBusy(b3.getRow()-1, b3.getColumn()+1) && !board.isBlockBusy(b3.getRow()+1, b3.getColumn()+1) && !board.isBlockBusy(b3.getRow()+1, b3.getColumn())){
    			b1.freeBlock();
    			b2.freeBlock();
    			b4.freeBlock();
    			b1 = board.getBlock(b3.getRow()-1, b3.getColumn()+1);
    			b2 = board.getBlock(b3.getRow(), b3.getColumn()+1);
    			b4 = board.getBlock(b3.getRow()+1, b3.getColumn());
    			b1.fillBlock(color, marginColor);
    			b2.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot90Degrees;
    			generateShadow();
    			return true;
    		}
    		
    	} else if(rotation == EnumPieceRotation.Rot90Degrees){
    		
    		if(!board.isBlockBusy(b3.getRow(), b3.getColumn()-1) && !board.isBlockBusy(b3.getRow()+1, b3.getColumn()-1) && !board.isBlockBusy(b3.getRow()+1, b3.getColumn()+1)){
    			// Space free in both sides
    			
    			b2.freeBlock();
    			b2 = b3;
    			b1.freeBlock();
    			b4.freeBlock();
    			b1 = board.getBlock(b2.getRow(), b2.getColumn()-1);
    			b3 = board.getBlock(b2.getRow()+1, b2.getColumn());
    			b4 = board.getBlock(b2.getRow()+1, b2.getColumn()+1);
    			b1.fillBlock(color, marginColor);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot180Degrees;
    			generateShadow();
    			return true;
    		} else if(!board.isBlockBusy(b3.getRow()+1, b3.getColumn()+1) && !board.isBlockBusy(b3.getRow(), b3.getColumn()+2) && !board.isBlockBusy(b3.getRow()+1, b3.getColumn()+2)){
    			// Free space on the right side only
    			
    			b1.freeBlock();
    			b4.freeBlock();
    			b1 = b3;
    			b3 = board.getBlock(b2.getRow()+1, b2.getColumn());
    			b4 = board.getBlock(b2.getRow()+1, b2.getColumn()+1);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot180Degrees;
    			generateShadow();
    			return true;
    		}
    		
    	} else if(rotation == EnumPieceRotation.Rot180Degrees){
    		
    		if(!board.isBlockBusy(b2.getRow()+1, b2.getColumn()-1) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()-1) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn())){
    			b1.freeBlock();
    			b3.freeBlock();
    			b4.freeBlock();
    			b1 = board.getBlock(b2.getRow()-1, b2.getColumn());
    			b3 = board.getBlock(b2.getRow(), b2.getColumn()-1);
    			b4 = board.getBlock(b2.getRow()+1, b2.getColumn()-1);
    			b1.fillBlock(color, marginColor);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot270Degrees;
    			generateShadow();
    			return true;
    		}
    		
    	} else if(rotation == EnumPieceRotation.Rot270Degrees){
    		
    		if(!board.isBlockBusy(b2.getRow()-1, b2.getColumn()-1) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()+1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()+1)){
    			// Space free in both sides
    			
    			b3.freeBlock();
    			b3 = b2;
    			b1.freeBlock();
    			b4.freeBlock();
    			b1 = board.getBlock(b3.getRow()-1, b3.getColumn()-1);
    			b2 = board.getBlock(b3.getRow()-1, b3.getColumn());
    			b4 = board.getBlock(b3.getRow(), b3.getColumn()+1);
    			b1.fillBlock(color, marginColor);
    			b2.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot0Degrees;
    			generateShadow();
    			return true;
    		} else if(!board.isBlockBusy(b2.getRow()-1, b2.getColumn()-1) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()-2) && !board.isBlockBusy(b2.getRow(), b2.getColumn()-2)){
    			// Free space on the left side only
    			
    			b1.freeBlock();
    			b4.freeBlock();
    			b4 = b2;
    			b2 = board.getBlock(b3.getRow()-1, b3.getColumn());
    			b1 = board.getBlock(b3.getRow()-1, b3.getColumn()-1);
    			b1.fillBlock(color, marginColor);
    			b2.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot0Degrees;
    			generateShadow();
    			return true;
    		}
    		
    	}
    	
    	return false;    
	}

    @Override
    public synchronized boolean moveRight() {

    	if(rotation == EnumPieceRotation.Rot0Degrees || rotation == EnumPieceRotation.Rot180Degrees){
    		
    		if(!board.isBlockBusy(b2.getRow(), b2.getColumn()+1) && !board.isBlockBusy(b4.getRow(), b4.getColumn()+1)){
    			b1.freeBlock();
    			b1 = b2;
    			b3.freeBlock();
    			b3 = b4;
    			b2 = board.getBlock(b2.getRow(), b2.getColumn()+1);
    			b2.fillBlock(color, marginColor);
    			b4 = board.getBlock(b4.getRow(), b4.getColumn()+1);
    			b4.fillBlock(color, marginColor);
    			generateShadow();
    			return true;
    		}
    		
    	} else if(rotation == EnumPieceRotation.Rot90Degrees || rotation == EnumPieceRotation.Rot270Degrees){
    		
    		if(!board.isBlockBusy(b1.getRow(), b1.getColumn()+1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()+1) && !board.isBlockBusy(b4.getRow(), b4.getColumn()+1)){
    			b3.freeBlock();
    			b3 = b2;
    			b1.freeBlock();
    			b1 = board.getBlock(b1.getRow(), b1.getColumn()+1);
    			b1.fillBlock(color, marginColor);
    			b2 = board.getBlock(b2.getRow(), b2.getColumn()+1);
    			b2.fillBlock(color, marginColor);
    			b4.freeBlock();
    			b4 = board.getBlock(b4.getRow(), b4.getColumn()+1);
    			b4.fillBlock(color, marginColor);
    			generateShadow();
    			return true;
    		}
    		
    	}
    	
    	return false;    
	}

    @Override
    public synchronized boolean moveLeft() {

    	if(rotation == EnumPieceRotation.Rot0Degrees || rotation == EnumPieceRotation.Rot180Degrees){
    		
    		if(!board.isBlockBusy(b1.getRow(), b1.getColumn()-1) && !board.isBlockBusy(b3.getRow(), b3.getColumn()-1)){
    			b2.freeBlock();
    			b2 = b1;
    			b4.freeBlock();
    			b4 = b3;
    			b1 = board.getBlock(b1.getRow(), b1.getColumn()-1);
    			b1.fillBlock(color, marginColor);
    			b3 = board.getBlock(b3.getRow(), b3.getColumn()-1);
    			b3.fillBlock(color, marginColor);
    			generateShadow();
    			return true;
    		}
    		
    	} else if(rotation == EnumPieceRotation.Rot90Degrees || rotation == EnumPieceRotation.Rot270Degrees){
    		
    		if(!board.isBlockBusy(b1.getRow(), b1.getColumn()-1) && !board.isBlockBusy(b3.getRow(), b3.getColumn()-1) && !board.isBlockBusy(b4.getRow(), b4.getColumn()-1)){
    			b2.freeBlock();
    			b2 = b3;
    			b1.freeBlock();
    			b1 = board.getBlock(b1.getRow(), b1.getColumn()-1);
    			b1.fillBlock(color, marginColor);
    			b3 = board.getBlock(b3.getRow(), b3.getColumn()-1);
    			b3.fillBlock(color, marginColor);
    			b4.freeBlock();
    			b4 = board.getBlock(b4.getRow(), b4.getColumn()-1);
    			b4.fillBlock(color, marginColor);
    			generateShadow();
    			return true;
    		}
    		
    	}
    	
    	return false;    
	}

    @Override
    public synchronized boolean moveDown() {
    	
    	if(rotation == EnumPieceRotation.Rot0Degrees || rotation == EnumPieceRotation.Rot180Degrees){
    		
    		if(!board.isBlockBusy(b1.getRow()+1, b1.getColumn()) && !board.isBlockBusy(b3.getRow()+1, b3.getColumn()) && !board.isBlockBusy(b4.getRow()+1, b4.getColumn())){
    			b2.freeBlock();
    			b2 = b3;
    			b3 = board.getBlock(b3.getRow()+1, b3.getColumn());
    			b3.fillBlock(color, marginColor);
    			b1.freeBlock();
    			b1 = board.getBlock(b1.getRow()+1, b1.getColumn());
    			b1.fillBlock(color, marginColor);
    			b4.freeBlock();
    			b4 = board.getBlock(b4.getRow()+1, b4.getColumn());
    			b4.fillBlock(color, marginColor);
    			generateShadow();
    			return true;
    		}
    		
    	} else if(rotation == EnumPieceRotation.Rot90Degrees || rotation == EnumPieceRotation.Rot270Degrees){
    		
    		if(!board.isBlockBusy(b2.getRow()+1, b2.getColumn()) && !board.isBlockBusy(b4.getRow()+1, b4.getColumn())){
    			b1.freeBlock();
    			b1 = b2;
    			b3.freeBlock();
    			b3 = b4;
    			b2 = board.getBlock(b2.getRow()+1, b2.getColumn());
    			b2.fillBlock(color, marginColor);
    			b4 = board.getBlock(b4.getRow()+1, b4.getColumn());
    			b4.fillBlock(color, marginColor);
    			generateShadow();
    			return true;
    		}
    		
    	}
    	
    	return false;
    }

	@Override
	protected boolean moveDownShadow() {
    	if(rotation == EnumPieceRotation.Rot0Degrees || rotation == EnumPieceRotation.Rot180Degrees){
    		
    		if(!board.isBlockBusy(b1Shadow.getRow()+1, b1Shadow.getColumn()) && !board.isBlockBusy(b3Shadow.getRow()+1, b3Shadow.getColumn()) && !board.isBlockBusy(b4Shadow.getRow()+1, b4Shadow.getColumn())){
    			b2Shadow = b3Shadow;
    			b3Shadow = board.getBlock(b3Shadow.getRow()+1, b3Shadow.getColumn());
    			b1Shadow = board.getBlock(b1Shadow.getRow()+1, b1Shadow.getColumn());
    			b4Shadow = board.getBlock(b4Shadow.getRow()+1, b4Shadow.getColumn());
    			return true;
    		}
    		
    	} else if(rotation == EnumPieceRotation.Rot90Degrees || rotation == EnumPieceRotation.Rot270Degrees){
    		
    		if(!board.isBlockBusy(b2Shadow.getRow()+1, b2Shadow.getColumn()) && !board.isBlockBusy(b4Shadow.getRow()+1, b4Shadow.getColumn())){
    			b1Shadow = b2Shadow;
    			b3Shadow = b4Shadow;
    			b2Shadow = board.getBlock(b2Shadow.getRow()+1, b2Shadow.getColumn());
    			b4Shadow = board.getBlock(b4Shadow.getRow()+1, b4Shadow.getColumn());
    			return true;
    		}
    		
    	}
    	
    	return false;
	}
    
}
