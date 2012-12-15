package com.example.gridviewpager2;

import android.app.Application;


public class Globals extends Application {

	public static final Integer[] ImageIds = {
        R.drawable.photo1,
        R.drawable.photo2, 
        R.drawable.splash_demo,
        R.drawable.app_notes,

        R.drawable.red_car, 
        R.drawable.top_secret,
        R.drawable.help_activity,
        R.drawable.async_task_demo, 

        R.drawable.pulse_2,
        R.drawable.dragview3_icon, 
        R.drawable.pulse_4,
        R.drawable.hello, 

        R.drawable.settings, 
        R.drawable.future_book_6,
        R.drawable.gl_pi_4,
        R.drawable.more_not_better
};
	
	public static final String[] Titles = {
        "Telescope",
        "Observatory",
        "Splash screen",
        "App Notes",

        "Red Car",
        "Top Secret",
        "Help Page",
        "Async Task Demo",

        "Featured",
        "Drag-Drop Gridview",
        "Pulse Help",
        "Yellow Sticky Note",

        "Settings",
        "Future of the Book",
        "Gomoku",
        "More Is Not Always Better"
};
	
	private static final long serialVersionUID = 1L;
	
	String mSampleTopicText = "Topic text goes here.";
	
	// get number of Titles
	public int getNumTitles() {
		return Titles.length;
	}
	
	// get the title of the nth Title in the list
	public String getTitle(int index) {
		return Titles[index];
	}
	
	// get the image Id of the nth title in the list
	public int getImage(int index) {
		return ImageIds[index];
	}
	
	// get TopicText
	public String getTopicText(int index) {
		if (index >= Titles.length) return null;
		return mSampleTopicText;
	}
	
	
}
