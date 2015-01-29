package com.tetris.presentation;

import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * Class that stored a reference to the textviews of layout 'item_list_score'. 
 * It is used to add to the tag of the view, to access to the textviews more quickly. 
 * 
 * @author Diego García Martín
 *
 */
public class ViewHolderScoreItemList {

	private LinearLayout linearLayoutView;
	private TextView textViewScore, textViewDate, textViewPosition;

	

	/**
	 * @param linearLayoutView
	 * @param textViewScore
	 * @param textViewDate
	 * @param textViewPosition
	 */
	public ViewHolderScoreItemList(LinearLayout linearLayoutView,
			TextView textViewScore, TextView textViewDate,
			TextView textViewPosition) {
		super();
		this.linearLayoutView = linearLayoutView;
		this.textViewScore = textViewScore;
		this.textViewDate = textViewDate;
		this.textViewPosition = textViewPosition;
	}

	/**
	 * @return the textViewScore
	 */
	public TextView getTextViewScore() {
		return textViewScore;
	}

	/**
	 * @param textViewScore the textViewScore to set
	 */
	public void setTextViewScore(TextView textViewScore) {
		this.textViewScore = textViewScore;
	}

	/**
	 * @return the textViewDate
	 */
	public TextView getTextViewDate() {
		return textViewDate;
	}

	/**
	 * @param textViewDate the textViewDate to set
	 */
	public void setTextViewDate(TextView textViewDate) {
		this.textViewDate = textViewDate;
	}

	/**
	 * @return the textViewPosition
	 */
	public TextView getTextViewPosition() {
		return textViewPosition;
	}

	/**
	 * @param textViewPosition the textViewPosition to set
	 */
	public void setTextViewPosition(TextView textViewPosition) {
		this.textViewPosition = textViewPosition;
	}

	/**
	 * @return the linearLayoutView
	 */
	public LinearLayout getLinearLayoutView() {
		return linearLayoutView;
	}

	/**
	 * @param linearLayoutView the linearLayoutView to set
	 */
	public void setLinearLayoutView(LinearLayout linearLayoutView) {
		this.linearLayoutView = linearLayoutView;
	}
	
	
	
}
