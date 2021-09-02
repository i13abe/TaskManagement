package com.example.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class CreateActivity extends AppCompatActivity {

    private final ArrayList<SubWorkRowData> sub_work_dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);

        //title view
        //work名を画面上部に表示
        Intent intent = getIntent();
        String data = intent.getStringExtra("WORK_TITLE");
        TextView work = findViewById(R.id.work_title);
        work.setText(data);

        //title用edit button
        ImageView edit_button = findViewById(R.id.edit_button);

        //title用変更alert dialog
        AlertDialog.Builder title_alertdialog = new AlertDialog.Builder(this);
        EditText work_title = new EditText(this);
        work_title.setText(work.getText());
        title_alertdialog.setTitle("仕事名/目的");
        title_alertdialog.setMessage("仕事/目的を入力");
        title_alertdialog.setView(work_title);
        title_alertdialog.setPositiveButton("RENAME", (dialog, which) -> {
            String new_data = work_title.getText().toString();
            work.setText(new_data);
        });
        title_alertdialog.setNegativeButton("CANCEL", (dialog, which) -> work_title.setText(work.getText()));
        AlertDialog title_dialog = title_alertdialog.create();

        edit_button.setOnClickListener(v -> title_dialog.show());


        //sub workの生成
        RecyclerView sub_works_rv = findViewById(R.id.sub_works);
        SubWorkAdapter sub_adapter = new SubWorkAdapter(this.sub_work_dataset);
        LinearLayoutManager sub_work_llm = new LinearLayoutManager(this);
        sub_works_rv.setHasFixedSize(true);
        sub_works_rv.setLayoutManager(sub_work_llm);

        sub_works_rv.setAdapter(sub_adapter);

        //sub workのボタン生成
        Button create_sub_work = findViewById(R.id.create_sub_work);

        //alert dialog when creating sub work
        //sub work用アラートダイアログ
        //ここにsub workのタイトルを記入
        AlertDialog.Builder sub_alertdialog = new AlertDialog.Builder(this);
        EditText sub_work_title = new EditText(this);
        sub_work_title.setHint("sub work");
        sub_alertdialog.setTitle("中項目");
        sub_alertdialog.setMessage("仕事/目的の中項目を入力");
        sub_alertdialog.setView(sub_work_title);
        sub_alertdialog.setPositiveButton("CREATE", (dialog, which) -> {
            SubWorkRowData sub_work = new SubWorkRowData();
            sub_work.setSubWorkTitle(sub_work_title.getText().toString());
            sub_work_dataset.add(sub_work);
            sub_works_rv.setAdapter(sub_adapter);
            sub_work_title.setText(null);
        });
        sub_alertdialog.setNegativeButton("CANCEL", (dialog, which) -> sub_work_title.setText(null));
        AlertDialog sub_dialog = sub_alertdialog.create();

        create_sub_work.setOnClickListener(v -> sub_dialog.show());

        //ItemTouch
        //Recycler Viewのドラッグ操作, スワイプ操作
        RecyclerView.ItemDecoration sub_works_idr = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        sub_works_rv.addItemDecoration(sub_works_idr);

        ItemTouchHelper sub_works_ith = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAbsoluteAdapterPosition();
                        final int toPos = target.getAbsoluteAdapterPosition();

                        //array listの更新
                        SubWorkRowData tmp = sub_work_dataset.get(fromPos);
                        sub_work_dataset.remove(fromPos);
                        sub_work_dataset.add(toPos, tmp);
                        sub_adapter.notifyItemMoved(fromPos, toPos);
                        sub_adapter.notifyItemRangeChanged(0, sub_adapter.getItemCount());

                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                    }
                }
        );

        sub_works_ith.attachToRecyclerView(sub_works_rv);
    }
}
