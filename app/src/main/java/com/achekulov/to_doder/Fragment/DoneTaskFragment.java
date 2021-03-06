package com.achekulov.to_doder.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
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
public class DoneTaskFragment extends Fragment {

    protected RecyclerView dRecyclerView;
    protected RecyclerView.Adapter dAdapter;
    protected List<Item> dItems;

    public DoneTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        dItems = new ArrayList<>();

        dRecyclerView = rootView.findViewById(R.id.rvCurrentTasks);
        dRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dAdapter = new CurrentTaskAdapter(getContext());

        dRecyclerView.setAdapter(dAdapter);
        return rootView;
    }

    public void addTask(ModelTask newTask){
        int position = -1;
        for (int i = 0; i  < dAdapter.getItemCount(); i++){
            ModelTask task = (ModelTask) ((CurrentTaskAdapter)dAdapter).getItem(i);
            if (newTask.getDate() < task.getDate()){
                position = i;
                break;
            }
        }

        if (position != -1){
            ((CurrentTaskAdapter) dAdapter).addItem(position, newTask);
        } else {
            ((CurrentTaskAdapter) dAdapter).addItem(newTask);
        }
    }

    public ModelTask getTask(int position){
        return (ModelTask)((CurrentTaskAdapter)dAdapter).getItem(position);
    }
}