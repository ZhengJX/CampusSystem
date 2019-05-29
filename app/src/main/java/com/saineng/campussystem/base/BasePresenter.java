package com.saineng.campussystem.base;

import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mop on 15/12/26.
 */
public abstract class BasePresenter <V extends BaseViewCallBack> {

    protected V view;

    public void setView(V view) {
        this.view = view;
    }

    public void cancleRequest(Context context) {
        OkHttpUtils.getInstance().cancelTag(context);
    }

    protected void clearFileCache(HashMap<String, File> fileHashMap) {
        for (Map.Entry<String, File> entry : fileHashMap.entrySet()) {
            entry.getValue().delete();
        }
    }
}
