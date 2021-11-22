package com.xyys.memorytext.net;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.xyys.memorytext.constant.GlobalParams;

/**
 * @ProjectName: MemoryTrain
 * @Package: com.xyys.memorytext.net
 * @ClassName: NetUtil
 * @Description: 检查用户上网网络
 * @Author: 完美中正
 * @CreateDate: 2021/11/2 18:29
 * @Version: 1.0
 */
public class NetUtil {
    /**
     * 检查用户网络:是否有网络
     */
    public static boolean checkNet(Context context){
        /**
         * 判断是否WiFi链接
         */
        boolean isWIFI = isWIFIConection(context);
        /**
         * 判断是否Mobile链接 数据连接
         */
        boolean isMOBILE = isMOBILEConection(context);

        /**
         * 如果是Mobile链接 判断哪个APN选中 wap 或者 是 net
         */
        if (isMOBILE) {
            /**
             * 如果是wap方式，代理信息是否有内容
             */
            readAPN(context);
        }

        if (!isMOBILE && !isWIFI) {
            return false;
        }

        return true;
    }

    /**
     * 如果是wap方式，代理信息是否有内容
     * @param context
     */
    private static void readAPN(Context context) {
//        Uri PREFERRED_APN_URI = Uri.parse("content://telephony/carriers/preferapn");//4.0模拟器屏蔽掉该权限
//
//        // 操作联系人类似
//        ContentResolver resolver = context.getContentResolver();
//        // 判断是哪个APN被选中了
//        Cursor cursor = resolver.query(PREFERRED_APN_URI, null, null, null, null);
//
//        if(cursor!=null&&cursor.moveToFirst())
//        {
//            GlobalParams.PROXY=cursor.getString(cursor.getColumnIndex("proxy"));
//            GlobalParams.PORT=cursor.getInt(cursor.getColumnIndex("port"));
//        }

    }

    /**
     * 判断是否WiFi链接
     * @param context
     * @return
     */
    private static boolean isWIFIConection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (networkInfo != null) {
            return networkInfo.isConnected();
        }
        return false;
    }

    /**
     * 判断是否Mobile链接 数据连接
     * @param context
     * @return
     */
    private static boolean isMOBILEConection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (networkInfo != null) {
            return networkInfo.isConnected();
        }

        return false;
    }


}

