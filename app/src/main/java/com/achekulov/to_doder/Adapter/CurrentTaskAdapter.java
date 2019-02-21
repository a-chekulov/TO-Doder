package com.achekulov.to_doder.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
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

public class CurrentTaskAdapter extends RecyclerView.Adapter<CurrentTaskAdapter.TaskViewHolder> {

    private List<Item> items;
    private Context context;

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

    public void removeItem(int position){
        if (position >= 0 || position <= getItemCount() -1);
        items.remove(position);
        notifyItemRemoved(position);
    }

    public CurrentTaskAdapter(Context context) {
        items = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public CurrentTaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model_task, viewGroup, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentTaskAdapter.TaskViewHolder viewHolder, int position) {
        if (items.get(position).isTask()) {
            ModelTask modelTask = (ModelTask) items.get(position);
            viewHolder.title.setText(modelTask.getTitle());
            viewHolder.date.setText(Utils.getFullDate(modelTask.getDate()));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        CardView cv;
        TextView title;
        TextView date;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            cv.setOnCreateContextMenuListener(this);
            title = itemView.findViewById(R.id.tv_task_title);
            date = itemView.findViewById(R.id.tv_task_date);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(this.getAdapterPosition(), 100, 1, "Delete");
            menu.add(this.getAdapterPosition(), 101, 0, "Complete the Task");
        }
    }
}
