package by.geekbrains.appnotes.ui;

import android.view.View;

import by.geekbrains.appnotes.domain.NoteEntity;

public interface OnNoteListener {

    void onClickNote(NoteEntity noteEntity);

    boolean onLongClickNote(NoteEntity noteEntity, View itemView);

    void onDeleteNote(NoteEntity noteEntity);

    void onAddNote(NoteEntity noteEntity);
}
