package com.accintern.ricardarmankuodis.receiver;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO REFACTOR, use str resources for format and instantiate intent to avoid using getIntent twice
        mTextView= (TextView) findViewById(R.id.messageTextView);
        String msg = getIntent().getStringExtra("sms_body");
        String from = getIntent().getStringExtra("address");
        if(msg!=null) {
            mTextView.setText("from: "+from+"\n"+msg);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_MESSAGE,mTextView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTextView.setText(savedInstanceState.getString(KEY_MESSAGE));
    }
}
