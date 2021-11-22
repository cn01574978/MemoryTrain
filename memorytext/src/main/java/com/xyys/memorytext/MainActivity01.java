package com.xyys.memorytext;

import android.os.Bundle;
import android.util.Log;

import com.xyys.memorytext.Engine.UserEngine;
import com.xyys.memorytext.base.BaseActivity;
import com.xyys.memorytext.bean.User;
import com.xyys.memorytext.factory.BasicFactory;
import com.xyys.memorytext.net.xmlProtocal.Message;
import com.xyys.memorytext.net.xmlProtocal.element.CurrentIssueElement;

import java.util.LinkedList;

public class MainActivity01 extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.il_main);
        super.onCreate(savedInstanceState);
//                boolean b= NetUtil.checkNet(MainActivity.this);

        new Thread(networkTask).start();
//        System.currentTimeMillis();
        createXMl();
//        LinkedList

    }

    /*Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //这里接收网络请求返回数据消息 更新UI
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            // TODO
            // UI界面的更新等相关操作
        }
    };*/

        Runnable networkTask = new Runnable() {

        @Override
        public void run() {
//            // TODO
//            // 在这里进行 http request.网络请求相关操作
//            Message msg = new Message();
//            Bundle data = new Bundle();
//            data.putString("value", "请求结果");
//            msg.setData(data);
//            handler.sendMessage(msg);
            testUserLogin();
        }
    };

    public void createXMl() {
        Message message = new Message();
        CurrentIssueElement element = new CurrentIssueElement();
        element.getLotteryid().setTagValue("118");
        String xml = message.getXml(element);
        Log.d("jpt", "~:~createXMl:~ " + xml);
    }

    public void testUserLogin(){
        /*
        UserEngineImpl impl = new UserEngineImpl();
        User user = new User();
        user.setUsername("111111");
        user.setPassword("333333");
        Message lonin = impl.login(user);
        Log.i(TAG, "testUserLogin: " + lonin.getBody().getOelement().getErrorcode());
        */
        BasicFactory basicFactory = BasicFactory.getFactory();
        UserEngine impl = basicFactory.getInstance(UserEngine.class);
        User user = new User();
        user.setUsername("111111");
        user.setPassword("333333");
        Message lonin = impl.login(user);
        Log.i(TAG, "testUserLogin: " + lonin.getBody().getOelement().getErrormsg());

    }
}