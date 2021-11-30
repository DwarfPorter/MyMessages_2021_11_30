package ru.geekbrains.mymessages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_toast).setOnClickListener(view ->{
            showToast();
        });
    }

    private void showToast() {
        Toast.makeText(this, "My Toast", Toast.LENGTH_LONG).show();
    }
}