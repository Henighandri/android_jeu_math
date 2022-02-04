package com.heni.jeumathmental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Random;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class FollowsActivity extends AppCompatActivity implements RewardedVideoAdListener{
    Button b11, b22, b33, b44, b5, b6, b7, b8, b9,menu,replay;
    TextView  temp, text, t123;
    CountDownTimer countDownTimer, countDownTimer2;
    int starCountDouwn = 10009,s;
    ArrayList<Integer> listenum;
    int  Numm ,second,milliseconde;
    Toast toast;
    LinearLayout linearLayout ,linearLayout2;
    private AdView mAdView;
    private RewardedVideoAd mRewardedVideoAd;
    String rewardId;
    Boolean iscompleted=false;

int var=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follows);
        b11 = (Button) findViewById(R.id.b1);
        b22 = (Button) findViewById(R.id.b2);
        b33 = (Button) findViewById(R.id.b3);
        b44 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        menu = (Button) findViewById(R.id.Menu);
        replay = (Button) findViewById(R.id.Continue);
        linearLayout = (LinearLayout) findViewById(R.id.ly);
        linearLayout2 = (LinearLayout) findViewById(R.id.layoutId);
        temp = (TextView) findViewById(R.id.sco);
        text = (TextView) findViewById(R.id.txt);
        t123 = (TextView) findViewById(R.id.t123);


        rewardId=this.getString(R.string.rewardId);
        MobileAds.initialize(this, this.getString(R.string.app_id));
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);;
        loadRewardedVideoAd();


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);




        listenum = new ArrayList<>();
        for (int i=0;i<9;i++) {
            verifRandom();
        }
        b11.setText(Integer.toString(listenum.get(0)));

        b22.setText(Integer.toString(listenum.get(1)));

        b33.setText(Integer.toString(listenum.get(2)));

        b44.setText(Integer.toString(listenum.get(3)));

        b5.setText(Integer.toString(listenum.get(4)));

        b6.setText(Integer.toString(listenum.get(5)));

        b7.setText(Integer.toString(listenum.get(6)));

        b8.setText(Integer.toString(listenum.get(7)));

        b9.setText(Integer.toString(listenum.get(8)));

        Numm = 1;
        text.setText("Trouver le numero: " + Integer.toString(Numm));

        game1();




        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(b11.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(FollowsActivity.this.getString(R.string.trouver_le_num) + Integer.toString(Numm));
                    } else {
                        stop();
                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }


            }
        });
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(b22.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(FollowsActivity.this.getString(R.string.trouver_le_num) + Integer.toString(Numm));
                    } else {
                        stop();
                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(b33.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(FollowsActivity.this.getString(R.string.trouver_le_num) + Integer.toString(Numm));
                    } else {
                        stop();
                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(b44.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(FollowsActivity.this.getString(R.string.trouver_le_num)  + Integer.toString(Numm));
                    } else {
                        stop();
                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(b5.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(FollowsActivity.this.getString(R.string.trouver_le_num) + Integer.toString(Numm));
                    } else {
                        stop();
                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(b6.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(FollowsActivity.this.getString(R.string.trouver_le_num)  + Integer.toString(Numm));
                    } else {
                        stop();
                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(b7.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(FollowsActivity.this.getString(R.string.trouver_le_num)  + Integer.toString(Numm));
                    } else {
                        stop();
                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(b8.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(FollowsActivity.this.getString(R.string.trouver_le_num) + Integer.toString(Numm));
                    } else {
                        stop();
                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(b9.getText().toString()) == Numm) {
                    if (Numm != 9) {
                        Numm = Numm + 1;
                        text.setText(""+FollowsActivity.this.getString(R.string.trouver_le_num) + Integer.toString(Numm));
                    } else {
                        stop();

                    }


                } else {

                    Toast.makeText(FollowsActivity.this, "faux", Toast.LENGTH_SHORT).show();
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               s=second*1000+milliseconde;
                new shard(FollowsActivity.this,s,"Follows").sevegardfollow();
                Intent i=new Intent(FollowsActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* MainActivity.affichrReward++;
                if (MainActivity.affichrReward >= 3) {
                    MainActivity.affichrReward=0;*/
                s=second*1000+milliseconde;
                new shard(FollowsActivity.this,s,"Follows").sevegardfollow();
                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show();
                    } else recreate();
              /*  }else recreate();

                Toast.makeText(FollowsActivity.this, ""+MainActivity.affichrReward, Toast.LENGTH_SHORT).show();*/
            }
        });


    }
    private void game() {

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }


        countDownTimer = new CountDownTimer
                (starCountDouwn, 1) {


            @Override
            public void onFinish() {

            stop();


            }

            @Override
            public void onTick(long millisUntilFinished) {

                second = (int) (starCountDouwn - millisUntilFinished) / 1000;
                milliseconde = (int) (starCountDouwn - millisUntilFinished) % 1000;

                temp.setText(Integer.toString((int) (starCountDouwn - millisUntilFinished) / 1000) +
                        ":" + (int) (starCountDouwn - millisUntilFinished) % 1000/10);


            }

        };


        countDownTimer.start();


    }

    void stop(){
        linearLayout.setVisibility(View.GONE);
        linearLayout2.setVisibility(View.VISIBLE);
        countDownTimer.cancel();
        text.setVisibility(View.GONE);


    }

    private void game1() {

        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }


        countDownTimer2 = new CountDownTimer
                (3000, 1) {


            @Override
            public void onFinish() {
                t123.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            game();

            }

            @Override
            public void onTick(long millisUntilFinished) {


                t123.setText(Integer.toString((int) (3050 - millisUntilFinished) / 1000));


            }

        };


        countDownTimer2.start();


    }

    public int numRandom() {
        Random random = new Random();
        int ran = random.nextInt(9);
        return ran + 1;

    }

    public int verifRandom() {


        int ran = numRandom();

        if (rechercheRandom(ran)) {

            verifRandom();
        } else {
            listenum.add(ran);
            return ran;
        }

        return ran;
    }




    public boolean rechercheRandom(int ran) {
        for (int i = 0; i < listenum.size(); i++) {
            if (ran == listenum.get(i)) {
                return true;
            }
        }

        return false;


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