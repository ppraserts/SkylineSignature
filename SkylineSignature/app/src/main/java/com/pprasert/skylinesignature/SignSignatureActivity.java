package com.pprasert.skylinesignature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

                //bitmap = getBitmapFromView(viewPager);
                Bitmap icon = BitmapFactory.decodeResource(getResources(),
                        R.drawable.th);

                Bitmap signature = getBitmapFromView(viewPager);

                // TODO: 1/25/2017 set Width and Hi 
//                signature.setWidth(icon.getWidth());
                //signature = scaleDown(signature, signature.getWidth() - 200 , true);

                ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
                bitmapList.add(icon);
                bitmapList.add(signature);
                bitmap = combineImages(bitmapList);

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

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    private static Bitmap combineImages(ArrayList<Bitmap> bitmap) {
        int w = 0, h = 0;
        for (int i = 0; i < bitmap.size(); i++) {
            if (i < bitmap.size() - 1) {
                w = bitmap.get(i).getWidth() > bitmap.get(i + 1).getWidth() ? bitmap.get(i).getWidth() : bitmap.get(i + 1).getWidth();
            }
            h += bitmap.get(i).getHeight();
        }

        Bitmap temp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(temp);
        int top = 0;
        for (int i = 0; i < bitmap.size(); i++) {
            Log.d("HTML", "Combine: "+i+"/"+bitmap.size());

            int hi = bitmap.get(0).getHeight()/3;

            top = (i == 0 ? 0 : top+bitmap.get(i).getHeight() - hi);
            canvas.drawBitmap(bitmap.get(i), 0f, top, null);
        }
        return temp;
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                  boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }
}
