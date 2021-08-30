package com.example.testproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SubSubWorkAdapter extends RecyclerView.Adapter<SubSubWorkViewHolder>{
    /*
    Sub  sub work用Adapter
    1行分のデータを生成する
     */
    private List<SubSubWorkRowData> list;;

    public SubSubWorkAdapter(List<SubSubWorkRowData> list){
        this.list = list;
    }

    public SubSubWorkViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType){
        /*
        Layoutファイルsub_work_row.xmlの参照
        参照したLayoutをinflateに渡しviewholderへ
         */
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_sub_work_row,
                parent, false);
        SubSubWorkViewHolder sub_sub_work_vh = new SubSubWorkViewHolder(inflate);
        return sub_sub_work_vh;
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
