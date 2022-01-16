package by.geekbrains.appnotes.ui;

import by.geekbrains.appnotes.domain.NoteEntity;

public interface OnNoteListener {

    void onClickNote(NoteEntity noteEntity);

    void onDeleteNote(NoteEntity noteEntity);

    void onAddNote(NoteEntity noteEntity);
}
