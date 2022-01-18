package by.geekbrains.appnotes.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;

public class NoteFragment extends Fragment {

    private static final String NOTE_ARG_KEY = "NOTE_ARG_KEY";

    NoteEntity noteEntity;
    private EditText titleNoteEditText;
    private EditText descriptionNoteEditText;
    private Button saveNoteButton;

    public static NoteFragment newInstance(NoteEntity noteEntity) {
        NoteFragment fragment = new NoteFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(NOTE_ARG_KEY, noteEntity);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        noteEntity = getArguments().getParcelable(NOTE_ARG_KEY);
        getInfoNote(noteEntity);

//        saveNoteButton.setOnClickListener(v -> {
//            Intent intentIO = new Intent();
//            intentIO.putExtra(NOTE_EXTRA_KEY, getNote());
//            setResult(Activity.RESULT_OK, intentIO);
//            finish();
//        });
    }

    private void initViews(@NonNull View view) {
        titleNoteEditText = view.findViewById(R.id.fragment_note__title_note_edit_text);
        descriptionNoteEditText = view.findViewById(R.id.fragment_note__description_note_edit_text);
        saveNoteButton = view.findViewById(R.id.fragment_note__save_note_button);
    }

    private void getInfoNote(NoteEntity noteEntity) {
        titleNoteEditText.setText(noteEntity.getTitle());
        descriptionNoteEditText.setText(noteEntity.getDescription());
    }

//    private NoteEntity getNote() {
//        String title = titleNoteEditText.getText().toString();
//        String description = descriptionNoteEditText.getText().toString();
//        noteEntity.setTitle(title);
//        noteEntity.setDescription(description);
//        return noteEntity;
//    }
}
