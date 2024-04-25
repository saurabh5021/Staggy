package com.example.staggy;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class DecodeResultActivity extends AppCompatActivity {

    public static boolean passwordFound;
    TextView secretMsg;
    Button menu, copyTxt;

    Button btnGet;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode_result);

        if (DecodeActivity.progressDialog.isShowing()) {
            DecodeActivity.progressDialog.dismiss();
        }

        secretMsg = findViewById(R.id.textView3);
        menu = findViewById(R.id.btnMenu);
        copyTxt = findViewById(R.id.btnCopy);

        btnGet = findViewById(R.id.btnGetMsg);
        pass = findViewById(R.id.PassDecode);

        if(passwordFound)
        {
            pass.setVisibility(View.VISIBLE);
            btnGet.setVisibility(View.VISIBLE);
        }
        else
        {
            secretMsg.setText(G.msgDecoded);
        }

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().equals(G.decodePassword)){
                    secretMsg.setText(G.msgDecoded);
                }
                else{
                    pass.setError("Password doesn't match");
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
            }
        });
        
        copyTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyText();
            }
        });
    }

    private void copyText() {
        String textToCopy = secretMsg.getText().toString();

        // Copy text to clipboard
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Copied Text", textToCopy);
        clipboardManager.setPrimaryClip(clipData);

        // Show a toast message indicating that text is copied
        Toast.makeText(DecodeResultActivity.this, "Text copied to clipboard", Toast.LENGTH_SHORT).show();

    }

    public void openMenu()
    {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.staggy_menu,menu);
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
        Intent intent = new Intent(this, RecentProjectsActivity.class);
        startActivity(intent);
    }
    public void openAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}