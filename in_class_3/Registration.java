package com.example.in_class_3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class Registration extends AppCompatActivity {

    final static public String USER_KEY = "USER";
    String selectedDept = null;

    ActivityResultLauncher<Intent> startDeptForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                String dept = result.getData().getExtras().getString("department");
                Log.d("demo", "onActivityResult: " + dept);
                selectedDept = dept;
                department_textTextView.setText(dept);

            } else if (result.getResultCode() == RESULT_CANCELED) {
                selectedDept = null;
                department_textTextView.setText("");
            }
        }
    });

    TextView department_textTextView;


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
        // the department is passed in as an extra from the Registration activity
        department_textTextView = findViewById(R.id.textView_selected_department_text);

        findViewById(R.id.button_select_department).setOnClickListener(v -> {
            // create the intent to start the SelectDepartment activity
            Intent intent = new Intent(Registration.this, Department.class);

            startDeptForResult.launch(intent);

            //start activity for result ..






        });






    }
}
