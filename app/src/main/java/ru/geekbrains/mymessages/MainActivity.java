package ru.geekbrains.mymessages;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        findViewById(R.id.button_alert_dialog).setOnClickListener(view -> {
            showAlertDialog();
        });
    }

    private int chosen = -1;
    private void showAlertDialog() {
        final String[] items = new String[3];
        items[0] = "First";
        items[1] = "Second";
        items[2] = "Third";
        new AlertDialog.Builder(this)
                .setTitle("Alert Dialog!")
                .setCancelable(false)
                .setSingleChoiceItems(items, chosen, (d, i) -> {
                    chosen = i;
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (chosen != -1)
                            Toast.makeText(MainActivity.this, "Ok! " + items[chosen], Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", (dialogInterface, i) -> {
                    Toast.makeText(MainActivity.this, "No!", Toast.LENGTH_LONG).show();
                })
                .setNeutralButton("Cancel", null)
                .show();
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