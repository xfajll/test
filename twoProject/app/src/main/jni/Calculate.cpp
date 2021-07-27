#include <jni.h>
#include <string>
#include<iostream>
#include "Counter.h"



using std::string;
extern "C"
JNIEXPORT jdouble JNICALL
Java_com_xf_twoproject_JNI_calculate(JNIEnv *env, jobject instance, jstring s_) {
    const char *s = env->GetStringUTFChars(s_, 0);
    FourArithmeticOP *c=new FourArithmeticOP();
    c->InorderToPost(const_cast<char *>(s));
    double d=c->Calculate();
    delete c;
    return d;
}
