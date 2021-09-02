package com.example.testproject;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubSubWorkViewHolder extends RecyclerView.ViewHolder{
    /*
    Sub sub work用View Holder
    1行分のViewの内容を保持する
     */
    public TextView sub_sub_work_titleView;
    private final View parent;

    private AlertDialog sub_sub_dialog;


    public SubSubWorkViewHolder(View itemView){
        super(itemView);
        sub_sub_work_titleView = itemView.findViewById(R.id.sub_sub_work_title);
        parent = itemView;
    }

    public void update(ArrayList<SubSubWorkRowData> list, int Pos, SubSubWorkAdapter adapter){
        //Sub work用変更alert dialog

        ImageView sub_sub_edit_button = parent.findViewById(R.id.sub_sub_edit_button);
        AlertDialog.Builder sub_sub_alertdialog = new AlertDialog.Builder(parent.getContext());
        EditText sub_sub_title = new EditText(parent.getContext());
        sub_sub_title.setText(list.get(Pos).getSubSubWorkTitle());
        sub_sub_alertdialog.setTitle("小項目");
        sub_sub_alertdialog.setMessage("小項目の修正or削除");
        sub_sub_alertdialog.setView(sub_sub_title);
        sub_sub_alertdialog.setPositiveButton("RENAME", (dialog, which) -> {
            String new_data = sub_sub_title.getText().toString();
            list.get(Pos).setSubSubWorkTitle(new_data);
            adapter.notifyItemChanged(Pos);
        });
        sub_sub_alertdialog.setNeutralButton("DELETE", (dialog, which) -> {
            list.remove(Pos);
            adapter.notifyItemRemoved(Pos);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        });
        sub_sub_alertdialog.setNegativeButton("CANCEL", (dialog, which) -> sub_sub_title.setText(list.get(Pos).getSubSubWorkTitle()));
        sub_sub_dialog = sub_sub_alertdialog.create();

        sub_sub_edit_button.setOnClickListener(v -> sub_sub_dialog.show());
    }

}
