package by.geekbrains.appnotes.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import by.geekbrains.appnotes.App;
import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;
import by.geekbrains.appnotes.domain.NoteRepository;
import by.geekbrains.appnotes.ui.OnNoteListener;
import by.geekbrains.appnotes.ui.details.NoteActivity;

public class NotesListFragment extends Fragment {

    private NoteRepository noteRepository;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private FloatingActionButton addButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteRepository = App.get().noteRepository;

        addButton = view.findViewById(R.id.fragment_notes_list__add_note_button);

        initRecycler(view);
    }

    private void initRecycler(@NonNull View view) {
        recyclerView = view.findViewById(R.id.fragment_notes_list__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NoteAdapter();
        adapter.setData(App.get().noteRepository.getNotes());
        adapter.setOnNoteListener(new OnNoteListener() {
            @Override
            public void onClickNote(NoteEntity noteEntity) {
                Intent intent = new Intent(getContext(), NoteActivity.class);
                intent.putExtra(NoteActivity.NOTE_EXTRA_KEY, noteEntity);
                startActivity(intent);
//                startActivityForResult(intent, Constants.NOTE_EXTRA_KEY);
            }

//            @Override
//            protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//                super.onActivityResult(requestCode, resultCode, data);
//                if (requestCode == Constants.NOTE_EXTRA_KEY && resultCode == Activity.RESULT_OK) {
//                    NoteEntity noteEntity = data.getParcelableExtra(Constants.NOTE_EXTRA_KEY);
//                    noteRepository.saveNote(noteEntity.getId(), noteEntity);
//                    adapter.saveNote(noteEntity.getId(), noteEntity);
//                }
//            }

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
        });
        recyclerView.setAdapter(adapter);
    }
}
