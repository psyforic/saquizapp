package com.example.saquizapp;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class FinishedActivity extends AppCompatActivity {

    @BindView(R.id.circularProgressBar)
    CircularProgressBar circularProgressBar;
    @BindView(R.id.tvScore)
    TextView tvScore;
    @BindView(R.id.btnTryAgain)
    Button btnTryAgain;
     String backslash="\\";
    int correctAnswerTotal, wrongAnswersTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

//        correctAnswerTotal = getIntent().getIntExtra("correctAnswers", 0);
//        wrongAnswersTotal = getIntent().getIntExtra("wrongAnswers", 0);
//        circularProgressBar.setProgress(correctAnswerTotal);
        tvScore.setText(correctAnswerTotal+""+backslash.charAt(0)+"15");
    }
}