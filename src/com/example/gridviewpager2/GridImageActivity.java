package com.example.gridviewpager2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class GridImageActivity extends Activity {

	/*
	 * activity shows a single image and the topic title associated with it.
	 * touching the image or title takes you to a view of the topic's text
	 */
	
	Globals g;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_pager_grid_item);
		Intent in = getIntent();
		int index = in.getIntExtra("index", 0);
		
		g = (Globals)getApplication();
		int imageId = g.getImage(index);
		String title = g.getTitle(index);
		
		TextView tv = (TextView)findViewById(R.id.title);
		if (tv != null) {
			tv.setText(title);
			tv.setTag(index);
			tv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onClickTitle(v);
				}
			});
		}
		
		ImageView iv = (ImageView)findViewById(R.id.image);
		if (iv != null) {
			iv.setTag(index);
			iv.setBackgroundResource(imageId);
			iv.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onClickImage(v);
				}
			});
		}
	}
	
	public void onClickImage(View v) {
		Integer index = (Integer)v.getTag();
		showTopic(index);
	}
	
	public void onClickTitle(View v) {
		Integer index = (Integer)v.getTag();
		showTopic(index);
	}
	
	// show topic indicated by index number
	public void showTopic(int index) {
		Intent intent = new Intent(this.getApplicationContext(), TopicActivity.class);
		intent.putExtra("index", index);
		startActivity(intent);
	}

}
