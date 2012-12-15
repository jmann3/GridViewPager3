package com.example.gridviewpager2;

//import com.wglxy.demo.pager.GridImageActivity;
//import com.wglxy.demo.pager.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GridImageAdapter extends BaseAdapter {

	public static final int DEFAULT_CELL_SIZE = 220;
	
	private Context mContext;
	private int mImageOffset = 0;		// the index offset into the list of images
	private int mImageCount = -1;		// -1 means display all images
	private int mNumTopics = 0;			// total number of topics in topics collection
	private int mCellWidth = DEFAULT_CELL_SIZE;
	private int mCellHeight = DEFAULT_CELL_SIZE;
	Globals globals;
	
	
	public GridImageAdapter(Context c, Globals g, int imageOffset, int imageCount) {
		mContext = c;
		mImageOffset = imageOffset;
		mImageCount = imageCount;
		mNumTopics = g.getNumTitles();
		globals = g;
	}
	
	public GridImageAdapter(Context c, Globals g, int imageOffset, int imageCount, int cellWidth, int cellHeight) {
		this(c, g, imageOffset, imageCount);
		mCellWidth = cellWidth;
		mCellHeight = cellHeight;
	}
	
	@Override
	public int getCount() {
		// if we are on last page and there are no more images, the count is how many are being displayed
		int count = mImageCount;
		if (mImageOffset + mImageCount >= mNumTopics)
			count = mNumTopics - mImageOffset;
		return count;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		return mImageOffset + position;
	}

	/*
	 * create a new ImageView for each item referenced by the Adapter
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//Log.d("GridImageAdapter", "Globals = " + globals);
		
		
		ImageView imageView = null;
		TextView textView = null;
		View childView = null;
		int numTopics = mNumTopics;
		
		if (convertView == null) { // if it's not recycled, initialize attributes
			int layoutId = R.layout.demo_pager_grid_item;
			LayoutInflater li = ((Activity)mContext).getLayoutInflater();
			childView = li.inflate(layoutId, null);
		} else {
			childView = convertView;
		}
		
		if (childView != null) {
			// set width and height of child view
			// Set the width and height of the child view.
	           childView.setLayoutParams(new GridView.LayoutParams(mCellWidth, mCellHeight));

	           int j = position + mImageOffset;
	           if (j < 0) j = 0;
	           if (j >= numTopics) j = numTopics - 1;
	   
	           imageView = (ImageView) childView.findViewById (R.id.image);
	           
	           if (imageView != null) {
	        	   Resources res = mContext.getResources();
	   				int imagePadding = res.getDimensionPixelSize(R.dimen.grid_image_padding);
	   			
	   				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	   				imageView.setBackgroundResource(R.color.background_grid1_cell);
	   				imageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);
	   				//imageView.setImageResource((Glob)
	   				
	   				imageView.setImageResource(globals.getImage(j));
	   				imageView.setTag(new Integer(j));
	   				
	   				imageView.setOnLongClickListener(new View.OnLongClickListener() {

						@Override
						public boolean onLongClick(View v) {
							showTopic((Integer)v.getTag());
							return true;
						}
	   					
	   				});
	           }
	           textView = (TextView)childView.findViewById(R.id.title);
	           if (textView != null) {
	        	   textView.setText(globals.getTitle(j));
	        	   textView.setTag(new Integer(j));
	        	   
	        	   textView.setOnLongClickListener(new View.OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						showTopic((Integer)v.getTag());
						return true;
					}
	        		   
	        	   });
	           } 
		}
		
		return childView;
	}
	
	public void showTopic (int index) {

	    Toast.makeText(mContext, "" + index, Toast.LENGTH_SHORT).show();
	    Intent intent = new Intent(mContext.getApplicationContext(), GridImageActivity.class);
	    intent.putExtra ("index", index);
	    mContext.startActivity (intent);
	}
}
