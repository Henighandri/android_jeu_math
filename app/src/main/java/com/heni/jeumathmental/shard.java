package com.heni.jeumathmental;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class shard {
    int scor;
    Context context;
    int scormax;
    String name;

    public shard(Context context, int scor, String name) {
        this.scor = scor;
        this.context=context;
        this.name=name;


    }
    public shard(Context context, String name) {
        this.scor = scor;
        this.context=context;
        this.name=name;


    }

    public int getScormax() {
        SharedPreferences shard=context.getSharedPreferences(name,MODE_PRIVATE);

        return shard.getInt("score_Max",0);
    }

  /*  public void setScormax(int scormax) {
        this.scormax = scormax;
    }*/
  public void sevegardGlobalscor(){
      SharedPreferences shard=context.getSharedPreferences(name,MODE_PRIVATE);
          SharedPreferences.Editor ed = shard.edit();
          ed.putInt("score_Max",scor);
          ed.apply();





  }
    public void sevegard(){
        SharedPreferences shard=context.getSharedPreferences(name,MODE_PRIVATE);
        int scorMax =shard.getInt("score_Max",0);

        if (scor>scorMax){
           scormax=scor;
            SharedPreferences.Editor ed = shard.edit();
            ed.putInt("score_Max",scor);
            ed.apply();


        }


    }
    public int getScormaxFollow() {
        SharedPreferences shard=context.getSharedPreferences(name,MODE_PRIVATE);

        return shard.getInt("score_Max",10000);
    }
    public void sevegardfollow() {
        SharedPreferences shard = context.getSharedPreferences(name, MODE_PRIVATE);
        int scorMax = shard.getInt("score_Max", 10000);

       if (scor < scorMax) {

            SharedPreferences.Editor ed = shard.edit();
            ed.putInt("score_Max", scor);
            ed.apply();


        }
    }


}
