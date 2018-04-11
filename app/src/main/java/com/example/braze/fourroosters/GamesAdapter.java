package com.example.braze.fourroosters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by braze on 3/12/18.
 */

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.GamesViewHolder> {

    //Member variables
    private GradientDrawable mGradientDrawable;
    private ArrayList<Game> mGameData;
    private Context mContext;

    /**
     * Constructor that passes in the games data and the context
     * @param gameData ArrayList containing the sports data
     * @param context Context of the application
     */
    GamesAdapter(Context context, ArrayList<Game> gameData) {
        this.mGameData = gameData;
        this.mContext = context;

        //Prepare gray placeholder
        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);

        //Make the placeholder same size as the images
        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.img_running);
        if(drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    /**
     * Required method for creating the viewholder objects.
     * @param parent The ViewGroup into which the new View is added after it is
     *               bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly create GamesViewHolder.
     */
    @Override
    public GamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GamesViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false), mGradientDrawable);
    }

    /**
     * Required method that binds the data to the viewholder.
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(GamesViewHolder holder, int position) {
        //Get the current sport
        Game currentGame = mGameData.get(position);

        //Bind the data to the views
        holder.bindTo(currentGame);
    }

    /**
     * Required method for determining the size of the data set.
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mGameData.size();
    }

    /**
     * GamesViewHolder class that represents each row of data in the RecyclerView
     */
    static class GamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Member Variables for the holder data
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mGamesImage;
        private Context mContext;
        private Game mCurrentGame;
        private GradientDrawable mGradientDrawable;

        /**
         * Constructor for the GamesViewHolder, used in onCreateViewHolder().
         * @param itemView The rootview of the list_item.xml layout file
         */
        GamesViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);

            //Initialize the views
            mTitleText = (TextView)itemView.findViewById(R.id.title);
            mInfoText = (TextView)itemView.findViewById(R.id.subTitle);
            mGamesImage = (ImageView)itemView.findViewById(R.id.image);

            mContext = context;
            mGradientDrawable = gradientDrawable;

            //Set the OnClickListener to the whole view
            itemView.setOnClickListener(this);
        }

         void bindTo(Game currentGame){
            //Populate the textviews with data
            mTitleText.setText(currentGame.getTitle());
            mInfoText.setText(currentGame.getInfo());

            //Get the current sport
            mCurrentGame = currentGame;

            //Load the images into the ImageView using the Glide library
            Glide.with(mContext).load(currentGame.
                    getImageResource()).placeholder(mGradientDrawable).into(mGamesImage);
        }

        @Override
        public void onClick(View view) {

            //Set up the intent
            Intent wannaGuess = new Intent(mContext, WannaGuessActivity.class);
            mContext.startActivity(wannaGuess);

//            Intent detailIntent = Game.starter(mContext, mCurrentSport.getTitle(),
//                    mCurrentSport.getImageResource());


            //Start the detail activity
//            mContext.startActivity(detailIntent);

            //anoter ver

            //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        //Find the view that shows the catch me category
////        TextView catchMe = (TextView) findViewById(R.id.catch_me);
////        catchMe.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent catchMeIntent = new Intent(MainActivity.this, CatchMe.class);
////                startActivity(catchMeIntent);
////            }
////        });
//
//    }
        }
    }
}
