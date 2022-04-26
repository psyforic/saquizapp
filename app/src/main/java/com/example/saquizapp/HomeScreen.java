package com.example.saquizapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.saquizapp.MainActivity.arrayListQ;

@SuppressWarnings("ALL")
public class HomeScreen extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timeLimit;
    @BindView(R.id.linearIndicator)
    LinearProgressIndicator linearIndicator;
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
    List<QuizModelClass> arrayQ;
    QuizModelClass quizModel;
    int index = 0;
    @BindView(R.id.cardQuestion)
    CardView cardQuestion;
    @BindView(R.id.cardOption1)
    CardView cardOption1;
    @BindView(R.id.cardOption2)
    CardView cardOption2;
    @BindView(R.id.cardOption3)
    CardView cardOption3;
    @BindView(R.id.cardOption4)
    CardView cardOption4;
    @BindView(R.id.ic_next)
    LinearLayout ic_next;
    int correctAnswers = 0;
    int wrongAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
        onClickListeners();
        ic_next.setClickable(false);
        final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        countDownTimer = new CountDownTimer(25000, 1000) {
            @Override
            public void onTick(long l) {
                timeLimit = timeLimit - 1;
                linearIndicator.setProgress(timeLimit);
            }

            @Override
            public void onFinish() {
                if (timeLimit != 0) {
                    Dialog alertDialog = new Dialog(HomeScreen.this);
                    alertDialog.setContentView(R.layout.custom_dialog);
                    alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                    alertDialog.findViewById(R.id.time).startAnimation(animShake);
                    alertDialog.findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                            //timeLimit= 25;
                        }
                    });
                    alertDialog.show();
                }

            }
        }.start();
        initData();
    }

    private void initData() {
        timeLimit = 25;
        linearIndicator.setProgress(timeLimit);
        arrayQ = arrayListQ;
        quizModel = arrayQ.get(index);
        main_question.setText(quizModel.getMainQuestion());
        first_option.setText(quizModel.getFirstOption());
        second_option.setText(quizModel.getSecondOption());
        third_option.setText(quizModel.getThirdOption());
        fourth_option.setText(quizModel.getFourthOption());
        countDownTimer.cancel();
        countDownTimer.start();
        changeColor();
        enableClick();
    }

    public void right(CardView cardView) {
        cardView.setBackgroundColor(getResources().getColor(R.color.green_A700));
        ic_next.setOnClickListener(view -> {
            correctAnswers++;
            index++;
            quizModel = arrayQ.get(index);
            initData();
        });
    }

    public void wrong(CardView cardView) {
        cardView.setBackgroundColor(getResources().getColor(R.color.red_A700));
        ic_next.setOnClickListener(view -> {
            wrongAnswers++;
            if (index < arrayQ.size() - 1) {
                index++;
                quizModel = arrayQ.get(index);
                initData();
            } else {
                finished();
            }
        });
    }

    private void finished() {
        Intent intent = new Intent(HomeScreen.this, FinishedActivity.class);
        intent.putExtra("correctAnswers",correctAnswers);
        intent.putExtra("wrongAnswers",wrongAnswers);
        startActivity(intent);
    }

    private void onClickListeners() {
        cardOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ic_next.setClickable(true);
                disableClick();
                if (quizModel.getFirstOption().equals(quizModel.getAnswer())) {
                    cardOption1.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
                    if (index < arrayQ.size() - 1) {
                        right(cardOption1);
                    } else {
                        finished();
                    }
                } else {
                    wrong(cardOption1);
                }
            }
        });
        cardOption2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableClick();
                ic_next.setClickable(true);
                if (quizModel.getSecondOption().equals(quizModel.getAnswer())) {
                    cardOption2.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
                    if (index < arrayQ.size() - 1) {
                        right(cardOption2);
                    } else {
                        finished();
                    }
                } else {
                    wrong(cardOption2);
                }
            }
        });
        cardOption3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableClick();
                ic_next.setClickable(true);
                if (quizModel.getThirdOption().equals(quizModel.getAnswer())) {
                    if (index < arrayQ.size() - 1) {
                        right(cardOption3);
                    } else {
                        finished();
                    }
                } else {
                    wrong(cardOption3);
                }
            }
        });
        cardOption4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableClick();
                ic_next.setClickable(true);
                if (quizModel.getFourthOption().equals(quizModel.getAnswer())) {
                    cardOption4.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
                    if (index < arrayQ.size() - 1) {
                        right(cardOption4);
                    } else {
                        finished();
                    }
                } else {
                    wrong(cardOption4);
                }
            }
        });
    }

    private void enableClick() {
        cardOption1.setClickable(true);
        cardOption2.setClickable(true);
        cardOption3.setClickable(true);
        cardOption4.setClickable(true);
    }

    private void disableClick() {
        cardOption1.setClickable(false);
        cardOption2.setClickable(false);
        cardOption3.setClickable(false);
        cardOption4.setClickable(false);
    }

    private void changeColor() {
        cardOption1.setBackgroundColor(getResources().getColor(R.color.white));
        cardOption2.setBackgroundColor(getResources().getColor(R.color.white));
        cardOption3.setBackgroundColor(getResources().getColor(R.color.white));
        cardOption4.setBackgroundColor(getResources().getColor(R.color.white));
    }


}