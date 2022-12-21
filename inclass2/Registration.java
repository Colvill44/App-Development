package com.gemini.inclass2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Registration extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("Registration");
    setContentView(R.layout.activity_registration);

    // create the binding for the ui elements
    Button Registration_button = findViewById(R.id.button_submit_registration);
    TextView name_text = findViewById(R.id.editTextTextPersonName);
    TextView email_text = findViewById(R.id.editTextTextEmailAddress);
    TextView id_text = findViewById(R.id.editTextNumberSigned_id);
    Button select_department_button = findViewById(R.id.button_select_department);
    // the department is passed in as an extra from the Registration activity
    TextView department_textTextView = findViewById(R.id.textView_selected_department_text);

    // Parcelable object
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      String name = bundle.getString("name");
      String email = bundle.getString("email");
      String id = bundle.getString("id");
      String department = bundle.getString("department");
      // set the text views to the values passed in
      department_textTextView.setText(department);
      name_text.setText(name);
      email_text.setText(email);
      id_text.setText(id);


    }
    // if the select department button is clicked, start the Department activity
    select_department_button.setOnClickListener(v -> {
      Intent intent = new Intent(Registration.this, Department.class);
      // pass the name, email, password, and department to the Department activity
      intent.putExtra("name", name_text.getText().toString());
      intent.putExtra("email", email_text.getText().toString());
      intent.putExtra("id", id_text.getText().toString());
      intent.putExtra("department", department_textTextView.getText().toString());

      startActivity(intent);
    });

    // if the registration button is clicked, start the Profile activity
    Registration_button.setOnClickListener(v -> {
      if (name_text.getText().toString().equals("") || email_text.getText().toString().equals("") || id_text.getText().toString().equals("")) {
        // if the name, email, or id is empty, display an error message
        name_text.setError("Name is required");
        email_text.setError("Email is required");
        id_text.setError("ID is required");
      } else if (department_textTextView.getText().toString().equals("")) {

        // Notify the user that the department is required
        Toast.makeText(Registration.this, "Please select a department", Toast.LENGTH_SHORT).show();

      } else {
        // if the name, email, and id are not empty, start the Profile activity
        Intent intent = new Intent(Registration.this, Profile.class);
        // pass the name, email, password, and department to the Profile activity
        intent.putExtra("name", name_text.getText().toString());
        intent.putExtra("email", email_text.getText().toString());
        intent.putExtra("id", id_text.getText().toString());
        intent.putExtra("department", department_textTextView.getText().toString());

        startActivity(intent);
      }
    });


  }
}
