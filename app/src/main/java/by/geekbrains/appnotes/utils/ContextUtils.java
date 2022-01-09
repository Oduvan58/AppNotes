package by.geekbrains.appnotes.utils;

import android.content.Context;

import by.geekbrains.appnotes.App;

public class ContextUtils {

    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }
}
