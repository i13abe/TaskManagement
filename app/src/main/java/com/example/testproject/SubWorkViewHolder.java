package com.example.testproject;

import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

public class SubWorkViewHolder extends RecyclerView.ViewHolder{
    /*
    Sub work用View Holder
    1行分のViewの内容を保持する
     */

    private final View parent;
    public TextView sub_work_titleView;
    private final ExpandableLayout sub_sub_work_el;

    private final RecyclerView sub_sub_works_rv;
    private final Button create_sub_sub_work;
    private final LinearLayoutManager sub_sub_work_llm;
    private final AlertDialog.Builder sub_sub_alertdialog;
    private final EditText sub_sub_work_title;
    private final AlertDialog.Builder sub_sub_delete_alertdialog;
    private final RecyclerView.ItemDecoration sub_sub_works_idr;
    private AlertDialog sub_dialog;

    public SubWorkViewHolder(View itemView){
        super(itemView);
        parent = itemView;
        sub_work_titleView = parent.findViewById(R.id.sub_work_title);
        sub_sub_work_el = itemView.findViewById(R.id.sub_sub_work_expandable_layout);
        sub_sub_work_el.setInterpolator(new OvershootInterpolator());

        sub_sub_works_rv = itemView.findViewById(R.id.sub_sub_works);
        sub_sub_work_llm = new LinearLayoutManager(itemView.getContext());

        create_sub_sub_work = itemView.findViewById(R.id.create_sub_sub_work);

        sub_sub_alertdialog = new AlertDialog.Builder(itemView.getContext());
        sub_sub_work_title = new EditText(itemView.getContext());

        sub_sub_delete_alertdialog = new AlertDialog.Builder(itemView.getContext());
        sub_sub_works_idr = new DividerItemDecoration(itemView.getContext(), DividerItemDecoration.VERTICAL);
    }

    public void update(ArrayList<SubWorkRowData> list, int Pos, SubWorkAdapter adapter){
        //Sub work用変更alert dialog

        ImageView sub_edit_button = parent.findViewById(R.id.sub_edit_button);
        AlertDialog.Builder sub_alertdialog = new AlertDialog.Builder(parent.getContext());
        EditText sub_title = new EditText(parent.getContext());
        sub_title.setText(list.get(Pos).getSubWorkTitle());
        sub_alertdialog.setTitle("中項目");
        sub_alertdialog.setMessage("中項目の修正or削除");
        sub_alertdialog.setView(sub_title);
        sub_alertdialog.setPositiveButton("RENAME", (dialog, which) -> {
            String new_data = sub_title.getText().toString();
            list.get(Pos).setSubWorkTitle(new_data);
            adapter.notifyItemChanged(Pos);
        });
        sub_alertdialog.setNeutralButton("DELETE", (dialog, which) -> {
            list.remove(Pos);
            adapter.notifyItemRemoved(Pos);
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        });
        sub_alertdialog.setNegativeButton("CANCEL", (dialog, which) -> sub_title.setText(list.get(Pos).getSubWorkTitle()));
        sub_dialog = sub_alertdialog.create();

        sub_edit_button.setOnClickListener(v -> sub_dialog.show());
    }

    public void expand(SubWorkRowData row_data){
        //Expandable Layout用
        //Viewの開閉部分
        LinearLayout expandable_button = parent.findViewById(R.id.sub_work_layout);
        ImageView arrow = parent.findViewById(R.id.arrow);
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
    }

    public void bind(SubWorkRowData row_data){
        //Sub sub work のRecyclerView Adpter設定
        SubSubWorkAdapter sub_sub_adapter = new SubSubWorkAdapter(row_data.getSubSubWorkDataset());
        this.sub_sub_works_rv.setHasFixedSize(true);
        this.sub_sub_works_rv.setLayoutManager(sub_sub_work_llm);

        this.sub_sub_works_rv.setAdapter(sub_sub_adapter);

        //Sub sub work用アラートダイアログ
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

        //Sub sub work用ItemTouch Helper
        //Recycler Viewのドラッグ操作, スワイプ操作
        this.sub_sub_works_rv.addItemDecoration(this.sub_sub_works_idr);

        ItemTouchHelper sub_sub_works_ith = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAbsoluteAdapterPosition();
                        final int toPos = target.getAbsoluteAdapterPosition();

                        //array listの更新
                        row_data.switchSubSubWorkDataset(fromPos, toPos);
                        sub_sub_adapter.notifyItemMoved(fromPos, toPos);
                        sub_sub_adapter.notifyItemRangeChanged(0, sub_sub_adapter.getItemCount());
                        return true;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                         int direction) {}
                }
        );

        sub_sub_works_ith.attachToRecyclerView(sub_sub_works_rv);
    }



}
