package com.operation;

import android.app.Application;
import com.alibaba.fastjson.JSON;
import com.facebook.stetho.Stetho;
import com.uzmap.pkg.openapi.APICloud;
import com.uzmap.pkg.uzkit.request.APICloudHttpClient;

import java.util.HashMap;

/**
 * 本项目的Application，需要在该类中初始化APICloud
 * @author dexing.li
 *
 */
public class MyApplication extends Application {

	public MyApplication() {
		;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		APICloud.initialize(this);//初始化APICloud，SDK中所有的API均需要初始化后方可调用执行
		//初始化APICloud网络请求框架，如果不需要则忽略，具体使用方式见MainActivity中的Case
		APICloudHttpClient.createInstance(this);
		Stetho.initializeWithDefaults(this);
		System.out.println(JSON.toJSON(new HashMap<>()));
	}
}

