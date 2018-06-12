package com.example.hp.huanxin.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadUtils {
    private static Executor sexecutor = Executors.newSingleThreadExecutor();
    private static Handler sHandler = new Handler(Looper.getMainLooper());


    public static void runOnsubThread(Runnable runnable)
    {
    sexecutor.execute(runnable);
    }
    public static void runOnuiThread(Runnable runnable)
    {
    sHandler.post(runnable);
    }
}
