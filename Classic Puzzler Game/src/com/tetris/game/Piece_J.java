package com.tetris.game;

/**
 *
 * Class that represent the J piece of the tetris's game
 *
 *         ____            ____                      ____ ____                 
 *    FREE|b1  |FREE      |b4  |FREE FREE       FREE|b3  |b4  |      FREE FREE FREE
 *        |____|          |____|____ ____           |____|____|      ____ ____ ____
 *    FREE|b2+ |FREE      |b3  |b2+ |b1  |      FREE|b2+ |FREE      |b1  |b2+ |b3  |
 *    ____|____|          |____|____|____|          |____|          |____|____|____|
 *   |b4  |b3  |FREE       FREE FREE FREE       FREE|b1  |FREE       FREE FREE|b4  |
 *   |____|____|                                    |____|                    |____|
 *    
 *    Rot0Degrees           Rot90Degrees         Rot180Degrees        Rot270Degrees
 *
 *	+ -> block of rotation
 *
 * @author Diego García Martín
 */
public class Piece_J extends Piece {

	public static final String colorJ = "#0000FF";			// Blue
	public static final String marginColorJ = "#000000";	// black
	
	/**
	 * 
	 * Constructs a Piece_J object with the specified arguments. This object represent the J piece of tetris's game.
	 * It uses the default colors: blue of filler and black for margin.
	 * 
	 * @param b1 Block 1
	 * @param b2 Block 2
	 * @param b3 Block 3
	 * @param b4 Block 4
	 * @param board Tetris's game
	 */
    public Piece_J(Block b1, Block b2, Block b3, Block b4, Tetris board) {
    	super(b1, b2, b3, b4,colorJ,marginColorJ, board);
    }
    
    /**
	 * 
	 * Constructs a Piece_J object with the specified arguments. This object represent the J piece of tetris's game.
	 * 
	 * @param b1 Block 1
	 * @param b2 Block 2
	 * @param b3 Block 3
	 * @param b4 Block 4
	 * @param color Color of piece
	 * @param marginColor Margin's color of piece
	 * @param board Tetris's game
	 */
    public Piece_J(Block b1, Block b2, Block b3, Block b4, Tetris board, String color, String marginColor) {
    	super(b1, b2, b3, b4,color,marginColor, board);
    }

