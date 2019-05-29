package com.saineng.campussystem.models;

import android.content.Context;

import com.saineng.campussystem.utils.LogUtils;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.HashMap;

/**
 * Created by ${charles}     on 2017/6/5.
 *
 * @desc ${TODO}
 */

public class CTModel extends BaseModel
{
    private static CTModel model;

    private final String TAG = CTModel.class.getSimpleName();

    private CTModel() {
    }

    public static CTModel getInstance() {
        if (model == null) {
            model = new CTModel();
        }
        return model;
    }

    public RequestCall getOrderData(final Context context,
                                    final BaseModelCallBack<String> callBack){
        HashMap<String, String> hashMap = new HashMap<>();


        return get(context, hashMap, URL + "index", new BaseModelCallBack<String>()
        {
            @Override
            public void onStart()
            {
                callBack.onStart();
            }

            @Override
            public void onError(String code, String msg)
            {
                callBack.onError(code,msg);
            }

            @Override
            public void onSuccess(String s)
            {
                LogUtils.e(" s " + s);
                callBack.onSuccess(s);
            }

            @Override
            public void onFinish()
            {
                callBack.onFinish();
            }
        });
    }
}
