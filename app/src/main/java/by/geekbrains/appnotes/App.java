package by.geekbrains.appnotes;

import android.app.Application;
import android.content.Context;

import by.geekbrains.appnotes.data.CacheNoteRepositoryImpl;
import by.geekbrains.appnotes.domain.NoteRepository;

public class App extends Application {
    private NoteRepository noteRepository = new CacheNoteRepositoryImpl();

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    public NoteRepository getNoteRepo() {
        return null;
    }
}
