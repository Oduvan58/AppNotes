package by.geekbrains.appnotes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import by.geekbrains.appnotes.App;
import by.geekbrains.appnotes.R;
import by.geekbrains.appnotes.domain.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    private final TextView titleTextView = itemView.findViewById(R.id.title_text_view);
    private final TextView descriptionTextView = itemView.findViewById(R.id.description_text_view);
    private final TextView dateTextView = itemView.findViewById(R.id.date_text_view);
    private final AppCompatImageView deleteImageView = itemView.findViewById(R.id.delete_image_view);

    private OnNoteListener onNoteListener;

    public NoteViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent,
            OnNoteListener onNoteListener
    ) {
        super(inflater.inflate(R.layout.item_note, parent, false));
        this.onNoteListener = onNoteListener;
    }

    public void bind(NoteEntity note) {
        itemView.setOnClickListener(v -> onNoteListener.onClickNote(note));
        deleteImageView.setOnClickListener(v -> onNoteListener.onDeleteNote(note));

        titleTextView.setText(note.getTitle());
        descriptionTextView.setText(note.getDescription());
        dateTextView.setText(note.getDate());
    }
}
