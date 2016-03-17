package com.miguelcr.navigationdrawer;


import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements View.OnClickListener {
    ImageView duck;
    SoundPool soundPool;
    int idSoundDuck;
    RelativeLayout layout;
    Random random;
    int maxHeight, maxWidth;
    boolean mMeasured = false;
    int counter = 0;
    MenuItem itemCounter;


    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game, container, false);

        random = new Random();

        // We have in this Fragment a custom options menu
        setHasOptionsMenu(true);

        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!mMeasured) {
                    // Here your view is already layed out and measured for the first time
                    mMeasured = true; // Some optional flag to mark, that we already got the sizes
                    // Width and height of layout
                    maxHeight = layout.getHeight()-duck.getHeight();
                    maxWidth = layout.getWidth()-duck.getWidth();

                    generateDuckRandomPosition();
                }
            }
        });


        layout = (RelativeLayout) v.findViewById(R.id.layout);
        duck = (ImageView) v.findViewById(R.id.duck);
        duck.setOnClickListener(this);


        // Player properties
        AudioAttributes aa = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ALARM)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(aa)
                .build();

        // Load sound of guitar
        idSoundDuck = soundPool.load(getActivity(),R.raw.duck_sound,1);

        return v;
    }

    private void generateDuckRandomPosition() {
        // Random position
        int positionY = random.nextInt(maxHeight - 0 + 1) + 0;
        int positionX = random.nextInt(maxWidth - 0 + 1) + 0;

        duck.setX(positionX);
        duck.setY(positionY);
    }

    @Override
    public void onClick(View v) {
        soundPool.play(idSoundDuck,1,1,0,0,1);
        generateDuckRandomPosition();

        counter++;
        itemCounter.setTitle(String.valueOf(counter));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.game_options_menu, menu);

        itemCounter = menu.findItem(R.id.counterItem);
    }


}
