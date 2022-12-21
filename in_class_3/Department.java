package com.example.in_class_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class Department extends AppCompatActivity {



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("Select Department");
    setContentView(R.layout.activity_department);

    // create the binding for the ui elements

    RadioButton radio_button_computer_science = findViewById(R.id.radioButton_computer_science);
    RadioButton radio_button_software_information_systems = findViewById(R.id.radioButton_software_information_systems);
    RadioButton radio_button_bio_informatics = findViewById(R.id.radioButton_bioInformatics);
    RadioButton radio_button_data_science = findViewById(R.id.radioButton_data_science);

      findViewById(R.id.button_select).setOnClickListener(view -> {
        // get the selected department
        String department = "";
        if(radio_button_computer_science.isChecked()) {
          department = radio_button_computer_science.getText().toString();
        } else if(radio_button_software_information_systems.isChecked()) {
          department = radio_button_software_information_systems.getText().toString();
        } else if(radio_button_bio_informatics.isChecked()) {
          department = radio_button_bio_informatics.getText().toString();
        } else if(radio_button_data_science.isChecked()) {
          department = radio_button_data_science.getText().toString();
        }



        // create an intent to pass the user object
        Intent intent = new Intent();
        intent.putExtra("department", department);
        setResult(RESULT_OK, intent);
        finish();

    });

      findViewById(R.id.button_cancel).setOnClickListener(view -> {
        setResult(RESULT_CANCELED);
        finish();
      });


  }
}
