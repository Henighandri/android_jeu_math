package com.heni.jeumathmental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.mariuszgromada.math.mxparser.Expression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class TrueFalseActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    TextView question,timing,textScore;
    Button BTrue,BFalse,Menu,continu;
    int scord=0;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    long starCountDouwn=10000;
    LinearLayout linearLayout;
    int number_qu=10;
    String stringquestion="",strquestion;

    int resultat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);
        question=(TextView)findViewById(R.id.question);
        BTrue=(Button)findViewById(R.id.Rep1);
        BFalse=(Button)findViewById(R.id.Rep2);

        Menu=(Button)findViewById(R.id.Menu);
        continu=(Button)findViewById(R.id.Continue);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        timing=(TextView)findViewById(R.id.a);
        textScore=(TextView)findViewById(R.id.score);
        linearLayout=(LinearLayout) findViewById(R.id.layoutId);
        progressBar.setMax(10 * 1000);

        MobileAds.initialize(this, this.getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(this.getString(R.string.InterstitialId));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

/*
if (activity.equals("Calculator")) {

    getQuestion("questionCalculator.txt","repCalculator.txt");

}else if (activity.equals("TF_Activity")){

    getQuestion("quvf.txt","repvf.txt");
}*/

        Showtxt();

        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new shard(TrueFalseActivity.this,scord,"TFActivity").sevegard();
                Intent intent =new Intent(TrueFalseActivity.this,MainActivity.class);

                startActivity(intent);
                finish();

            }
        });
        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new shard(TrueFalseActivity.this,scord,"TFActivity").sevegard();
                int scoreglobal = new shard(TrueFalseActivity.this, "scoreglobal").getScormax();

                if (scoreglobal - 5 >= 0) {
                    new shard(TrueFalseActivity.this, scoreglobal-5, "scoreglobal").sevegardGlobalscor();
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else continu();

                }

            }
        });
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.

                continu();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        BTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultat == result(strquestion)) {
                    starCountDouwn=10000;
                    Showtxt();
                    scord++;
                    textScore.setText(Integer.toString(scord));


                }else fini();


            }
        });
        BFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (resultat != result(strquestion)) {

                    starCountDouwn=10000;
                    scord++;
                    textScore.setText(Integer.toString(scord));
                    Showtxt();
                }else fini();
            }
        });




    }
    public void Showtxt() {

        game();
        Random random;
        int ran;
        int ranOper;
       Random random1 = new Random();

        int rando = random1.nextInt(number_qu);

        stringquestion=Integer.toString(rando);
       for (int i=0;i<2;i++) {
           String stringope = "";
            random = new Random();

           ran = random.nextInt(number_qu);

           Random randomOperation = new Random();

           ranOper = randomOperation.nextInt(3);
           switch (ranOper) {
               case 0:
                   stringope = "+";
                   break;
               case 1:
                   stringope = "-";
                   break;
               case 2:
                   stringope = "*";
                   break;
              /* case 3:
                   stringope = "/";
                   break;*/
           }


           stringquestion = stringquestion + stringope + ran;
           strquestion=stringquestion;
       }
       Random randomResult = new Random();

        int ranrusult = randomResult.nextInt(2);
        if (ranrusult==1){
            resultat=result(stringquestion);
            question.setText(stringquestion+"="+resultat);
        }else{
            resultat=result(stringquestion)+rando;
            question.setText(stringquestion+"="+resultat);
        }



    }

    private int result(String qustion){

        Expression e = new Expression(qustion);
        //String result = String.valueOf(e.calculate());

        return (int) e.calculate();

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

                fini();
                timing.setVisibility(View.INVISIBLE);

            }
            @Override
            public void onTick ( long millisUntilFinished){
                starCountDouwn=millisUntilFinished;

                progressBar
                        .setProgress((int) (progressBar.getMax() - millisUntilFinished+1000));
                timing.setText(Integer.toString(10-(progressBar.getProgress()/1000)));



            }

        };


        countDownTimer.start();


    }
    void fini(){
        linearLayout.setVisibility(View.VISIBLE);
        BFalse.setVisibility(View.GONE);
        BTrue.setVisibility(View.GONE);

        question.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        timing.setVisibility(View.GONE);
        countDownTimer.cancel();
        int a=scord/5;
        int scoreglob = new shard(TrueFalseActivity.this, "scoreglobal").getScormax();
        new shard(TrueFalseActivity.this, scoreglob+a,"scoreglobal").sevegardGlobalscor();
    }
    void continu(){
        linearLayout.setVisibility(View.INVISIBLE);
        BFalse.setVisibility(View.VISIBLE);
        BTrue.setVisibility(View.VISIBLE);

        question.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        timing.setVisibility(View.VISIBLE);
        starCountDouwn=10000;
        game();
    }


    @Override
    protected void onPause() {
        super.onPause();
        new shard(TrueFalseActivity.this,scord,"TFActivity").sevegard();

    }
}