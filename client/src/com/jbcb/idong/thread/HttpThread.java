package com.jbcb.idong.thread;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import com.jbcb.idong.LoginActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.DropBoxManager.Entry;
import android.os.Handler;
import android.os.Message;

public class HttpThread extends Thread {
	private Handler handler = null;
	private Context context = null;
	String url = null;
	String nameSpace = null;
	String methodName = null;
	HashMap<String, Object> params = null;
	ProgressDialog progressDialog = null;

	public HttpThread(Handler handler, Context context) {
		this.handler = handler;
		this.context = context;
	}

	public void doStart(String url, String nameSpace, String methodName,
			HashMap<String, Object> params) {
		// 把参数传进来
		this.url = url;
		this.nameSpace = nameSpace;
		this.methodName = methodName;
		this.params = params;
		// 告诉使用者，请求开始了
		progressDialog = new ProgressDialog(context);
		progressDialog.setTitle("网络连接");
		progressDialog.setMessage("正在请求，请稍等......");
		progressDialog.setIndeterminate(true);
		// progressDialog=ProgressDialog.show(clswdy.this,
		// "网络连接","正在验证，请稍等......",true,true);
		progressDialog.setButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int i) {
				progressDialog.cancel();

			}
		});
		progressDialog
				.setOnCancelListener(new DialogInterface.OnCancelListener() {
					public void onCancel(DialogInterface dialog) {
					}
				});
		progressDialog.show();
		this.start(); // 线程开始了
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			// web service请求,result为返回结果
			int result = CallWebService();

			if (result == 1) {

				// 取消进度对话框
				progressDialog.dismiss();
				// clswdy.this.setProgressBarIndeterminateVisibility(false);
				// 构造消息,验证通过了
				Message message = handler.obtainMessage();
				Bundle b = new Bundle();
				message.what = 1; // 这里是消息的类型
				b.putInt("data", 1); // 这里是消息传送的数据

				message.setData(b);
				handler.sendMessage(message);
			} else {
				progressDialog.dismiss();

				Message message = handler.obtainMessage();
				Bundle b = new Bundle();
				message.what = 1;
				b.putInt("data", result); // 这里是消息传送的数据
				message.setData(b);
				handler.sendMessage(message);

			}
		} catch (Exception ex) {
			progressDialog.dismiss();
			// 构造消息，程序出错了
			Message message = handler.obtainMessage();
			Bundle b = new Bundle();
			message.what = 2;

			b.putString("error", ex.getMessage());

			message.setData(b);
			handler.sendMessage(message);

		} finally {

		}
	}

	protected int CallWebService() throws Exception {
		String SOAP_ACTION = nameSpace + methodName;
		int response = 0;
		SoapObject request = new SoapObject(nameSpace, methodName);
		// boolean request=false;
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);

		envelope.dotNet = true; // .net 支持

		// 参数

		if (params != null && !params.isEmpty()) {
			for (Iterator it = params.entrySet().iterator(); it.hasNext();) {
				Map.Entry e = (Map.Entry) it.next();
				request.addProperty(e.getKey().toString(), e.getValue());

			}
		}
		envelope.bodyOut = request;
		//
		AndroidHttpTransport androidHttpTrandsport = new AndroidHttpTransport(url);
		SoapObject result = null;
		try {
			// web service请求
			androidHttpTrandsport.call(SOAP_ACTION, envelope);
			// 得到返回结果
			Object temp = envelope.getResponse();
			response = Integer.parseInt(temp.toString());
		} catch (Exception ex) {
			throw ex;
		}
		return response;
	}
}
