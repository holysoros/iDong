package com.jbcb.idong;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jbcb.idong.model.Party;
import com.jbcb.idong.thread.HttpThread;
import com.jbcb.idong.widget.ImageListAdapter;
import com.jbcb.idong.widget.ImageListView;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 需要使用不带参数的构造器，可以使用getActivity()替换context参数 否则屏幕在旋转的时候会抛出异常： Caused by:
 * java.lang.InstantiationException: can't instantiate class
 * com.michael.fragment.FragmentExecute; no empty constructor
 * 
 * @see http
 *      ://stackoverflow.com/questions/7016632/unable-to-instantiate-fragment
 * */
public class FragmentSearch extends Fragment {
	HttpThread thread = null;

	public FragmentSearch() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			// Currently in a layout without a container, so no
			// reason to create our view.
			return null;
		}
		LayoutInflater myInflater = (LayoutInflater) getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = myInflater.inflate(R.layout.fragment_search, container,
				false);

		final List<Party> partyList = new ArrayList<Party>();

		new Thread() {

			@Override
			public void run() {
				getData();
			}
		}.start();
		

		for (int i = 0; i < 3; i++) {
			Party party = new Party();
			String bitmapPath = "";
			if (i == 0) {
				party.setTitle("海边比2");
				party.setDescription("比比谁更2");
				bitmapPath = Environment.getExternalStorageDirectory()
						.getPath() + "/DCIM/Camera/1.png";
			} else if (i == 1) {
				party.setTitle("畅游吕德斯海姆");
				party.setDescription("真心是个好地方");
				bitmapPath = Environment.getExternalStorageDirectory()
						.getPath() + "/DCIM/Camera/2.png";
			} else if (i == 2) {
				party.setTitle("卢浮魅影");
				party.setDescription("卢浮宫太他妈大了");
				bitmapPath = Environment.getExternalStorageDirectory()
						.getPath() + "/DCIM/Camera/4.png";
			}

			Set<String> imageURLSet = new HashSet<String>();
			imageURLSet.add(bitmapPath);
			party.setPartyImageURLSet(imageURLSet);
			partyList.add(party);
		}

		final ImageListView list = (ImageListView) layout
				.findViewById(R.id.lv_search_partylist);
		ImageListAdapter adapter = new ImageListAdapter(this.getActivity(),
				partyList);
		list.setAdapter(adapter);
		list.setItemsCanFocus(false);

		return layout;
	}

	private void getData() {
		String path = "http://holyweibo.sinaapp.com/parties/";
		URL url = null;
		byte[] data = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			InputStream inStream = conn.getInputStream();
			data = readInputSream(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String json = new String(data);
		int a = 1;
		// Handler handler = new Handler();
		// thread = new HttpThread(handler, this.getActivity()); // 建立线程实例
		//
		// HashMap<String, Object> params = new HashMap<String, Object>();
		// // try {
		// // String strvalidate = "galyglxxxt";
		// // strvalidate = new String(strvalidate.toString().getBytes(),
		// "UTF-8");
		// // params.put("username", username);// 加入参数
		// // params.put("pwd", password);
		// // params.put("validate", strvalidate);
		// // } catch (Exception ex) {
		// // ex.printStackTrace();
		// // }
		// String url = "http://holyweibo.sinaapp.com/parties/.json";//
		// webserivce地址
		// String nameSpace = "http://tempuri.org/"; // 空间名,可修改
		// String methodName = "User_login"; // 需调用webservice名称
		// thread.doStart(url, nameSpace, methodName, params); // 启动线程
	}

	public byte[] readInputSream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}
}
