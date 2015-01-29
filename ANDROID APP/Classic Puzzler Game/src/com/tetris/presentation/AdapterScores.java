package com.tetris.presentation;

import java.text.SimpleDateFormat;
import java.util.List;

import com.dgm.classicPuzzlerGame.R;
import com.tetris.scoreManager.ScoreTetris;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterScores extends ArrayAdapter<ScoreTetris> {
	
	private List<ScoreTetris> scores;
	private Activity context;
	private SimpleDateFormat sdf;
	
	public AdapterScores(Activity context, List<ScoreTetris> scores) {
        super(context, R.layout.item_list_scores, scores);
        this.scores = scores;
        this.context = context;
        
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
	    
		View item = convertView;
	    ViewHolderScoreItemList holder;
	 
	    if(item == null){
			LayoutInflater inflater = context.getLayoutInflater();
	        item = inflater.inflate(R.layout.item_list_scores, null);
	        
	        TextView textViewScore, textViewDate, textViewPosition;
	        LinearLayout linearLayoutView = (LinearLayout)item.findViewById(R.id.layoutItemScore);
		    textViewScore = (TextView)item.findViewById(R.id.textViewScoreRating);
		    textViewDate = (TextView)item.findViewById(R.id.textViewDateScoreRating);
		    textViewPosition = (TextView)item.findViewById(R.id.textViewPosScoreRating);
		    
		    holder = new ViewHolderScoreItemList(linearLayoutView,textViewScore, textViewDate, textViewPosition);
		    
		    item.setTag(holder);
	        
	    } else {
	    	holder = (ViewHolderScoreItemList)item.getTag();
	    }
	    
	    holder.getTextViewPosition().setText(position + 1 + "");
	    holder.getTextViewScore().setText(scores.get(position).getScore() + "");
	    holder.getTextViewDate().setText(sdf.format(scores.get(position).getDate()));
	    
	    if(position%2 == 0){
	    	holder.getLinearLayoutView().setBackgroundResource(R.color.SlateGray);
	    	holder.getTextViewPosition().setBackgroundResource(R.color.SlateGray);
	    } else {
	    	holder.getLinearLayoutView().setBackgroundResource(R.color.LightSteelBlue);
	    	holder.getTextViewPosition().setBackgroundResource(R.color.LightSteelBlue);
	    }
	    
	    
	    return(item);
	}
	

}
