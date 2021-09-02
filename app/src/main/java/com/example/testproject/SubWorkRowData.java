package com.example.testproject;

import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class SubWorkRowData {
    /*
    Sub work用のメタデータ
    1行分のデータ内容(主にテキスト部分)
     */
    private String sub_work_title;
    public Boolean is_expanded = false;
    private final ArrayList<SubSubWorkRowData> sub_sub_work_dataset = new ArrayList<>();

    public String getSubWorkTitle(){
        return this.sub_work_title;
    }

    public void setSubWorkTitle(String sub_work_title){
        this.sub_work_title = sub_work_title;
    }

    public void isExpanded() {
        this.is_expanded = !this.is_expanded;
    }

    public ArrayList<SubSubWorkRowData> getSubSubWorkDataset(){
        return this.sub_sub_work_dataset;
    }

    public void setSubSubWorkDataset(SubSubWorkRowData sub_sub_work_data){
        this.sub_sub_work_dataset.add(sub_sub_work_data);
    }

    public void switchSubSubWorkDataset(int fromPos, int toPos){
        SubSubWorkRowData tmp = this.sub_sub_work_dataset.get(fromPos);
        this.sub_sub_work_dataset.remove(fromPos);
        this.sub_sub_work_dataset.add(toPos, tmp);
    }

    public void deleteSubSubWorkDataset(int Pos){
        this.sub_sub_work_dataset.remove(Pos);
    }
}
