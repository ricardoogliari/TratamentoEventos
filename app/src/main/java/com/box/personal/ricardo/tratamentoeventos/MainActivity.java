package com.box.personal.ricardo.tratamentoeventos;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Button btnInicia;
    private Button btnPara;
    private LinearLayout container;

    private TimerTask tTask;
    private Timer timer;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInicia = (Button) findViewById(R.id.btnInicia);
        btnInicia.setOnClickListener(this);

        btnPara = (Button) findViewById(R.id.btnPara);
        btnPara.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                isRunning = false;
                timer.cancel();
                tTask.cancel();
                timer = null;
                tTask = null;
                System.gc();
            }
        });

        container = (LinearLayout) findViewById(R.id.container);
    }

    public void mudaCor(View v){
        setCor();
    }

    public void setCor(){
        int r = (int)(Math.random()*256);
        int g = (int)(Math.random()*256);
        int b = (int)(Math.random()*256);
        container.setBackgroundColor(Color.rgb(r, g, b));
    }

    @Override
    public void onClick(View view) {
        if (!isRunning) {
            tTask = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setCor();
                        }
                    });

                }
            };

            timer = new Timer();
            timer.schedule(tTask, 500, 500);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
