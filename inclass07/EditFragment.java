package com.example.inclass07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class EditFragment extends Fragment {

    private final OkHttpClient client = new OkHttpClient();
    Button submitButton;
    EditText nameText;
    EditText phoneText;
    EditText emailText;
    EditText typeText;
    int position;

    public EditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameText = view.findViewById(R.id.editTextNameUpdate);
        phoneText = view.findViewById(R.id.editTextPhoneUpdate);
        emailText = view.findViewById(R.id.editTextEmailUpdate);
        typeText = view.findViewById(R.id.editTextTypeUpdate);
        submitButton = view.findViewById(R.id.buttonSubmitUpdate);


        Contacts MySelectedContact = mListener.onGetSelectedContact();
        nameText.setText(MySelectedContact.getName());
        phoneText.setText(MySelectedContact.getPhone());
        emailText.setText(MySelectedContact.getEmail());
        typeText.setText(MySelectedContact.getPhoneType());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact(MySelectedContact);
                mListener.gotoContactsAfterUpdate();
            }
        });


    }

    void updateContact(Contacts contact) {
        RequestBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(contact.getCid()))
                .add("name", nameText.getText().toString())
                .add("email", emailText.getText().toString())
                .add("phone", phoneText.getText().toString())
                .add("type", typeText.getText().toString())
                .build();
        
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contact/json/update")
                .post(formBody)
                .build();
        
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d("demo", "onResponse: " + response.body().string());
                    mListener.gotoContactsAfterUpdate();
                } else {
                    String responseBody = response.body().string();
                    Log.d("demo", "onResponse: " + responseBody);
                    
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("demo", "onFailure: " + e.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_contact, container, false);
    }

    EditFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (EditFragmentListener) context;
    }

    interface EditFragmentListener {
        Contacts onGetSelectedContact();

        void gotoContactsAfterUpdate();
    }

}

