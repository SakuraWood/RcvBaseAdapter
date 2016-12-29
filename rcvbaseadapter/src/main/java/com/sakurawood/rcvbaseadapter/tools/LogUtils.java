package com.sakurawood.rcvbaseadapter.tools;

import android.util.Log;

public class LogUtils {
	
	static String className;
	static String methodName;
	static int lineNumber;
	static boolean DEBUGGABLE = true; // [应用Log开关]  当为TRUE时应用全局打印LOG,上线时可设置为FALSE
	
    private LogUtils(){
        /* Protect from instantiations */
    }

	public static boolean isDebuggable() {
		return DEBUGGABLE;
	}

	private static String createLog(String log ) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		buffer.append(methodName);
		buffer.append(":");
		buffer.append(lineNumber);
		buffer.append("]");
		buffer.append(log);

		return buffer.toString();
	}
	
	private static void getMethodNames(StackTraceElement[] sElements){
		className = sElements[1].getFileName();
		methodName = sElements[1].getMethodName();
		lineNumber = sElements[1].getLineNumber();
	}

	public static void e(String className, String message){
		if (!isDebuggable())
			return;
		// Throwable instance must be created before any methods  
		Log.e(className, createLog(message));
	}

	public static void i(String className, String message){
		if (!isDebuggable())
			return;

		Log.i(className, createLog(message));
	}
	
	public static void d(String className, String message){
		if (!isDebuggable())
			return;

		Log.d(className, createLog(message));
	}
	
	public static void v(String className, String message){
		if (!isDebuggable())
			return;

		Log.v(className, createLog(message));
	}
	
	public static void w(String className, String message){
		if (!isDebuggable())
			return;

		Log.w(className, createLog(message));
	}
	
	public static void wtf(String className, String message){
		if (!isDebuggable())
			return;
		Log.wtf(className, createLog(message));
	}

}
