package com.example.braze.fourroosters;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<Game> mGamesData;
    private GamesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the status bar.
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //Initialize the RecyclerView
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Initialize the ArrayList that will contain the data
        mGamesData = new ArrayList<>();

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = new GamesAdapter(this, mGamesData);
        mRecyclerView.setAdapter(mAdapter);

        //Get the data
        initializeData();

    }

    /**
     * Method for initializing the sports data from resources.
     */
    private void initializeData() {
        //Get the resources from the XML file
        String[] gamesList = getResources().getStringArray(R.array.main_titles);
        String[] gamesInfo = getResources().getStringArray(R.array.main_info);
        TypedArray gamesImageResources = getResources().obtainTypedArray(R.array.main_img);
        //Clear the existing data (to avoid duplication)
        mGamesData.clear();


        //Create the ArrayList of Sports objects with the titles, images
        // and information about each sport
        for(int i = 0; i < gamesList.length; i++){
            mGamesData.add(new Game(gamesList[i], gamesInfo[i], gamesImageResources.getResourceId(i,0)));
        }

        //Recycle the typed array
        gamesImageResources.recycle();

        //Notify the adapter of the change
        mAdapter.notifyDataSetChanged();
    }

    /**
     * onClick method for th FAB that resets the data
     * @param view The button view that was clicked
     */
    public void resetSports(View view) {
        initializeData();
    }
}
