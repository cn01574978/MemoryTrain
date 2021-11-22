package com.xyys.memorytrain;

import android.os.Bundle;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import androidx.appcompat.app.AppCompatActivity;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        StringUtils.isNotBlank(DigestUtils.md5Hex("123"));
//        getPackageManager()
    }
}