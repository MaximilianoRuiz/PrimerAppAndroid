package com.example.maxi.nomorefat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {

    private static final long SPLASH_SCREEN_DELAY = 3000;
    private ProgressBar progressBar;
    private Handler handler;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        handler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100)
                {
                    try
                    {
                        Thread.sleep(200);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    progressStatus += 10;
                    handler.post(new Runnable()
                    {
                        public void run()
                        {
                            progressBar.setProgress(progressStatus);
                        }
                    });

                }
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                SplashActivity.this.finish();
            }
        }).start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
