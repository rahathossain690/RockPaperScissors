package com.example.rockpaperscissors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView me;
    private ImageView pc;
    private LinearLayout backMe;
    private LinearLayout backPc;
    private Button rock;
    private Button paper;
    private Button scissor;
    private TextView scoreMe;
    private TextView scorePc;
    private int myScore = 0;
    private int pcScore = 0;

    private enum Shot{
        rock, paper, scissor, no
    }

    private Shot meShot = Shot.no;
    private Shot pcShot = Shot.no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        me = (ImageView) findViewById(R.id.me);
        pc = (ImageView) findViewById(R.id.pc);
        backMe = (LinearLayout) findViewById(R.id.backMe);
        backPc = (LinearLayout) findViewById(R.id.backPc);
        rock = (Button) findViewById(R.id.rock);
        paper = (Button) findViewById(R.id.paper);
        scissor = (Button) findViewById(R.id.scissor);
        scoreMe = (TextView) findViewById(R.id.scoreMe);
        scorePc = (TextView) findViewById(R.id.scorePC);

        rock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pcPlay();
                meShot = Shot.rock;
                game();
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pcPlay();
                meShot = Shot.paper;
                game();
            }
        });

        scissor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pcPlay();
                meShot = Shot.scissor;
                game();
            }
        });

    }

    private void pcPlay(){
        int cur = (int)Math.floor(Math.random() * 3) + 1;

        if(cur == 1)  pcShot = Shot.rock;
        else if(cur == 2) pcShot =  Shot.paper;
        else pcShot =  Shot.scissor;
    }

    private void game(){
        Log.v("tag", "" + meShot + " " + pcShot + "");
        visual();
        if(meShot == pcShot) draw();
        else{
            if(meShot == Shot.rock){
                if(pcShot == Shot.paper){ // me Rock pc paper
                    pcWin();
                }
                else { // me rock pc scissor
                    meWin();
                }
            }
            if(meShot == Shot.paper){
                if(pcShot == Shot.rock){ // me paper pc rock
                    meWin();
                }
                else { // me paper pc scissor
                    pcWin();
                }
            }
            if(meShot == Shot.scissor){
                if(pcShot == Shot.rock){ // me scissor pc rock
                    pcWin();
                }
                else { // me scissor pc paper
                    meWin();
                }
            }
        }
        meShot = pcShot = Shot.no;
    }

    private void updateText(int in){
        if(in == 1) scoreMe.setText("" + myScore + "");
        else scorePc.setText("" + pcScore + "");
    }

    private void draw(){
        backMe.setBackgroundColor(getResources().getColor(R.color.white));
        backPc.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void pcWin(){
        pcScore++;
        backMe.setBackgroundColor(getResources().getColor(R.color.white));
        backPc.setBackgroundColor(getResources().getColor(R.color.red));
        updateText(2);
    }

    private void meWin(){
        myScore++;
        backMe.setBackgroundColor(getResources().getColor(R.color.green));
        backPc.setBackgroundColor(getResources().getColor(R.color.white));
        updateText(1);
    }

    private void visual(){
        if(meShot == Shot.rock) me.setImageResource(R.drawable.merock);
        if(meShot == Shot.paper) me.setImageResource(R.drawable.mepaper);
        if(meShot == Shot.scissor) me.setImageResource(R.drawable.mescissor);
        if(pcShot == Shot.rock) pc.setImageResource(R.drawable.pcrock);
        if(pcShot == Shot.paper) pc.setImageResource(R.drawable.pcscissor);
        if(pcShot == Shot.scissor) pc.setImageResource(R.drawable.pcpaper);
    }
}
