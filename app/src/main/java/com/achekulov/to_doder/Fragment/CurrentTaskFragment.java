package com.achekulov.to_doder.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.achekulov.to_doder.Adapter.CurrentTaskAdapter;
import com.achekulov.to_doder.Model.Item;
import com.achekulov.to_doder.Model.ModelTask;
import com.achekulov.to_doder.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTaskFragment extends Fragment {

    protected RecyclerView cRecyclerView;
    protected RecyclerView.Adapter cAdapter;
    protected List<Item> cItems;

    public CurrentTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        cItems = new ArrayList<>();

        cRecyclerView = rootView.findViewById(R.id.rvCurrentTasks);
        cRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cAdapter = new CurrentTaskAdapter(getContext());

        cRecyclerView.setAdapter(cAdapter);
        return rootView;
    }

    public void addTask(ModelTask newTask){
        int position = -1;
        for (int i = 0; i  < cAdapter.getItemCount(); i++){
            ModelTask task = (ModelTask) ((CurrentTaskAdapter)cAdapter).getItem(i);
            if (newTask.getDate() < task.getDate()){
                position = i;
                break;
            }
        }

        if (position != -1){
            ((CurrentTaskAdapter) cAdapter).addItem(position, newTask);
        } else {
            ((CurrentTaskAdapter) cAdapter).addItem(newTask);
        }
    }

    public void removeTask(int position){
        ((CurrentTaskAdapter)cAdapter).removeItem(position);
    }

    public ModelTask getTask(int position){
        return (ModelTask)((CurrentTaskAdapter)cAdapter).getItem(position);
    }

}
