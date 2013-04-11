package com.jbcb.idong;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 需要使用不带参数的构造器，可以使用getActivity()替换context参数
 * 否则屏幕在旋转的时候会抛出异常：
 * Caused by: java.lang.InstantiationException: 
 * can't instantiate class com.michael.fragment.FragmentExecute; no empty constructor
 * 
 * @see http://stackoverflow.com/questions/7016632/unable-to-instantiate-fragment
 * */
public class FragmentMine extends Fragment
{
	public int type = 0;

	public FragmentMine()
	{
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		if (container == null) 
		{
            // Currently in a layout without a container, so no
            // reason to create our view.
            return null;
        }
		
		type = getArguments().getInt("showMine");
		
		if (type == 0) {
			Intent loginIntent = new Intent();
			loginIntent.setClass(this.getActivity().getBaseContext(), LoginActivity.class);
			startActivity(loginIntent);
			this.getActivity().finish();
            return null;
		} else {
			LayoutInflater myInflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		    View layout = myInflater.inflate(R.layout.fragment_mine, container, false); 
			
			return layout;
		}
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
