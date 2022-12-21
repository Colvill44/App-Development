package com.example.inclass07;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ContactsFragment extends Fragment {

    ArrayList<Contacts> contacts = new ArrayList<>();
    private final OkHttpClient client = new OkHttpClient();
    RecyclerView recyclerView;
    ContactsAdapter adapter;

    public ContactsFragment() {
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
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoCreateContact();
            }
        });




        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ContactsAdapter();
        recyclerView.setAdapter(adapter);
        getContacts();
        adapter.notifyDataSetChanged();
    }

    void getContacts(){
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contacts/json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    if(responseBody != null){
                        String bodyString = responseBody.string();
                        try {
                            JSONObject jsonObject = new JSONObject(bodyString);
                            JSONArray jsonArrayContacts = jsonObject.getJSONArray("contacts");

                            for (int i = 0; i < jsonArrayContacts.length(); i++){
                                JSONObject contactJsonObject = jsonArrayContacts.getJSONObject(i);
                                Contacts contact = new Contacts(contactJsonObject);
                                contacts.add(contact);
                            }

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                } else {
                    Log.d("demo", "onResponse: " + response.code());
                }
            }
        });
     }

     void getCid(int cid){
        for (Contacts contact : contacts) {
            if(contact.getCid() == (cid)){
                mListener.gotoEditContact(contact);
            }
        }
     }

     void deleteContact(Contacts contact){
         RequestBody formBody = new FormBody.Builder()
                 .add("id", String.valueOf(contact.getCid()))
                 .build();
        Request request = new Request.Builder()
                    .url("https://www.theappsdr.com/contact/json/delete?id=" + contact.getCid())
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                contacts.remove(contact);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });


    }

     class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>{
         @NonNull
         @Override
         public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View view = getLayoutInflater().inflate(R.layout.contact_display, parent, false);
             return new ContactViewHolder(view);
         }

         @Override
         public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
             Contacts contact = contacts.get(position);
             holder.setupUI(contact);

         }

         @Override
         public int getItemCount() {
             return contacts.size();
         }

         class ContactViewHolder extends RecyclerView.ViewHolder{

             TextView textViewName, textViewEmail, textViewPhone, textViewPhoneType;
             Contacts mContact;
             Button buttonDelete, buttonEdit;
            public ContactViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewName);
                textViewEmail = itemView.findViewById(R.id.textViewEmail);
                textViewPhone = itemView.findViewById(R.id.textViewPhone);
                textViewPhoneType = itemView.findViewById(R.id.textViewPhoneType);
                buttonDelete = itemView.findViewById(R.id.buttonDelete);
                buttonEdit = itemView.findViewById(R.id.buttonEdit);


            }

            void setupUI(Contacts contact){
                mContact = contact;
                textViewName.setText(contact.getName());
                textViewEmail.setText(contact.getEmail());
                textViewPhone.setText(contact.getPhone());
                textViewPhoneType.setText(contact.getPhoneType());
                buttonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("demo", "Delete clicked" + textViewName.getText().toString());
                        deleteContact(mContact);
                    }
                });
                buttonEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("demo", "Edit clicked" + textViewName.getText().toString());
                        getCid(mContact.getCid());
                    }
                });
            }
        }
     }

     ContactsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ContactsListener) context;

    }

    interface ContactsListener{
         void gotoCreateContact();

        void gotoEditContact(Contacts contact);
    }

}