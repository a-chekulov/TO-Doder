package com.achekulov.to_doder;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.achekulov.to_doder.Model.ModelTask;
import com.achekulov.to_doder.Utils;

import java.util.Calendar;
import java.util.Objects;

public class AddingTaskDialogFragment extends android.support.v4.app.DialogFragment {

    Calendar calendar;
    int year;
    int month;
    int dayOfMonth;
    int hour;
    int minute;

    private AddingTaskListener addingTaskListener;
    public interface AddingTaskListener {
        void onTaskAdded(ModelTask newTask);

        void onTaskAddingCancel();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            addingTaskListener = (AddingTaskListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "must implement AddingTaskListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_title);

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View container = inflater.inflate(R.layout.dialog_task, null);
        final TextInputLayout tilTitle = container.findViewById(R.id.tilDialogTaskTitle);
        final EditText etTitle = tilTitle.getEditText();

        TextInputLayout tilDate = container.findViewById(R.id.tilDialogTaskData);
        final EditText etDate = tilDate.getEditText();

        TextInputLayout tilTime = container.findViewById(R.id.tilDialogTaskTime);
        final EditText edTime = tilTime.getEditText();

        tilTitle.setHint(getResources().getString(R.string.task_title));
        tilDate.setHint(getResources().getString(R.string.task_date));
        tilTime.setHint(getResources().getString(R.string.task_time));

        builder.setView(container);

        final ModelTask task = new ModelTask();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.HOUR_OF_DAY, mCalendar.get(Calendar.HOUR_OF_DAY) + 1);


        assert etDate != null;
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDate.length() == 0) {
                    etDate.setText(" ");
                }
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()),
                        new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mCalendar.set(Calendar.YEAR, year);
                                mCalendar.set(Calendar.MONTH, month);
                                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                etDate.setText(Utils.getDate(mCalendar.getTimeInMillis()));
                                //etDate.setText(dayOfMonth + "." + (month+1) + "." + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
                // datePickerFragment = new DatePickerFragment();
                //Calendar dateCalendar = Calendar.getInstance();
                // dateCalendar.set(year, monthOfYear, dayOfMonth);
                //etDate.setText(Utils.getDate(dateCalendar.getTimeInMillis()));
                //datePickerFragment.show(getFragmentManager(), "DatePickerFragment");

                /*
                DialogFragment datePickerFragment = new DatePickerFragment(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar dateCalendar = Calendar.getInstance();
                        dateCalendar.set(year, monthOfYear, dayOfMonth);
                        etDate.setText(Utils.getDate(dateCalendar.getTimeInMillis()));
                    }
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        etDate.setText(null);
                    }
                };
                assert getFragmentManager() != null;
                datePickerFragment.show(getFragmentManager(), "DatePickerFragment");
                */
            }
        });


        assert edTime != null;
        edTime.setOnClickListener(new View.OnClickListener() {
            @Override
            @SuppressWarnings("ValidFragment")
            public void onClick(View v) {
                if (edTime.length() == 0){
                    edTime.setText(" ");
                }
                calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(Objects.requireNonNull(getActivity()),
                        new TimePickerDialog.OnTimeSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                mCalendar.set(Calendar.MINUTE, minute);
                                mCalendar.set(Calendar.SECOND, 0);
                                edTime.setText(Utils.getTime(mCalendar.getTimeInMillis()));
                                //edTime.setText(hourOfDay + ":" + minute);
                            }
                        }, dayOfMonth, minute, true);
                timePickerDialog.show();

                /*
                DialogFragment timePickerFragment = new TimePickerFragmant(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar timeCalendar = Calendar.getInstance();
                        timeCalendar.set(0,0,0, hourOfDay, minute);
                        edTime.setText(Utils.getTime(timeCalendar.getTimeInMillis()));
                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        edTime.setText(null);
                    }
                };
                assert getFragmentManager() != null;
                timePickerFragment.show(getFragmentManager(), "TimePickerFragment");
                */
            }
        });

        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                task.setTitle(etTitle.getText().toString());
                if (etDate.length() != 0 || edTime.length() != 0 ){
                    task.setDate(mCalendar.getTimeInMillis());
                }


                addingTaskListener.onTaskAdded(task);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addingTaskListener.onTaskAddingCancel();
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final Button positiveButton = ((AlertDialog)dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                if (edTime.length() == 0){
                    positiveButton.setEnabled(false);
                    tilTitle.setError(getResources().getString(R.string.dialog_error_empty_title));
                }

                assert etTitle != null;
                etTitle.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0){
                            positiveButton.setEnabled(false);
                            tilTitle.setError(getResources().getString(R.string.dialog_error_empty_title));
                        } else {
                            positiveButton.setEnabled(true);
                            tilTitle.setErrorEnabled(false);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });
        return  alertDialog;
    }
}
