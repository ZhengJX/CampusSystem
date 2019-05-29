package com.saineng.campussystem.utils;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by mop on 15/12/23.
 */
public class SDCardUtils
{
    public static final String FILE_NAME = "JX";
    public static final String TEMP = "temp";
    public static final String UPDATE = "update";

    private SDCardUtils() {
        /** cannot be instantiated **/
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 临时文件夹
     *
     * @return
     */
    public static String getAppTempDataPath() {
        String rev = getSDCardPath() + FILE_NAME + File.separator + TEMP + File.separator;
        isHavePath(rev);
        return rev;
    }

    /**
     * 存放apk文件夹
     *
     * @return
     */
    public static String getAppUpdatePath() {
        String rev = getSDCardPath() + FILE_NAME + File.separator + UPDATE + File.separator;
        isHavePath(rev);
        return rev;
    }

    /**
     * 检测SD卡文件路径是否存在，不存在则创建
     *
     * @param path 文件路径
     */
    public static void isHavePath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }


    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());

// 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;

// 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }


    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {

// 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {
// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }


    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }


}
