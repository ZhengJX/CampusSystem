package com.saineng.campussystem.activitys.presenter;

import android.content.Context;

import com.saineng.campussystem.base.BasePresenter;
import com.saineng.campussystem.base.BaseViewCallBack;
import com.saineng.campussystem.models.CTModel;
import com.saineng.campussystem.models.SimpleFileCallBack;
import com.saineng.campussystem.models.SimpleModelCallBack;
import com.saineng.campussystem.utils.SDCardUtils;

import java.io.File;

/**
 * Created by ${charles}     on 2017/6/5.
 *
 * @desc ${TODO}
 */

public class MainPImpl extends BasePresenter
{

    public void getOrderData(final Context context,
                             final BaseViewCallBack<String> callBack){

        CTModel.getInstance().getOrderData(context, new SimpleModelCallBack<String>()
        {
            @Override
            public void onError(String code, String msg)
            {
                callBack.showErrorView(msg);

            }

            @Override
            public void onSuccess(String s)
            {
                callBack.showSuccessView(s);
            }
        });

    }


    public void downLoadFile(final Context context, String url, String fileName,
                             final SimpleFileCallBack<File> callback) {
        String path = SDCardUtils.getAppUpdatePath() + File.separator;
        CTModel.getInstance().downLoadFile(context, url, fileName, path, callback);
    }
}
