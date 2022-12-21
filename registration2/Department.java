package com.example.registration2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Department extends Fragment {
    Ilistener mlistener;
    private String selectedDept;

    public Department() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_department, container, false);
        view.findViewById(R.id.button_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view.findViewById(R.id.radioButton_bioInformatics).isSelected()) {
                    selectedDept = "BioInformatics";
                } else if (view.findViewById(R.id.radioButton_computer_science).isSelected()) {
                    selectedDept = "Computer Science";
                } else if (view.findViewById(R.id.radioButton_data_science).isSelected()) {
                    selectedDept = "Data Science";
                } else if (view.findViewById(R.id.radioButton_software_information_systems).isSelected()) {
                    selectedDept = "Software Information Systems";
                }
                if (selectedDept != null) {
                    mlistener.onDepartmentSelect(selectedDept);
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Ilistener) {
            mlistener = (Ilistener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement Ilistener");
        }
    }




    interface Ilistener {
        void onDepartmentSelect(String department);

    }
}