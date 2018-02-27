package com.example.akauf.carloan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RadioButton;

import static java.lang.Math.pow;


public class MainActivity extends AppCompatActivity {

    private EditText CarCost;
    private EditText DownPayment;
    private EditText APR;
    private TextView Months;
    private TextView MonthlyPayment;
    private RadioButton Loan;
    private RadioButton Lease;
    private SeekBar bar;
    private RadioGroup group;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarCost = findViewById(R.id.CarCost);
        DownPayment = findViewById(R.id.DownPayment);
        APR = findViewById(R.id.APR);
        Months = findViewById(R.id.Monthsnumb);
        bar = findViewById(R.id.Monthsseek);
        MonthlyPayment = findViewById(R.id.done);
        Lease = findViewById(R.id.leasebutton);
        Loan = findViewById(R.id.loanbutton);
        group = findViewById(R.id.layout4);

        bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        Months.setText(i+"");
                        Calculate(seekBar);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        APR.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        Calculate(textView);
                        return false;
                    }
                }
        );
        group.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        Calculate(radioGroup);
                    }
                }
        );

    }

    public void Calculate(View v){
        try {
            String input1 = CarCost.getText().toString();
            String input2 = DownPayment.getText().toString();
            String input3 = APR.getText().toString();
            String input4 = Months.getText().toString();
            double cost = Double.parseDouble(input1);
            double downp = Double.parseDouble(input2);
            double apr = Double.parseDouble(input3);
            int months = Integer.parseInt(input4);
            double mr = (apr * .01) / 12;

            if (Loan.isChecked()) {
                double l = (cost - downp);
                double Payment = (mr * l) / (1 - (pow((1 + mr), -months)));
                MonthlyPayment.setText("$" + String.format("%.2f", Payment));
            }

            if (Lease.isChecked()) {
                double l = ((cost / 3) - downp);
                double Payment = (mr * l) / (1 - (pow((1 + mr), -36)));
                MonthlyPayment.setText("$" + String.format("%.2f", Payment));
            }
        }
        catch (NumberFormatException e){

        }


    }
}
