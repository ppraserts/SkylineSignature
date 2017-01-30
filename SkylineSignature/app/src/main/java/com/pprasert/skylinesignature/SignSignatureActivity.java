package com.pprasert.skylinesignature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_signature);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvPlayTime = (TextView)findViewById(R.id.tvPlayTime);
        String mydate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        tvPlayTime.setText(mydate);

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
                signature = Bitmap.createScaledBitmap(signature, agreement.getWidth(), signature.getHeight() - 55, true);

                ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
                bitmapList.add(agreement);
                bitmapList.add(signature);
                bitmap =  Helper.combineImages(bitmapList);

                File filepath = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);

                File dir = new File(filepath.getAbsolutePath());
                dir.mkdirs();

                File file = new File(dir, "myimage.png");

                Toast.makeText(SignSignatureActivity.this, R.string.msg_saveImageSuccess, Toast.LENGTH_SHORT).show();

                try {
                    output = new FileOutputStream(file);

                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                    output.flush();
                    output.close();

                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public Bitmap getAgreementLang()
    {
        int position = Helper.currentFragementPosition;
        if(position == 0)
            return BitmapFactory.decodeResource(getResources(),
                    R.drawable.th);
        else if(position == 1)
            return BitmapFactory.decodeResource(getResources(),
                    R.drawable.en);
        else if(position == 2)
            return BitmapFactory.decodeResource(getResources(),
                    R.drawable.cn);

        return null;
    }

}
