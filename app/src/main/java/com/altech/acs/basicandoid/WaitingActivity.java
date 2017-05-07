package com.altech.acs.basicandoid;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterAmountActivity_InputAmount;
import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.EnterAmountActivity_InputPrompt;
import static com.altech.acs.basicandoid.IntentExtraDataIdentifiers.WaitingActivity_InputPrompt;

public class WaitingActivity extends Activity {

    public static WaitingActivity Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        Instance = this;

        Intent thisIntent = getIntent();
        String sentData_Prompt = thisIntent.getStringExtra(WaitingActivity_InputPrompt);

        TextView promptText = (TextView) findViewById(R.id.WaitPromptText);
        promptText.setText(sentData_Prompt);
    }

}
