package com.heni.jeumathmental;

import androidx.appcompat.app.AppCompatActivity;


import androidx.cardview.widget.CardView;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

import hotchemi.android.rate.AppRate;


public class MainActivity extends AppCompatActivity {
    int scoreCalculator,scoreTrueFalse,scoreEquality,scoreFollow,scoreChallenge,scoreMemory;
    TextView RecordCalculator,RecordTF,RecordEquality,RecordFollow,RecordChallenge,RecordMemory;
    Intent intent,TF_intent,Equality_intent,Follows_intent,Challenge_intent,Memory_intent;
    CardView  CardCalculator,CardTF,CardEquality,CardFollow,CardChallenge,CardMemory;
    ImageView Image_Calculator,Image_TF,Image_Equality,ImageFollow,Image_Challenge,Image_Memory;
    TextView textScoreGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecordCalculator=(TextView)findViewById(R.id.record_calculator);
        RecordTF=(TextView)findViewById(R.id.record_TF);
        RecordEquality=(TextView)findViewById(R.id.record_equality);
        RecordFollow=(TextView)findViewById(R.id.record_follows);
        RecordChallenge=(TextView)findViewById(R.id.record_challenge);
        RecordMemory=(TextView)findViewById(R.id.recordMemory);
        CardCalculator=(CardView)findViewById(R.id.calculatorcardId);
        CardTF=(CardView)findViewById(R.id.TFcardId);
        CardEquality=(CardView)findViewById(R.id.EquatityCardId);
        CardFollow=(CardView)findViewById(R.id.FollowCardId);
        CardChallenge=(CardView)findViewById(R.id.ChallengeCardId);
        CardMemory=(CardView)findViewById(R.id.momory);
        Image_Calculator=(ImageView)findViewById(R.id.etoil_calculator);
        Image_TF=(ImageView)findViewById(R.id.etoil_TF);
        Image_Equality=(ImageView)findViewById(R.id.etoil_equality);
        ImageFollow=(ImageView)findViewById(R.id.etoil_follows);
        Image_Challenge=(ImageView)findViewById(R.id.etoil_challenge);
        Image_Memory=(ImageView)findViewById(R.id.etoil_Memory);
        textScoreGlobal=(TextView)findViewById(R.id.scoreglobal);

        int scoreglobal= new shard(MainActivity.this,"scoreglobal").getScormax();
        textScoreGlobal.setText(""+scoreglobal);

        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);



        CreatNotificationChannel();
        Intent Notif_intent= new Intent(MainActivity.this,RecieverBrodcaste.class);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,Notif_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager =(AlarmManager)getSystemService(ALARM_SERVICE) ;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 27);
        calendar.set(Calendar.SECOND, 00);


       // alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pendingIntent);

        getDataFromShard();
        CardCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this, CalculatorActivity.class);

                startActivity(intent);

            }
        });

        CardTF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TF_intent=new Intent(MainActivity.this, TrueFalseActivity.class);

                startActivity(TF_intent);
            }
        });

        CardEquality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Equality_intent=new Intent(MainActivity.this, EqualityActivity.class);

                startActivity(Equality_intent);
            }
        });

        CardFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Follows_intent=new Intent(MainActivity.this, FollowsActivity.class);

                startActivity(Follows_intent);
            }
        });
        CardChallenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Challenge_intent=new Intent(MainActivity.this, challengeActivity.class);

                startActivity(Challenge_intent);
            }
        });

        CardMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memory_intent=new Intent(MainActivity.this, MemorylActivity.class);

                startActivity(Memory_intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromShard();
    }

    void getDataFromShard(){

         scoreCalculator= new shard(MainActivity.this,"calculator").getScormax();
        RecordCalculator.setText(""+this.getString(R.string.record)+":"+scoreCalculator);

         scoreTrueFalse= new shard(MainActivity.this,"TFActivity").getScormax();
        RecordTF.setText(""+this.getString(R.string.record)+":"+scoreTrueFalse);

         scoreEquality= new shard(MainActivity.this,"equality").getScormax();
        RecordEquality.setText(""+this.getString(R.string.record)+":"+scoreEquality);

         scoreFollow= new shard(MainActivity.this,"Follows").getScormaxFollow();
        RecordFollow.setText(""+this.getString(R.string.record)+":"+scoreFollow/1000+":"+scoreFollow%1000);

         scoreChallenge= new shard(MainActivity.this,"challenge").getScormax();
        RecordChallenge.setText(""+this.getString(R.string.record)+":"+scoreChallenge);

         scoreMemory= new shard(MainActivity.this,"memory").getScormax();
        RecordMemory.setText(""+this.getString(R.string.record)+":"+scoreMemory);




        if (scoreCalculator>20){
            Image_Calculator.setImageResource(R.drawable.star);
        }
        if (scoreTrueFalse>20){
            Image_TF.setImageResource(R.drawable.star);
        }
        if (scoreEquality>20){
            Image_Equality.setImageResource(R.drawable.star);
        }
        if (scoreFollow/1000<5){
            ImageFollow.setImageResource(R.drawable.star);
        }
        if (scoreChallenge>10){
            Image_Challenge.setImageResource(R.drawable.star);
        }
        if (scoreMemory>3){
            Image_Memory.setImageResource(R.drawable.star);
        }

    }
    private void CreatNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.Notif_name);
            String description = getString(R.string.Notif_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notificationId", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}

