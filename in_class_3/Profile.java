package com.example.in_class_3;

import static com.example.in_class_3.Registration.USER_KEY;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setTitle("Profile");
      setContentView(R.layout.activity_profile);

        // get the user object from the intent
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(USER_KEY);

        // create the binding for the ui elements
        TextView text_view_name = findViewById(R.id.textView_name);
        TextView text_view_email = findViewById(R.id.textView_email);
        TextView text_view_id = findViewById(R.id.textView_id);
        TextView text_view_department = findViewById(R.id.textView_department);

        // set the text for the ui elements
        text_view_name.setText(user.getName());
        text_view_email.setText(user.getEmail());
        text_view_id.setText(String.valueOf(user.getId()));
        text_view_department.setText(user.getDepartment());





      }

    }

