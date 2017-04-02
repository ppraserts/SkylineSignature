package com.pprasert.skylinesignature;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Helper {

    public static  int currentFragementPosition;
    public static  int currentRoundPosition;

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Bitmap bitmap = Bitmap.createScaledBitmap(returnedBitmap,view.getWidth(),view.getHeight(),false);
        //Bind a canvas to it
        Canvas canvas = new Canvas(bitmap);
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
        return bitmap;
    }

    public static Bitmap combineImages(ArrayList<Bitmap> bitmap) {
        int w = 0, h = 0;
        for (int i = 0; i < bitmap.size(); i++) {
            w = bitmap.get(i).getWidth() > w ? bitmap.get(i).getWidth() : w;
            h += bitmap.get(i).getHeight();
        }

        Bitmap temp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(temp);
        int top = 0;
        for (int i = 0; i < bitmap.size(); i++) {
            Log.d("HTML", "Combine: "+i+"/"+bitmap.size());

            top = (i == 0 ? 0 : h - bitmap.get(i).getHeight());
            canvas.drawBitmap(bitmap.get(i), 0f, top, null);
        }
        return temp;
    }

    public static String getSavedImageFileName()
    {
        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;
        return "SkyLine_" + dateFormat.format(date) + ".png";
    }
}
