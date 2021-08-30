package com.example.testproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubSubWorkAdapter extends RecyclerView.Adapter<SubSubWorkViewHolder>{
    /*
    Sub  sub work用Adapter
    1行分のデータを生成する
     */
    private final ArrayList<SubSubWorkRowData> list;

    public SubSubWorkAdapter(ArrayList<SubSubWorkRowData> list){
        this.list = list;
    }

    @NonNull
    public SubSubWorkViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType){
        /*
        Layoutファイルsub_work_row.xmlの参照
        参照したLayoutをinflateに渡しview holderへ
         */
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_sub_work_row,
                parent, false);
        return new SubSubWorkViewHolder(inflate);
    }

    public void onBindViewHolder(SubSubWorkViewHolder holder,
                                 int position){
        /*
        1行ごとにデータを描画
         */
        holder.sub_sub_work_titleView.setText(list.get(position).getSubSubWorkTitle());
    }

    public int getItemCount(){
        return list.size();
    }
}
