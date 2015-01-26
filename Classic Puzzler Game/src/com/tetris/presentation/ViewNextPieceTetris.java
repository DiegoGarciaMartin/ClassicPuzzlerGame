package com.tetris.presentation;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.tetris.game.Block;
import com.tetris.game.EnumTypePiece;
import com.tetris.game.PieceSquare;
import com.tetris.game.Piece_I;
import com.tetris.game.Piece_J;
import com.tetris.game.Piece_L;
import com.tetris.game.Piece_S;
import com.tetris.game.Piece_T;
import com.tetris.game.Piece_Z;

public class ViewNextPieceTetris extends View {

	private EnumTypePiece piece;
	private ArrayList<ArrayList<Block>> rowsBlocks;

	private ArrayList<ArrayList<Block>> rowsBlocksAux1; // board to pieces: I,T,S,Z
	private ArrayList<ArrayList<Block>> rowsBlocksAux2; // board to pieces: Square,L,J
	
	private int numColumnsAux1 = 5;
	private int numColumnsAux2 = 4;
	private int numRows = 4;

	private int width, height; // overall size of the view in the screen in pixels
	private float blockSize; // size of each block in pixels
	private Paint pFilled;
	private float blockMargin; // size of the margin of each block in pixels
	float boardWidth, boardHeight; // size of the board in pixels -> (overall size - remaining size)
	private float twoDpInPx; // 2 dip in pixels -> margin of the board

	public ViewNextPieceTetris(Context context) {
		super(context);
		inicialize();
	}

	public ViewNextPieceTetris(Context context, AttributeSet attrs) {
		super(context, attrs);
		inicialize();
	}

	public ViewNextPieceTetris(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inicialize();
	}

	public void setPiece(EnumTypePiece piece) {
		if(this.piece != piece){
			cleanRows();
			loadPiece(piece);
			this.piece = piece;
			this.invalidate();
		}
	}
	
	private void cleanRows(){
		if (rowsBlocks != null){
			for(ArrayList<Block> row: rowsBlocks){
				for(Block b:row){
					b.freeBlock("#000000","#000000");
				}
			}
		}
	}
	
	private void loadPiece(EnumTypePiece piece){
		
        Block b1,b2,b3,b4;
        String color = "";
        String marginColor = "";
        
        if(piece == EnumTypePiece.PieceSquare){
        	this.rowsBlocks = this.rowsBlocksAux2;
        	b1 = rowsBlocks.get(1).get(1);
            b2 = rowsBlocks.get(1).get(2);
            b3 = rowsBlocks.get(2).get(1);
            b4 = rowsBlocks.get(2).get(2);
            color = PieceSquare.colorSquare;
            marginColor = PieceSquare.marginColorSquare;
            b1.fillBlock(color, marginColor);
            b2.fillBlock(color, marginColor);
            b3.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
        } else if(piece == EnumTypePiece.Piece_I){
        	this.rowsBlocks = this.rowsBlocksAux1;
        	b1 = rowsBlocks.get(0).get(2);
            b2 = rowsBlocks.get(1).get(2);
            b3 = rowsBlocks.get(2).get(2);
            b4 = rowsBlocks.get(3).get(2);
            color = Piece_I.colorI;
            marginColor = Piece_I.marginColorI;
            b1.fillBlock(color, marginColor);
            b2.fillBlock(color, marginColor);
            b3.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
        } else if(piece == EnumTypePiece.Piece_J){
        	this.rowsBlocks = this.rowsBlocksAux2;
        	b1 = rowsBlocks.get(0).get(2);
            b2 = rowsBlocks.get(1).get(2);
            b3 = rowsBlocks.get(2).get(2);
            b4 = rowsBlocks.get(2).get(1);
            color = Piece_J.colorJ;
            marginColor = Piece_J.marginColorJ;
            b1.fillBlock(color, marginColor);
            b2.fillBlock(color, marginColor);
            b3.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
        } else if(piece == EnumTypePiece.Piece_L){
        	this.rowsBlocks = this.rowsBlocksAux2;
        	b1 = rowsBlocks.get(0).get(1);
            b2 = rowsBlocks.get(1).get(1);
            b3 = rowsBlocks.get(2).get(1);
            b4 = rowsBlocks.get(2).get(2);
            color = Piece_L.colorL;
            marginColor = Piece_L.marginColorL;
            b1.fillBlock(color, marginColor);
            b2.fillBlock(color, marginColor);
            b3.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
        } else if(piece == EnumTypePiece.Piece_S){
        	this.rowsBlocks = this.rowsBlocksAux1;
        	b1 = rowsBlocks.get(1).get(3);
            b2 = rowsBlocks.get(1).get(2);
            b3 = rowsBlocks.get(2).get(2);
            b4 = rowsBlocks.get(2).get(1);
            color = Piece_S.colorS;
            marginColor = Piece_S.marginColorS;
            b1.fillBlock(color, marginColor);
            b2.fillBlock(color, marginColor);
            b3.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
        } else if(piece == EnumTypePiece.Piece_T){
        	this.rowsBlocks = this.rowsBlocksAux1;
        	b1 = rowsBlocks.get(1).get(2);
            b2 = rowsBlocks.get(2).get(1);
            b3 = rowsBlocks.get(2).get(2);
            b4 = rowsBlocks.get(2).get(3);
            color = Piece_T.colorT;
            marginColor = Piece_T.marginColorT;
            b1.fillBlock(color, marginColor);
            b2.fillBlock(color, marginColor);
            b3.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
        } else if(piece == EnumTypePiece.Piece_Z){
        	this.rowsBlocks = this.rowsBlocksAux1;
        	b1 = rowsBlocks.get(1).get(1);
            b2 = rowsBlocks.get(1).get(2);
            b3 = rowsBlocks.get(2).get(2);
            b4 = rowsBlocks.get(2).get(3);
            color = Piece_Z.colorZ;
            marginColor = Piece_Z.marginColorZ;
            b1.fillBlock(color, marginColor);
            b2.fillBlock(color, marginColor);
            b3.fillBlock(color, marginColor);
            b4.fillBlock(color, marginColor);
        }
        
	}

