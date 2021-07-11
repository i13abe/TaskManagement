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
import android.widget.ExpandableListView;
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

        //Expandable array
        ExpandableListView sub_works_view = new ExpandableListView(this);
        List<String> sub_works = new ArrayList<>();
        List<String> sub_sub_work = new ArrayList<>();
        List<List<String>> sub_sub_works = new ArrayList<>(); //sub sub works listで書き換える可能性あり
        sub_sub_work.add("work1");
        sub_sub_work.add("work2");
        sub_sub_work.add("work3");
        sub_sub_works.add(sub_sub_work);
        work_layout.addView(sub_works_view, 0, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        SubWorkListAdapter adapter = new SubWorkListAdapter(this, sub_works, sub_sub_works);
        sub_works_view.setAdapter(adapter);
        /*sub_works_view.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
                SubWorkListAdapter child_adapter = (SubWorkListAdapter) parent.getExpandableListAdapter();
                String sub_work = (String) child_adapter.getGroup(groupPosition);
                String sub_sub_work = (String) child_adapter.getChild(groupPosition, childPosition);

            }
        });*/


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
                sub_sub_works.add(sub_sub_work);
                sub_works_view.setAdapter(adapter);
                subworktitle.setText(null);
            }
        });
        alertdialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                subworktitle.setText(null);
            }
        });
        AlertDialog dialog = alertdialog.create();

        create_sub_work.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.show();
            }
        });
    }
}
