package com.achekulov.to_doder.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.achekulov.to_doder.Fragment.TaskFragment;
import com.achekulov.to_doder.Model.Item;
import com.achekulov.to_doder.R;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Item> items;
    TaskFragment taskFragment;

    public TaskAdapter(TaskFragment taskFragment) {
        this.taskFragment = taskFragment;
        items = new ArrayList<>();
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    public void addItem(Item item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addItem(int position, Item item) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        if (position >= 0 || position <= getItemCount() - 1) ;
        items.remove(position);
        notifyItemRemoved(position);
    }

    protected class TaskViewHolder extends RecyclerView.ViewHolder {
        protected CardView cv;
        protected TextView title;
        protected TextView date;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            title = itemView.findViewById(R.id.tv_task_title);
            date = itemView.findViewById(R.id.tv_task_date);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public TaskFragment getTaskFragment(){
        return taskFragment;
    }
}
