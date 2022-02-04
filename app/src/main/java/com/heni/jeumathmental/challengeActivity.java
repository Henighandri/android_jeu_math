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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.mariuszgromada.math.mxparser.Expression;


import java.util.Random;

public class challengeActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    TextView question,timing,textScore,test;
    Button Rep1,Rep2,Rep3,Rep4,Rep5,Rep6,Rep7,Rep8,Rep9,Rep0,Menu,continu;
    Button bok,bclear,bmoin;
    int scord=0,res;
    ProgressBar progressBar;
    CountDownTimer  countDownTimer,countDownTimer2;
    long starCountDouwn=10000;
    LinearLayout linearLayout ,linearLayoutbuttonid;
    int number_qu=10;
     String stringquestion="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);




        question=(TextView)findViewById(R.id.question);
        Rep1=(Button)findViewById(R.id.Rep1);
        Rep2=(Button)findViewById(R.id.Rep2);
        Rep3=(Button)findViewById(R.id.Rep3);
        Rep4=(Button)findViewById(R.id.Rep4);
        Rep5=(Button)findViewById(R.id.Rep5);
        Rep6=(Button)findViewById(R.id.Rep6);
        Rep7=(Button)findViewById(R.id.Rep7);
        Rep8=(Button)findViewById(R.id.Rep8);
        Rep9=(Button)findViewById(R.id.Rep9);
        Rep0=(Button)findViewById(R.id.Rep0);
        bok=(Button)findViewById(R.id.Bok);
        bclear=(Button)findViewById(R.id.Bclear);
        bmoin=(Button)findViewById(R.id.Bmoin);
        Menu=(Button)findViewById(R.id.Menu);
        continu=(Button)findViewById(R.id.Continue);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        timing=(TextView)findViewById(R.id.a);
        textScore=(TextView)findViewById(R.id.score);
        linearLayout=(LinearLayout) findViewById(R.id.layoutId);
        linearLayoutbuttonid=(LinearLayout) findViewById(R.id.layoutbuttonid);

        progressBar.setMax(10 * 1000);

        MobileAds.initialize(this, this.getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(this.getString(R.string.InterstitialId));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        Intent i=getIntent();
        String activity=i.getStringExtra("Activity");
/*
if (activity.equals("Calculator")) {

    getQuestion("questionCalculator.txt","repCalculator.txt");

}else if (activity.equals("TF_Activity")){

    getQuestion("quvf.txt","repvf.txt");
}*/
game1();
stringquestion=question.getText().toString();
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new shard(challengeActivity.this,scord,"challenge").sevegard();
                Intent intent =new Intent(challengeActivity.this,MainActivity.class);

                startActivity(intent);
                finish();

            }
        });
        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new shard(challengeActivity.this,scord,"challenge").sevegard();
                int scoreglobal = new shard(challengeActivity.this, "scoreglobal").getScormax();

                if (scoreglobal - 3 >= 0) {
                    new shard(challengeActivity.this, scoreglobal-5, "scoreglobal").sevegardGlobalscor();
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
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                continu();

            }
        });


        bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             question.setText("");

            }
        });
        bok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (question.getText().toString()!="") {
                if (result() == Integer.parseInt(question.getText().toString())) {

                    game1();
                    countDownTimer.cancel();
                    progressBar.setProgress(0);
                    timing.setText("");

                    scord++;
                    textScore.setText(Integer.toString(scord));

                } else {
                    fini();
                }

            }

            }
        });


        bmoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression("-");

            }
        });
               Rep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep1.getText().toString());

            }
        });
        Rep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep2.getText().toString());

            }
        });
        Rep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep3.getText().toString());

            }
        });
        Rep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                writeExpression(Rep4.getText().toString());

            }
        });

        Rep5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep5.getText().toString());


            }
        });
        Rep6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep6.getText().toString());

            }
        });
        Rep7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep7.getText().toString());

            }
        });
        Rep8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep8.getText().toString());


            }
        });
        Rep9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep9.getText().toString());

            }
        });
        Rep0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeExpression(Rep0.getText().toString());

            }
        });



    }
    public void Showtxt() {
        String stringope="";
        Random random = new Random();
        int ran;
        ran = random.nextInt(number_qu);

        Random randomOperation = new Random();
        int ranOper;
        ranOper = randomOperation.nextInt(3);
        switch (ranOper){
            case 0:stringope="+";break;
            case 1:stringope="-";break;
            case 2:stringope="*";break;
           // case 3:stringope="/";break;
        }
       /* if (stringope=="/"&&ran==0){
            Showtxt();
        }*/
        question.setText(stringope+ran);
        stringquestion=stringquestion+stringope+ran;

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
        linearLayoutbuttonid.setVisibility(View.GONE);

        question.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        timing.setVisibility(View.GONE);
        countDownTimer.cancel();
        int a=scord/3;
        int scoreglob = new shard(challengeActivity.this, "scoreglobal").getScormax();
        new shard(challengeActivity.this, scoreglob+a,"scoreglobal").sevegardGlobalscor();
    }
    void continu(){
        linearLayout.setVisibility(View.GONE);
        linearLayoutbuttonid.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        timing.setVisibility(View.VISIBLE);

        game1();
        countDownTimer.cancel();
        progressBar.setProgress(0);
        timing.setText("");

    }

    private void game1() {

        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }


        countDownTimer2 = new CountDownTimer
                (5000, 1000) {


            @Override
            public void onFinish() {


            }

            @Override
            public void onTick(long millisUntilFinished) {

               // Toast.makeText(challengeActivity.this, ""+millisUntilFinished, Toast.LENGTH_SHORT).show();
                Rep0.setEnabled(false);
                Rep1.setEnabled(false);
                Rep2.setEnabled(false);
                Rep3.setEnabled(false);
                Rep4.setEnabled(false);
                Rep5.setEnabled(false);
                Rep6.setEnabled(false);
                Rep7.setEnabled(false);
                Rep8.setEnabled(false);
                Rep9.setEnabled(false);
                bclear.setEnabled(false);
                bmoin.setEnabled(false);
                bok.setEnabled(false);

                int p= (int) (5000 - millisUntilFinished);

               if (5-(p/1000)==5 ){

                   Random random = new Random();
                   int ran;
                   ran = random.nextInt(number_qu);
                   question.setText(Integer.toString(ran));
                   stringquestion=Integer.toString(ran);

               }else if (5-(p/1000)==3  || 5-(p/1000)==4  ){

                   Showtxt();
               }else if (5-(p/1000)==2){
                   question.setText("=?");
               }else if (5-(p/1000)==1){

                   question.setText("");
                   starCountDouwn=10000;
                   Rep0.setEnabled(true);
                   Rep1.setEnabled(true);
                   Rep2.setEnabled(true);
                   Rep3.setEnabled(true);
                   Rep4.setEnabled(true);
                   Rep5.setEnabled(true);
                   Rep6.setEnabled(true);
                   Rep7.setEnabled(true);
                   Rep8.setEnabled(true);
                   Rep9.setEnabled(true);
                   bclear.setEnabled(true);
                   bmoin.setEnabled(true);
                   bok.setEnabled(true);
                   game();

                  // test.setText(stringquestion+"="+Integer.toString(result()));


               }





            }

        };


        countDownTimer2.start();


    }
   private int result(){

        Expression e = new Expression(stringquestion);
        //String result = String.valueOf(e.calculate());

       return (int)e.calculate();

    }
    void writeExpression(String value){
        String expression = question.getText().toString();
        expression = expression + value;

        question.setText(expression);
    }

    @Override
    protected void onPause() {
        super.onPause();
        new shard(challengeActivity.this,scord,"challenge").sevegard();
    }
}