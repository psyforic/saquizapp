package com.example.saquizapp;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class HomeScreen extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timeLimit = 25;
    @BindView(R.id.linearIndicator)
    LinearProgressIndicator linearIndicator;
    @BindView(R.id.count_down_time)
    TextView count_down_time;
    @BindView(R.id.main_question)
    TextView main_question;
    @BindView(R.id.first_option)
    TextView first_option;
    @BindView(R.id.second_option)
    TextView second_option;
    @BindView(R.id.third_option)
    TextView third_option;
    @BindView(R.id.fourth_option)
    TextView fourth_option;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        final Animation  animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        countDownTimer = new CountDownTimer(25000, 1000) {
            @Override
            public void onTick(long l) {
                timeLimit = timeLimit - 1;
                linearIndicator.setProgress(timeLimit);
            }

            @Override
            public void onFinish() {
                Dialog alertDialog = new Dialog(HomeScreen.this,R.style.CustomAlertDialog);
                alertDialog.setContentView(R.layout.custom_dialog);
//                alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                alertDialog.findViewById(R.id.time).startAnimation(animShake);
                alertDialog.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        }.start();
    }
}