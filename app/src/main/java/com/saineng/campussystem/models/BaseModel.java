package com.saineng.campussystem.models;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saineng.campussystem.R;
import com.saineng.campussystem.common.DataDeserializer;
import com.saineng.campussystem.models.bean.BaseResult;
import com.saineng.campussystem.utils.LogUtils;
import com.saineng.campussystem.utils.MD5Util;
import com.saineng.campussystem.utils.NetUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by sahara on 2016/9/28.
 */
public class BaseModel <T> {

    public static final String BASEURL = "http://192.168.1.119/";
    protected static final String URL = BASEURL + "qa/api/";     //http://m.chemm.com   192.168.1.119

    protected static final String USER_URL = BASEURL + "user/api/";


    protected static final String NETWORK_ERROR = "-1";
    protected static final String SERVICE_ERROR = "-2";
    protected static final String PARSE_ERROR = "-3";
    /**
     * 十秒超时
     */
    private static final long TIME_OUT = 10 * 1000;
    private static final String ACCESSTOKEN = "caibopx";
    private Gson gson;

    /**
     * 基本返回结果框架
     *
     * @param response
     * @return
     */
    protected BaseResult parseBaseResult(String response) throws Exception {
        if (gson == null) {
            gson = new Gson();
        }
        return gson.fromJson(response, BaseResult.class);
    }

    /**
     * 返回主体Data数据
     *
     * @param response
     * @param tClass
     * @return
     */
    protected T parseResult(String response, Class<T> tClass) throws Exception {
        gson = new GsonBuilder()
                .registerTypeAdapter(tClass, new DataDeserializer<>(tClass))
                .create();

        return gson.fromJson(response, tClass);
    }

    /**
     * String 转 List
     *
     * @param json
     * @param listType
     * @return
     */
    protected List<T> parseToList(String json, Type listType) {
        if (gson == null)
            gson = new Gson();
        return (List<T>) gson.fromJson(json, listType);
    }

    /**
     * String 转 Object
     *
     * @param json
     * @param tClass
     * @return
     */
    protected T parseObject(String json, Class<T> tClass) {
        if (gson == null)
            gson = new Gson();
        return gson.fromJson(json, tClass);
    }

    /**
     * 接口都要加上access_token参数和time参数，用于接口加密
     *
     * @param hashMap
     * @return
     */
    private HashMap<String, String> addBaseParam(HashMap<String, String> hashMap) {
//        long second = System.currentTimeMillis() / 1000;
//        String time = String.valueOf(second);
//
//        //token    //access_token
//        hashMap.put("access_token", MD5Util.getMD5(ACCESSTOKEN + time));
//        hashMap.put("time", time);

        return hashMap;
    }

    private HashMap<String,String> addHeaderParam(){
        HashMap<String,String> hashMap = new HashMap<String,String>();
        long second = System.currentTimeMillis() / 1000;
        String time = String.valueOf(second);

        //token    //access_token
        hashMap.put("access_token", MD5Util.getMD5(ACCESSTOKEN + time));
        hashMap.put("time", time);

        return hashMap;
    }

    /**
     * 处理返回接口返回结果
     *
     * @param response
     * @param callback
     * @param context
     */
    private void processResponse(String response, BaseModelCallBack<String> callback, Context context) {
        //先判断是否有数据返回
        if (!TextUtils.isEmpty(response)) {

            BaseResult baseResult;
            try {
                baseResult = parseBaseResult(response);

                if (baseResult.getStauts() == null){
                    callback.onSuccess(response);

                    return;
                }

                //判断状态码是否成功
                if ("1".equals(baseResult.getStauts())) {
                    callback.onSuccess(response);
                } else {
                    //返回接口错误信息
                    callback.onError(baseResult.getStauts(), baseResult.getMsg());
                }
            } catch (Exception e) {
                e.printStackTrace();
                callback.onError(SERVICE_ERROR, context.getResources().getString(R.string.network_api_error));
            }
        } else {
            //后台无返回
            callback.onError(SERVICE_ERROR, context.getResources().getString(R.string.network_api_error));
        }
    }

