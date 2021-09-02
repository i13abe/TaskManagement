package com.example.testproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SubWorkAdapter extends RecyclerView.Adapter<SubWorkViewHolder>{
    /*
    Sub work用Adapter
    1行分のデータを生成する
     */
    private final ArrayList<SubWorkRowData> list;

    public SubWorkAdapter(ArrayList<SubWorkRowData> list){
        this.list = list;
    }

    @NonNull
    public SubWorkViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType){
        /*
        Layoutファイルsub_work_row.xmlの参照
        参照したLayoutをinflateに渡しview holderへ
         */
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_work_row,
                parent, false);
        return new SubWorkViewHolder(inflate);
    }

    public void onBindViewHolder(SubWorkViewHolder holder,
                                 int position){
        /*
        1行ごとにデータを描画
         */
        holder.sub_work_titleView.setText(list.get(position).getSubWorkTitle());
        holder.update(list, position, this);
        holder.expand(list.get(position));
        holder.bind(list.get(position));
    }

    public int getItemCount(){
        return list.size();
    }

}
