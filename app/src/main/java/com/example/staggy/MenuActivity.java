package com.example.staggy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button btnEnc = findViewById(R.id.btnEnc);
        Button btnDec = findViewById(R.id.btnDec);
        btnEnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEncodeActivity();
            }
        });
        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDecodeActivity();
            }
        });
    }
    public void openEncodeActivity(){
        intent = new Intent(this,EncodeActivity.class);
        startActivity(intent);
    }
    public void openDecodeActivity() {
        intent = new Intent(this, DecodeActivity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu Staggy) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.staggy_menu,Staggy);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.recent) {
            openRecentActivity();
        } else if (id == R.id.about) {
            openAboutActivity();
        }
        return super.onOptionsItemSelected(item);
    }
    public void openRecentActivity() {
        intent = new Intent(this, RecentProjectsActivity.class);
        startActivity(intent);
    }
    public void openAboutActivity() {
        intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}