package com.tetris.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;


/**
 *
 * Class that represent the TETRIS game
 * 
 * 
 * -----------------------------------------------------------------------
 * |                                BOARD                                |
 * -----------------------------------------------------------------------
 * 
 *      _ _ _ _ _ _ _ _ _ _ _ _ _
 *  0  |_|_|_|_|_|_|_|_|_|_|_|_|_|  -> Invisible
 *  1  |_|_|_|_|_|_|_|_|_|_|_|_|_|  -> Invisible
 *  2  |_|_|_|_|_|_|_|_|_|_|_|_|_|  -> Invisible
 *  3  |_|_|_|_|_|_|_|_|_|_|_|_|_|  -> Invisible
 *  4  |_|_|_|_|_|_|_|_|_|_|_|_|_|  -> Visible
 *  5  |_|_|_|_|_|_|_|_|_|_|_|_|_|  -> Visible
 *  6  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 *  7  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 *  8  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 *  9  |_|_|_|_|_|_|_|_|_|_|_|_|_| 
 * 10  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 11  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 12  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 13  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 14  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 15  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 16  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 17  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 18  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 19  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 20  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 21  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 22  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 23  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 24  |_|_|_|_|_|_|_|_|_|_|_|_|_|
 * 
 *      0 1 2 3 4 5 6 7 8 9 10 11 12
 * 
 * 
 * > The board is implemented as a list of rows. Each row is a list of blocks.
 * 
 * > Invisible rows -> They are used to place the piece initially
 * 
 * 
 * 
 * -----------------------------------------------------------------------
 * |                              PIECES                                 |
 * -----------------------------------------------------------------------
 * 
 *       _
 *   _ _|_|   -   L piece
 *  |_|_|_|      
 *              
 *   _
 *  |_|_ _    -   J piece
 *  |_|_|_|      
 * 
 *     _ _
 *   _|_|_|   -   S piece
 *  |_|_|        
 * 
 *   _ _
 *  |_|_|_    -   Z piece
 *    |_|_|      
 * 
 *     _
 *   _|_|_    -   T piece
 *  |_|_|_|      
 * 
 *    _ _
 *   |_|_|    -   Square piece
 *   |_|_|       
 * 
 *   _ _ _ _
 *  |_|_|_|_| -   I piece
 * 
 * 
 * 
 *   = = = THE APPEARANCE RATE OF PIECES = = =
 * 
 *  L piece.........11%
 *  J piece.........11%
 *  S piece.........19%
 *  Z piece.........10%
 *  T piece.........19%
 *  Square piece....18%
 *  I piece.........12%
 * 
 * 
 * 
 * 
 * -----------------------------------------------------------------------
 * |                              SCORING                                |
 * -----------------------------------------------------------------------
 *
 *
 *	Level     Points for     Points for     Points for     Points for
 *              1 line         2 lines       3 lines        4 lines
 *
 *    0	          40	         100	       300	          1200
 *    1	          80	         200	       600	          2400
 *    2	          120	         300	       900	          3600
 *    ...
 *    9	          400	         1000	       3000	          12000
 *    n	       40 * (n + 1)	 100 * (n + 1)	 300 * (n + 1)	 1200 * (n + 1)
 * 
 * 
 * 
 * 
 * -----------------------------------------------------------------------
 * |                              LEVELS                                 |
 * -----------------------------------------------------------------------
 * 
 * > The level increases every 10 lines
 * 
 * 
 *     Level         Speed
 * 	
 *      0            0.80s
 *      1            0.72s
 *      2            0.63s
 *      3            0.55s
 *      4            0.47s
 *      5            0.38s
 *      6            0.30s
 *      7            0.22s
 *      8            0.13s
 *      9            0.10s
 *    10-12          0.08s
 *    13-15          0.07s
 *    16-18          0.05s
 *    19-28          0.03s
 *     +29           0.02s
 * 
 * 
 * -----------------------------------------------------------------------
 * 
 * 
 * 
 * @author Diego García Martín
 * 
 */
public class Tetris extends Observable{
	
	private final int numInvisibleRow = 4;
    private int numRow = 25;
    private int numColumns = 13;
    private List<ArrayList<Block>> boardRows; // List of row, each row is an ArrayList of blocks
    private Piece activePiece;
    private boolean enablePieceShadow;
    private boolean increaseLevel;
    private EnumTypePiece nextPiece;
    private int level;
    private int initLevel;
    private int score;
    private int lines;
    
