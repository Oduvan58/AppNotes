package by.geekbrains.appnotes;

import android.app.Application;
import android.content.Context;

import by.geekbrains.appnotes.data.CacheNoteRepositoryImpl;
import by.geekbrains.appnotes.domain.NoteRepository;

public class App extends Application {
    private static App sInstance;
    public final NoteRepository noteRepository = new CacheNoteRepositoryImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App get() {
        return sInstance;
    }
}
