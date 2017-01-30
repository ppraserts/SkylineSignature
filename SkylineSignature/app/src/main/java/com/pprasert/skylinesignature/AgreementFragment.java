package com.pprasert.skylinesignature;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


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

        return rootViewFragment;
    }

    private void ChooseFragmentViewToRender(ImageView imgView) {
        if(this.position == 0) {
            imgView.setImageResource(R.drawable.th);
        }
        else if(this.position == 1){
            imgView.setImageResource(R.drawable.en);
        }
        else if(this.position == 2){
            imgView.setImageResource(R.drawable.cn);
        }
    }

}