    @Override
    public boolean rotate() {
    	
    	if (rotation == EnumPieceRotation.Rot0Degrees) {

    		if(!board.isBlockBusy(b2.getRow(), b2.getColumn()+1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()-1) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()-1) && !board.isBlockBusy(b2.getRow()+1, b2.getColumn()+1)){
    			// Space free in both sides
    			
    			b1.freeBlock();
    			b3.freeBlock();
    			b4.freeBlock();
    			b1 = board.getBlock(b2.getRow(), b2.getColumn()+1);
    			b3 = board.getBlock(b2.getRow(), b2.getColumn()-1);
    			b4 = board.getBlock(b2.getRow()-1, b2.getColumn()-1);
    			b1.fillBlock(color, marginColor);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot90Degrees;
    			generateShadow();
    			return true;
    		} else if(!board.isBlockBusy(b2.getRow(), b2.getColumn()-1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()-2) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()-2)){
    			// Free space on the left side only
    			
    			b1.freeBlock();
    			b3.freeBlock();
    			b4.freeBlock();
    			b1 = b2;
    			b2 = board.getBlock(b1.getRow(), b1.getColumn()-1);
    			b3 = board.getBlock(b1.getRow(), b1.getColumn()-2);
    			b4 = board.getBlock(b1.getRow()-1, b1.getColumn()-2);
    			b2.fillBlock(color, marginColor);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot90Degrees;
    			generateShadow();
    			return true;
    		}
    		
		} else if (rotation == EnumPieceRotation.Rot90Degrees) {
			
			if(!board.isBlockBusy(b2.getRow()+1, b2.getColumn()) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()) && !board.isBlockBusy(b2.getRow()+1, b2.getColumn()+1) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()+1)){
				b1.freeBlock();
    			b3.freeBlock();
    			b4.freeBlock();
    			b1 = board.getBlock(b2.getRow()+1, b2.getColumn());
    			b3 = board.getBlock(b2.getRow()-1, b2.getColumn());
    			b4 = board.getBlock(b2.getRow()-1, b2.getColumn()+1);
    			b1.fillBlock(color, marginColor);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot180Degrees;
    			generateShadow();
    			return true;
    		}
			
		} else if (rotation == EnumPieceRotation.Rot180Degrees) {
			
			if(!board.isBlockBusy(b2.getRow(), b2.getColumn()+1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()-1) && !board.isBlockBusy(b2.getRow()+1, b2.getColumn()+1) && !board.isBlockBusy(b2.getRow()+1, b2.getColumn()-1)){
				// Space free in both sides
				
				b1.freeBlock();
    			b3.freeBlock();
    			b4.freeBlock();
    			b1 = board.getBlock(b2.getRow(), b2.getColumn()-1);
    			b3 = board.getBlock(b2.getRow(), b2.getColumn()+1);
    			b4 = board.getBlock(b2.getRow()+1, b2.getColumn()+1);
    			b1.fillBlock(color, marginColor);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot270Degrees;
    			generateShadow();
    			return true;
    		} else if(!board.isBlockBusy(b2.getRow(), b2.getColumn()+1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()+2) && !board.isBlockBusy(b2.getRow()+1, b2.getColumn()+2)){
    			// Free space on the right side only
				
    			b1.freeBlock();
    			b3.freeBlock();
    			b4.freeBlock();
				b1 = b2;
				b2 = board.getBlock(b1.getRow(), b1.getColumn()+1);
				b3 = board.getBlock(b1.getRow(), b1.getColumn()+2);
				b4 = board.getBlock(b1.getRow()+1, b1.getColumn()+2);
				b2.fillBlock(color, marginColor);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot270Degrees;
    			generateShadow();
    			return true;
    		}
			
		} else if (rotation == EnumPieceRotation.Rot270Degrees) {

			if(!board.isBlockBusy(b2.getRow()+1, b2.getColumn()) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()) && !board.isBlockBusy(b2.getRow()+1, b2.getColumn()-1) && !board.isBlockBusy(b2.getRow()-1, b2.getColumn()-1)){
				b1.freeBlock();
    			b3.freeBlock();
    			b4.freeBlock();
    			b1 = board.getBlock(b2.getRow()-1, b2.getColumn());
    			b3 = board.getBlock(b2.getRow()+1, b2.getColumn());
    			b4 = board.getBlock(b2.getRow()+1, b2.getColumn()-1);
    			b1.fillBlock(color, marginColor);
    			b3.fillBlock(color, marginColor);
    			b4.fillBlock(color, marginColor);
    			rotation = EnumPieceRotation.Rot0Degrees;
    			generateShadow();
    			return true;
    		}
			
		}

		return false;
    
    }

    @Override
    public boolean moveRight() {
    	if (rotation == EnumPieceRotation.Rot0Degrees) {

    		if(!board.isBlockBusy(b1.getRow(), b1.getColumn()+1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()+1) && !board.isBlockBusy(b3.getRow(), b3.getColumn()+1)){
				b4.freeBlock();
				b4 = b3;
				b3 = board.getBlock(b3.getRow(), b3.getColumn()+1);
				b3.fillBlock(color, marginColor);
				b2.freeBlock();
				b2 = board.getBlock(b2.getRow(), b2.getColumn()+1);
				b2.fillBlock(color, marginColor);
				b1.freeBlock();
				b1 = board.getBlock(b1.getRow(), b1.getColumn()+1);
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
    		
		} else if (rotation == EnumPieceRotation.Rot90Degrees) {
			
			if(!board.isBlockBusy(b1.getRow(), b1.getColumn()+1) && !board.isBlockBusy(b4.getRow(), b4.getColumn()+1)){
				b4.freeBlock();
				b4 = board.getBlock(b4.getRow(), b4.getColumn()+1);
				b4.fillBlock(color, marginColor);
				b3.freeBlock();
				b3 = b2;
				b2 = b1;
				b1 = board.getBlock(b1.getRow(), b1.getColumn()+1);
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
			
		} else if (rotation == EnumPieceRotation.Rot180Degrees) {
			
			if(!board.isBlockBusy(b4.getRow(), b4.getColumn()+1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()+1) && !board.isBlockBusy(b1.getRow(), b1.getColumn()+1)){
				b3.freeBlock();
				b3 = b4;
				b4 = board.getBlock(b4.getRow(), b4.getColumn()+1);
				b4.fillBlock(color, marginColor);
				b2.freeBlock();
				b2 = board.getBlock(b2.getRow(), b2.getColumn()+1);
				b2.fillBlock(color, marginColor);
				b1.freeBlock();
				b1 = board.getBlock(b1.getRow(), b1.getColumn()+1);
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
			
		} else if (rotation == EnumPieceRotation.Rot270Degrees) {

			if(!board.isBlockBusy(b3.getRow(), b3.getColumn()+1) && !board.isBlockBusy(b4.getRow(), b4.getColumn()+1)){
    			b1.freeBlock();
    			b1 = b2;
    			b2 = b3;
    			b3 = board.getBlock(b3.getRow(), b3.getColumn()+1);
    			b3.fillBlock(color, marginColor);
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
    public boolean moveLeft() {
    	if (rotation == EnumPieceRotation.Rot0Degrees) {

    		if(!board.isBlockBusy(b4.getRow(), b4.getColumn()-1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()-1) && !board.isBlockBusy(b1.getRow(), b1.getColumn()-1)){
				b3.freeBlock();
				b3 = b4;
				b4 = board.getBlock(b4.getRow(), b4.getColumn()-1);
				b4.fillBlock(color, marginColor);
				b2.freeBlock();
				b2 = board.getBlock(b2.getRow(), b2.getColumn()-1);
				b2.fillBlock(color, marginColor);
				b1.freeBlock();
				b1 = board.getBlock(b1.getRow(), b1.getColumn()-1);
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
    		
		} else if (rotation == EnumPieceRotation.Rot90Degrees) {
			
			if(!board.isBlockBusy(b3.getRow(), b3.getColumn()-1) && !board.isBlockBusy(b4.getRow(), b4.getColumn()-1)){
    			b1.freeBlock();
    			b1 = b2;
    			b2 = b3;
    			b3 = board.getBlock(b3.getRow(), b3.getColumn()-1);
    			b3.fillBlock(color, marginColor);
    			b4.freeBlock();
    			b4 = board.getBlock(b4.getRow(), b4.getColumn()-1);
    			b4.fillBlock(color, marginColor);
    			generateShadow();
    			return true;
    		}
			
		} else if (rotation == EnumPieceRotation.Rot180Degrees) {
			
			if(!board.isBlockBusy(b1.getRow(), b1.getColumn()-1) && !board.isBlockBusy(b2.getRow(), b2.getColumn()-1) && !board.isBlockBusy(b3.getRow(), b3.getColumn()-1)){
				b4.freeBlock();
				b4 = b3;
				b3 = board.getBlock(b3.getRow(), b3.getColumn()-1);
				b3.fillBlock(color, marginColor);
				b2.freeBlock();
				b2 = board.getBlock(b2.getRow(), b2.getColumn()-1);
				b2.fillBlock(color, marginColor);
				b1.freeBlock();
				b1 = board.getBlock(b1.getRow(), b1.getColumn()-1);
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
			
		} else if (rotation == EnumPieceRotation.Rot270Degrees) {

			if(!board.isBlockBusy(b1.getRow(), b1.getColumn()-1) && !board.isBlockBusy(b4.getRow(), b4.getColumn()-1)){
				b4.freeBlock();
				b4 = board.getBlock(b4.getRow(), b4.getColumn()-1);
				b4.fillBlock(color, marginColor);
				b3.freeBlock();
				b3 = b2;
				b2 = b1;
				b1 = board.getBlock(b1.getRow(), b1.getColumn()-1);
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
			
		}

		return false;
    }

    @Override
    public boolean moveDown() {
    	if (rotation == EnumPieceRotation.Rot0Degrees) {

    		if(!board.isBlockBusy(b3.getRow()+1, b3.getColumn()) && !board.isBlockBusy(b4.getRow()+1, b4.getColumn())){
    			b1.freeBlock();
    			b1 = b2;
    			b2 = b3;
    			b3 = board.getBlock(b3.getRow()+1, b3.getColumn());
    			b3.fillBlock(color, marginColor);
    			b4.freeBlock();
    			b4 = board.getBlock(b4.getRow()+1, b4.getColumn());
    			b4.fillBlock(color, marginColor);
    			generateShadow();
    			return true;
    		}
    		
		} else if (rotation == EnumPieceRotation.Rot90Degrees) {
			
			if(!board.isBlockBusy(b1.getRow()+1, b1.getColumn()) && !board.isBlockBusy(b2.getRow()+1, b2.getColumn()) && !board.isBlockBusy(b3.getRow()+1, b3.getColumn())){
				b4.freeBlock();
				b4 = b3;
				b3 = board.getBlock(b3.getRow()+1, b3.getColumn());
				b3.fillBlock(color, marginColor);
				b2.freeBlock();
				b2 = board.getBlock(b2.getRow()+1, b2.getColumn());
				b2.fillBlock(color, marginColor);
				b1.freeBlock();
				b1 = board.getBlock(b1.getRow()+1, b1.getColumn());
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
			
		} else if (rotation == EnumPieceRotation.Rot180Degrees) {
			
			if(!board.isBlockBusy(b1.getRow()+1, b1.getColumn()) && !board.isBlockBusy(b4.getRow()+1, b4.getColumn())){
				b4.freeBlock();
				b4 = board.getBlock(b4.getRow()+1, b4.getColumn());
				b4.fillBlock(color, marginColor);
				b3.freeBlock();
				b3 = b2;
				b2 = b1;
				b1 = board.getBlock(b1.getRow()+1, b1.getColumn());
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
			
		} else if (rotation == EnumPieceRotation.Rot270Degrees) {

			if(!board.isBlockBusy(b4.getRow()+1, b4.getColumn()) && !board.isBlockBusy(b2.getRow()+1, b2.getColumn()) && !board.isBlockBusy(b1.getRow()+1, b1.getColumn())){
				b3.freeBlock();
				b3 = b4;
				b4 = board.getBlock(b4.getRow()+1, b4.getColumn());
				b4.fillBlock(color, marginColor);
				b2.freeBlock();
				b2 = board.getBlock(b2.getRow()+1, b2.getColumn());
				b2.fillBlock(color, marginColor);
				b1.freeBlock();
				b1 = board.getBlock(b1.getRow()+1, b1.getColumn());
				b1.fillBlock(color, marginColor);
				generateShadow();
				return true;
			}
			
		}

		return false;
    }

	@Override
	protected boolean moveDownShadow() {
    	if (rotation == EnumPieceRotation.Rot0Degrees) {

    		if(!board.isBlockBusy(b3Shadow.getRow()+1, b3Shadow.getColumn()) && !board.isBlockBusy(b4Shadow.getRow()+1, b4Shadow.getColumn())){
    			b1Shadow = b2Shadow;
    			b2Shadow = b3Shadow;
    			b3Shadow = board.getBlock(b3Shadow.getRow()+1, b3Shadow.getColumn());
    			b4Shadow = board.getBlock(b4Shadow.getRow()+1, b4Shadow.getColumn());
    			return true;
    		}
    		
		} else if (rotation == EnumPieceRotation.Rot90Degrees) {
			
			if(!board.isBlockBusy(b1Shadow.getRow()+1, b1Shadow.getColumn()) && !board.isBlockBusy(b2Shadow.getRow()+1, b2Shadow.getColumn()) && !board.isBlockBusy(b3Shadow.getRow()+1, b3Shadow.getColumn())){
				b4Shadow = b3Shadow;
				b3Shadow = board.getBlock(b3Shadow.getRow()+1, b3Shadow.getColumn());
				b2Shadow = board.getBlock(b2Shadow.getRow()+1, b2Shadow.getColumn());
				b1Shadow = board.getBlock(b1Shadow.getRow()+1, b1Shadow.getColumn());
				return true;
			}
			
		} else if (rotation == EnumPieceRotation.Rot180Degrees) {
			
			if(!board.isBlockBusy(b1Shadow.getRow()+1, b1Shadow.getColumn()) && !board.isBlockBusy(b4Shadow.getRow()+1, b4Shadow.getColumn())){
				b4Shadow = board.getBlock(b4Shadow.getRow()+1, b4Shadow.getColumn());
				b3Shadow = b2Shadow;
				b2Shadow = b1Shadow;
				b1Shadow = board.getBlock(b1Shadow.getRow()+1, b1Shadow.getColumn());
				return true;
			}
			
		} else if (rotation == EnumPieceRotation.Rot270Degrees) {

			if(!board.isBlockBusy(b4Shadow.getRow()+1, b4Shadow.getColumn()) && !board.isBlockBusy(b2Shadow.getRow()+1, b2Shadow.getColumn()) && !board.isBlockBusy(b1Shadow.getRow()+1, b1Shadow.getColumn())){
				b3Shadow = b4Shadow;
				b4Shadow = board.getBlock(b4Shadow.getRow()+1, b4Shadow.getColumn());
				b2Shadow = board.getBlock(b2Shadow.getRow()+1, b2Shadow.getColumn());
				b1Shadow = board.getBlock(b1Shadow.getRow()+1, b1Shadow.getColumn());
				return true;
			}
			
		}

		return false;
	}
    
}
