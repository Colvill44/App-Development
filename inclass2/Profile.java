package com.gemini.inclass2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setTitle("Profile");
      setContentView(R.layout.activity_profile);
      // create the binding for the ui elements
      TextView name_text = findViewById(R.id.textView_profile_name_value);
      TextView email_text = findViewById(R.id.textView_profile_email_value);
      TextView id_text = findViewById(R.id.textView_profile_id_value);
      TextView department_text = findViewById(R.id.textView_profile_department_value);

      // Parcelable object
      Bundle bundle = getIntent().getExtras();
      if (bundle != null) {
        String name = bundle.getString("name");
        String email = bundle.getString("email");
        String id = bundle.getString("id");
        String department = bundle.getString("department");
        // set the text views to the values passed in
        name_text.setText(name);
        email_text.setText(email);
        id_text.setText(id);
        department_text.setText(department);
      }

    }
}
