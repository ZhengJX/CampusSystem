package com.saineng.campussystem.base;

/**
 * 基础回调接口
 * Created by mop on 15/12/26.
 */
public interface BaseViewCallBack<T> {
    /**
     * 网络请求开始界面回调
     */
    void showStartView();

    /**
     * 网络请求错误界面回调
     *
     * @param msg
     */
    void showErrorView(String msg);

    /**
     * 网络请求成功界面回调
     *
     * @param t
     */
    void showSuccessView(T t);

    /**
     * 请求完成
     */
    void showFinishView();
}
