package com.gemini.inclass2;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import com.gemini.inclass2.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

  // Create a Key for the Intent extra serializable object


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    ActivityMainBinding binding;

    super.onCreate(savedInstanceState);

    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    binding.buttonRegistration.setOnClickListener(v -> {
      // start the Registration activity

      Intent intent = new Intent(MainActivity.this, Registration.class);



      startActivity(intent);
    });

  }


}
