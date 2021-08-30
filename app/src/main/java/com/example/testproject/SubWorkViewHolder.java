package com.example.testproject;

import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    public SubWorkViewHolder(View itemView){
        super(itemView);
        expandable_button = itemView.findViewById(R.id.sub_work_layout);
        sub_work_titleView = itemView.findViewById(R.id.sub_work_title);
        sub_sub_work_el = itemView.findViewById(R.id.sub_sub_work_expandable_layout);
        sub_sub_work_el.setInterpolator(new OvershootInterpolator());
        arrow = itemView.findViewById(R.id.arrow);
    }

    public void bind(SubWorkRowData row_data){
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



}
