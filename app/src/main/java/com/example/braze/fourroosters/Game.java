package com.example.braze.fourroosters;

/**
 * Created by braze on 3/12/18.
 */

public class Game {

    //Member variables representing the title, image and information about the game
    private final String title;
    private final String info;
    private final int imageResource;

//    static final String TITLE_KEY = "Title";
//    static final String IMAGE_KEY = "Image Resource";

    /**
     * Constructor for the Game class data model
     * @param title The name if the game.
     * @param info Information about the game rules.
     * @param imageResource The resource for the game image
     */

    Game(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    /**
     * Gets the title of the game
     * @return The title of the sport.
     */
    String getTitle() {
        return title;
    }
    /**
     * Gets the info about the game
     * @return The info about the sport.
     */
    String getInfo() {
        return info;
    }

    /**
     * Gets the resource for the image
     * @return The resource for the image
     */
    int getImageResource() {
        return imageResource;
    }

    /**
     * Method for creating  the Detail Intent
     * @param context Application context, used for launching the Intent
     * @param title The title of the current Sport
     * @param imageResId The image resource associated with the current sport
     * @return The Intent containing the extras about the sport, launches Detail activity
     */
//    static Intent starter(Context context, String title, @DrawableRes int imageResId) {
//        Intent detailIntent = new Intent(context, DetailActivity.class);
//        detailIntent.putExtra(TITLE_KEY, title);
//        detailIntent.putExtra(IMAGE_KEY, imageResId);
//        return detailIntent;
//    }
}
