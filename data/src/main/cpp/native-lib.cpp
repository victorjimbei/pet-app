#include <jni.h>

extern "C" {

JNIEXPORT jstring JNICALL
Java_com_petapp_data_local_ApiKeysLocalSourceImpl_getClientId(
        JNIEnv *env, jobject instance) {
    return env->NewStringUTF("hb8k0LViFXXBg8aqYnqkzffJGvQIxVrVRj8eKx3MgqINmO1b2F");
}

JNIEXPORT jstring JNICALL
Java_com_petapp_data_local_ApiKeysLocalSourceImpl_getClientSecret(
        JNIEnv *env, jobject instance) {
    return env->NewStringUTF("lsGHh9H7zewMtAYXNfmVo311Qce28TYmet384I4k");
}

}
