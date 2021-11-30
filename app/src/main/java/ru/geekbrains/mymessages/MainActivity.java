package ru.geekbrains.mymessages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_toast).setOnClickListener(view ->{
            showToast();
        });
        findViewById(R.id.button_snakbar).setOnClickListener(view -> {
            showSnackbar();
        });
        findViewById(R.id.button_snakbar_with_action).setOnClickListener(view -> {
            showSnackbarWithAction();
        });
    }

    private void showSnackbar() {
        Snackbar.make(findViewById(R.id.container), "Show message in snack bar", Snackbar.LENGTH_LONG).show();
    }

    private void showSnackbarWithAction() {
        Snackbar.make(findViewById(R.id.container), "Show message with action", Snackbar.LENGTH_INDEFINITE)
                .setAction("Push me", view -> { showToast();})
                .show();
    }

    private void showToast() {
        Toast.makeText(this, "My Toast", Toast.LENGTH_LONG).show();
    }
}