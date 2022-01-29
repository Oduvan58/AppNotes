package by.geekbrains.appnotes.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;
import by.geekbrains.appnotes.ui.details.AboutFragment;
import by.geekbrains.appnotes.ui.details.NoteFragment;
import by.geekbrains.appnotes.ui.list.NotesListFragment;

public class MainActivity
        extends AppCompatActivity
        implements NotesListFragment.Controller, NoteFragment.Controller {

    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Fragment notesListFragment = new NotesListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main__main_fragment_container, notesListFragment, TAG_LIST_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void showNoteDetail(NoteEntity noteEntity) {
        Fragment noteFragment = NoteFragment.newInstance(noteEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__second_fragment_container, noteFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSaveNote(String noteId, NoteEntity noteEntity) {
        getSupportFragmentManager().popBackStack();
        NotesListFragment notesListFragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        if (notesListFragment == null)
            throw new IllegalStateException("NotesListFragment not on screen");
        notesListFragment.onSaveNote(noteId, noteEntity);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        OnBackPressedListener onBackPressedListener = null;
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment instanceof OnBackPressedListener) {
                onBackPressedListener = (OnBackPressedListener) fragment;
                break;
            }
        }
        if (onBackPressedListener != null) {
            onBackPressedListener.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
}