	private void inicialize() {
		
		piece = null;

		rowsBlocksAux1 = new ArrayList<ArrayList<Block>>();
		rowsBlocksAux2 = new ArrayList<ArrayList<Block>>();
		ArrayList<Block> row1, row2;
		Block b;
		for(int i=0; i < this.numRows; i++){
			
			row1 = new ArrayList<Block>();
			for (int j=0; j < this.numColumnsAux1; j++){
				b = new Block(i, j,"#000000","#000000",false);
				row1.add(b);
			}
			rowsBlocksAux1.add(row1);
			
			row2 = new ArrayList<Block>();
			for (int j=0; j < this.numColumnsAux2; j++){
				b = new Block(i, j,"#000000","#000000",false);
				row2.add(b);
			}
			rowsBlocksAux2.add(row2);
			
		}
		
		this.rowsBlocks = rowsBlocksAux1;
		
		width = 0;
		height = 0;
		blockSize = 0;
		boardWidth = 0;
		boardHeight = 0;

		pFilled = new Paint();
		pFilled.setStyle(Style.FILL);

		// Converts 2 dp into its equivalent px
		twoDpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				(float) 2, getResources().getDisplayMetrics());

		// Converts 0.8 dp into its equivalent px
		blockMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				(float) 0.8, getResources().getDisplayMetrics());

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		// Calculate width
		int width = MeasureSpec.getSize(widthMeasureSpec);

		// Calculate height
		int height = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(width, height);

		this.width = getMeasuredWidth();
		this.height = getMeasuredHeight();
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if(rowsBlocks == null){
			
			pFilled.setColor(Color.BLACK);
			canvas.drawRect(0, 0, this.width, this.height, pFilled);
			
		}else {
		
			int numColumns = this.rowsBlocks.get(0).size();
			
	
			/*
			 * Calculate width and height of the blocks -> ( -this.twoDpInPx) ->
			 * size to the margin of board
			 */
			float widthBlock = (width - twoDpInPx * 2)
					/ (float) numColumns;
			float heightBlock = (height - twoDpInPx * 2) / (float) this.numRows;

			if (widthBlock <= heightBlock) {
				blockSize = widthBlock;
			} else {
				blockSize = heightBlock;
			}

			boardWidth = numColumns * blockSize;
			boardHeight = this.numRows * blockSize;
	
	
			pFilled.setColor(Color.BLACK);
			canvas.drawRect(0, 0, this.width, this.height, pFilled);
			
	
			float left, top, right, bottom;
	
			int numRow = 0;
			int numColumn;
	
			for (ArrayList<Block> row : rowsBlocks) {
	
				numColumn = 0;
				for (Block b : row) {
	
					left = numColumn * blockSize + this.twoDpInPx;
					top = numRow * blockSize + this.twoDpInPx;
					right = left + blockSize;
					bottom = top + blockSize;
	
					pFilled.setColor(Color.parseColor(b.getColor()));
					canvas.drawRect(left, top, right, bottom, pFilled);
					
					// margin left
					pFilled.setColor(Color.parseColor(b.getMarginColor()));
					canvas.drawRect(left, top, left + blockMargin, bottom, pFilled);
	
					// margin rigth
					pFilled.setColor(Color.parseColor(b.getMarginColor()));
					canvas.drawRect(right - blockMargin, top, right, bottom,
							pFilled);
	
					// margin top
					pFilled.setColor(Color.parseColor(b.getMarginColor()));
					canvas.drawRect(left, top, right, top + blockMargin, pFilled);
	
					// margin top
					pFilled.setColor(Color.parseColor(b.getMarginColor()));
					canvas.drawRect(left, bottom - blockMargin, right, bottom, pFilled);
					
					numColumn++;
				}
				numRow++;
			}
		}
	}

}
