package by.geekbrains.appnotes.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE_EXTRA_KEY = "show_note";

    private EditText titleNoteEditText;
    private EditText descriptionNoteEditText;
    private Button saveNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initViews();
        NoteEntity noteEntity = getIntent().getParcelableExtra(NOTE_EXTRA_KEY);
        getInfoNote(noteEntity);
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
}