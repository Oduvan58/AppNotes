package by.geekbrains.appnotes.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import by.geekbrains.appnotes.R;

public class AlertDialogFragment extends DialogFragment {

    public static final String DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT_TAG";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = requireActivity();
        return new AlertDialog.Builder(activity)
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setTitle(R.string.text_title_alert_dialog)
                .setMessage(R.string.text_message_alert_dialog)
                .setPositiveButton(R.string.text_positive_alert_dialog, (dialogInterface, i) -> {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.popBackStack();
                })
                .setNegativeButton(R.string.text_negative_alert_dialog, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setNeutralButton(R.string.text_neutral_alert_dialog, (dialogInterface, i) -> {
                    Toast.makeText(getContext(), R.string.text_help_alert_dialog, Toast.LENGTH_SHORT).show();
                })
                .setCancelable(false)
                .create();
    }
}
