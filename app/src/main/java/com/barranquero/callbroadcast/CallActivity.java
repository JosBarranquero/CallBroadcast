package com.barranquero.callbroadcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CallActivity extends AppCompatActivity {
    TextView txvNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        txvNumber = (TextView)findViewById(R.id.txvPhoneNumber);

    }
}
