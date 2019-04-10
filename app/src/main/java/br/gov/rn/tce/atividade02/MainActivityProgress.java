package br.gov.rn.tce.atividade02;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivityProgress extends AppCompatActivity {

    private Button btn;
    private ProgressBar progressBar;

    Integer count =1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_progress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(10);
        btn = (Button) findViewById(R.id.btn);
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {
                count =1;
                btn.setClickable(false);
                btn.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                switch (view.getId()) {
                    case R.id.btn:
                        new MyTask().execute(10);
                        break;
                }
            }
        };
        btn.setOnClickListener(listener);
    }

    public void go(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    class MyTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {
            btn.setClickable(true);
            btn.setEnabled(true);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
    }
}