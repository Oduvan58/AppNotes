package by.geekbrains.appnotes.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;
import by.geekbrains.appnotes.ui.details.NoteFragment;

public class MainActivity extends AppCompatActivity implements NotesListFragment.Controller {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment notesListFragment = new NotesListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__fragment_container, notesListFragment)
                    .commit();
        }
    }

    @Override
    public void showNoteDetail(NoteEntity noteEntity) {
        Fragment noteFragment = NoteFragment.newInstance(noteEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__fragment_container, noteFragment)
                .addToBackStack(null)
                .commit();
    }
}