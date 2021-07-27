package com.xf.twoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String emptyText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册点击事件
        findViewById(R.id.textView3).setOnClickListener(this);
        findViewById(R.id.textView4).setOnClickListener(this);
        findViewById(R.id.textView5).setOnClickListener(this);
        findViewById(R.id.textView6).setOnClickListener(this);
        findViewById(R.id.textView7).setOnClickListener(this);
        findViewById(R.id.textView10).setOnClickListener(this);
        findViewById(R.id.textView11).setOnClickListener(this);
        findViewById(R.id.textView18).setOnClickListener(this);
        findViewById(R.id.textView8).setOnClickListener(this);
        findViewById(R.id.textView12).setOnClickListener(this);
        findViewById(R.id.textView13).setOnClickListener(this);
        findViewById(R.id.textView19).setOnClickListener(this);
        findViewById(R.id.textView9).setOnClickListener(this);
        findViewById(R.id.textView14).setOnClickListener(this);
        findViewById(R.id.textView15).setOnClickListener(this);
        findViewById(R.id.textView20).setOnClickListener(this);
        findViewById(R.id.textView2).setOnClickListener(this);
        findViewById(R.id.textView16).setOnClickListener(this);
        findViewById(R.id.textView21).setOnClickListener(this);
        findViewById(R.id.textView).setOnClickListener(this);
        findViewById(R.id.textView17).setOnClickListener(this);
        findViewById(R.id.textView22).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TextView text = findViewById(R.id.process_textview);
        TextView result = findViewById(R.id.result_textview);
        if (((TextView) v).getText().toString().equals("AC")) {
            clearAll(v);
        } else if (((TextView) v).getText().toString().equals("BACK")) {
            backOne(v);
        } else if (((TextView) v).getText().toString().equals("=")) {
            CharSequence text1 = text.getText();
            String data = text1.toString();
            double v1 = new JNI().calculate(data);
            DecimalFormat decimalFormat = new DecimalFormat("#######.#######");
            String format = decimalFormat.format(v1);
            result.setText(format);

        } else if (((TextView) v).getText().toString().equals("测试画面")) {
            translate2(v);
        } else if (((TextView) v).getText().toString().equals("主画面")) {

        } else {
            emptyText += ((TextView) v).getText();
            text.setText(emptyText);
        }


    }
    //删除
    public void backOne(View view) {
        TextView text = findViewById(R.id.process_textview);
        if (emptyText != null && emptyText.length() > 0) {
            emptyText = emptyText.substring(0, emptyText.length() - 1);
            text.setText(emptyText);
        } else {
            clearAll(view);

        }
    }
    //清空
    public void clearAll(View view) {
        emptyText = "";
        TextView textView = (TextView) findViewById(R.id.process_textview);
        TextView result = findViewById(R.id.result_textview);
        textView.setText(emptyText);
        result.setText("");
    }

    //跳转
    public void translate2(View view) {
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        startActivity(intent);

    }


}