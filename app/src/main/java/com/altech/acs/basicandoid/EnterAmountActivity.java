package com.altech.acs.basicandoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterAmountActivity_EnteredAmount;
import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterAmountActivity_InputAmount;
import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterAmountActivity_InputPrompt;

public class EnterAmountActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_amount);

        Intent thisIntent = getIntent();
        String sentData_Prompt = thisIntent.getStringExtra(EnterAmountActivity_InputPrompt);
        String sentData = thisIntent.getStringExtra(EnterAmountActivity_InputAmount);

        TextView promptText = (TextView) findViewById(R.id.AmountPromptText);
        promptText.setText(sentData_Prompt);

        EditText editText = (EditText)findViewById(R.id.AmountEditText);
        editText.setText(sentData);

        Button proceedButton = (Button)findViewById(R.id.ProceedButton);
        proceedButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent();
                EditText editText = (EditText)findViewById(R.id.AmountEditText);
                intent.putExtra(EnterAmountActivity_EnteredAmount, editText.getText().toString()); //value should be your string from the edittext
                setResult(RESULT_OK, intent); //The data you want to send back
                finish();
            }
        });




        Button btn = (Button) findViewById(R.id.buttonClear);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button0);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button6);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button7);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button8);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.button9);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberButtonClick(v);
            }
        });

        btn = (Button) findViewById(R.id.buttonDot);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDotButtonClick(v);
            }
        });

    }

    public void onNumberButtonClick(View view)
    {
        EditText editText = (EditText)findViewById(R.id.AmountEditText);
        editText.setText(editText.getText().toString() + ((Button)view).getText().toString());
    }

    public void onDotButtonClick(View view)
    {
        EditText editText = (EditText)findViewById(R.id.AmountEditText);
        editText.setText(editText.getText().toString() + ".");
    }

    public void onClearButtonClick(View view)
    {
        EditText editText = (EditText)findViewById(R.id.AmountEditText);
        editText.setText("");
    }
}
