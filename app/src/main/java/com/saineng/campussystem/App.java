package com.saineng.campussystem;

import android.app.Application;
import android.content.Context;

import com.saineng.dao.DaoMaster;
import com.saineng.dao.DaoSession;

/**
 * Created by ${charles}     on 2017/6/5.
 *
 * @desc ${TODO}
 */

public class App extends Application
{
    private static App mInstance;
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        if(mInstance == null)
            mInstance = this;
    }

    /**
     * 取得DaoMaster
     *
     * @param context        上下文
     * @return               DaoMaster
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,"myDb",null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context        上下文
     * @return               DaoSession
     */
    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }


}
