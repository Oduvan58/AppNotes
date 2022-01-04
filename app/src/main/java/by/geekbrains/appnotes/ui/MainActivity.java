package by.geekbrains.appnotes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.data.CacheNoteRepositoryImpl;
import by.geekbrains.appnotes.domain.NoteRepository;

public class MainActivity extends AppCompatActivity {

    private final NoteRepository noteRepository = new CacheNoteRepositoryImpl();
    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter();
        adapter.setData(noteRepository.getNotes());
        recyclerView.setAdapter(adapter);
    }
}