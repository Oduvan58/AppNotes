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

    private static ArrayList<NoteEntity> createNotesData() {
        final ArrayList<NoteEntity> noteEntities = new ArrayList<>();
        noteEntities.add(new NoteEntity(
                UUID.randomUUID().toString(),
                "Тема",
                "Описание"
        ));
        return noteEntities;
    }

    @Override
    public ArrayList<NoteEntity> getNotes() {
        return new ArrayList<>(cache);
    }

    @Override
    public void deleteNote(String id) {
        for (int i = 0; i < cache.size(); i++) {
            NoteEntity temp = cache.get(i);
            if (temp.getId().equals(id)) {
                cache.remove(i);
                return;
            }
        }
    }

    @Override
    public void addNote(NoteEntity noteEntity) {
        cache.add(new NoteEntity(UUID.randomUUID().toString(),
                "Тема",
                "Описание"));
    }

    @Override
    public void saveNote(String id, NoteEntity noteEntity) {
        for (int i = 0; i < cache.size(); i++) {
            NoteEntity item = cache.get(i);
            if (item.getId().equals(id)) {
                item.setTitle(noteEntity.getTitle());
                item.setDescription(noteEntity.getDescription());
                return;
            }
        }
    }

    @Override
    public void getDeletedNote(NoteEntity noteEntity) {
        cache.add(noteEntity);
    }
}
