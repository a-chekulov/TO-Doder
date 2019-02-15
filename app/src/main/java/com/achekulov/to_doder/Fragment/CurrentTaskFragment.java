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

    private RecyclerView currentRecyclerView;
    private RecyclerView.Adapter adapterTA;
    private List<Item> items;

    public CurrentTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        items = new ArrayList<>();

        currentRecyclerView = rootView.findViewById(R.id.rvCurrentTasks);
        currentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTA = new CurrentTaskAdapter(getContext());

        currentRecyclerView.setAdapter(adapterTA);
        return rootView;
    }

    public void addTask(ModelTask newTask){
        int position = -1;
        for (int i = 0; i  < adapterTA.getItemCount(); i++){
            ModelTask task = (ModelTask) ((CurrentTaskAdapter)adapterTA).getItem(i);
            if (newTask.getDate() < task.getDate()){
                position = i;
                break;
            }
        }

        if (position != -1){
            ((CurrentTaskAdapter) adapterTA).addItem(position, newTask);
        } else {
            ((CurrentTaskAdapter) adapterTA).addItem(newTask);
        }
    }
}
