package com.jbcb.idong.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

/**
 * @ClassName PreviewImgDlg.java
 * @author Clame
 * 
 */
public class PreviewImgDlg extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);   
	    
	    ImageView image=new ImageView(this);
        image.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        setContentView(image);
        
		Intent intent = this.getIntent();
		if (intent.getExtras() != null) {
			String imagePath = intent.getExtras().getString("imageurl");
			
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			Bitmap bmp = BitmapFactory.decodeFile(imagePath, options);
			
			int width = options.outWidth;//��
			int height = options.outHeight;//��
			
			Display display = getWindowManager().getDefaultDisplay();
			Point point = new Point();
			display.getSize(point);
			int screenHeight = point.y;
			int screenWidth = point.x;
			
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
	}

}
