package com.jbcb.idong.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class PreviewImgDlg extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);   
	    
	    ImageView image=new ImageView(this);
        image.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
        setContentView(image);
        
		Intent intent = this.getIntent();
		if (intent.getExtras() != null) {
			String imagePath = intent.getExtras().getString("imagepath");
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			Bitmap bmp = BitmapFactory.decodeFile(imagePath, options);
			
			int width = options.outWidth;//¿í
			int height = options.outHeight;//¸ß
			
			Display display = getWindowManager().getDefaultDisplay();
			int screenHeight = display.getHeight();
			int screenWidth = display.getWidth();
			
			boolean isWidthBig = false;
			boolean isHeightBig = false;
			
			if (width >= screenWidth) {
				isWidthBig = true;
			}
			
			if (height >= screenHeight) {
				isHeightBig = true;
			}
			
			float rate = 1;
			if ( isWidthBig && isHeightBig ) {
				rate = (float)width / (float)screenWidth;
				if ( rate < (float)height / (float)screenHeight ) {
					rate = (float)height / (float)screenHeight;
				}
			} else if ( !isWidthBig && isHeightBig ) {
				rate = (float)height / (float)screenHeight;
			} else if ( isWidthBig && !isHeightBig ) {
				rate = (float)width / (float)screenHeight;
			}
			
			options.outWidth = (int) Math.round(width / rate + 0.5);
			options.outHeight = (int) Math.round(height / rate + 0.5);
			options.inJustDecodeBounds = false;
			bmp = BitmapFactory.decodeFile(imagePath, options);
			image.setImageBitmap(bmp);
		}
		
	    // TODO Auto-generated method stub
	}

}