	/**
	 * Constructs a Tetris object with the specified arguments.
	 */
	public Tetris() {
		level = 0;
		lines = 0;
		score = 0;
		enablePieceShadow = true;
		
		prepareGame();
    }
	
	/**
	 * 
	 * Constructs a Tetris object with the specified arguments.
	 * 
	 * @param level Initial level of the game
	 * @param enablePieceShadow Enable the shadow of the piece
	 * @param increaseLevel Enable increases level
	 */
	public Tetris(int level, boolean enablePieceShadow, boolean increaseLevel) {
		this.level = level;
		this.initLevel = level;
		this.lines = 0;
		this.score = 0;
		this.enablePieceShadow = enablePieceShadow;
		this.increaseLevel = increaseLevel;
		
		prepareGame();
    }

	/**
	 * Method that fills the board of blocks, generates the active piece and the next piece.
	 */
	public void prepareGame(){
		fillBoard();
        
        // Pieces
        this.activePiece = generatePiece(randomPiece());
        this.activePiece.setEnableShowShadow(enablePieceShadow);
        setNextPiece(randomPiece());
	}
	
	
	/**
	 * Method that fills the board of blocks
	 */
	private void fillBoard(){
		
		// Create blocks
        this.boardRows = new LinkedList<ArrayList<Block>>();
        
		ArrayList<Block> row;
        Block c;
        
		for (int x = 0; x < this.numRow + this.numInvisibleRow; x++) {
            row = new ArrayList<Block>();

            for (int y = 0; y < this.numColumns; y++) {
        		c = new Block(x, y);
                row.add(c);
            }

            this.boardRows.add(row);
        }
	}
	
    /**
     * 
     * Method that indicates if a block from board is busy
     * 
     * @param row block's row
     * @param column block's column
     * @return true if block is busy or the (row,column) indicate is offboard, and false otherwise
     */
    public boolean isBlockBusy(int row, int column) {
        if (row < 0 || row > (this.boardRows.size()-1) || column < 0 || column > (this.numColumns-1)) {
            return true; // offboard
        } else {
            return this.boardRows.get(row).get(column).isBusy();
        }
    }

    /**
     * Method that rotates the active piece and notifies to the observers if rotate is posible
     * 
     * @return true if the move has been completed successfully and false
	 *         otherwise
     */
    public synchronized boolean rotatePiece() {
    	if(this.activePiece != null){
    		if (this.activePiece.rotate()){
	    		this.setChanged();
	    		this.notifyObservers(EnumActionTetris.RotatePiece);
	    		return true;
    		}
    	}
    	
    	return false;
    }

    /**
     * Method that moves the active piece to the right and notifies to the observers if move is posible
     * 
     * @return true if the move has been completed successfully and false
	 *         otherwise
     */
    public synchronized boolean movePieceRight() {
    	if(this.activePiece != null){
    		if (this.activePiece.moveRight()){
	    		this.setChanged();
	    		this.notifyObservers(EnumActionTetris.MoveRightPiece);
	    		return true;
    		}
    	}
    	
    	return false;
    }

    /**
     * Method that moves the active piece to the left and notifies to the observers if move is posible
     * 
     * @return true if the move has been completed successfully and false
	 *         otherwise
     */
    public synchronized boolean movePieceLeft() {
    	if(this.activePiece != null){
    		if (this.activePiece.moveLeft()){
	    		this.setChanged();
	    		this.notifyObservers(EnumActionTetris.MoveLeftPiece);
	    		return true;
    		}
    	}
    	
    	return false;
    }

    /**
     * Method that moves the active piece to the down and notifies to the observers if move is posible
     * 
     * @return true if the move has been completed successfully and false
	 *         otherwise
	 *         
     */
    public synchronized boolean movePieceDown() {
    	boolean moveDown = false;
    	
        if(this.activePiece != null){
        	moveDown = this.activePiece.moveDown();
        	
        	boolean outBoard = this.activePiece.isPieceOutBoard();
        	
        	if(!moveDown && outBoard){
        		this.setChanged();
            	this.notifyObservers(EnumActionTetris.EndGame);
        	} else {
	            if(moveDown){
	            	this.setChanged();
	            	this.notifyObservers(EnumActionTetris.MoveDownPiece);
	            } else {
	            	deleteFillRows();
	            	this.activePiece = generatePiece(this.nextPiece);
	            	this.activePiece.setEnableShowShadow(enablePieceShadow);
	            	setNextPiece(randomPiece());
	            }
        	}
        }
        
        return moveDown;
    }
    
