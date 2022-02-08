package by.geekbrains.appnotes.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import by.geekbrains.appnotes.App;
import by.geekbrains.appnotes.R;

public class CounterFragment extends Fragment {

    private TextView titleCounterTextView;
    private TextView countTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_counter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleCounterTextView = view.findViewById(R.id.fragment_counter__description_text_view);
        countTextView = view.findViewById(R.id.fragment_counter__count_text_view);

        String counter = App.get().getCounter().getCount().toString();
        countTextView.setText(counter);
    }
}
