package com.gemini.hw1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gemini.hw1.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
  private static final DecimalFormat df = new DecimalFormat("0.00");

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setContentView(R.layout.activity_main);
        TextView billInput = (TextView) findViewById(R.id.textView_total_text_input);
        TextView tipOutput = (TextView) findViewById(R.id.textView_tip_Amount);
        TextView totalOutput = (TextView) findViewById(R.id.textView_total_text_number_variable);
        RadioGroup radio = (RadioGroup) findViewById(R.id.Discount_button_group);
        RadioGroup splitby = (RadioGroup) findViewById(R.id.Split_by_button_group);
        TextView textView_total_split_output = (TextView) findViewById(R.id.textView_total_split_output);

        SeekBar seekBar_custom_tip = (SeekBar) findViewById(R.id.seekBar_custom_tip);
        TextView textView_total_text_Percentage_variable_value = (TextView) findViewById(R.id.textView_total_text_Percentage_variable_value);
        Button clear_button = (Button) findViewById(R.id.button_clear);
        RadioButton splitby_button_group_1 = (RadioButton) findViewById(R.id.Split_by_button_group_1);

      // default value for split by




        tipOutput.setText("0");
        totalOutput.setText("0");
        seekBar_custom_tip.setActivated(false);


        billInput.setOnKeyListener((v, keyCode, event) -> {
          totalOutput.setText(billInput.getText().toString());

          if (!billInput.getText().toString().isEmpty()) {

                radio.setOnCheckedChangeListener((group, checkedId) -> {
                    double billAmount = Double.parseDouble(billInput.getText().toString());
                    RadioButton selectedSplitBy = (RadioButton) findViewById(splitby.getCheckedRadioButtonId());
                    switch (checkedId) {
                        case R.id.radioButton_10:
                            double tip;
                            tip = .1 * billAmount;
                            tipOutput.setText(String.valueOf(df.format(billAmount * .1)));
                            totalOutput.setText(String.valueOf(df.format(billAmount + tip)));
                            textView_total_split_output.setText(df.format(billAmount + tip) );
                            break;
                        case R.id.radioButton_15:
                            tip = .15 * billAmount;
                            tipOutput.setText(String.valueOf(df.format(billAmount * .15)));
                            totalOutput.setText(String.valueOf(df.format(billAmount + tip)));
                            textView_total_split_output.setText(df.format(billAmount + tip) );
                            break;
                        case R.id.radioButton_20:
                            tip = .2 * billAmount;
                            tipOutput.setText(String.valueOf(df.format(billAmount * .2)));
                            totalOutput.setText(String.valueOf(df.format(billAmount + tip)));
                            break;
                        case R.id.radioButton_Custom:
                            seekBar_custom_tip.setActivated(true);
                            double customTip = (double) seekBar_custom_tip.getProgress() / 100;

                            tipOutput.setText(String.valueOf(df.format(billAmount * customTip)));
                            break;

                    }

                });

          }
            return false;
        });
      seekBar_custom_tip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          RadioButton customRadio = (RadioButton) findViewById(R.id.radioButton_Custom);

          if(customRadio.isChecked()) {

            double customTip = (double) seekBar_custom_tip.getProgress() / 100;
            double billAmount = Double.parseDouble(billInput.getText().toString());
            textView_total_text_Percentage_variable_value.setText(String.valueOf(progress) + "%");
            tipOutput.setText(String.valueOf(df.format(billAmount * customTip)));
            totalOutput.setText(String.valueOf(df.format(billAmount + (billAmount * customTip))));

          }





        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
      });
        splitby.setOnCheckedChangeListener((group, checkedId) -> {
            double billAmount = (Double.parseDouble((billInput.getText().toString()) ) + Double.parseDouble(tipOutput.getText().toString()));
            switch (checkedId) {
              case R.id.Split_by_button_group_1:
                textView_total_split_output.setText(String.valueOf(df.format(billAmount)));
                    break;
                case R.id.Split_by_button_group_2:
                  textView_total_split_output.setText(String.valueOf(df.format(billAmount/2)));

                    break;
                case R.id.Split_by_button_group_3:
                  textView_total_split_output.setText(String.valueOf(df.format(billAmount/3)));

                  break;
              case R.id.Split_by_button_group_4:
                textView_total_split_output.setText(String.valueOf(df.format(billAmount/4)));

                break;


            } });


                // get custom tip amount, set tip to custom amount * bill, set tip, set total to bill + tip


            // create clear function
      clear_button.setOnClickListener(v -> {
        billInput.setText("0");
        tipOutput.setText("0");
        totalOutput.setText("0");
        seekBar_custom_tip.setActivated(false);

        textView_total_split_output.setText("0");
        textView_total_text_Percentage_variable_value.setText("0%");
        radio.clearCheck();
        splitby_button_group_1.setChecked(true);
        seekBar_custom_tip.setProgress(0);
      });



            setSupportActionBar(binding.toolbar);




    }


}
