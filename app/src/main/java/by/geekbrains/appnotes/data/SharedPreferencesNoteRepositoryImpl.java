package by.geekbrains.appnotes.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

import by.geekbrains.appnotes.domain.NoteEntity;
import by.geekbrains.appnotes.domain.NoteRepository;

public class SharedPreferencesNoteRepositoryImpl implements NoteRepository {
    private static final String SHARED_PREFS_NAME = "SHARED_PREFS_NAME";
    private static final String SHARED_PREFS_NOTES_KEY = "SHARED_PREFS_NOTES_KEY";

    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();

    public SharedPreferencesNoteRepositoryImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public ArrayList<NoteEntity> getNotes() {
        final String notesJson = sharedPreferences.getString(SHARED_PREFS_NOTES_KEY, "");
        if (notesJson != null && !notesJson.equals("")) {
            Type type = new TypeToken<ArrayList<NoteEntity>>() {
            }.getType();
            return gson.fromJson(notesJson, type);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteNote(String id) {
        final ArrayList<NoteEntity> data = getNotes();
        for (int i = 0; i < data.size(); i++) {
            NoteEntity temp = data.get(i);
            if (temp.getId().equals(id)) {
                data.remove(i);
                break;
            }
        }

        final String jsonString = gson.toJson(data);

        sharedPreferences
                .edit()
                .putString(SHARED_PREFS_NOTES_KEY, jsonString)
                .apply();
    }

    @Override
    public void addNote(NoteEntity noteEntity) {
        final ArrayList<NoteEntity> data = getNotes();
        data.add(new NoteEntity(UUID.randomUUID().toString(),
                "Тема",
                "Описание"));

        final String jsonString = gson.toJson(data);

        sharedPreferences
                .edit()
                .putString(SHARED_PREFS_NOTES_KEY, jsonString)
                .apply();
    }

    @Override
    public void saveNote(String id, NoteEntity noteEntity) {
        final ArrayList<NoteEntity> data = getNotes();

        for (int i = 0; i < data.size(); i++) {
            NoteEntity item = data.get(i);
            if (item.getId().equals(id)) {
                item.setTitle(noteEntity.getTitle());
                item.setDescription(noteEntity.getDescription());
                break;
            }
        }
        final String jsonString = gson.toJson(data);

        sharedPreferences
                .edit()
                .putString(SHARED_PREFS_NOTES_KEY, jsonString)
                .apply();
    }

    @Override
    public void getDeletedNote(NoteEntity noteEntity) {
        final ArrayList<NoteEntity> data = getNotes();
        data.add(noteEntity);

        final String jsonString = gson.toJson(data);

        sharedPreferences
                .edit()
                .putString(SHARED_PREFS_NOTES_KEY, jsonString)
                .apply();
    }
}
