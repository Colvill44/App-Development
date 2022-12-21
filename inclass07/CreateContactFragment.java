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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class CreateContactFragment extends Fragment {

    private final OkHttpClient client = new OkHttpClient();
    Button submitButton;
    EditText nameText;
    EditText phoneText;
    EditText emailText;
    EditText typeText;
    Button deleteButton;


    public CreateContactFragment() {
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
        deleteButton = view.findViewById(R.id.buttonDelete);




        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String phone = phoneText.getText().toString();
                String email = emailText.getText().toString();
                String type = typeText.getText().toString();
                createContact(name, phone, email, type);
                mListener.gotoContacts();

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_contact, container, false);
    }

    void createContact(String Name, String Email, String Phone, String PhoneType){
        RequestBody formBody = new FormBody.Builder()
                .add("name", Name)
                .add("email", Email)
                .add("phone", Phone)
                .add("type", PhoneType)
                .build();

        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/contact/json/create")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    if(responseBody != null){

                    }
                } else {
                    String responseBody = response.body().string();
                    Log.d("demo", "onResponse: " + responseBody);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });
    }






    CreateContactListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CreateContactListener) context;
    }

    public interface CreateContactListener {
        void gotoContacts();
        void gotoEdit();
    }
}

