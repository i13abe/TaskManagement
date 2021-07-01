package com.example.testproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CreateActivity extends AppCompatActivity {

    private final String[] dataset = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        //---title view---
        Intent intent = getIntent();
        String data = intent.getStringExtra("WORK_TITLE");
        TextView work = findViewById(R.id.work_title);
        work.setText(data);

        //---button expandablelist view for sub work---
        LinearLayout work_layout = findViewById(R.id.works_layout);

        LinearLayout new_button_sub_work = new LinearLayout(this);
        new_button_sub_work.setOrientation(LinearLayout.HORIZONTAL);
        Button create_sub_work = new Button(this);
        create_sub_work.setText("+");
        new_button_sub_work.addView(create_sub_work, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100));

        TextView hint_button_sub_work = new TextView(this);
        hint_button_sub_work.setText("create sub work");
        hint_button_sub_work.setTextColor(getResources().getColor(R.color.dark_gray));
        new_button_sub_work.addView(hint_button_sub_work, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100));
        work_layout.addView(new_button_sub_work, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 100));

        //list array
        ArrayList<String> sub_works = new ArrayList<>();
        ListView sub_works_view = new ListView(this); //ここからをexpandablelayoutに変える
        ArrayAdapter<String> array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sub_works);
        List<String> sub_works_list = new ArrayList<>(); // for sub_sub_works?
        int num_of_sub_work = 0;
        sub_works_view.setAdapter(array_adapter);
        work_layout.addView(sub_works_view, num_of_sub_work, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        //alert dialog when creating sub work
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        EditText subworktitle = new EditText(this);
        subworktitle.setHint("sub work");
        alertdialog.setTitle("中項目");
        alertdialog.setMessage("仕事/目的の中項目を入力");
        alertdialog.setView(subworktitle);
        alertdialog.setPositiveButton("CREATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sub_works.add(subworktitle.getText().toString());
                sub_works_view.setAdapter(array_adapter);
                subworktitle.setText(null);
            }
        });
        alertdialog.setNegativeButton("CANCEL", null);
        AlertDialog dialog = alertdialog.create();

        create_sub_work.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.show();
            }
        });
    }
}
