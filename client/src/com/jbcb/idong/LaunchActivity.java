package com.jbcb.idong;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

/**
 * @category LaunchActivity.java
 * @author Clame
 *
 */
public class LaunchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		Thread waitThread = new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
				}

				runOnUiThread(new Runnable() {
					// @Override
					public void run() {
						Intent mainGroupIntent = new Intent();
						mainGroupIntent.setClass(LaunchActivity.this, MainActivity.class);
						startActivity(mainGroupIntent);		
						LaunchActivity.this.finish();
					}
				});
			}
		};
        waitThread.start();
    }

}