    /**
     * @param context
     * @param url
     * @param fileName
     * @param filenPath
     * @param callback
     * @return
     */
    public RequestCall downLoadFile(final Context context, String url, String fileName, String filenPath,
                                    final SimpleFileCallBack<File> callback) {
        if (!NetUtils.isConnected(context)) {
            callback.onError(NETWORK_ERROR, context.getString(R.string.network_connect_error));
            return null;
        }

        final RequestCall call = OkHttpUtils.get()
                .url(url)
                .tag(context)
                .build()
                .connTimeOut(TIME_OUT);

        call.execute(new FileCallBack(filenPath, fileName) {

            @Override
            public void onBefore(Request request) {
                callback.onStart();
            }

            @Override
            public void onAfter() {
                callback.onFinish();
            }

            @Override
            public void inProgress(float progress) {
                callback.inProgress(progress);
            }

            @Override
            public void onError(Call call, Exception e) {
                //后台无返回
                callback.onError(SERVICE_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(File response) {
                callback.onSuccess(response);
            }
        });
        return call;
    }

    /**
     * 基本get请求
     *
     * @param context
     * @param hashMap
     * @param url
     * @param callback
     */
    protected RequestCall get(final Context context, HashMap<String, String> hashMap, String url,
                              final BaseModelCallBack<String> callback) {

        if (!NetUtils.isConnected(context)) {
            callback.onError(NETWORK_ERROR, context.getString(R.string.network_connect_error));
            return null;
        }

        RequestCall call = OkHttpUtils.get()
                .params(addBaseParam(hashMap))
                .url(url)
                .tag(context)
                .build()
                .connTimeOut(TIME_OUT);

        call.execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                LogUtils.e(" 短信 " + response);

                processResponse(response, callback, context);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                callback.onFinish();
            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callback.onStart();
            }

            @Override
            public void onError(Call call, Exception e) {
                callback.onError(NETWORK_ERROR, e.getMessage());
            }


        });

