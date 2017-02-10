package com.pprasert.skylinesignature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SignSignatureActivity extends AppCompatActivity {
    static{
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_signature);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvPlayTime = (TextView)findViewById(R.id.tvPlayTime);
        String myDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        tvPlayTime.setText(myDate);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                // TODO Auto-generated method stub

                Bitmap bitmap;
                OutputStream output;

                View viewPager = findViewById(R.id.signView);

                Bitmap agreement = getAgreementLang();

                Bitmap signature = Helper.getBitmapFromView(viewPager);

                // TODO: 1/25/2017 set Width and Hi
                signature = Bitmap.createScaledBitmap(signature, agreement.getWidth(), agreement.getHeight() + agreement.getHeight()  , true);

                ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
                bitmapList.add(agreement);
                bitmapList.add(signature);
                bitmap =  Helper.combineImages(bitmapList);

                String root = Environment.getExternalStorageDirectory().toString();
                File filepath = new File(root + "/Skyline/");

                File dir = new File(filepath.getAbsolutePath());
                dir.mkdirs();

                File file = new File(dir, Helper.getSavedImageFileName());

                Toast.makeText(SignSignatureActivity.this, R.string.msg_saveImageSuccess, Toast.LENGTH_SHORT).show();

                try {
                    output = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                    output.flush();
                    output.close();
                    Log.d("Save OK","Save OK");

                }catch(Exception e) {
                    e.printStackTrace();
                    Log.d("Save NO","Save NO");
                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public Bitmap getAgreementLang()
    {
        int position = Helper.currentFragementPosition;
        if(position == 0)
            return decodeImage(getDrawableID("th"));
        else if(position == 1)
            return decodeImage(getDrawableID("en"));
        else if(position == 2)
            return decodeImage(getDrawableID("cn"));

        return null;
    }

    private int getDrawableID(String mDrawableName)
    {
        int resID = getResources().getIdentifier(mDrawableName , "drawable", this.getPackageName());
        return  resID;
    }

    public Bitmap decodeImage(int resourceId) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), resourceId, o);
            // The new size we want to scale to
            final int REQUIRED_SIZE = 1280; // you are free to modify size as your requirement

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE)
                scale *= 2;

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeResource(getResources(), resourceId, o2);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }

}
