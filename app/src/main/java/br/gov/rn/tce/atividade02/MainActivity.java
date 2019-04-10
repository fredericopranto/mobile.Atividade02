package br.gov.rn.tce.atividade02;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int time = 10;
    private Timer timer;
    private TextView txtTimer;
    private Button btnPlay;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btn_play);
        txtTimer = findViewById(R.id.txt_timer);
    }

    public void play(View view) {
        btnPlay.setClickable(false);
        btnPlay.setEnabled(false);
        handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startTimer();
                    }
                });
    }

    public void startTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        txtTimer.setText(String.format(Locale.getDefault(), "%d", time));
                        if (time > 0)
                            time -= 1;
                        else {
                            txtTimer.setText("Game Over!");
                            btnPlay.setClickable(true);
                            btnPlay.setEnabled(true);
                        }
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void go(View view) {
        Intent intent = new Intent(this, MainActivityProgress.class);
        startActivity(intent);
    }
}