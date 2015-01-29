package com.tetris.presentation;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tetris.game.Block;
import com.tetris.game.Tetris;

public class ViewBoardTetris extends View {

	private Tetris board;
	private int width, height; 				// overall size of the view in the screen in pixels
	private float blockSize;				// size of each block in pixels
	private Paint pFilled;
	private float blockMargin;				// size of the margin of each block in pixels
	private float marginTop, marginLeft; 	// remaining size of the view in pixels  -> (overall size - board size)
	float boardWidth, boardHeight; 			// size of the board in pixels -> (overall size - remaining size)
	private float twoDpInPx;				// 2 dip in pixels -> margin of the board
	
	public ViewBoardTetris(Context context) {
		super(context);
		inicialize ();
	}

	public ViewBoardTetris(Context context, AttributeSet attrs) {
		super(context, attrs);
		inicialize ();
	}

	public ViewBoardTetris(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inicialize ();
	}
	
	private void inicialize (){
		board = null;
		width = 0;
		height = 0;
		blockSize = 0;
		marginTop = 0;
		marginLeft = 0;
		boardWidth = 0;
		boardHeight = 0;
		
		pFilled = new Paint();
	    pFilled.setStyle(Style.FILL);
	    
	    //Converts 2 dp into its equivalent px
	    twoDpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)2, getResources().getDisplayMetrics());
	    
	    //Converts 0.8 dp into its equivalent px
	    blockMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float)0.8, getResources().getDisplayMetrics());
		
	    if(blockMargin < 1){
	    	blockMargin = 1;
	    }
	    
	}
	
	public void setBoard(Tetris b) {
		blockSize = 0;
		marginTop = 0;
		marginLeft = 0;
		boardWidth = 0;
		boardHeight = 0;
		this.board = b;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		
		// Calculate width
		int width = MeasureSpec.getSize(widthMeasureSpec);
	    
	    // Calculate height
	    int height = MeasureSpec.getSize(heightMeasureSpec);
	    
	    setMeasuredDimension(width, height);
	    
	    this.width = getMeasuredWidth();
	    this.height = getMeasuredHeight();
	}

	@Override
	protected void onDraw(Canvas canvas){
		
	    if(board == null){
	    	
	    	pFilled.setColor(Color.BLACK);
		    canvas.drawRect(0 + twoDpInPx, 0  + twoDpInPx, width - twoDpInPx, height - twoDpInPx, pFilled);
		    
		    // margin
		    pFilled.setColor(Color.YELLOW);
		    
		    canvas.drawRect(0, 0, twoDpInPx, height, pFilled); //left
		    canvas.drawRect(width - twoDpInPx, 0, width, height, pFilled); //right
		    canvas.drawRect(0, 0, width, twoDpInPx, pFilled); //top
		    canvas.drawRect(0, height - twoDpInPx, width, height, pFilled); //bottom
		    
	    } else { // Draw blocks

	    	if (blockSize == 0){

		    	/* Calculate width and height of the blocks
		    	 * 		-> ( -this.twoDpInPx) -> size to the margin of board
		    	 * 
		    	 */
		    	float widthBlock = (width - twoDpInPx * 2)/(float)board.getNumColumns();
		    	float heightBlock = (height - twoDpInPx * 2)/(float)board.getNumRow();
		    	
		    	if(widthBlock <= heightBlock){
		    		
		    		blockSize = widthBlock;
		    		marginTop = (height - twoDpInPx * 2) - (board.getNumRow() * blockSize);
		    		marginLeft = 0;
		    		
	    			int rowExtra = (int)(marginTop / blockSize);
	    			
	    			marginTop -= (rowExtra * blockSize);
	    			//marginTop = marginTop/(float)2;
	    			
	    			board.setNumRow(board.getNumRow() + rowExtra);
	    			board.prepareGame();
	    			
		    	} else {
		    		
		    		blockSize = heightBlock;
		    		marginTop = 0;
		    		marginLeft = (width - twoDpInPx * 2) - (board.getNumColumns() * blockSize);

		    		int columnExtra = (int)(marginLeft / blockSize);
		    		
		    		marginLeft -= (columnExtra * blockSize);
		    		//marginLeft = marginLeft / (float)2;
		    		
		    		board.setNumColumns(board.getNumColumns() + columnExtra);
		    		board.prepareGame();
		    		
		    	}
		    	
		    	boardWidth = board.getNumColumns() * blockSize;
		    	boardHeight = board.getNumRow() * blockSize;
		    	
	    	}
	    	

	    	pFilled.setColor(Color.BLACK);
		    canvas.drawRect(0, 0, this.width, this.height, pFilled);
		    
		    // margin
		    pFilled.setColor(Color.YELLOW);
		    
		    canvas.drawRect(0, 0, twoDpInPx, boardHeight + twoDpInPx * 2, pFilled); //left
		    canvas.drawRect(boardWidth + twoDpInPx, 0,boardWidth + twoDpInPx * 2, boardHeight + twoDpInPx * 2, pFilled); //right
		    canvas.drawRect(0, 0, boardWidth + twoDpInPx * 2, twoDpInPx, pFilled); //top
		    canvas.drawRect(0, boardHeight + twoDpInPx, boardWidth + twoDpInPx *2, boardHeight + twoDpInPx * 2, pFilled); //bottom
	    	
	    	float left,top,right,bottom;
	    	
	    	int numRow = 0;
	    	int numColumn;
	    	
	    	List<ArrayList<Block>> blocks = this.board.getBoardRows();
	    	for(ArrayList<Block> row: blocks){
	    		
	    		numColumn = 0;
	    		for(Block b: row){
	    			
	    			left = numColumn * blockSize + this.twoDpInPx;
	    			top = numRow * blockSize + this.twoDpInPx;
	    			right = left + blockSize;
	    			bottom = top + blockSize;
	    			
	    			pFilled.setColor(Color.parseColor(b.getColor()));
	    			canvas.drawRect(left,top,right,bottom,pFilled);
	    			
	    			//margin left
	    			pFilled.setColor(Color.parseColor(b.getMarginColor()));
	    			canvas.drawRect(left,top,left+blockMargin,bottom,pFilled);
	    			
	    			//margin rigth
	    			pFilled.setColor(Color.parseColor(b.getMarginColor()));
	    			canvas.drawRect(right-blockMargin,top,right,bottom,pFilled);
	    			
	    			//margin top
	    			pFilled.setColor(Color.parseColor(b.getMarginColor()));
	    			canvas.drawRect(left,top,right,top+blockMargin,pFilled);
	    			
	    			//margin top
	    			pFilled.setColor(Color.parseColor(b.getMarginColor()));
	    			canvas.drawRect(left,bottom-blockMargin,right,bottom,pFilled);
	    			
	    			numColumn++;
	    		}
	    		numRow++;
	    	}
	    }
	 
	}

}
