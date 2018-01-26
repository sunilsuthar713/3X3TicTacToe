package com.sunil.user.a3x3tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button singlePlayerButton;
    Button twoPlayerButton;

    public void singleMode(View view) {

        Intent intent = new Intent(MainActivity.this, SinglePlayer.class);
        startActivity(intent);

    }

    public void multiMode(View view) {

        Intent intent = new Intent(MainActivity.this, MultiPlayer.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singlePlayerButton = (Button)findViewById(R.id.singlePlayerButton);
        twoPlayerButton = (Button)findViewById(R.id.twoPlayerButton);
    }
}
