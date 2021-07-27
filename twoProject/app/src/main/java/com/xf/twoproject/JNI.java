package com.xf.twoproject;



public class JNI {
        {
            //加载
            System.loadLibrary("Calculate");
        }



        public native double calculate(String data);



}
