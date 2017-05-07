package com.altech.acs.basicandoid;

import android.os.Bundle;
import android.app.Activity;

public class WaitingActivity extends Activity {

    public static WaitingActivity Instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        Instance = this;
    }

}
