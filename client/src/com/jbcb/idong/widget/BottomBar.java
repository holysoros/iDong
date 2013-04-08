package com.jbcb.idong.widget;

import java.util.ArrayList;
import java.util.List;

import com.jbcb.idong.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class BottomBar extends LinearLayout implements OnClickListener {

	private static final int TAG_SUGGEST = 0;
	private static final int TAG_SEARCH = 1;
	private static final int TAG_MINE = 2;
	private Context mContext;
	
	public BottomBar(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		mContext = context;
		init();
	}

	public BottomBar(Context context) 
	{
		super(context);
		mContext = context;
		init();
	}

	private List<View> itemList;
	/**
	 * get the buttons from layout
	 * 
	 * 得到布局文件中的按钮
	 * 
	 * */
	private void init()
	{
		LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
	    View layout = inflater.inflate(R.layout.bottom_bar, null);
	    layout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));
	    
	    Button btnSuggest = (Button)layout.findViewById(R.id.bottombar_btn_suggest);
	    Button btnSearch = (Button)layout.findViewById(R.id.bottombar_btn_search);
	    Button btnMine = (Button)layout.findViewById(R.id.bottombar_btn_mine);
	    
	    btnSuggest.setOnClickListener(this);
	    btnSearch.setOnClickListener(this);
	    btnMine.setOnClickListener(this);
	    
	    btnSuggest.setTag(TAG_SUGGEST);
	    btnSearch.setTag(TAG_SEARCH);
	    btnMine.setTag(TAG_MINE);
	    
	    itemList = new ArrayList<View>();
	    itemList.add(btnSuggest);
	    itemList.add(btnSearch);
	    itemList.add(btnMine);
	    
	    this.addView(layout);
	}
	
	@Override
	public void onClick(View v)
	{
		int tag = (Integer)v.getTag();
		switch (tag) 
		{
			case TAG_SUGGEST:
				setNormalState(lastButton);
				setSelectedState(tag);
				break;
			case TAG_SEARCH:
				setNormalState(lastButton);
				setSelectedState(tag);
				break;
			case TAG_MINE:
				setNormalState(lastButton);
				setSelectedState(tag);
				break;
		}
	}
	
	private int lastButton = -1;
	/**
	 * set the default bar item of selected
	 * 
	 * 设置默认选中的Item
	 * 
	 * */
	public void setSelectedState(int index)
	{
		if(index != -1 && onItemChangedListener != null)
		{
			if(index > itemList.size())
			{
				throw new RuntimeException("the value of default bar item can not bigger than string array's length");
			}
			itemList.get(index).setSelected(true);
			onItemChangedListener.onItemChanged(index);
			lastButton = index;
		}
	}
	
	/**
	 * set the normal state of the button by given index
	 * 
	 * 恢复未选中状态
	 * 
	 * */
	private void setNormalState(int index)
	{
		if(index != -1)
		{
			if(index > itemList.size())
			{
				throw new RuntimeException("the value of default bar item can not bigger than string array's length");
			}
			itemList.get(index).setSelected(false);
		}
	}
	
	public interface OnItemChangedListener
	{
		public void onItemChanged(int index);
	}
	
	private OnItemChangedListener onItemChangedListener;
	
	public void setOnItemChangedListener(OnItemChangedListener onItemChangedListener)
	{
	    this.onItemChangedListener = onItemChangedListener;
	}

}
