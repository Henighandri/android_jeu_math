package com.heni.jeumathmental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.Random;

public class MemorylActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private AdView mAdView;
    private RewardedVideoAd mRewardedVideoAd;
    String rewardId;
    Boolean iscompleted=false;
    Button b11, b22, b33, b44, b5, b6, b7, b8, b9,b10,b111,b12,menu,replay,button;
    ArrayList<Integer> listnum1,listnum2;
    int turn=0;
    int numButton,score=0,scorepurfini=0;
    long starCountDouwn=20000;
    TextView temp, timing, textScore;
    CountDownTimer countDownTimer, countDownTimer2,countDownTimer3;
LinearLayout linearLayout,linearLayout2;
int tempt=20;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        b11 = (Button) findViewById(R.id.Rep1);
        b22 = (Button) findViewById(R.id.Rep2);
        b33 = (Button) findViewById(R.id.Rep3);
        b44 = (Button) findViewById(R.id.Rep4);
        b5 = (Button) findViewById(R.id.Rep5);
        b6 = (Button) findViewById(R.id.Rep6);
        b7 = (Button) findViewById(R.id.Rep7);
        b8 = (Button) findViewById(R.id.Rep8);
        b9 = (Button) findViewById(R.id.Rep9);
        b10 = (Button) findViewById(R.id.Rep10);
        b111= (Button) findViewById(R.id.Rep11);
        b12 = (Button) findViewById(R.id.Rep12);
        menu = (Button) findViewById(R.id.Menu);
        replay = (Button) findViewById(R.id.Continue);
        timing=(TextView)findViewById(R.id.a);
        textScore=(TextView)findViewById(R.id.score);
        linearLayout = (LinearLayout) findViewById(R.id.layoutbuttonid);
        linearLayout2 = (LinearLayout) findViewById(R.id.layoutId);

        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(tempt * 1000);



        rewardId=this.getString(R.string.rewardId);
        MobileAds.initialize(this, this.getString(R.string.app_id));
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);;
        loadRewardedVideoAd();


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        listnum1 = new ArrayList<>();
        listnum2 = new ArrayList<>();
for (int i=0;i<6;i++) {
    verifRandom(listnum1);
    verifRandom(listnum2);
}
        b11.setText(Integer.toString(listnum1.get(0)));

        b22.setText(Integer.toString(listnum1.get(1)));

        b33.setText(Integer.toString(listnum1.get(2)));

        b44.setText(Integer.toString(listnum1.get(3)));

        b5.setText(Integer.toString(listnum1.get(4)));

        b6.setText(Integer.toString(listnum1.get(5)));

        b7.setText(Integer.toString(listnum2.get(0)));

        b8.setText(Integer.toString(listnum2.get(1)));

        b9.setText(Integer.toString(listnum2.get(2)));
        b10.setText(Integer.toString(listnum2.get(3)));

        b111.setText(Integer.toString(listnum2.get(4)));

        b12.setText(Integer.toString(listnum2.get(5)));



