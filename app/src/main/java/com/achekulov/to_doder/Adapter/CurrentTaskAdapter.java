package com.achekulov.to_doder.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.achekulov.to_doder.Model.Item;
import com.achekulov.to_doder.Model.ModelTask;
import com.achekulov.to_doder.R;
import com.achekulov.to_doder.Utils;

import java.util.ArrayList;
import java.util.List;

public class CurrentTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  List<Item> items = new ArrayList<>();
    private static final int TYPE_TASK = 0;
    private static final int TYPE_SEPARATOR = 1;

    public Item getItem(int position){
        return items.get(position);
    }

    public void addItem(Item item){
        items.add(item);
        notifyItemInserted(getItemCount() -1);
    }

    public void addItem(int position, Item item){
        items.add(position, item);
        notifyItemInserted(position);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case TYPE_TASK: View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.model_task, viewGroup, false);
                TextView title = v.findViewById(R.id.tv_task_title);
                TextView date = v.findViewById(R.id.tv_task_Date);
                return new TaskViewHolder(v, title, date);
            default: return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Item item = items.get(position);
        if (item.isTask()){
            viewHolder.itemView.setEnabled(true);
            ModelTask task = (ModelTask) item;
            TaskViewHolder taskViewHolder = (TaskViewHolder) viewHolder;
            ((TaskViewHolder) viewHolder).title.setText(task.getTitle());
            if (task.getDate() != 0) {
                ((TaskViewHolder) viewHolder).date.setText(Utils.getFullDate(task.getDate()));
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isTask()){
            return TYPE_TASK;
        } return TYPE_SEPARATOR;

    }

    private class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;

        public TaskViewHolder(@NonNull View itemView, TextView title, TextView date) {
            super(itemView);
            this.title = title;
            this.date = date;
        }
    }
}
