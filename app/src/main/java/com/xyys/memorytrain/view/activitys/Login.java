package com.xyys.memorytrain.view.activitys;

import android.os.Bundle;
import com.xyys.memorytrain.R;
import com.xyys.memorytrain.base.BaseActivity;

public class Login extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        super.onCreate(savedInstanceState);
    }

    /**
     * 提交用户名 密码 登录？
     * 成功 记录用户信息  并 跳转到等级页面  （根据信息显示）
     * 不成功 提示是否注册 或 游客 模式
     */

}