game1();

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new shard(MemorylActivity.this,score,"memory").sevegard();
                Intent i=new Intent(MemorylActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new shard(MemorylActivity.this,score,"memory").sevegard();
                int scoreglobal = new shard(MemorylActivity.this, "scoreglobal").getScormax();

                if (scoreglobal - 2 >= 0) {
                    new shard(MemorylActivity.this, scoreglobal-5, "scoreglobal").sevegardGlobalscor();
                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show();
                    } else recreate();

                }

            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b11.setText(Integer.toString(listnum1.get(0)));
                //turnB(b11,"?");
               // b11.setEnabled(false);
                //b11.setVisibility(View.INVISIBLE);

            turn++;

          if (turn==2){
              if ( numButton==listnum1.get(0)){
                  score++;

                  textScore.setText(Integer.toString(score));
                  b11.setText(Integer.toString(listnum1.get(0)));
                  turnB(b11,"num");
                  scorepurfini++;
                  if (scorepurfini==6){
                      stop();
                  }
              }else {

                  turnB(button,"?");
                  turnB(b11,"?");
                  score--;
                  textScore.setText(Integer.toString(score));

              }
              turn=0;

          }else {
              numButton=listnum1.get(0);
              button=b11;
          }



            }
        });
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b22.setText(Integer.toString(listnum1.get(1)));
              //  turnB(b22,"?");
                // b22.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum1.get(1)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b22.setText(Integer.toString(listnum1.get(1)));
                        turnB(b22,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }

                    }else {
                        turnB(b22,"?");
                        turnB(button,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum1.get(1);
                    button=b22;
                }
               // b11.setText(Integer.toString(listnum1.get(0)));


            }
        });
        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b33.setText(Integer.toString(listnum1.get(2)));

                // b33.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum1.get(2)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b33.setText(Integer.toString(listnum1.get(2)));
                        turnB(b33,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b33,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum1.get(2);
                    button=b33;
                }

            }
        });
        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b44.setText(Integer.toString(listnum1.get(3)));


                // b44.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum1.get(3)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b44.setText(Integer.toString(listnum1.get(3)));
                        turnB(b44,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b44,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum1.get(3);
                    button=b44;
                }


            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b5.setText(Integer.toString(listnum1.get(4)));

                // b5.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum1.get(4)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b5.setText(Integer.toString(listnum1.get(4)));
                        turnB(b5,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b5,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum1.get(4);
                    button=b5;
                }

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b6.setText(Integer.toString(listnum1.get(5)));

                // b6.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum1.get(5)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b6.setText(Integer.toString(listnum1.get(5)));
                        turnB(b6,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b6,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum1.get(5);
                    button=b6;
                }


            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b7.setText(Integer.toString(listnum2.get(0)));

                // b7.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum2.get(0)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b7.setText(Integer.toString(listnum2.get(0)));
                        turnB(b7,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b7,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum2.get(0);
                    button=b7;
                }

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b8.setText(Integer.toString(listnum2.get(1)));

                // b8.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum2.get(1)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b8.setText(Integer.toString(listnum2.get(1)));
                        turnB(b8,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b8,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum2.get(1);
                    button=b8;
                }

            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b9.setText(Integer.toString(listnum2.get(2)));

                // b9.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum2.get(2)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b9.setText(Integer.toString(listnum2.get(2)));
                        turnB(b9,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b9,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum2.get(2);
                    button=b9;
                }
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b10.setText(Integer.toString(listnum2.get(3)));

                // b10.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum2.get(3)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b10.setText(Integer.toString(listnum2.get(3)));
                        turnB(b10,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b10,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum2.get(3);
                    button=b10;
                }
            }
        });
        b111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b111.setText(Integer.toString(listnum2.get(4)));

                // b111.setVisibility(View.INVISIBLE);
                turn++;

                if (turn==2){
                    if ( numButton==listnum2.get(4)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b111.setText(Integer.toString(listnum2.get(4)));
                        turnB(b111,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b111,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum2.get(4);
                    button=b111;
                }
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                b12.setText(Integer.toString(listnum2.get(5)));

                // b12.setVisibility(View.INVISIBLE);

                turn++;

                if (turn==2){
                    if ( numButton==listnum2.get(5)){
                        score++;
                        textScore.setText(Integer.toString(score));
                        b12.setText(Integer.toString(listnum2.get(5)));
                        turnB(b12,"num");
                        scorepurfini++;
                        if (scorepurfini==6){
                            stop();
                        }
                    }else {
                        turnB(button,"?");
                        turnB(b12,"?");
                        score--;
                        textScore.setText(Integer.toString(score));

                    }
                    turn=0;

                }else {
                    numButton=listnum2.get(5);
                    button=b12;
                }
            }
        });




    }


    public int numRandom() {
        Random random = new Random();
        int ran = random.nextInt(6);
        return ran + 1;

    }

    public int verifRandom(ArrayList<Integer> listenum) {


        int ran = numRandom();

        if (rechercheRandom(ran,listenum)) {

            verifRandom(listenum);
        } else {
            listenum.add(ran);
            return ran;
        }

        return ran;
    }




    public boolean rechercheRandom(int ran,ArrayList<Integer> listenum) {
        for (int i = 0; i < listenum.size(); i++) {
            if (ran == listenum.get(i)) {
                return true;
            }
        }

        return false;


    }
    private void game() {

        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
        progressBar.setProgress(0);

        countDownTimer = new CountDownTimer
                (starCountDouwn, 1) {


            @Override
            public void onFinish() {
            stop();


            }
            @Override
            public void onTick ( long millisUntilFinished){
                starCountDouwn=millisUntilFinished;

                progressBar
                        .setProgress((int) (progressBar.getMax() - millisUntilFinished+1000));
                timing.setText(Integer.toString(tempt-(progressBar.getProgress()/1000)));



            }

        };


        countDownTimer.start();


    }


    private void game1() {

        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }


        countDownTimer2 = new CountDownTimer
                (3000, 1) {


            @Override
            public void onFinish() {
                turnButton();
                game();

            }

            @Override
            public void onTick(long millisUntilFinished) {

            progressBar.setProgress(0);
              //  t123.setText(Integer.toString((int) (3050 - millisUntilFinished) / 1000));


            }

        };


        countDownTimer2.start();


    }

    private void turnB(final Button bb , final String etat) {

       /* if (countDownTimer3 != null) {
            countDownTimer3.cancel();
        }*/


      CountDownTimer  countDownTimer3 = new CountDownTimer
                (500, 1) {


            @Override
            public void onFinish() {
                if (etat.equals("?")) {
                    bb.setText("?");
                }else{
                    bb.setEnabled(false);
                    button.setEnabled(false);
                    bb.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onTick(long millisUntilFinished) {


                //  t123.setText(Integer.toString((int) (3050 - millisUntilFinished) / 1000));


            }

        };


        countDownTimer3.start();


    }
    void turnButton(){
        b11.setText("?");
        b22.setText("?");
        b33.setText("?");
        b44.setText("?");
        b5.setText("?");
        b6.setText("?");
        b7.setText("?");
        b8.setText("?");
        b9.setText("?");
        b10.setText("?");
        b111.setText("?");
        b12.setText("?");
        b11.setEnabled(true);
        b22.setEnabled(true);
        b33.setEnabled(true);
        b44.setEnabled(true);
        b5.setEnabled(true);
        b6.setEnabled(true);
        b7.setEnabled(true);
        b8.setEnabled(true);
        b9.setEnabled(true);
        b10.setEnabled(true);
        b111.setEnabled(true);
        b12.setEnabled(true);



    }
    void stop(){
        linearLayout.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.VISIBLE);
        countDownTimer.cancel();
       // text.setVisibility(View.GONE);
        countDownTimer.cancel();
        int a=score/2;
        int scoreglob = new shard(MemorylActivity.this, "scoreglobal").getScormax();
        new shard(MemorylActivity.this, scoreglob+a,"scoreglobal").sevegardGlobalscor();

    }

    @Override
    protected void onPause() {
        super.onPause();
        new shard(MemorylActivity.this,score,"memory").sevegard();

    }


    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd(rewardId,
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {
     loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        if (iscompleted) {
            recreate();
        }
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {
        iscompleted=true;
    }
}