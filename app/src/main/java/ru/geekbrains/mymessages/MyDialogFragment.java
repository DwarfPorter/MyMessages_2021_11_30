package ru.geekbrains.mymessages;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) requireActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Dialog fragment")
                .setMessage("My super message!")
                .setPositiveButton("Yes", (d, i) -> {
                    Toast.makeText(activity, "YES!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", (d, i) -> {
                    Toast.makeText(activity, "NO!", Toast.LENGTH_SHORT).show();
                });
        Dialog answer = builder.create();
        return answer;
    }
}
