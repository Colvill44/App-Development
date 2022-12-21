package com.example.midterm;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterFragment extends Fragment {

    NameRecyclerViewAdapter filterNamesAdapter;
    AgeRecyclerViewAdapter filterAgesAdapter;
    ArrayList<String> filterNames = new ArrayList<>();
    ArrayList<String> filterAges = new ArrayList<>();

    public FilterFragment() {
        // Required empty public constructor
    }


    RecyclerView filterNameRecyclerView;
    RecyclerView filterAgesRecyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filterNameRecyclerView = view.findViewById(R.id.filterNameRecyclerView);
        filterAgesRecyclerView = view.findViewById(R.id.filterAgeRecyclerView);

        filterNames.add("Clear");

        filterAges.add("Clear");
        filterAges.add("0-10");
        filterAges.add("18-25");
        filterAges.add("25-35");
        filterAges.add("25-35");
        filterAges.add(">55");

        RecyclerView.LayoutManager layoutManagerNames = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        filterNameRecyclerView.setLayoutManager(layoutManagerNames);
        filterNamesAdapter = new NameRecyclerViewAdapter();
        filterNameRecyclerView.setAdapter(filterNamesAdapter);

        RecyclerView.LayoutManager layoutManagerAges = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        filterAgesRecyclerView.setLayoutManager(layoutManagerAges);
        filterAgesAdapter = new AgeRecyclerViewAdapter();
        filterAgesRecyclerView.setAdapter(filterAgesAdapter);

        filterNames.addAll(getLetters());

    }

    ArrayList<String> getLetters() {
        ArrayList<DataServices.User> users = DataServices.getAllUsers();
        ArrayList<String> uniqueLetters = new ArrayList<>();
        for (DataServices.User user : users) {
            String firstLetter = user.name.substring(0, 1);
            if (!uniqueLetters.contains(firstLetter)) {
                uniqueLetters.add(firstLetter);
            }
        }
        return uniqueLetters;
    }

    class NameRecyclerViewAdapter extends RecyclerView.Adapter<NameRecyclerViewAdapter.NameViewHolder>{

        @NonNull
        @Override
        public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_filter, parent, false);
            return new NameViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
            String item = filterNames.get(position);
            holder.setupUI(item);
        }

        @Override
        public int getItemCount() {
            return filterNames.size();
        }

        class NameViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            String mItem;

            public NameViewHolder(@NonNull View itemView) {
                super(itemView);
                this.textView = itemView.findViewById(R.id.textView);


            }
            public void setupUI(String item){
                this.mItem = item;
                this.textView.setText(item);
            }
        }
    }

    class AgeRecyclerViewAdapter extends RecyclerView.Adapter<AgeRecyclerViewAdapter.AgeViewHolder>{

        @NonNull
        @Override
        public AgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_filter, parent, false);
            return new AgeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AgeViewHolder holder, int position) {
            String item = filterAges.get(position);
            holder.setupUI(item);
        }

        @Override
        public int getItemCount() {
            return filterAges.size();
        }

        class AgeViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            String mItem;

            public AgeViewHolder(@NonNull View itemView) {
                super(itemView);
                this.textView = itemView.findViewById(R.id.textView);
                UserFragment fragment = (UserFragment)getParentFragmentManager().findFragmentByTag("user-fragment");

                itemView.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItem.equals("Clear")){
                            fragment.filterUsersByAge(0, 1000);
                        } else if (mItem.equals("0-10")){
                            fragment.filterUsersByAge(0, 10);
                            Log.d("tag", "onClick: " + mItem);
                        } else if (mItem.equals("18-25")){
                            fragment.filterUsersByAge(18, 25);
                        } else if (mItem.equals("25-35")){
                            fragment.filterUsersByAge(25, 35);
                        } else if (mItem.equals(">55")){
                            fragment.filterUsersByAge(55, 1000);
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
