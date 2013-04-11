package com.jbcb.idong.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class ImageListView extends ListView {

	private ImageListAdapter mSelfAdapter;

	public ImageListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ImageListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public ImageListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 删除ListView中上一次渲染的View，并添加新View。
	 */
	private void buildList() {
		if (mSelfAdapter == null) {

		}

		if (getChildCount() > 0) {
			removeAllViews();
		}

		int count = mSelfAdapter.getCount();

		for (int i = 0; i < count; i++) {
			View view = mSelfAdapter.getView(i, null, null);
			if (view != null) {
				addView(view, i);
			}
		}
	}

	public ImageListAdapter getSelfAdapter() {
		return mSelfAdapter;
	}

	/**
	 * 设置Adapter。
	 * 
	 * @param selfAdapter
	 */
	public void setSelfAdapter(ImageListAdapter selfAdapter) {
		this.mSelfAdapter = selfAdapter;
		buildList();
	}
}
