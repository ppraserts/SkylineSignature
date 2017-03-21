package com.pprasert.skylinesignature;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgreementFragment extends Fragment {

    private int position;

    public static AgreementFragment initial(int position) {
        AgreementFragment conFragment = new AgreementFragment();
        conFragment.position = position;
        return conFragment;
    }

    public AgreementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootViewFragment= inflater.inflate(R.layout.fragment_agreement, container, false);

        ImageView imgView = (ImageView)rootViewFragment.findViewById(R.id.imageView);

        ChooseFragmentViewToRender(imgView);

        PhotoViewAttacher mAttacher = new PhotoViewAttacher(imgView);
        mAttacher.update();

        return rootViewFragment;
    }

    private void ChooseFragmentViewToRender(ImageView imgView) {
        if(this.position == 0) {
            imgView.setImageResource(getDrawableID("th"));
        }
        else if(this.position == 1){
            imgView.setImageResource(getDrawableID("en"));
        }
        else if(this.position == 2){
            imgView.setImageResource(getDrawableID("cn"));
        }
    }

    private int getDrawableID(String mDrawableName)
    {
        int resID = getResources().getIdentifier(mDrawableName , "drawable", getActivity().getPackageName());
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
