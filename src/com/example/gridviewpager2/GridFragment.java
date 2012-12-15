package com.example.gridviewpager2;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



public class GridFragment extends Fragment {

	int mNum;				// id number for this fragment
	int mFirstImage = 0;	// index number of first image to show in fragment
	int mImageCount = -1;	// the number of images to show in the fragment
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Read the arguments and check resource values for number of rows and columns
		
		Bundle args = getArguments();
		mNum = ((args != null) ? args.getInt("num") : 0);
		
		if (args != null) {
			mFirstImage = args.getInt("firstImage");
		}
		
		Resources res = getActivity().getResources();
		int numRows = res.getInteger(R.integer.grid_num_rows);
		int numCols = res.getInteger(R.integer.grid_num_cols);
		int numTopicsPerPage = numRows * numCols;
		mImageCount = numTopicsPerPage;
		
		//mFirstImage = (mFirstImage/numTopicsPerPage) * numTopicsPerPage;
	}
	
	/*
	 * Build the view that shows the grid
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.demo_pager_grid, container, false);
		
		GridView gridview = (GridView)view.findViewById(R.id.gridview);
		gridview.setTag(new Integer(mNum));
		
		// set label text for the view
		View tv = view.findViewById(R.id.text);
		if (tv != null) {
			((TextView)tv).setText("Topics " + mNum);
		}
		
		// Hide the "no items" content until it is needed
		View nc = view.findViewById(R.id.no_topics_text);
		if (nc != null) {
			nc.setVisibility(View.INVISIBLE);
		}
		
		return view;
	}
	
	/*
	 * divide usable space into columns and put grid of images in that area
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Activity a = getActivity();
		Resources res = a.getResources();
		
		View rootView = getView();
		GridView gridview = (GridView)rootView.findViewById(R.id.gridview);
		
		DisplayMetrics metrics = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		
		// from the resource files, determine how many rows and columns are to be displayed
		final int numRows = res.getInteger(R.integer.grid_num_rows);
		final int numCols = res.getInteger(R.integer.grid_num_cols);
		
		// Figure out how much space is available for the N rows and M columns to be displayed
		// Start at the root view for the fragment and adjust for the title, padding, ...
		int titleHeight = res.getDimensionPixelSize(R.dimen.topic_title_height);
		int titlePadding = res.getDimensionPixelSize(R.dimen.topic_title_padding);
		int buttonAreaHeight = res.getDimensionPixelSize(R.dimen.button_area_height);
		int titleBarHeight = res.getDimensionPixelSize (R.dimen.title_bar_height);
		int gridHspacing = res.getDimensionPixelSize (R.dimen.image_grid_hspacing);
		int gridVSpacing = res.getDimensionPixelSize (R.dimen.image_grid_vspacing);
		int otherGridH = res.getDimensionPixelSize (R.dimen.other_grid_h);
		int otherGridW = res.getDimensionPixelSize (R.dimen.other_grid_w);
		int heightUsed = 2 * titleBarHeight + (numRows + 2) * gridVSpacing + (titleHeight + 2 * titlePadding)
							+ otherGridH + buttonAreaHeight;
		
		int widthUsed = 60;  // guess for now
		int availableHeight = metrics.heightPixels - heightUsed;
		int availableWidth = metrics.widthPixels - widthUsed;
		int cellWidth = availableWidth / numCols;
		int cellHeight = availableHeight / numRows;
		
		if (gridview == null) {
			Log.d("DEBUG", "Unable to locate gridview.");
		} else {
			Globals g = (Globals)a.getApplication();
			//int n = g.getNumTitles();
			gridview.setAdapter(new GridImageAdapter(a, g, mFirstImage, mImageCount, cellWidth, cellHeight));
			
			// arrange so a long click on an item in the grid shows the topic associated with that image
			gridview.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> parent, View v,
						int position, long id) {
					showTopic(mFirstImage + position);
					return true;
				}
			});
		}
	}
	
	public void showTopic(int index) {
		
		Toast.makeText(getActivity(), "" + index, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(getActivity().getApplicationContext(), GridImageActivity.class);
		intent.putExtra("index", index);
		startActivity(intent);
		
	}
	
}
