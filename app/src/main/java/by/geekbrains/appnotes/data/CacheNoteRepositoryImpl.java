package by.geekbrains.appnotes.data;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import java.util.ArrayList;

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
    public void addNote(NoteEntity noteEntity) {

    }

    private static ArrayList<NoteEntity> createNotesData() {
        final ArrayList<NoteEntity> noteEntities = new ArrayList<>();
        noteEntities.add(new NoteEntity(
                "Shop",
                "Milk, juice, bread",
                "04.01.2022"
        ));
        noteEntities.add(new NoteEntity(
                "Meetings",
                "Mother, brother, wife",
                "07.01.2022"
        ));
        noteEntities.add(new NoteEntity(
                "Travel",
                "Spain, Germany, China",
                "28.05.2022"
        ));
        noteEntities.add(new NoteEntity(
                "Repair",
                "Car, house, watch",
                "10.03.2022"
        ));
        noteEntities.add(new NoteEntity(
                "Job",
                "Write a report, pass a test",
                "19.01.2022"
        ));
        noteEntities.add(new NoteEntity(
                "Investment",
                "Bitcoin, tokens, stocks",
                "24.04.2022"
        ));
        return noteEntities;
    }

}
