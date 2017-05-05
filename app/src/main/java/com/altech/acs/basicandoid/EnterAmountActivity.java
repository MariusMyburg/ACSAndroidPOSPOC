package com.altech.acs.basicandoid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
    }
}
