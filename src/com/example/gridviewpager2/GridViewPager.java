package com.example.gridviewpager2;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GridViewPager extends FragmentActivity {

	static final int DEFAULT_NUM_FRAGMENTS = 4;
	static final int DEFAULT_NUM_ITEMS = 4;
	
	MyAdapter mAdapter;
	ViewPager mPager;
	int mNumFragments;
	int mNumItems;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_fragment_pager);
        
        Resources res = getResources();
        Globals globals = (Globals)getApplication();
        
        String sampleText = res.getString(R.string.sample_topic_text);
        
        // create an adapter object that creates the fragments we need 
        // to display images and titles of all topics
        mAdapter = new MyAdapter(this, globals, res);
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        
        
        // get number of fragments and items per page information
        int numTopics = globals.getNumTitles();
        int numRows = res.getInteger(R.integer.grid_num_rows);
        int numCols = res.getInteger(R.integer.grid_num_cols);
        int numTopicsPerPage = numRows * numCols;
        int numFragments = numTopics / numTopicsPerPage;
        if (numTopics % numTopicsPerPage != 0)
        	numFragments++;
        mNumFragments = numFragments;
        mNumItems = numTopicsPerPage;
        
        // watch for button clicks on first and last buttons
        Button firstButton = (Button)findViewById(R.id.goto_first);
        firstButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				mPager.setCurrentItem(0);
			}
        });
         
        Button lastButton = (Button)findViewById(R.id.goto_last);
        lastButton.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
				mPager.setCurrentItem(mNumFragments - 1);
			}
        });
    }

   /* 
 public boolean onLongClick(View v) {
	 Integer num = (Integer)v.getTag();
	 int index = num.intValue();
	 showTopic(index);
	 
	 return true;
 }
 
 public void showTopic(int index) {
	 Toast.makeText(this, index, Toast.LENGTH_SHORT).show();
	 Intent intent = new Intent(this.getApplicationContext(), GridImageActivity.class);
	 intent.putExtra("index", index);
	 this.startActivity(intent);
 }
 */
 
 /*
	public void setup(Resources res) {
		mNumFragments = 0;
		mNumItems = 0;
		
	     // get number of fragments and items per page information
	     int numTopics = globals.getNumTitles();
	     int numRows = res.getInteger(R.integer.grid_num_rows);
	     int numCols = res.getInteger(R.integer.grid_num_cols);
	     int numTopicsPerPage = numRows * numCols;
	     int numFragments = numTopics / numTopicsPerPage;
	     if (numTopics % numTopicsPerPage != 0)
	     	numFragments++;
	     mNumFragments = numFragments;
	     mNumItems = numTopicsPerPage;
	 }
 */
 
    public static class MyAdapter extends FragmentStatePagerAdapter {
    	
    	private int mNumItems = 0;
    	private int mNumFragments = 0;
    	Globals g;
    	
		public MyAdapter(FragmentActivity activity, Globals g, Resources res) {
			super(activity.getSupportFragmentManager());
			this.g = g;
			setup(res, g);
		}

		@Override
		public Fragment getItem(int position) {
			// create a new fragment and supply the fragment number, image position, and image count as
			// arguments.
			Bundle args = new Bundle();
			args.putInt("num", position + 1);
			args.putInt("firstImage", position * mNumItems);
			
			// the last page might not have the full number of items
			int imageCount = mNumItems;
			if (position == (mNumFragments - 1)) {
				int numTopics = g.getNumTitles();
				int rem = numTopics % mNumItems;
				if (rem > 0)
					imageCount = rem;
			}
			args.putInt("imageCount", imageCount);
			
			// return new GridFragment object
			GridFragment f = new GridFragment();
			f.setArguments(args);
			
			return f;
		}

		@Override
		public int getCount() {
			return mNumFragments;
		}
		
		void setup(Resources res, Globals g) {
			
			if (g.getNumTitles() == 0 || (res == null)) {
				mNumItems = DEFAULT_NUM_ITEMS;
				mNumFragments = DEFAULT_NUM_FRAGMENTS;
			} else {
				int numTopics = g.getNumTitles();
			     int numRows = res.getInteger(R.integer.grid_num_rows);
			     int numCols = res.getInteger(R.integer.grid_num_cols);
			     int numTopicsPerPage = numRows * numCols;
			     int numFragments = numTopics / numTopicsPerPage;
			     if (numTopics % numTopicsPerPage != 0)
			     	numFragments++;
			     mNumFragments = numFragments;
			     mNumItems = numTopicsPerPage;
			}
			Log.d("GridViewPager", "num fragments, topics per page: " + mNumFragments + " " + mNumItems);
		}
    }
}
