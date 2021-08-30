package com.example.testproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        //alert dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        EditText work_title = new EditText(this);
        work_title.setHint("Work Title");
        alertDialog.setTitle("仕事名/目的");
        alertDialog.setMessage("仕事名や目的を入力");
        alertDialog.setView(work_title);
        alertDialog.setPositiveButton("CREATE", (dialog, which) -> {
            Intent intent = new Intent(getApplication(), CreateActivity.class);
            intent.putExtra("WORK_TITLE", work_title.getText().toString());
            startActivity(intent);
        });
        alertDialog.setNegativeButton("CANCEL", (dialog, which) -> work_title.setText(null));
        AlertDialog dialog = alertDialog.create();


        //work生成用ボタン
        Button createButton = findViewById(R.id.create_new);
        createButton.setOnClickListener(v -> dialog.show());
    }
}