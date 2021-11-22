package com.xyys.memorytrain.base;


import android.app.Application;
import android.content.Context;
import android.util.Log;

//import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

//import io.github.inflationx.calligraphy3.CalligraphyConfig;
//import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
//import io.github.inflationx.viewpump.ViewPump;

//import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @ProjectName: DivinePower
 * @Package: com.example.divinepower
 * @ClassName: BaseApplications
 * @Description: java类作用描述
 * @Author:
 */
public class BaseApplication extends Application {

    private static Context context;
    private static BaseApplication instance;

    public static BaseApplication getInstance(){
        return instance;
    }

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;


//======= 应用启动时的字体设置 没有弄明白=======
//        ViewPump.init(ViewPump.builder()
//                .addInterceptor(new CalligraphyInterceptor(
//                        new CalligraphyConfig.Builder()
//                                .setDefaultFontPath("fonts/xinkai.ttf")
//                                .setFontAttrId(R.attr.fontPath)
//                                .build()))
//                .build());

//=======应用启动时的字体设置 可以用=======
        /*CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/xinkai.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );*/

        Log.d("jpt", "~BaseApplication:~ onCreate:~  这里初始化数据");

    }
}
