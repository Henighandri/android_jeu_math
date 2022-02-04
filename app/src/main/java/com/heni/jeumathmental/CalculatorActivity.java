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
import com.google.android.gms.ads.reward.RewardedVideoAd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class CalculatorActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    private TextView question,timing,textScore;
    private Button Rep1,Rep2,Rep3,Rep4,Menu,continu;
    private int scord=0,res;
    private ProgressBar progressBar;
    private  CountDownTimer  countDownTimer;
    private long starCountDouwn=10000;
    private LinearLayout linearLayout;
    private int number_qu=10;
    private ArrayList<String> listqu=new ArrayList<>();
    private ArrayList<String> listAn=new ArrayList<>();
    private ArrayList<String> verif_qu=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        question=(TextView)findViewById(R.id.question);
        Rep1=(Button)findViewById(R.id.Rep1);
        Rep2=(Button)findViewById(R.id.Rep2);
        Rep3=(Button)findViewById(R.id.Rep3);
        Rep4=(Button)findViewById(R.id.Rep4);
        Menu=(Button)findViewById(R.id.Menu);
        continu=(Button)findViewById(R.id.Continue);

        timing=(TextView)findViewById(R.id.a);
        textScore=(TextView)findViewById(R.id.score);
        linearLayout=(LinearLayout) findViewById(R.id.layoutId);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
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
        getQuestion("questionCalculator.txt","repCalculator.txt");
       Showtxt();

               Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new shard(CalculatorActivity.this,scord,"calculator").sevegard();
                Intent intent =new Intent(CalculatorActivity.this,MainActivity.class);

                startActivity(intent);

                finish();

            }
        });
        continu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new shard(CalculatorActivity.this, scord, "calculator").sevegard();
                int scoreglobal = new shard(CalculatorActivity.this, "scoreglobal").getScormax();

                if (scoreglobal - 5 >= 0) {
                    new shard(CalculatorActivity.this, scoreglobal-5, "scoreglobal").sevegardGlobalscor();
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

        Rep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(Rep1.getText().toString()) == res) {
                    starCountDouwn=10000;
                    Showtxt();
                    scord++;
                    textScore.setText(Integer.toString(scord));


                }else fini();


            }
        });
        Rep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(Rep2.getText().toString()) == res) {

                    starCountDouwn=10000;
                    scord++;
                    textScore.setText(Integer.toString(scord));
                    Showtxt();
                }else fini();
            }
        });
        Rep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(Rep3.getText().toString()) == res) {
                    starCountDouwn=10000;

                    scord++;
                 textScore.setText(Integer.toString(scord));
                    Showtxt();

                }else fini();

            }
        });
        Rep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(Rep4.getText().toString()) == res) {
                    starCountDouwn=10000;
                    scord++;
                    textScore.setText(Integer.toString(scord));
                    Showtxt();

                }else fini();

            }
        });




    }
    public void Showtxt() {
       int i = 0;

        game();
        Random random = new Random();
        int ran;
        ran = random.nextInt(number_qu);
        String qus = listqu.get(ran);
        if (number_qu == verif_qu.size()-1) {
            number_qu = number_qu + 10;
            Showtxt();
        }
        if (number_qu <= listqu.size() ) {
            if (verif_question(qus)) {
                verif_qu.add(qus);


                question.setText(qus);
                String[] Rep = listAn.get(ran).split(",");
                Rep1.setText(Rep[0]);
                Rep2.setText(Rep[1]);
                Rep3.setText(Rep[2]);
                Rep4.setText(Rep[3]);
                res = Integer.parseInt(Rep[4]);
            } else Showtxt();
        }else {


            Rep1.setText("");
            Rep2.setText("");
            Rep3.setText("");
            Rep4.setText("");
        }
    }
    private boolean verif_question(String qus) {
        for (int i=0;i<verif_qu.size();i++){
            if (qus.equals(verif_qu.get(i))){
                return false;
            }
        }
        return true;
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
        Rep1.setVisibility(View.GONE);
        Rep2.setVisibility(View.GONE);
        Rep3.setVisibility(View.GONE);
        Rep4.setVisibility(View.GONE);
        question.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        timing.setVisibility(View.GONE);
        countDownTimer.cancel();
        int a=scord/5;
        int scoreglob = new shard(CalculatorActivity.this, "scoreglobal").getScormax();
        int som=scoreglob+a;
        new shard(CalculatorActivity.this, som,"scoreglobal").sevegardGlobalscor();

    }
    void continu(){
        linearLayout.setVisibility(View.INVISIBLE);
        Rep1.setVisibility(View.VISIBLE);
        Rep2.setVisibility(View.VISIBLE);
        Rep3.setVisibility(View.VISIBLE);
        Rep4.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        timing.setVisibility(View.VISIBLE);
        starCountDouwn=10000;
        game();
    }

    void getQuestion(String question ,String rep){

        try {
            InputStream inputStream = getAssets().open(question);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listqu.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream inputStream = getAssets().open(rep);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                listAn.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        verif_qu.add(" ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        new shard(CalculatorActivity.this,scord,"calculator").sevegard();
    }
}
