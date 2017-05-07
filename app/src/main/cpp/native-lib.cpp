#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_altech_acs_basicandoid_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Please wait... (oh by the way, this string comes from C++!)";
    return env->NewStringUTF(hello.c_str());
}
