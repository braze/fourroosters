package com.example.braze.fourroosters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class WannaGuessActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String RETAINED_LIST_OF_ATTEMPTS = "key";
    private static final String RETAINED_SECRET_NUMBER = "secretNumber";
    private static final String RETAINED_GUESS_NUMBER = "guessNumber";

    private TextView textView;
    private String[] guessNumber = {"X","X","X","X"};
    private GuessGameAdapter mAdapter;

    /**
     * this is the number user trying guess
     */
    private String[] secretNumber = new String[4];

    private ArrayList<GuessGame> mListOfAttempts = new ArrayList<>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wanna_guess);

        if (savedInstanceState != null){
            mListOfAttempts = savedInstanceState.getParcelableArrayList(RETAINED_LIST_OF_ATTEMPTS);
            secretNumber = savedInstanceState.getStringArray(RETAINED_SECRET_NUMBER);
            guessNumber = savedInstanceState.getStringArray(RETAINED_GUESS_NUMBER);

            TextView textView1 = (TextView) findViewById(R.id.firstGuessBlock);
            textView1.setText(guessNumber[0]);
            TextView textView2 = (TextView) findViewById(R.id.secondGuessBlock);
            textView2.setText(guessNumber[1]);
            TextView textView3 = (TextView) findViewById(R.id.thirdGuessBlock);
            textView3.setText(guessNumber[2]);
            TextView textView4 = (TextView) findViewById(R.id.fourthGuessBlock);
            textView4.setText(guessNumber[3]);

            deActivateButtons();
            activateOkButton();
        }
        else {
            generateSecretNumber();
            System.out.println("Secret number is: " + Arrays.toString(secretNumber));

            //make all buttons unavailable
            deActivateButtons();
        }

        mAdapter = new GuessGameAdapter(this, mListOfAttempts);

        ListView listView = (ListView) findViewById(R.id.game_flow_list);
        listView.setAdapter(mAdapter);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deActivateButtons();
                switch (v.getId()){
                    case R.id.firstGuessBlock :
                        textView = (TextView) findViewById(R.id.firstGuessBlock);
                        textView.setBackgroundResource(R.drawable.rectangle_red);
                        activateButtons();
                        break;
                    case R.id.secondGuessBlock :
                        textView = (TextView) findViewById(R.id.secondGuessBlock);
                        textView.setBackgroundResource(R.drawable.rectangle_red);
                        activateButtons();
                        break;
                    case R.id.thirdGuessBlock :
                        textView = (TextView) findViewById(R.id.thirdGuessBlock);
                        textView.setBackgroundResource(R.drawable.rectangle_red);
                        activateButtons();
                        break;
                    case R.id.fourthGuessBlock :
                        textView = (TextView) findViewById(R.id.fourthGuessBlock);
                        textView.setBackgroundResource(R.drawable.rectangle_red);
                        activateButtons();
                        break;
                    case R.id.okButton :
                        checkNumber(guessNumber);
                        mAdapter.notifyDataSetChanged();
                        reset();
                }
            }
        };
        findViewById(R.id.firstGuessBlock).setOnClickListener(clickListener);
        findViewById(R.id.secondGuessBlock).setOnClickListener(clickListener);
        findViewById(R.id.thirdGuessBlock).setOnClickListener(clickListener);
        findViewById(R.id.fourthGuessBlock).setOnClickListener(clickListener);
        findViewById(R.id.okButton).setOnClickListener(clickListener);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button)v;
        String buttonText = button.getText().toString();
        if (Objects.equals(buttonText, "delete")){
            textView.setText("X");
            putGuessNumber("X");
            deActivateOkButton();
        } else {
            textView.setText(buttonText);
            putGuessNumber(buttonText);
            activateOkButton();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(RETAINED_LIST_OF_ATTEMPTS, mListOfAttempts);
        outState.putStringArray(RETAINED_SECRET_NUMBER, secretNumber);
        outState.putStringArray(RETAINED_GUESS_NUMBER, guessNumber);
        super.onSaveInstanceState(outState);
    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        mListOfAttempts = savedInstanceState.getParcelableArrayList("key");
//        secretNumber = savedInstanceState.getStringArray("secretNumber");
//        super.onRestoreInstanceState(savedInstanceState);
//    }

    private void putGuessNumber(String buttonText){
        switch (textView.getId()){
            case (R.id.firstGuessBlock) : guessNumber[0] = buttonText;
                break;
            case (R.id.secondGuessBlock) : guessNumber[1] = buttonText;
                break;
            case (R.id.thirdGuessBlock) : guessNumber[2] = buttonText;
                break;
            case (R.id.fourthGuessBlock) : guessNumber[3] = buttonText;
                break;
        }
    }

    private void deActivateButtons(){
        //deselect gess textviews
        TextView one = (TextView) findViewById(R.id.firstGuessBlock);
        one.setBackgroundResource(R.drawable.rectangle);
        TextView two = (TextView) findViewById(R.id.secondGuessBlock);
        two.setBackgroundResource(R.drawable.rectangle);
        TextView three = (TextView) findViewById(R.id.thirdGuessBlock);
        three.setBackgroundResource(R.drawable.rectangle);
        TextView four = (TextView) findViewById(R.id.fourthGuessBlock);
        four.setBackgroundResource(R.drawable.rectangle);

        //deselect buttons
        String s;
        for (int i = 0; i < 10; i++) {
            s = "Button" + i;
            int resID = getResources().getIdentifier(s, "id", getPackageName());
            Button tmp = (Button) findViewById(resID);
            tmp.setClickable(false);
            tmp.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tmp.setBackgroundResource(R.drawable.button_disabled);
        }

        Button delete = (Button) findViewById(R.id.deleteButton);
        delete.setClickable(false);
        delete.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        delete.setBackgroundResource(R.drawable.button_disabled);

        deActivateOkButton();
    }

    private void deActivateOkButton() {
        Button ok = (Button) findViewById(R.id.okButton);
        ok.setClickable(false);
        ok.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        ok.setBackgroundResource(R.drawable.button_disabled);
    }

    private void activateOkButton() {
        if (Arrays.asList(guessNumber).contains("X")) {
            deActivateOkButton();
        } else {
            Button ok = (Button) findViewById(R.id.okButton);
            ok.setClickable(true);
            ok.setTextColor(getResources().getColor(R.color.black));
            ok.setBackgroundResource(R.drawable.button_enabled);
        }
    }

    private void activateButtons(){
        String s;
        for (int i = 0; i < 10; i++) {
            s = "Button" + i;
            String chekButton = String.valueOf(i);
            //check if button had been pressed
            if (Arrays.asList(guessNumber).contains(chekButton)){
                //do nothing
            } else {
                int resID = getResources().getIdentifier(s, "id", getPackageName());
                Button tmp = (Button) findViewById(resID);
                tmp.setClickable(true);
                tmp.setTextColor(getResources().getColor(R.color.black));
                tmp.setBackgroundResource(R.drawable.button_enabled);
            }
            activateOkButton();
        }

        Button delete = (Button) findViewById(R.id.deleteButton);
        delete.setClickable(true);
        delete.setTextColor(getResources().getColor(R.color.black));
        delete.setBackgroundResource(R.drawable.button_enabled);

    }

    private void deactivateTextViews() {
        TextView textView1 = (TextView) findViewById(R.id.firstGuessBlock);
        textView1.setText("X");
        textView1.setBackgroundResource(R.drawable.rectangle);

        TextView textView2 = (TextView) findViewById(R.id.secondGuessBlock);
        textView2.setText("X");
        textView2.setBackgroundResource(R.drawable.rectangle);

        TextView textView3 = (TextView) findViewById(R.id.thirdGuessBlock);
        textView3.setText("X");
        textView3.setBackgroundResource(R.drawable.rectangle);

        TextView textView4 = (TextView) findViewById(R.id.fourthGuessBlock);
        textView4.setText("X");
        textView4.setBackgroundResource(R.drawable.rectangle);
    }

    private void reset() {
        deActivateButtons();
        deactivateTextViews();
        for (int i = 0; i < guessNumber.length; i++) {
            guessNumber[i] = "X";
        }
    }

    //check the users guess number
    private void checkNumber(String[] guessNumber) {

        //convert array to String
        StringBuilder builder = new StringBuilder();
        for(String s : guessNumber) {
            builder.append(s);
        }
        String guessNum = builder.toString();

        //counting roosters and chickens
        String index = String.valueOf(mListOfAttempts.size() + 1);
        if (Arrays.deepEquals(secretNumber, guessNumber)){
            win();
            mListOfAttempts.add(new GuessGame(index, guessNum, "Four roosters)"));

        } else {

            int rooster = 0;
            int chicken = 0;
            for (int i = 0; i < secretNumber.length; i++) {
                for (int j = 0; j < guessNumber.length; j++) {
                    if (i == j && secretNumber[i].equals(guessNumber[j])) {
                        rooster++;
                    }else if (i != j && secretNumber[i].equals(guessNumber[j])) {
                        chicken++;
                    }
                }
            }

            //making description of guessing number
            String description = "";
            if (rooster > 0 && chicken > 0) {
                description = rooster + " rooster," + chicken + " chicken";
            }else if (rooster == 0 && chicken > 0 ) {
                description = chicken + " chicken";

            }else if (rooster > 0 && chicken == 0 ) {
                description = rooster + " rooster";

            }else if (rooster == 0 && chicken == 0) {
                description = "nothing";
            }
            mListOfAttempts.add(new GuessGame(index, guessNum, description));
        }
    }

    private void generateSecretNumber() {
        Random random = new Random();
        Integer[] array = new Integer[4];
        int i = 0;
        while (true){
            Integer digit = random.nextInt(10);
            if (!Arrays.asList(array).contains(digit)){
                array[i] = digit;
                i++;
                if (i > 3){
                    break;
                }
            }
        }
        for (int j = 0; j <array.length ; j++) {
            secretNumber[j] = String.valueOf(array[j]);
        }
    }

    private void win(){
        StringBuilder builder = new StringBuilder();
        for(String s : secretNumber) {
            builder.append(s);
        }
        String secretNum = builder.toString();

        final AlertDialog alertDialog = new AlertDialog.Builder(WannaGuessActivity.this).create();
        alertDialog.setTitle(R.string.win_dialog);
        alertDialog.setMessage(secretNum);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "new game",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        reset();
                        generateSecretNumber();
                        mListOfAttempts.clear();
                        mAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

//        Toast message = Toast.makeText(WannaGuessActivity.this,"You Win!!!", Toast.LENGTH_LONG);
//        message.show();
    }

}