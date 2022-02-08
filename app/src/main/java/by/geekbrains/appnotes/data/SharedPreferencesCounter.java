package by.geekbrains.appnotes.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesCounter {
    public static final String APP_PREF = "APP_PREF";
    public static final String APP_PREF_COUNTER_KEY = "APP_PREF_COUNTER_KEY";

    private final SharedPreferences sharedPreferences;

    public SharedPreferencesCounter(Context context) {
        this.sharedPreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE);
    }

    public void setCount() {
        Integer count = getCount();
        count++;
        sharedPreferences
                .edit()
                .putInt(APP_PREF_COUNTER_KEY, count)
                .apply();
    }

    public Integer getCount() {
        return sharedPreferences.getInt(APP_PREF_COUNTER_KEY, 0);
    }
}
