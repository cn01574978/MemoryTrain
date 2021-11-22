
package com.xyys.memorytext.factory;

import com.xyys.memorytext.base.BaseApplication;

import java.util.Properties;

/**
 * @ProjectName: MemoryTrain
 * @Package: com.xyys.memorytrain.factory
 * @ClassName: BasicFactory
 * @Description: 基本工厂
 * @Author: 完美中正
 * @CreateDate: 2021/10/28 21:52
 * @Version: 1.0
 */
public class BasicFactory {
    private static BasicFactory mFactory = new BasicFactory();

    private static Properties sProperties = null;

//    private static String path;

    private BasicFactory() {}

    static {
        sProperties = new Properties();
        try {
            /**
             * path = BasicFactory.class.getClassLoader().getResource("config/factory").getPath();
             * path 是 在servlet服务器 中使用时 通过反射获取路径
             */

            //            path = "file:///android_asset/config/factory";
            //            sProperties.load(new FileReader(path));
            //            sProperties.load(new FileReader());
            /**
             * 需要在BaseApplication中封装获取实例方法 getInstance()
             */
            sProperties.load(BaseApplication.getInstance().getAssets().open("config/class.properties"));


        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



//    public static String getPath() {
//        return path;
//    }

    public static BasicFactory getFactory() {
        return mFactory;
    }



    public static <T> T getInstance(Class<T> tClass) {
        try {
            String infName = tClass.getSimpleName();
            String implName = sProperties.getProperty(infName);
            return (T) Class.forName(implName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