        return call;
    }

    /**
     * 多图上传
     *
     * @param context
     * @param hashMap
     * @param fileHashMap
     * @param url
     * @param callback
     * @return
     */
    protected RequestCall postFiles(final Context context, HashMap<String, String> hashMap,
                                    HashMap<String, File> fileHashMap, String url,
                                    final BaseModelCallBack<String> callback) {

        if (!NetUtils.isConnected(context)) {
            callback.onError(NETWORK_ERROR, context.getString(R.string.network_connect_error));
            return null;
        }

        long second = System.currentTimeMillis() / 1000;
        String time = String.valueOf(second);
       // url = url + "&access_token=" + MD5Util.getMD5(ACCESSTOKEN + time) + "&time=" + time;
        RequestCall call = OkHttpUtils.post()
                .addFiles(fileHashMap)
                .params(hashMap)
                .url(url)
                .tag(context)
//                .addHeader("access_token",addHeaderParam().get("access_token"))
//                .addHeader("time",addHeaderParam().get("time"))
                .build()
                .connTimeOut(30 * 1000);

        call.execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                //先判断是否有数据返回
                processResponse(response, callback, context);
            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callback.onStart();
            }

            @Override
            public void onError(Call call, Exception e) {
                callback.onError(NETWORK_ERROR, e.getMessage());
            }

            @Override
            public void onAfter() {
                super.onAfter();
                callback.onFinish();
            }
        });

        return call;
    }


    /**
     * 多图上传
     *
     * @param context
     * @param hashMap
     * @param
     * @param url
     * @param callback
     * @return
     */
    protected RequestCall postSingleFile(final Context context, HashMap<String, String> hashMap,
                                         File file, String url, String filename,
                                         final BaseModelCallBack<String> callback) {

        if (!NetUtils.isConnected(context)) {
            callback.onError(NETWORK_ERROR, context.getString(R.string.network_connect_error));
            return null;
        }

        long second = System.currentTimeMillis() / 1000;
        String time = String.valueOf(second);
        // url = url + "&access_token=" + MD5Util.getMD5(ACCESSTOKEN + time) + "&time=" + time;
        RequestCall call = OkHttpUtils.post()
                .addFile("image",filename,file)
                .params(hashMap)
                .url(url)
                .tag(context)
//                .addHeader("access_token",addHeaderParam().get("access_token"))
//                .addHeader("time",addHeaderParam().get("time"))
                .build();
//                .connTimeOut(30 * 1000);

        call.execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
              //  LogUtils.e(" 图片上传 " + response);

                //先判断是否有数据返回
               if (!TextUtils.isEmpty(response))
                   callback.onSuccess(response);
            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callback.onStart();
            }

            @Override
            public void onError(Call call, Exception e) {
                callback.onError(NETWORK_ERROR, e.getMessage());
            }

            @Override
            public void onAfter() {
                super.onAfter();
                callback.onFinish();
            }
        });

        return call;
    }




    /**
     * 普通post请求
     *
     * @param context
     * @param hashMap
     * @param url
     * @param callback
     * @return
     */
    protected RequestCall post(final Context context, HashMap<String, String> hashMap, String url, final BaseModelCallBack<String> callback) {

        if (!NetUtils.isConnected(context)) {
            callback.onError(NETWORK_ERROR, context.getString(R.string.network_connect_error));
            return null;
        }

        RequestCall call = OkHttpUtils.post()
                .params(addBaseParam(hashMap))
                .url(url)
                .tag(context)
//                .addHeader("access_token",addHeaderParam().get("access_token"))
//                .addHeader("time",addHeaderParam().get("time"))
                .build()
                .connTimeOut(TIME_OUT);

        call.execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                LogUtils.e(" 个人INFO " + response);

                //先判断是否有数据返回
                processResponse(response, callback, context);

               // LogUtils.e(" post " + response);
            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callback.onStart();
            }

            @Override
            public void onError(Call call, Exception e) {
                callback.onError(NETWORK_ERROR, e.getMessage());
            }

            @Override
            public void onAfter() {
                super.onAfter();
                callback.onFinish();
            }
        });

        return call;
    }


    /**
     * 普通post请求
     *
     * @param context
     * @param hashMap
     * @param url
     * @param callback
     * @return
     */
    protected RequestCall postOtherWay(final Context context, HashMap<String, String> hashMap, String url, final BaseModelCallBack<String> callback) {

        if (!NetUtils.isConnected(context)) {
            callback.onError(NETWORK_ERROR, context.getString(R.string.network_connect_error));
            return null;
        }

        RequestCall call = OkHttpUtils.post()
                .params(addBaseParam(hashMap))
                .url(url)
                .tag(context)
                .addHeader("from-app","tech_wcjs")
//                .addHeader("time",addHeaderParam().get("time"))
                .build()
                .connTimeOut(TIME_OUT);

        call.execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                //先判断是否有数据返回
                if (!TextUtils.isEmpty(response))
                    callback.onSuccess(response);
              //  processResponse(response, callback, context);

                // LogUtils.e(" post " + response);
            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callback.onStart();
            }

            @Override
            public void onError(Call call, Exception e) {
                callback.onError(NETWORK_ERROR, e.getMessage());
            }

            @Override
            public void onAfter() {
                super.onAfter();
                callback.onFinish();
            }
        });

        return call;
    }




    /**
     * 基本get请求
     *
     * @param context
     * @param hashMap
     * @param url
     * @param callback
     */
    protected RequestCall getOtherWay(final Context context, HashMap<String, String> hashMap, String url,
                                      final BaseModelCallBack<String> callback) {

        if (!NetUtils.isConnected(context)) {
            callback.onError(NETWORK_ERROR, context.getString(R.string.network_connect_error));
            return null;
        }

        final RequestCall call = OkHttpUtils.get()
                .params(addBaseParam(hashMap))
                .url(url)
                .tag(context)
                .build()
                .connTimeOut(TIME_OUT);

        call.execute(new StringCallback() {

            @Override
            public void onResponse(String response) {
                LogUtils.e(" response " + response);
                if (!TextUtils.isEmpty(response)){
                    callback.onSuccess(response);
                }else {

                    callback.onError(SERVICE_ERROR, context.getResources().getString(R.string.network_api_error));
                }

               // processResponse(response, callback, context);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                callback.onFinish();
            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                callback.onStart();
            }

            @Override
            public void onError(Call call, Exception e) {
                callback.onError(NETWORK_ERROR, e.getMessage());
            }
        });

        return call;
    }



}
