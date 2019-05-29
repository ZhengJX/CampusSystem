package com.saineng.campussystem.models;

/**
 * Created by mop on 15/12/26.
 */
public interface BaseModelCallBack <T> {
    void onStart();

    void onError(String code, String msg);

    void onSuccess(T t);

    void onFinish();
}
