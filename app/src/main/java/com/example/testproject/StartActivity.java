package com.example.testproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
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
        EditText worktitle = new EditText(this);
        worktitle.setHint("Work Title");

        Button createButton = findViewById(R.id.create_new);
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                alertDialog.setTitle("仕事名/目的");
                alertDialog.setMessage("仕事名や目的を入力");
                alertDialog.setView(worktitle);
                alertDialog.setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplication(), CreateActivity.class);
                                intent.putExtra("WORK_TITLE", worktitle.getText().toString());
                                startActivity(intent);
                            }
                        });
                alertDialog.setNegativeButton("CANCEL", null);
                alertDialog.show();
            }
        });
    }
}