package ru.geekbrains.mymessages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View customView = inflater.inflate(R.layout.custom_view, null);

        IDialogResult dialogResult = (IDialogResult) requireActivity();
        EditText editText = customView.findViewById(R.id.edit_text_custom_view);

        customView.findViewById(R.id.button_custom_view).setOnClickListener(view -> {
            String text = editText.getText().toString();
            dialogResult.onDialogResult(text);
            dismiss();
        });

        setCancelable(false);
        return customView;
    }
}
