package com.window.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		long height = UIUtils.getNavigationBarHeight(this);
		Log.i("NavigationBar height", "" + height);
	}
}
