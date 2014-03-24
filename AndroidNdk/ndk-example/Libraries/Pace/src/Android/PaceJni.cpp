#include <jni.h>
#include "Pace.h"

JNIEnv *PaceAndroid_jenv = 0;
jclass PaceAndroid_jcls = 0;
jobject PaceAndroid_jobj = 0;
JavaVM *gJavaVM;

#include <android/log.h>

extern "C"
{

jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
	JNIEnv *env;
	gJavaVM = vm;

	if (vm->GetEnv((void**) &env, JNI_VERSION_1_4) != JNI_OK) {
		return -1;
	}

	return JNI_VERSION_1_4;
}

/*
 * Class:     edu_pace_PaceJni
 * Method:    initializeJni
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_edu_pace_PaceJni_initializeJni
  (JNIEnv *env, jobject obj, jstring ServerAddr)
{
	char *server = 0;
	if (ServerAddr!=0)server= (char*)env->GetStringUTFChars(ServerAddr, 0);
	__android_log_print(ANDROID_LOG_DEBUG  , "JNI Native C Code %s", server);
	PaceAndroid_jenv = env;
	PaceAndroid_jobj = obj;
	jclass cls = env->GetObjectClass(PaceAndroid_jobj); // Lifetime of this?
	PaceAndroid_jcls = (jclass) env->NewGlobalRef(cls);
}

}
