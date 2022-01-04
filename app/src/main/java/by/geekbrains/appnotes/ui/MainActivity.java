package by.geekbrains.appnotes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;

    private final ArrayList<NoteEntity> noteList = new ArrayList<>();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteList.addAll(createNotesData());

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter();
        adapter.setData(noteList);
        recyclerView.setAdapter(adapter);
    }
}