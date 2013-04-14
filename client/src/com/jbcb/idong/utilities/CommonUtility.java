package com.jbcb.idong.utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

/**
 * @ClassName CommonUtility.java
 * @Description Some common methods
 * @author Clame
 * 
 */
public class CommonUtility {

    /**
     * @FunName getAppPath
     * @Description get the path of iDong app
     * @param N/A
     * @return the path of iDong app
     * 
     */
    public static String getAppPath() {
        String appPath = "";
        if (android.os.Environment.MEDIA_MOUNTED.equals(android.os.Environment.getExternalStorageState())) {
            appPath = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/iDong";
        } else {
            appPath = android.os.Environment.getDataDirectory().getAbsolutePath() + "/iDong";
        }

        File app = new File(appPath);
        if (!app.exists()) {
            app.mkdir();
        }

        return appPath;
    }

    /**
     * @FunNam0 getImageThumbnail
     * @Description get the thumbnail of an bitmap from Internet with the appointed width and height
     * @param bitmapURL
     * @param width
     * @param height
     * @return the thumbnail of an image
     * 
     */
    public static Bitmap getImageThumbnail(String bitmapURL, int width, int height) {
        Bitmap bitmap = null;
        InputStream in = null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        // set the inJustDecodeBounds as false to get a real bitmap object
        options.inJustDecodeBounds = false;

        try {
            URL url = new URL(bitmapURL);
            in = url.openStream();
            bitmap = BitmapFactory.decodeStream(in, null, options);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // get the basic height and width of the bitmap
        int basicHeight = options.outHeight;
        int basicWidth = options.outWidth;

        // get the scaling
        int beHeight = basicHeight / height;
        int beWidth = basicWidth / width;
        int be = 1;
        if (beWidth < beHeight) {
            be = beWidth;
        } else {
            be = beHeight;
        }
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;

        // get the thumbnail of the bitmap
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        return bitmap;
    }
    
    /**
     * @FunName getData
     * @Description get data from the parties URL
     * @param N/A
     * @return N/A
     * 
     */
    public static String getWebData(String URL) {
        String path = "URL";
        URL url = null;
        byte[] data = null;
        String jsonStr = "";
        
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
            return jsonStr;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return jsonStr;
        }

        jsonStr = new String(data);        
        return jsonStr;
    }

    /**
     * @FunName readInputSream
     * @Description read the input stream, and save it as byte[]
     * @param inStream
     * @return byte[]
     * 
     */
    private static byte[] readInputSream(InputStream inStream) throws Exception {
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
