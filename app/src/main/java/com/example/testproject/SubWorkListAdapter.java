package com.example.testproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.List;

public class SubWorkListAdapter extends BaseExpandableListAdapter {
    List<String> sub_works_list;
    List<List<String>> sub_sub_works_list; //ここは変わるかも
    Context context;

    SubWorkListAdapter (Context context, List<String> sub_works_list, List<List<String>> sub_sub_works_lsit){
        this.sub_works_list = sub_works_list;
        this.sub_sub_works_list = sub_sub_works_lsit;
        this.context = context;
    }

    @Override
    public int getGroupCount(){
        return sub_works_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return sub_sub_works_list.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return sub_works_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition){
        return sub_sub_works_list.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition){
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition){
        return 0;
    }

    @Override
    public boolean hasStableIds(){
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        //layout
        LinearLayout sub_work_layout = new LinearLayout(this.context);
        sub_work_layout.setOrientation(LinearLayout.HORIZONTAL);

        //余白
        Space sp = new Space(this.context);
        sub_work_layout.addView(sp, new LinearLayout.LayoutParams(70, ViewGroup.LayoutParams.MATCH_PARENT));

        //中項目表示
        TextView sub_work = new TextView(this.context);
        sub_work.setText(sub_works_list.get(groupPosition));
        sub_work.setPadding(20,20,20,20);
        sub_work.setTextSize(20);
        


        sub_work_layout.addView(sub_work, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return sub_work_layout;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        TextView sub_sub_work = new TextView(this.context);
        sub_sub_work.setText(sub_sub_works_list.get(groupPosition).get(childPosition));
        sub_sub_work.setPadding(20,20,20,20);
        sub_sub_work.setTextSize(15);
        /*ViewGroup.MarginLayoutParams margin=(ViewGroup.MarginLayoutParams) sub_sub_work;
        margin.setMargins(60, 0,0,0);
        sub_sub_work.setLayoutParams(margin);*/
        return sub_sub_work;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return false; //かえるかも
    }
}
