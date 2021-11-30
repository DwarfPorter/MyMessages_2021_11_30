package ru.geekbrains.mymessages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements IDialogResult {

    private static final String CHANNEL_ID = "CHA_ID";

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
        findViewById(R.id.button_alert_dialog_custom_view).setOnClickListener(view -> {
            showAlertDialogCustomView();
        });
        findViewById(R.id.button_dialog_fragment).setOnClickListener(view -> {
            showDialogFragment();
        });
        findViewById(R.id.button_dialog_fragment_custom_view).setOnClickListener(view -> {
            showDialogFragmentCustomView();
        });
        findViewById(R.id.button_bottom_dialog_fragment).setOnClickListener(view -> {
            showBottomSheetDialogFragment();
        });
        findViewById(R.id.button_notification).setOnClickListener(view -> {
            showNotification();
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel();
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        // Все цветные иконки отображаются только в оттенках серого
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Hello")
                .setContentText("GeekBrains")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // Числовой идентификатор (42) указывает на уникальный ID нотификации. Если вы не хотите добавлять новую нотификацию, а изменить старую, можете найти ее по ID и поменять
        NotificationManagerCompat.from(this).notify(42, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        String name = "Name";
        String descriptionText = "Description";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(descriptionText);

        // Регистрируем канал в системе
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    private void showBottomSheetDialogFragment() {
        new MyBottomSheetDialogFragment().show(getSupportFragmentManager(), "BottomDialogFragmentTAG");
    }

    private void showDialogFragmentCustomView() {
        new MyDialogCustomViewFragment().show(getSupportFragmentManager(), "DialogFragmentTAG");
    }

    private void showDialogFragment() {
        new MyDialogFragment().show(getSupportFragmentManager(), "DialogTAG");
    }

    private void showAlertDialogCustomView() {
        final View customView = getLayoutInflater().inflate(R.layout.custom_view, null);
        customView.findViewById(R.id.button_custom_view).setOnClickListener(view -> {
            showToast();
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert dialog with custom view")
                .setView(customView)
                .setPositiveButton("Yes", (d, i) -> {
                    Toast.makeText(MainActivity.this, "YES!", Toast.LENGTH_SHORT).show();
                })
                .show();
    }

    private void showAlertDialog() {
        final String[] items = new String[3];
        items[0] = "First";
        items[1] = "Second";
        items[2] = "Third";
        final boolean[] chosen = {false, true, true};
        new AlertDialog.Builder(this)
                .setTitle("Alert Dialog!")
                .setCancelable(false)
                .setMultiChoiceItems(items, chosen, (dialogInterface, i, b) -> chosen[i] = b)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder sb = new StringBuilder("Ok, Выбрано:");
                        for (int index = 0; index < chosen.length; index++){
                            if (chosen[index]){
                                sb.append(items[index]);
                                sb.append("; ");
                            }
                        }
                        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
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

    @Override
    public void onDialogResult(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}