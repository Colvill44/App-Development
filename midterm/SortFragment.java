package com.example.midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class SortFragment extends Fragment {

    ArrayList<String> sortOptions = new ArrayList<>();
    SortRecyclerViewAdapter adapter;

    RecyclerView recyclerView;

    public SortFragment() {
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
        return inflater.inflate(R.layout.fragment_sort, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SortRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        sortOptions.add("Name");
        sortOptions.add("Age");
        sortOptions.add("State");
    }

    class SortRecyclerViewAdapter extends RecyclerView.Adapter<SortRecyclerViewAdapter.SortViewHolder>{

        @NonNull
        @Override
        public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_sort, parent, false);
            return new SortViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
            String item = sortOptions.get(position);
            holder.setupUI(item);
        }

        @Override
        public int getItemCount() {
            return sortOptions.size();
        }

        class SortViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            String mItem;
            ImageView imageViewAsc, imageViewDesc;

            public SortViewHolder(@NonNull View itemView) {
                super(itemView);
                this.textView = itemView.findViewById(R.id.textView);
                this.imageViewAsc = itemView.findViewById(R.id.imageViewAsc);
                this.imageViewDesc = itemView.findViewById(R.id.imageViewDesc);

                itemView.findViewById(R.id.imageViewAsc).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserFragment fragment = (UserFragment)getParentFragmentManager().findFragmentByTag("user-fragment");
                        if (textView.getText().toString().equals("Name")){
                            if (fragment != null){
                                fragment.sortUsersByNameAsc();
                            }
                        } else if (textView.getText().toString().equals("Age")) {
                            if (fragment != null) {
                                fragment.sortUsersByAgeAsc();
                            }
                        } else if (textView.getText().toString().equals("State")) {
                            if (fragment != null) {
                                fragment.sortUsersByStateAsc();
                            }

                        }
                    }
                });
                itemView.findViewById(R.id.imageViewDesc).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserFragment fragment = (UserFragment)getParentFragmentManager().findFragmentByTag("user-fragment");
                        if (textView.getText().toString().equals("Name")){
                            if (fragment != null){
                                fragment.sortUsersByNameDesc();
                            }
                        } else if (textView.getText().toString().equals("Age")) {
                            if (fragment != null) {
                                fragment.sortUsersByAgeDesc();
                            }
                        } else if (textView.getText().toString().equals("State")) {
                            if (fragment != null) {
                                fragment.sortUsersByStateDesc();
                            }
                        }

                    }
                });
            }
            public void setupUI(String item){
                this.mItem = item;
                this.textView.setText(item);
            }
        }
    }
}