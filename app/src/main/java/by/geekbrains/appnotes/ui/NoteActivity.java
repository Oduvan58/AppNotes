package by.geekbrains.appnotes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;
import by.geekbrains.appnotes.domain.NoteRepository;

public class NoteActivity extends AppCompatActivity implements Constants {

    //    public static final String NOTE_EXTRA_KEY = "show_note";
    NoteRepository noteRepository;
    NoteEntity noteEntity;
    private EditText titleNoteEditText;
    private EditText descriptionNoteEditText;
    private Button saveNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initViews();
        noteEntity = getIntent().getParcelableExtra(NOTE_EXTRA_KEY);
        getInfoNote(noteEntity);

        saveNoteButton.setOnClickListener(v -> {
            Intent intentIO = new Intent();
            intentIO.putExtra(NOTE_EXTRA_KEY, getNote());
            setResult(Activity.RESULT_OK, intentIO);
            finish();

        });
    }

    private void initViews() {
        titleNoteEditText = findViewById(R.id.title_note_edit_text);
        descriptionNoteEditText = findViewById(R.id.description_note_edit_text);
        saveNoteButton = findViewById(R.id.save_note_button);
    }

    private void getInfoNote(NoteEntity noteEntity) {
        titleNoteEditText.setText(noteEntity.getTitle());
        descriptionNoteEditText.setText(noteEntity.getDescription());
    }

    private NoteEntity getNote() {
        String title = titleNoteEditText.getText().toString();
        String description = descriptionNoteEditText.getText().toString();
        noteEntity.setTitle(title);
        noteEntity.setDescription(description);
        return noteEntity;
    }
}