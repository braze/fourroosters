package com.example.braze.fourroosters;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by braze on 3/25/18.
 */

public class GuessGame implements Parcelable {

    private String indexNumber;
    private String guessNumber;
    private String description;


    public GuessGame(String indexNumber, String guessNumber, String description) {
        super();
        this.indexNumber = indexNumber;
        this.guessNumber = guessNumber;
        this.description = description;
    }


    protected GuessGame(Parcel in) {
        indexNumber = in.readString();
        guessNumber = in.readString();
        description = in.readString();
    }

    public static final Creator<GuessGame> CREATOR = new Creator<GuessGame>() {
        @Override
        public GuessGame createFromParcel(Parcel in) {
            return new GuessGame(in);
        }

        @Override
        public GuessGame[] newArray(int size) {
            return new GuessGame[size];
        }
    };

    public String getIndexNumber() {
        return indexNumber;
    }

    public String getGuessNumber() {
        return guessNumber;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(indexNumber);
        dest.writeString(guessNumber);
        dest.writeString(description);
    }
}
