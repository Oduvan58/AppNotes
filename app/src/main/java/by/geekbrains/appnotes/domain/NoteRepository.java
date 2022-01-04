package by.geekbrains.appnotes.domain;

import java.util.ArrayList;

public interface NoteRepository {

    ArrayList<NoteEntity> getNotes();

    void deleteNote(NoteEntity noteEntity);
}
