package com.example.testproject;

import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.cachapa.expandablelayout.ExpandableLayout;

public class SubWorkViewHolder extends RecyclerView.ViewHolder{
    /*
    Sub work用View Holder
    1行分のViewの内容を保持する
     */

    public TextView sub_work_titleView;
    private final LinearLayout expandable_button;
    private final ExpandableLayout sub_sub_work_el;
    private final ImageView arrow;
    private final RecyclerView sub_sub_works_rv;
    private final Button create_sub_sub_work;
    private final LinearLayoutManager sub_sub_work_llm;
    private final AlertDialog.Builder sub_sub_alertdialog;
    private final EditText sub_sub_work_title;

    public SubWorkViewHolder(View itemView){
        super(itemView);
        expandable_button = itemView.findViewById(R.id.sub_work_layout);
        sub_work_titleView = itemView.findViewById(R.id.sub_work_title);
        sub_sub_work_el = itemView.findViewById(R.id.sub_sub_work_expandable_layout);
        sub_sub_work_el.setInterpolator(new OvershootInterpolator());
        arrow = itemView.findViewById(R.id.arrow);
        sub_sub_works_rv = itemView.findViewById(R.id.sub_sub_works);
        sub_sub_work_llm = new LinearLayoutManager(itemView.getContext()); //ここあってんのか...?

        create_sub_sub_work = itemView.findViewById(R.id.create_sub_sub_work);

        sub_sub_alertdialog = new AlertDialog.Builder(itemView.getContext()); //ここもあってる?
        sub_sub_work_title = new EditText(itemView.getContext()); //ここもあってる?
    }

    public void bind(SubWorkRowData row_data){
        //Expandable Layout用
        //Viewの開閉部分
        expandable_button.setSelected(row_data.is_expanded);
        sub_sub_work_el.setExpanded(row_data.is_expanded, false);
        expandable_button.setOnClickListener(v -> {
            row_data.isExpanded();
            if (row_data.is_expanded){
                arrow.setSelected(true);
                sub_sub_work_el.expand();
            }else{
                arrow.setSelected(false);
                sub_sub_work_el.collapse();
            }
        });

        //Sub sub work のRecyclerView Adpter設定
        SubSubWorkAdapter sub_sub_adapter = new SubSubWorkAdapter(row_data.getSubSubWorkDataset());
        this.sub_sub_works_rv.setHasFixedSize(true);
        this.sub_sub_works_rv.setLayoutManager(sub_sub_work_llm);

        this.sub_sub_works_rv.setAdapter(sub_sub_adapter);

        //sub sub work用アラートダイアログ
        //ここにsub sub workのタイトルを記入
        this.sub_sub_work_title.setHint("sub sub work");
        this.sub_sub_alertdialog.setTitle("小項目");
        this.sub_sub_alertdialog.setMessage("仕事/目的の小項目を入力");
        this.sub_sub_alertdialog.setView(this.sub_sub_work_title);
        this.sub_sub_alertdialog.setPositiveButton("CREATE", (dialog, which) -> {
            SubSubWorkRowData sub_sub_work = new SubSubWorkRowData();
            sub_sub_work.setSubSubWorkTitle(this.sub_sub_work_title.getText().toString());
            row_data.setSubSubWorkDataset(sub_sub_work);
            this.sub_sub_works_rv.setAdapter(sub_sub_adapter);
            this.sub_sub_work_title.setText(null);
        });
        this.sub_sub_alertdialog.setNegativeButton("CANCEL", (dialog, which) -> this.sub_sub_work_title.setText(null));
        AlertDialog sub_dialog = this.sub_sub_alertdialog.create();

        this.create_sub_sub_work.setOnClickListener(v -> sub_dialog.show());


    }



}
