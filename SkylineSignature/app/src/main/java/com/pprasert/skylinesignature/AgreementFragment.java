package com.pprasert.skylinesignature;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;


public class AgreementFragment extends Fragment {

    private int position;

    public static AgreementFragment initial(int position) {
        AgreementFragment conFragment = new AgreementFragment();
        conFragment.position = position;
        return conFragment;
    }

    public AgreementFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootViewFragment= inflater.inflate(R.layout.fragment_agreement, container, false);
        rootViewFragment.setBackgroundColor(Color.WHITE);
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

}
