package com.achekulov.to_doder.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.achekulov.to_doder.Adapter.CurrentTaskAdapter;
import com.achekulov.to_doder.Model.ModelTask;
import com.achekulov.to_doder.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentTaskFragment extends Fragment {

    private RecyclerView rvCurrentTasks;
    private RecyclerView.LayoutManager layoutManager;
    private CurrentTaskAdapter currentTaskAdapter;

    public CurrentTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_current_task, container, false);

        rvCurrentTasks = rootView.findViewById(R.id.rvDoneTasks);
        layoutManager = new LinearLayoutManager(getActivity());
        rvCurrentTasks.setLayoutManager(layoutManager);

        currentTaskAdapter = new CurrentTaskAdapter();
        rvCurrentTasks.setAdapter(currentTaskAdapter);


        return rootView;
    }

    public void addTask(ModelTask newTask){
        int position = -1;
        for (int i = 0; i  < currentTaskAdapter.getItemCount(); i++){
            ModelTask task = (ModelTask) currentTaskAdapter.getItem(i);
            if (newTask.getDate() < task.getDate()){
                position = i;
                break;
            }
        }

        if (position != -1){
            currentTaskAdapter.addItem(position, newTask);
        } else {
            currentTaskAdapter.addItem(newTask);
        }
    }

}
