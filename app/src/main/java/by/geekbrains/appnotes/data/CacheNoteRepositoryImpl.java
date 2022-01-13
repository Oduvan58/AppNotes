package by.geekbrains.appnotes.data;

import java.util.ArrayList;
import java.util.UUID;

import by.geekbrains.appnotes.domain.NoteEntity;
import by.geekbrains.appnotes.domain.NoteRepository;

public class CacheNoteRepositoryImpl implements NoteRepository {

    private final ArrayList<NoteEntity> cache = new ArrayList<>();

    public CacheNoteRepositoryImpl() {
        cache.addAll(createNotesData());
    }

    @Override
    public ArrayList<NoteEntity> getNotes() {
        return new ArrayList<>(cache);
    }

    @Override
    public void deleteNote(String id) {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getId().equals(id)) {
                cache.remove(i);
                return;
            }
        }
    }

    @Override
    public void addNote(NoteEntity noteEntity) {
        cache.add(noteEntity);
    }

    private static ArrayList<NoteEntity> createNotesData() {
        final ArrayList<NoteEntity> noteEntities = new ArrayList<>();
        noteEntities.add(new NoteEntity(
                UUID.randomUUID().toString(),
                "Тема",
                "Описание",
                ""
        ));
        return noteEntities;
    }
}
