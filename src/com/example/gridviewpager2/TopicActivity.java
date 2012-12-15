package com.example.gridviewpager2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TopicActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.demo_topic);
		Intent in = getIntent();
		int index = in.getIntExtra("index", 0);
		
		Globals globals = (Globals) getApplication();
		String title = globals.getTitle(index);
		String body = globals.getTopicText(index);
		
		TextView tv = (TextView)findViewById(R.id.title);
		if (tv != null) {
			tv.setText(title);
			tv.setTag(index);
		}
		
		TextView tv2 = (TextView)findViewById(R.id.body);
		if (tv2 != null) {
			tv2.setText(body);
			tv2.setTag(index);
		}
	}
}
