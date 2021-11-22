package com.xyys.memorytrain.view.activitys;

import android.os.Bundle;
import android.util.Log;

import com.xyys.memorytrain.R;
import com.xyys.memorytrain.base.BaseActivity;
import com.xyys.memorytrain.engine.controller.UserController;
import com.xyys.memorytrain.factory.BasicFactory;


public class Advs extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_advs);
        super.onCreate(savedInstanceState);

        BasicFactory basicFactory = BasicFactory.getFactory();
        UserController userController = basicFactory.getInstance(UserController.class);
        Log.d("jpt", "~:~onCreate:~ "+userController.getClass().getSimpleName());

//        getAssets().list("")



    }
}