package by.geekbrains.appnotes.domain;

import java.util.ArrayList;

public interface NoteRepository {

    ArrayList<NoteEntity> getNotes();

    void deleteNote(String id);

    void addNote(NoteEntity noteEntity);

    void saveNote(String id, NoteEntity noteEntity);

    void getDeletedNote(NoteEntity noteEntity);
}
