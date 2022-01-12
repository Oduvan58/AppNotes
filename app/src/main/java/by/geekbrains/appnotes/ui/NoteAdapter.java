package by.geekbrains.appnotes.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import by.geekbrains.appnotes.domain.NoteEntity;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private ArrayList<NoteEntity> data = new ArrayList<>();
    private OnNoteListener onNoteListener;

    public void setOnNoteListener(OnNoteListener onNoteListener) {
        this.onNoteListener = onNoteListener;
    }

    public void setData(ArrayList<NoteEntity> noteList) {
        data = noteList;
        notifyDataSetChanged();
    }

    public void deleteNote(String noteId) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(noteId)) {
                data.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new NoteViewHolder(inflater, parent, onNoteListener);
    }

    private NoteEntity getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
