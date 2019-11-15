package com.example.myhelloworld;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent myIntent = new Intent(MainActivity.this, DataOrder.class);
        EditText ipEdit = (EditText)findViewById(R.id.ip);
        EditText portEdit = (EditText)findViewById(R.id.port);

        myIntent.putExtra("ip",ipEdit.getText().toString());
        myIntent.putExtra("port",portEdit.getText().toString());

        Button connect_b = (Button) findViewById(R.id.connecter);
        connect_b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                startActivity(myIntent);
            }
        });
    }
}

