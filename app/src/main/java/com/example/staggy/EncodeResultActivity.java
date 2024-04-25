package com.example.staggy;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncodeResultActivity extends AppCompatActivity {

    Button btnSave;
    Button btnShr;
    ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encode_result);

        if (EncodeActivity.progressDialog.isShowing()) {
            EncodeActivity.progressDialog.dismiss();
        }

        btnSave = findViewById(R.id.btnSave);
        btnShr = findViewById(R.id.sharebtn);
        img2 = findViewById(R.id.img2);

        img2.setImageBitmap(G.enbmap);
        Bitmap imgBitmap = G.enbmap;

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OutputStream fOut;
                long currentTime = System.currentTimeMillis();
                File file = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), "staggy-"+currentTime+".png"); // the File to save ,
                try {
                    fOut = new FileOutputStream(file);
                    imgBitmap.compress(Bitmap.CompressFormat.PNG,100, fOut); // saving the Bitmap to a file,,
                    fOut.flush(); // Not really required
                    fOut.close(); // do not forget to close the stream
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(EncodeResultActivity.this, "Image saved to Downloads", Toast.LENGTH_SHORT).show();
            }
        });

        btnShr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "title");
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


                OutputStream outstream;
                try {
                    outstream = getContentResolver().openOutputStream(uri);
                    G.enbmap.compress(Bitmap.CompressFormat.PNG, 100, outstream);
                    outstream.close();
                } catch (Exception e) { }

                share.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(share, "Share Image"));
            }
//            @Override
//            public void onClick(View view) {
//                BitmapDrawable bitmapDrawable = (BitmapDrawable) img2.getDrawable();
//                Bitmap bitmap = bitmapDrawable.getBitmap();
//                shareImageandText(bitmap);
//            }


        });
    }
//    private void shareImageandText(Bitmap bitmap) {
//        Uri uri = getmageToShare(bitmap);
//        Intent intent = new Intent(Intent.ACTION_SEND);
//
//        intent.putExtra(Intent.EXTRA_STREAM, uri);
//        intent.putExtra(Intent.EXTRA_TEXT, "Secret Message");
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
//        intent.setType("image/png");
//        startActivity(Intent.createChooser(intent, "Share Via"));
//    }

//    private Uri getmageToShare(Bitmap bitmap) {
//        File imagefolder = new File(getCacheDir(), "images");
//        Uri uri = null;
//        try {
//            imagefolder.mkdirs();
//            File file = new File(imagefolder, "shared_image.png");
//            FileOutputStream outputStream = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
//            outputStream.flush();
//            outputStream.close();
//            uri = FileProvider.getUriForFile(this, "com.anni.shareimage.fileprovider", file);
//        } catch (Exception e) {
//            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_LONG).show();
//        }
//        return uri;
//    }

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