package by.geekbrains.appnotes;

import android.app.Application;

import by.geekbrains.appnotes.data.CacheNoteRepositoryImpl;
import by.geekbrains.appnotes.data.SharedPreferencesNoteRepositoryImpl;
import by.geekbrains.appnotes.domain.NoteRepository;

public class App extends Application {
    private static App sInstance;
    public NoteRepository noteRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        noteRepository = new SharedPreferencesNoteRepositoryImpl(this);
        sInstance = this;
    }

    public static App get() {
        return sInstance;
    }
}
