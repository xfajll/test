package com.xf.twoproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{
    private String emptyWord = "";
    int num = 0;
    int num2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //注册监听事件
        findViewById(R.id.textView17).setOnClickListener(this);
        findViewById(R.id.editTextTextPersonName).setOnClickListener(this);
        findViewById(R.id.editTextTextPersonName2).setOnClickListener(this);
        findViewById(R.id.textView23).setOnClickListener(this);
        findViewById(R.id.textView24).setOnClickListener(this);
        ((TextView)findViewById(R.id.textView23)).setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        TextView word1 = findViewById(R.id.editTextTextPersonName);
        TextView word2 = findViewById(R.id.editTextTextPersonName2);
        if (((TextView)v).getText().toString().equals("主画面")){
            translate(v);
        }else if (((TextView)v).getText().toString().equals("开始测试")){
            if (!TextUtils.isEmpty(word1.getText()) && !TextUtils.isEmpty(word2.getText())){
                testExample(v);
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("请输入表达式和期望值！或导入用例！");
                builder.setPositiveButton("确定", null);
                final AlertDialog alertdialog1 = builder.create();
                alertdialog1.show();
            }
        }

    }
    //页面跳转
    public void translate(View v){
        Intent intent = new Intent(TestActivity.this, MainActivity.class);
        startActivity(intent);
    }

    //单个用例的输入
    public void testExample(View v){
        TextView word1 = findViewById(R.id.editTextTextPersonName);
        TextView word2 = findViewById(R.id.editTextTextPersonName2);
        TextView outPut = findViewById(R.id.textView23);
        TextView exampleNum = findViewById(R.id.textView29);
        String s = word1.getText().toString();
        String s1 = word2.getText().toString();
        double rs = new JNI().calculate(s);
        DecimalFormat decimalFormat = new DecimalFormat("#######.#######");
        String format = decimalFormat.format(rs);
        if (s1.equals(format)){
            num2++;
        }else{
            System.out.println(num2);
        }
        num++;
        String s2 = String.valueOf(num);
        emptyWord+=("测试用例"+s2+"：表达式"+s+"期望值:"+s1+"实际计算值:"+format+"\n");
        outPut.setText(emptyWord);
        float a = (float) num2 / num * 100;
        exampleNum.setText("当前用例数"+String.valueOf(num)+"个"+"\u3000\u3000\u3000"+"通过率为"+String.valueOf(a)+"%");
    }

    //读取csv文件，计算个数，通过率
    private void readFiles() {
        int num1 = 0;
        int num2 =0;
        TextView outPut = findViewById(R.id.textView23);
        TextView a = findViewById(R.id.textView29);
        InputStreamReader is;
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        try {
            is = new InputStreamReader(getAssets().open("example.csv"));
            BufferedReader reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(",");
                map.put(strings[0],strings[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : map.keySet()) {
            double v = new JNI().calculate(s);
            DecimalFormat decimalFormat = new DecimalFormat("#######.#######");
            String format = decimalFormat.format(v);
            //String s1 = String.valueOf(v);
            num1++;
            if (map.get(s).equals(format)){
                num2++;
            }else{

            }
            emptyWord+="测试用例"+num1+"：表达式"+s+"期望值:"+map.get(s)+"实际计算值:"+format+"\n";
        }
        outPut.setText(emptyWord);
        float c = (float) num2 / num1 * 100;
        a.setText("当前用例数"+String.valueOf(num1)+"个"+"\u3000\u3000\u3000"+"通过率为"+String.valueOf(c)+"%");



    }

    //导入文件
    public void inTouch(View view) {
        //调出文件选择窗口
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，这里设置的是全部类型，没有过滤其他类型的文件
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 1);
        //使用本地文件测试
        readFiles();
    }
    //导出文件
    public void outTouch(View view) {
        /*try {
            FileOutputStream fos = new FileOutputStream("exampleOut.txt");
            System.out.println(emptyWord);
            fos.write(emptyWord.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
