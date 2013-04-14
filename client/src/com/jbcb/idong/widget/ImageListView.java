package com.jbcb.idong.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * @ClassName ImageListView.java
 * @author Clame
 * 
 */
public class ImageListView extends ListView {

    private ImageListAdapter mAdapter;

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
     * @FunName buildList
     * @Description build the list
     * @param N/A
     * @return N/A
     * 
     */
    private void buildList() {
        if (mAdapter == null) {

        }

        if (getChildCount() > 0) {
            removeAllViews();
        }

        int count = mAdapter.getCount();

        for (int i = 0; i < count; i++) {
            View view = mAdapter.getView(i, null, null);
            if (view != null) {
                addView(view, i);
            }
        }
    }

    /**
     * @FunName setSelfAdapter
     * @Description set the adapter for the listview
     * @param adapter
     * @return N/A
     * 
     */
    public void setSelfAdapter(ImageListAdapter adapter) {
        this.mAdapter = adapter;
        buildList();
    }
}
