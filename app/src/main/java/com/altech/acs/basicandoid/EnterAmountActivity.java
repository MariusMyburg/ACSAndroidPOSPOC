package com.altech.acs.basicandoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterAmountActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_amount);

        Intent thisIntent = getIntent();
        String sentData = thisIntent.getStringExtra("MyData");
        EditText editText = (EditText)findViewById(R.id.AmountEditText);
        editText.setText(sentData);

        Button proceedButton = (Button)findViewById(R.id.ProceedButton);
        proceedButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent();
                EditText editText = (EditText)findViewById(R.id.AmountEditText);
                intent.putExtra("EnteredAmount", editText.getText().toString()); //value should be your string from the edittext
                setResult(RESULT_OK, intent); //The data you want to send back
                finish();
            }
        });
    }
}
