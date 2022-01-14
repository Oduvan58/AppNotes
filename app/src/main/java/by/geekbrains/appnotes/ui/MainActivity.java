package by.geekbrains.appnotes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import by.geekbrains.appnotes.App;
import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;
import by.geekbrains.appnotes.domain.NoteRepository;

public class MainActivity extends AppCompatActivity implements OnNoteListener, Constants {
    private static final int NOTE_REQUEST_CODE = 28;

    private NoteRepository noteRepository;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteRepository = App.get(this).getNoteRepo();

        addButton = findViewById(R.id.add_note_button);

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter();
        adapter.setData(noteRepository.getNotes());
        adapter.setOnNoteListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickNote(NoteEntity noteEntity) {
        Intent intent = new Intent(this, NoteActivity.class);
        intent.putExtra(NoteActivity.NOTE_EXTRA_KEY, noteEntity);
        startActivityForResult(intent, NOTE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data.getParcelableExtra(NOTE_EXTRA_KEY);
            adapter.setData(noteRepository.getNotes());
        }
    }

    @Override
    public void onDeleteNote(NoteEntity noteEntity) {
        noteRepository.deleteNote(noteEntity.getId());
        adapter.deleteNote(noteEntity.getId());
    }

    @Override
    public void onAddNote(NoteEntity noteEntity) {
        addButton.setOnClickListener(v -> {
            noteRepository.addNote(noteEntity);
            adapter.addNote(noteEntity);
        });
    }
}