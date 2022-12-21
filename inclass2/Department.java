package com.gemini.inclass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

public class Department extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("Select Department");
    setContentView(R.layout.activity_department);

    // create the binding for the ui elements
    Button select_department_button = findViewById(R.id.button_select);
    Button cancel_department_button = findViewById(R.id.button_cancel);
    RadioButton radio_button_computer_science = findViewById(R.id.radioButton_computer_science);
    RadioButton radio_button_software_information_systems = findViewById(R.id.radioButton_software_information_systems);
    RadioButton radio_button_bio_informatics = findViewById(R.id.radioButton_bioInformatics);
    RadioButton radio_button_data_science = findViewById(R.id.radioButton_data_science);

    // if the cancel button is clicked, go back to the Registration activity
    cancel_department_button.setOnClickListener(v -> {
      Intent intent = new Intent(Department.this, Registration.class);
      // pass the department back to the Registration activity
      intent.putExtra("department", "Cancelled");
      intent.putExtra("department", getIntent().getStringExtra("department"));
      intent.putExtra("name", getIntent().getStringExtra("name"));
      intent.putExtra("email", getIntent().getStringExtra("email"));
      intent.putExtra("id", getIntent().getStringExtra("id"));

      startActivity(intent);
    });

    // if the select button is clicked, set the department and go back to the registration activity
    select_department_button.setOnClickListener(v -> {
      if (radio_button_computer_science.isChecked()) {
        Intent intent = new Intent(Department.this, Registration.class);
        intent.putExtra("department", "Computer Science");
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("id", getIntent().getStringExtra("id"));

        startActivity(intent);
      } else if (radio_button_software_information_systems.isChecked()) {
        Intent intent = new Intent(Department.this, Registration.class);
        intent.putExtra("department", "Software Information Systems");
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("id", getIntent().getStringExtra("id"));


        startActivity(intent);
      } else if (radio_button_bio_informatics.isChecked()) {
        Intent intent = new Intent(Department.this, Registration.class);
        intent.putExtra("department", "Bio Informatics");
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("id", getIntent().getStringExtra("id"));

        startActivity(intent);
      } else if (radio_button_data_science.isChecked()) {
        Intent intent = new Intent(Department.this, Registration.class);
        intent.putExtra("department", "Data Science");
        intent.putExtra("name", getIntent().getStringExtra("name"));
        intent.putExtra("email", getIntent().getStringExtra("email"));
        intent.putExtra("id", getIntent().getStringExtra("id"));

        startActivity(intent);
      }

    });


  }
}
