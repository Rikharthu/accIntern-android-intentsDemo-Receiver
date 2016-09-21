package com.accintern.ricardarmankuodis.receiver;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO REFACTOR, use str resources for format and instantiate intent to avoid using getIntent twice
        mTextView= (TextView) findViewById(R.id.messageTextView);
        String msg = getIntent().getStringExtra("sms_body");
        Log.d(TAG,"received msg:["+msg+"}");
        String from = getIntent().getStringExtra("address");

        if(msg==null || msg.equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Receiver should not be launched explicitly!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity.this.finish();
                        }
                    });
            Dialog dialog = builder.create();
            dialog.setOwnerActivity(this);
            dialog.show();
        }else
            mTextView.setText("from: "+from+"\n"+msg);
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG,"onConfigurationChanged()");
        Log.d(TAG,newConfig.toString());
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        Log.d(TAG,"onStop()");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"onPause()");
        super.onPause();
    }
}
