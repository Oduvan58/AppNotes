package by.geekbrains.appnotes.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE_EXTRA_KEY = "show_note";

    private TextView titleNoteTextView;
    private TextView descriptionNoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initViews();
        NoteEntity noteEntity = getIntent().getParcelableExtra(NOTE_EXTRA_KEY);
        getInfoNote(noteEntity);
    }

    private void initViews() {
        titleNoteTextView = findViewById(R.id.title_note_text_view);
        descriptionNoteTextView = findViewById(R.id.description_note_text_view);
    }

    private void getInfoNote(NoteEntity noteEntity) {
        titleNoteTextView.setText(noteEntity.getTitle());
        descriptionNoteTextView.setText(noteEntity.getDescription());
    }
}