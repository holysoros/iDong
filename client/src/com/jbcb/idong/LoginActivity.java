package com.jbcb.idong;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button close = (Button)findViewById(R.id.login_btn_close);
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mainGroupIntent = new Intent();
				mainGroupIntent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(mainGroupIntent);		
				LoginActivity.this.finish();
			}
		});
		
		Button login = (Button)findViewById(R.id.login_btn_login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent mainGroupIntent = new Intent();
				mainGroupIntent.setClass(LoginActivity.this, MainActivity.class);
				mainGroupIntent.putExtra("index", 2);
				mainGroupIntent.putExtra("showMine", 1);
				startActivity(mainGroupIntent);
				LoginActivity.this.finish();
			}
		});
		
		Button regist = (Button)findViewById(R.id.login_btn_regist);
		regist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ExitDialog();
			}
		});
		
	    // TODO Auto-generated method stub
	}
	
	private void ExitDialog(){  
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);  
        builder.setTitle("Confirm").setMessage("你是2B吗?")  
            .setPositiveButton("是的", new DialogInterface.OnClickListener() {  
                @Override  
                public void onClick(DialogInterface dialog, int which) {  
                    dialog.dismiss(); 
                }  
            }).setNegativeButton("真是的", new DialogInterface.OnClickListener(){  
                @Override  
                public void onClick(DialogInterface dialog, int which) {  
                    dialog.dismiss();  
                }  
            });  
        Dialog dialog = builder.create();  
        dialog.show();  
    } 
}
