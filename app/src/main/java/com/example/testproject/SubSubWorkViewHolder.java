package com.example.testproject;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SubSubWorkViewHolder extends RecyclerView.ViewHolder{
    /*
    Sub sub work用View Holder
    1行分のViewの内容を保持する
     */
    public TextView sub_sub_work_titleView;

    public SubSubWorkViewHolder(View itemView){
        super(itemView);
        sub_sub_work_titleView = itemView.findViewById(R.id.sub_sub_work_title);
    }

}
