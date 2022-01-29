package by.geekbrains.appnotes.ui.list;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import by.geekbrains.appnotes.App;
import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;
import by.geekbrains.appnotes.domain.NoteRepository;
import by.geekbrains.appnotes.ui.MainActivity;
import by.geekbrains.appnotes.ui.OnNoteListener;

public class NotesListFragment extends Fragment {
    private static final String CHANNEL_ID = "channel for test";
    private static final int NOTIFICATION_ID = 28;

    private NoteRepository noteRepository;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private FloatingActionButton addButton;

    private Controller controller;

    public void onSaveNote(String noteId, NoteEntity noteEntity) {
        noteRepository.saveNote(noteId, noteEntity);
        adapter.saveNote(noteId, noteEntity);
    }

    public interface Controller {
        void showNoteDetail(NoteEntity noteEntity);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement NotesListFragment.Controller");
        }
    }

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

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        final NotificationChannelCompat notificationChannel = new NotificationChannelCompat.Builder(
                CHANNEL_ID,
                NotificationManagerCompat.IMPORTANCE_MAX)
                .setName("Notifications")
                .setDescription("App messages")
                .build();
        NotificationManagerCompat.from(App.get()).createNotificationChannel(notificationChannel);
    }

    private void initRecycler(@NonNull View view) {
        recyclerView = view.findViewById(R.id.fragment_notes_list__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NoteAdapter();
        adapter.setData(App.get().noteRepository.getNotes());
        adapter.setOnNoteListener(new OnNoteListener() {
            @Override
            public void onClickNote(NoteEntity noteEntity) {
                controller.showNoteDetail(noteEntity);
                final Notification notification = new NotificationCompat.Builder(App.get(), CHANNEL_ID)
                        .setContentTitle(getString(R.string.text_title_notification))
                        .setColorized(true)
                        .setContentText(getString(R.string.text_description_notification))
                        .setColor(Color.GREEN)
                        .setSmallIcon(R.drawable.ic_baseline_edit_note_24)
                        .build();
                NotificationManagerCompat.from(App.get()).notify(NOTIFICATION_ID, notification);
            }

            @Override
            public void onDeleteNote(NoteEntity noteEntity) {
                noteRepository.deleteNote(noteEntity.getId());
                adapter.deleteNote(noteEntity.getId());
                final Snackbar snackbar = Snackbar.make(view, "Note deleted", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.BLUE);
                snackbar.setTextColor(Color.YELLOW);
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE);
                snackbar.setAction("Cancel", snackView -> {
                    noteRepository.getDeletedNote(noteEntity);
                    adapter.getDeletedNote(noteEntity);
                    snackbar.dismiss();
                });
                snackbar.show();
            }

            @Override
            public void onAddNote(NoteEntity noteEntity) {
                addButton.setOnClickListener(v -> {
                    noteRepository.addNote(noteEntity);
                    adapter.addNote(noteEntity);
                    Toast.makeText(getContext(), R.string.text_add_note_toast, Toast.LENGTH_SHORT).show();
                });
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
