package com.jbcb.idong;

import java.util.ArrayList;
import java.util.List;

import com.jbcb.idong.model.Party;
import com.jbcb.idong.thread.GetPartyListThread;
import com.jbcb.idong.widget.ImageListAdapter;
import com.jbcb.idong.widget.ImageListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ClassName FragmentSearch.java
 * @author Clame
 * 
 */
public class FragmentSearch extends Fragment {
    String      jsonStr   = "";
    boolean     showList  = false;
    static Activity    context   = null;
    static View        layout    = null;

    public FragmentSearch() {
    }

    public static Handler handler = new Handler() {

                                @SuppressWarnings({ "unchecked", "rawtypes" })
                                @Override
                                public void handleMessage(Message msg) {
                                    super.handleMessage(msg);
                                    Bundle bundle = msg.getData();
                                    ArrayList list = bundle.getParcelableArrayList("partylist");

                                    List<Party> partyList = new ArrayList<Party>();
                                    partyList= (List<Party>) list.get(0);
                                    
                                    ImageListAdapter adapter = new ImageListAdapter(context, partyList);
                                    ImageListView listView = (ImageListView) layout.findViewById(R.id.lv_search_partylist);
                                    listView.setAdapter(adapter);
                                    listView.setItemsCanFocus(false);
                                }

                            };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            // Currently in a layout without a container, so no
            // reason to create our view.
            return null;
        }
        LayoutInflater myInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = myInflater.inflate(R.layout.fragment_search, container, false);
        context = this.getActivity();
        
        GetPartyListThread thread = new GetPartyListThread();
        thread.run();

        return layout;

//        new Thread() {
//            @Override
//            public void run() {
//                getData();
//                JSONArray array;
//                try {
//                    array = new JSONArray(jsonStr);
//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject item = array.getJSONObject(i);
//                        String title = item.getString("title");
//                        String description = item.getString("category");
//                        String thumbnail = item.getString("thumbnail");
//
//                        Party party = new Party();
//                        party.setTitle(title);
//                        party.setDescription(description);
//                        Bitmap img = CommonUtility.getImageThumbnail(thumbnail, 80, 80);
//                        party.setThumbnail(img);
//                        party.setThumbnailURL(thumbnail);
//                        partyList.add(party);
//                    }
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//                handler.sendEmptyMessage(0);
//            }
//        }.start();
    }

//    private void getData() {
//        String path = "http://holyweibo.sinaapp.com/parties/";
//        URL url = null;
//        byte[] data = null;
//        try {
//            url = new URL(path);
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        HttpURLConnection conn;
//        try {
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(5 * 1000);
//            conn.setRequestMethod("GET");
//            InputStream inStream = conn.getInputStream();
//            data = readInputSream(inStream);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        jsonStr = new String(data);
//    }
//
//    public byte[] readInputSream(InputStream inStream) throws Exception {
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        while ((len = inStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, len);
//        }
//        inStream.close();
//        return outStream.toByteArray();
//    }
}
