package com.jbcb.idong.widget;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.jbcb.idong.R;
import com.jbcb.idong.model.Party;
import com.jbcb.idong.utilities.CommonUtility;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageListAdapter extends BaseAdapter {
	private Activity context;
	private List<Party> partyList;

	public ImageListAdapter(Activity context, List<Party> partyList) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.partyList = partyList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return partyList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return partyList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = context.getLayoutInflater();
		View itemView = inflater.inflate(R.layout.listveiw_searchparty, null);
		ImageView iv_partyicon = (ImageView) itemView .findViewById(R.id.iv_partyicon);
		TextView tv_title = (TextView) itemView.findViewById(R.id.tv_partytitle);
		TextView tv_detail = (TextView) itemView.findViewById(R.id.tv_partydetail);
		
		Party party = partyList.get(position);		
		
		tv_title.setText(party.getTitle());
		tv_detail.setText(party.getDescription());
		Set<String> imageURLSet = party.getPartyImageURLSet();
		Iterator<String> it = imageURLSet.iterator();
		String imageURLtmp = "";
		while (it.hasNext()) {
			imageURLtmp = it.next().toString();
			break;
		}
		final String imageURL = imageURLtmp;
		
		iv_partyicon.setImageBitmap(CommonUtility.getImageThumbnail(imageURL, 80, 80));
		iv_partyicon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Click(imageURL);
			}
		});
		
		return itemView;
	}

	public void Click(String imageURL)
    {
		Intent intent = new Intent();
		intent.putExtra("imagepath", imageURL);
	    intent.setClass(context, PreviewImgDlg.class);
	    context.startActivity(intent);
    }
}
