package com.pprasert.skylinesignature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PlaytimeActivity extends AppCompatActivity {

    private ListView playtime_list_view;

    String[] itemname ={
            "Round 1",
            "Round 2",
            "Round 3",
            "Round 4",
            "Round 5",
            "Other"
    };

    String[] itemDesc ={
            "Play Time 08:30",
            "Play Time 10:00",
            "Play Time 11:30",
            "Play Time 13:00",
            "Play Time 14:30",
            "Anytime come to play"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playtime);

        Helper.currentRoundPosition = 6;
        Helper.currentFragementPosition = 0;

        CustomListAdapter adapter=new CustomListAdapter(this, itemname, itemDesc);
        playtime_list_view=(ListView)findViewById(R.id.playtime_list_view);
        playtime_list_view.setAdapter(adapter);

        playtime_list_view.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= "Choose " + itemname[+position] + " to sign risk concern";
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                Helper.currentRoundPosition = (position + 1);
                Helper.currentFragementPosition = 0;

                Intent mintent = new Intent(PlaytimeActivity.this, MainActivity.class);
                startActivity(mintent);

            }
        });
    }
}
