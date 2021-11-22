package com.xyys.memorytext.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Toast;

import com.xyys.memorytext.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;


//import io.github.inflationx.viewpump.ViewPumpContextWrapper;
//import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends Activity {

    //**************** Android M Permission (Android 6.0权限控制代码封装)
    private final int permissionRequestCode = 88;
    private PermissionCallback permissionRunnable;

    public interface PermissionCallback {
        void hasPermission();

        void noPermission();
    }


    /**
     * Android M运行时权限请求封装
     *
     * @param permissionDes 权限描述
     * @param runnable      请求权限回调
     * @param permissions   请求的权限（数组类型），直接从Manifest中读取相应的值，比如Manifest.permission.WRITE_CONTACTS
     */
    public void performCodeWithPermission(@NonNull String permissionDes, PermissionCallback runnable, @NonNull String... permissions) {
        if (permissions == null || permissions.length == 0)
            return;
        //        this.permissionrequestCode = requestCode;
        this.permissionRunnable = runnable;
        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || checkPermissionGranted(permissions)) {
            if (permissionRunnable != null) {
                permissionRunnable.hasPermission();
                permissionRunnable = null;
            }
        } else {
            //permission has not been granted.
            requestPermission(permissionDes, permissionRequestCode, permissions);
        }

    }

    private boolean checkPermissionGranted(String[] permissions) {
        boolean flag = true;
        for (String p : permissions) {
            if (ActivityCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private void requestPermission(String permissionDes, final int requestCode, final String[] permissions) {
        if (shouldShowRequestPermissionRationale(permissions)) {
            /*1. 第一次请求权限时，用户拒绝了，下一次：shouldShowRequestPermissionRationale()  返回 true，应该显示一些为什么需要这个权限的说明
            2.第二次请求权限时，用户拒绝了，并选择了“不在提醒”的选项时：shouldShowRequestPermissionRationale()  返回 false
            3. 设备的策略禁止当前应用获取这个权限的授权：shouldShowRequestPermissionRationale()  返回 false*/
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example, if the request has been denied previously.

            //            Snackbar.make(getWindow().getDecorView(), requestName,
            //                    Snackbar.LENGTH_INDEFINITE)
            //                    .setAction(R.string.common_ok, new View.OnClickListener() {
            //                        @Override
            //                        public void onClick(View view) {
            //                            ActivityCompat.requestPermissions(BaseAppCompatActivity.this,
            //                                    permissions,
            //                                    requestCode);
            //                        }
            //                    })
            //                    .show();
            //如果用户之前拒绝过此权限，再提示一次准备授权相关权限
            //            new AlertDialog.Builder(this)     //此处创建对话框会错误提示，You need to use a Theme.AppCompat theme (or descendant) with this activity.
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                    .setTitle("提示")
                    .setMessage(permissionDes + "后——程序才能运行.--------------")
                    .setPositiveButton("授权", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //                            ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
                                    ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
                                }
                            }
                    ).show();

        } else {
            // Contact permissions have not been granted yet. Request them directly.
            ActivityCompat.requestPermissions(BaseActivity.this, permissions, requestCode);
        }
    }

    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        boolean flag = false;
        for (String p : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, p)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == permissionRequestCode) {
            if (verifyPermissions(grantResults)) {
                if (permissionRunnable != null) {
                    permissionRunnable.hasPermission();
                    permissionRunnable = null;
                }
            } else {
                Toast.makeText(this, "暂无权限执行相关操作！", Toast.LENGTH_SHORT).show();
                if (permissionRunnable != null) {
                    permissionRunnable.noPermission();
                    permissionRunnable = null;
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    public boolean verifyPermissions(int[] grantResults) {
        // At least one result must be checked.
        if (grantResults.length < 1) {
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    //********************** END Android M Permission ****************************************
    //    设置为全屏 和 横屏
    public final void setFullScreen() {
        //设置为横屏
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //        noinspection deprecation
        //                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //            Log.d("jpt", "~ >30 :~setFullScreen:~ ");
            Window window = this.getWindow();
            WindowInsetsController windowInsetsController = window.getInsetsController();


            if (windowInsetsController != null) {
                windowInsetsController.hide(WindowInsets.Type.navigationBars());
            }
            //                windowInsetsController.hide(WindowInsets.Type.statusBars());
            //                  windowInsetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        } else {
            //            Window window = this.getWindow();
            //            WindowInsetsController windowInsetsController = window.getInsetsController();
            //            if (windowInsetsController != null) {
            //                windowInsetsController.hide(WindowInsets.Type.navigationBars());
            //                            Log.d("jpt", "~ <30 :~setFullScreen:~ ");
            hideBottomUIMenu();
        }
    }


        //获取系统标题对象，在app创建的 onCreate 的时候隐藏 系统标题
        /*ActionBar actionBar = getSupportActionBar();    //ActionBar在十二章详解
        if (actionBar != null){     //如果系统标题对象不为空
            actionBar.hide();       //隐藏标题栏
        }*/

//    隐藏虚拟按键，并且全屏
    public void hideBottomUIMenu(){
//      第一种方法，调用键盘时会自动显示好像不太好用
        /*if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }*/
//      第二种方法，完全隐藏，但是弹出对话框时还是会显示
        Window _window;
        _window = getWindow();
        WindowManager.LayoutParams params = _window.getAttributes();
        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE;
        _window.setAttributes(params);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("jpt", "~BaseActivity:~ onCreate:~  "+getClass().getSimpleName());
        //管理创建的活动，每创建一个活动就添加
        ActivityCollector.addActivity(this);

        setFullScreen();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("jpt", "~BaseActivity:~ onDestroy:~  "+getClass().getSimpleName());
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("jpt", "~BaseActivity:~ onStart:~  "+getClass().getSimpleName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("jpt", "~BaseActivity:~ onStop:~  "+getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        hideBottomUIMenu();
        Log.d("jpt", "~BaseActivity:~ onResume:~  "+getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("jpt", "~BaseActivity:~ onPause:~  "+getClass().getSimpleName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        hideBottomUIMenu();
        Log.d("jpt", "~BaseActivity:~ onRestart:~  "+getClass().getSimpleName());
    }

//=======活动启动后字体设置，在应用启动也要设置 可以用========
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

//=======活动启动后字体设置，在应用启动也要设置 没有弄明白=======
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
//    }

}

class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static void addActivity(Activity activity) {
        activities.add(activity);
        Log.d("jpt", "~ActivityCollector:~ addActivity:~  "+activity.getLocalClassName());
    }
    public static void removeActivity(Activity activity) {
        Log.d("jpt", "~ActivityCollector:~ removeActivity:~  "+activity.getLocalClassName());
        activities.remove(activity);

    }
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
