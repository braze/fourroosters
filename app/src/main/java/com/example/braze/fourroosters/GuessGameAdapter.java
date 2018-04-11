package com.example.braze.fourroosters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by braze on 3/25/18.
 */

public class GuessGameAdapter extends ArrayAdapter<GuessGame> {

//    private final Context context;
    private final ArrayList<GuessGame> guessGameArrayList;
    private LayoutInflater inflater;


    public GuessGameAdapter(Context context, ArrayList<GuessGame> guessGamesArrayList) {

        super(context, R.layout.game_flow_listview, guessGamesArrayList);

//        this.context = context;
        this.guessGameArrayList = guessGamesArrayList;

        // 1. Create inflater
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // 2. Get rowView from inflater
        View lineView = inflater.inflate(R.layout.game_flow_listview, parent, false);

        // 3. Get the three text view from the game folw listview
        ViewHolder holder = new ViewHolder();
        holder.mLineNumber = (TextView) lineView.findViewById(R.id.line_number);
        holder.mLineItem = (TextView) lineView.findViewById(R.id.line_item);
        holder.mLineDescription = (TextView) lineView.findViewById(R.id.line_description);

        // 4. Set the text for textView
        holder.mLineNumber.setText(guessGameArrayList.get(position).getIndexNumber());
        holder.mLineItem.setText(guessGameArrayList.get(position).getGuessNumber());
        holder.mLineDescription.setText(guessGameArrayList.get(position).getDescription());

        // 5. return rowView

        return lineView;
    }

    static class ViewHolder {

        private TextView mLineNumber;
        private TextView mLineItem;
        private TextView mLineDescription;
    }
}
