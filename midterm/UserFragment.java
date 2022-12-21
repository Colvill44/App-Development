package com.example.midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class UserFragment extends Fragment {

    ArrayList<DataServices.User> users = new ArrayList<>();

    public UserFragment() {
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    RecyclerView recyclerView;
    UsersAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewUser);
        users.clear();
        users.addAll(DataServices.getAllUsers());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UsersAdapter();
        recyclerView.setAdapter(adapter);

    }

    void getUsersFirstLetter(String letter) {
        users.clear();
        ArrayList<DataServices.User> firstLetters = new ArrayList<>();
        for (DataServices.User user : DataServices.getAllUsers()) {
            String firstLetter = user.name.substring(0, 1);
            if (user.name.startsWith(firstLetter)) {
                users.add(user);
            }
        }
        adapter.notifyDataSetChanged();

    }

    void sortUsersByNameAsc() {
        users.clear();
        users.addAll(DataServices.getAllUsers());
        Collections.sort(users, (o1, o2) -> o1.name.compareTo(o2.name));
        adapter.notifyDataSetChanged();
    }
    void sortUsersByNameDesc() {
        users.clear();
        users.addAll(DataServices.getAllUsers());
        Collections.sort(users, (o1, o2) -> o2.name.compareTo(o1.name));
        adapter.notifyDataSetChanged();
    }

    void sortUsersByAgeAsc() {
        users.clear();
        users.addAll(DataServices.getAllUsers());
        Collections.sort(users, (o1, o2) -> o1.age - o2.age);
        adapter.notifyDataSetChanged();
    }

    void sortUsersByAgeDesc() {
        users.clear();
        users.addAll(DataServices.getAllUsers());
        Collections.sort(users, (o1, o2) -> o2.age - o1.age);
        adapter.notifyDataSetChanged();
    }

    void sortUsersByStateAsc() {
        users.clear();
        users.addAll(DataServices.getAllUsers());
        Collections.sort(users, (o1, o2) -> o1.state.compareTo(o2.state));
        adapter.notifyDataSetChanged();
    }

    void sortUsersByStateDesc() {
        users.clear();
        users.addAll(DataServices.getAllUsers());
        Collections.sort(users, (o1, o2) -> o2.state.compareTo(o1.state));
        adapter.notifyDataSetChanged();
    }

    void filterUsersByAge(int from, int to) {
        users.clear();
        ArrayList<DataServices.User> filteredUsers = new ArrayList<>();
        for (DataServices.User user : DataServices.getAllUsers()) {
            if (user.age >= from && user.age <= to) {
                filteredUsers.add(user);
            }
        }
        users.addAll(filteredUsers);
        Toast.makeText(getContext(), "Number of users" + users.size() + "I received " + from + to, Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_user_row, parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            DataServices.User user = users.get(position);
            holder.setupUI(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        class UserViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textViewName, textViewAge, textViewState, textViewGroup;


            public UserViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                textViewName = itemView.findViewById(R.id.textViewName);
                textViewAge = itemView.findViewById(R.id.textViewAge);
                textViewState = itemView.findViewById(R.id.textViewState);
                textViewGroup = itemView.findViewById(R.id.textViewGroup);

            }

            public void setupUI(DataServices.User user) {
                textViewName.setText(user.name);
                textViewAge.setText(String.valueOf(user.age));
                textViewState.setText(user.state);
                textViewGroup.setText(user.group);

                if (user.gender.equals("Male")) {
                    imageView.setImageResource(R.drawable.avatar_male);
                } else {
                    imageView.setImageResource(R.drawable.avatar_female);
                }
            }
        }
    }
}