    /**
     * Method that moves the active piece to the down full and notifies to the observers if move is posible
	 *         
     */
    public synchronized void movePieceDownFull() {
        if(this.activePiece != null){
        	while(this.activePiece.moveDown()){}
        	
        	this.setChanged();
        	this.notifyObservers(EnumActionTetris.MoveDownPiece);
        	
        	deleteFillRows();
        	this.activePiece = generatePiece(this.nextPiece);
        	this.activePiece.setEnableShowShadow(enablePieceShadow);
        	this.nextPiece = randomPiece();
        	
        	this.setChanged();
        	this.notifyObservers(EnumActionTetris.NextPiece);
        }
    }

    /**
     * 
     * Returns the block in the specifies row and column
     * 
     * @param row block's row
     * @param column block's column
     * @return Object Block in the specifies row and column
     */
    public Block getBlock (int row, int column){
        if(row < 0 || row > (this.boardRows.size()-1) || column < 0 || column > (this.numColumns-1)){
            return null;
        }else{
            return this.boardRows.get(row).get(column);
        }
    }
    
    
    /**
     * 
     * Method that indicates if all blocks of a row are busy 
     * 
     * @param row row of board
     * @return true if the row is fill and false otherwise
     */
    private boolean isFillRow(ArrayList<Block> row) {

        boolean fill = true;
        int x = 0;
        while (fill && x < row.size()) {
            if (!row.get(x).isBusy()) {
                fill = false;
            }
            x++;
        }
        return fill;

    }
    
    
    /**
     * Method that deletes the full rows, and moves down the rows above of full rows
     */
    public void deleteFillRows() {
    	
    	int fillRows = 0;
    	
    	ArrayList<Block> row,rowAux1,rowAux2;
    	int i = this.numInvisibleRow + this.numRow - 1;
    	while(i >= this.numInvisibleRow){ // through all the rows from below
    		row = this.boardRows.get(i);
    		
			if (isFillRow(row)) { // if any row is full
                
				// For all rows above the checked, shifts the state of the blocks 
				// of the upper row to the lower row
				for(int j=i; j > this.numInvisibleRow; j--){
					rowAux1 = this.boardRows.get(j);
					rowAux2 = this.boardRows.get(j-1);
					
					for(int z=0; z < rowAux1.size(); z++){
						if(rowAux2.get(z).isBusy()){
							rowAux1.get(z).fillBlock(rowAux2.get(z).getColor(), rowAux2.get(z).getMarginColor());
						} else {
							rowAux1.get(z).freeBlock();
						}
					}
				}
				
				// Notifies the observers the delete of rows
				this.setChanged();
				this.notifyObservers(EnumActionTetris.RemoveRow);
				
				fillRows++;
				
            } else {
            	// Only if the row is not full, move to the next row
            	i--;
            }
    	}
    	
    	// Depends of the deletes rows increases the score
    	if(fillRows == 1){
    		this.setScore(score + (40 * (level + 1)));
    	} else if(fillRows == 2){
    		this.setScore(score + (100 * (level + 1)));
    	} else if(fillRows == 3){
    		this.setScore(score + (300 * (level + 1)));
    	} else if(fillRows == 4){
    		this.setScore(score + (1200 * (level + 1)));
    	}
    	
    	// Upgrade lines 
    	if(fillRows > 0){
    		this.setLines(lines += fillRows);
    	}
    	
    }
    
    
    /**
     * 
     * Method that generates the type of piece and puts it in the board
     * 
     * @param piece Type of piece to generate
     * @return Instance of object Piece puts in the board in the invisible rows
     */
    private Piece generatePiece(EnumTypePiece piece){
        
        Piece p = null;
        Block b1,b2,b3,b4;
        
        if(piece == EnumTypePiece.PieceSquare){
        	b1 = this.getBlock(2, ((int)(this.numColumns/2))-1);
            b2 = this.getBlock(2, ((int)this.numColumns/2));
            b3 = this.getBlock(3, ((int)this.numColumns/2)-1);
            b4 = this.getBlock(3, ((int)this.numColumns/2));
            p = new PieceSquare(b1, b2, b3, b4, this);
        } else if(piece == EnumTypePiece.Piece_I){
        	b1 = this.getBlock(0, ((int)this.numColumns/2));
            b2 = this.getBlock(1, ((int)this.numColumns/2));
            b3 = this.getBlock(2, ((int)this.numColumns/2));
            b4 = this.getBlock(3, ((int)this.numColumns/2));
            p = new Piece_I(b1, b2, b3, b4, this);
        } else if(piece == EnumTypePiece.Piece_J){
        	b1 = this.getBlock(1, ((int)this.numColumns/2));
            b2 = this.getBlock(2, ((int)this.numColumns/2));
            b3 = this.getBlock(3, ((int)this.numColumns/2));
            b4 = this.getBlock(3, ((int)this.numColumns/2)-1);
        	p = new Piece_J(b1, b2, b3, b4, this);
        } else if(piece == EnumTypePiece.Piece_L){
        	b1 = this.getBlock(1, ((int)this.numColumns/2));
            b2 = this.getBlock(2, ((int)this.numColumns/2));
            b3 = this.getBlock(3, ((int)this.numColumns/2));
            b4 = this.getBlock(3, ((int)this.numColumns/2)+1);
            p = new Piece_L(b1, b2, b3, b4, this);
        } else if(piece == EnumTypePiece.Piece_S){
        	b1 = this.getBlock(2, ((int)this.numColumns/2)+1);
            b2 = this.getBlock(2, ((int)this.numColumns/2));
            b3 = this.getBlock(3, ((int)this.numColumns/2));
            b4 = this.getBlock(3, ((int)this.numColumns/2)-1);
            p = new Piece_S(b1, b2, b3, b4, this);
        } else if(piece == EnumTypePiece.Piece_T){
        	b1 = this.getBlock(2, ((int)this.numColumns/2));
            b2 = this.getBlock(3, ((int)this.numColumns/2)-1);
            b3 = this.getBlock(3, ((int)this.numColumns/2));
            b4 = this.getBlock(3, ((int)this.numColumns/2)+1);
            p = new Piece_T(b1, b2, b3, b4, this);
        } else if(piece == EnumTypePiece.Piece_Z){
        	b1 = this.getBlock(2, ((int)this.numColumns/2)-1);
            b2 = this.getBlock(2, ((int)this.numColumns/2));
            b3 = this.getBlock(3, ((int)this.numColumns/2));
            b4 = this.getBlock(3, ((int)this.numColumns/2)+1);
            p = new Piece_Z(b1, b2, b3, b4, this);
        }
        
        return p;
    }

    
    /**
     * 
     * Method that randomly generated a type of piece
     * 
     * @return An enumerate of EnumTypePiece
     */
    private EnumTypePiece randomPiece(){
        
        EnumTypePiece p = null;
        double typePiece = Math.random();
        
        if(typePiece <= 0.11){	// L piece -> 11%
        	p = EnumTypePiece.Piece_L;
        } else if (typePiece > 0.11 && typePiece <= 0.22){	// J piece -> 11%
        	p = EnumTypePiece.Piece_J;
        } else if (typePiece > 0.22 && typePiece <= 0.41){	// S piece -> 19%
        	p = EnumTypePiece.Piece_S;
        } else if (typePiece > 0.41 && typePiece <= 0.60){	// T piece -> 19%
        	p = EnumTypePiece.Piece_T;
        } else if (typePiece > 0.60 && typePiece <= 0.78){	// Square piece -> 18%
        	p = EnumTypePiece.PieceSquare;
        } else if (typePiece > 0.78 && typePiece <= 0.90){	// I piece -> 12%
        	p = EnumTypePiece.Piece_I;
        } else if (typePiece > 0.90 && typePiece <= 1){	// Z piece -> 10%
        	p = EnumTypePiece.Piece_Z;
        }
        
        return p;
    }

    
    /**
     * 
     * Returns the number of rows in the board
     * 
     * @return the number of rows in the board
     */
    public int getNumRow() {
		return numRow;
	}

    
	/**
	 * 
	 * Sets the number of rows in the board
	 * 
	 * @param numRow the number of rows in the board
	 */
	public void setNumRow(int numRow) {
		this.numRow = numRow;
	}

	
	/**
     * 
     * Returns the number of columns in the board
     * 
     * @return the number of columns in the board
     */
	public int getNumColumns() {
		return numColumns;
	}

	
	/**
	 * 
	 * Sets the number of columns in the board
	 * 
	 * @param numColumns the number of columns in the board
	 */
	public void setNumColumns(int numColumns) {
		this.numColumns = numColumns;
	}

	
	/**
     * 
     * Returns the number of invisible rows in the board
     * 
     * @return the number of invisible rows in the board
     */
	public int getNumInvisibleRow() {
		return numInvisibleRow;
	}
	
	
	/**
	 * 
	 * Returns the next piece
	 * 
	 * @return Type of piece (EnumTypePiece)
	 */
	public EnumTypePiece getNextPiece() {
		return nextPiece;
	}
	
	
	/**
	 * 
	 * Sets the next piece
	 * 
	 * @param nextPiece Type of piece (EnumTypePiece)
	 */ 
	private void setNextPiece(EnumTypePiece nextPiece) {
		this.nextPiece = nextPiece;
		this.setChanged();
    	this.notifyObservers(EnumActionTetris.NextPiece);
	}

	
	/**
	 * 
	 * Returns the list of rows of blocks
	 * 
	 * @return List of rows of blocks
	 */
	public List<ArrayList<Block>> getBoardRows() {
		return boardRows.subList(this.numInvisibleRow, this.numRow+this.numInvisibleRow);
	}

	
	/**
	 * 
	 * Returns the level of the game
	 * 
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	
	/**
	 * 
	 * Sets the level of the game
	 * 
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
		this.setChanged();
		this.notifyObservers(EnumActionTetris.LevelChange);
	}

	
	/**
	 * 
	 * Returns the score of the game
	 *  
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	
	/**
	 * 
	 * Sets the score of the game. Notifies the observers the change.
	 * 
	 * @param score score
	 */
	private void setScore(int score) {
		this.score = score;
		this.setChanged();
		this.notifyObservers(EnumActionTetris.ScoreChange);
	}

	
	/**
	 * 
	 * Returns the delete lines in game.
	 * 
	 * @return delete lines in game
	 */
	public int getLines() {
		return lines;
	}

	
	/**
	 * 
	 * Sets the delete lines in game. Notifies the observers the change.
	 * If the delete lines are multiples of 10, it increases the level.
	 * 
	 * @param lines delete lines in game
	 */
	private void setLines(int lines) {
		this.lines = lines;
		this.setChanged();
		this.notifyObservers(EnumActionTetris.LinesChange);
		
		if(increaseLevel){
			if (lines >= (10*(level-initLevel+1))){
				this.setLevel(level + 1);
			}
		}
		
	}

	
	/**
	 * 
	 * Returns the speed of game in milisecons
	 * 
	 * @return the speed of game in milisecons
	 */
	public long getSpeed(){
		
		long speed; // in miliseconds
		
		if (level == 0){
			speed = 800;
		} else if (level == 1){
			speed = 720;
		} else if (level == 2){
			speed = 630;
		} else if (level == 3){
			speed = 550;
		} else if (level == 4){
			speed = 470;
		} else if (level == 5){
			speed = 380;
		} else if (level == 6){
			speed = 300;
		} else if (level == 7){
			speed = 220;
		} else if (level == 8){
			speed = 130;
		} else if (level == 9){
			speed = 100;
		} else if (level >= 10 && level <= 12){
			speed = 80;
		} else if (level >= 13 && level <= 15){
			speed = 70;
		} else if (level >= 16 && level <= 18){
			speed = 50;
		} else if (level >= 19 && level <= 28){
			speed = 30;
		} else {
			speed = 20;
		}
		
		return speed;
		
	}
	
	
	/**
	 * 
	 * Sets enable piece's shadow
	 * 
	 * @param enablePieceShadow enable piece's shadow
	 */
	public void setEnablePieceShadow(boolean enablePieceShadow) {
		this.enablePieceShadow = enablePieceShadow;
	}
	
}
