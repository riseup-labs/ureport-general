package io.rapidpro.surveyor.extend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.rapidpro.surveyor.R;
import io.rapidpro.surveyor.SurveyorApplication;
import io.rapidpro.surveyor.activity.BaseActivity;

import static io.rapidpro.surveyor.extend.StaticMethods.playNotification;

public class ResultSelectionActivity extends AppCompatActivity {

    ViewGroup headerLayout;
    CardView storyList;
    ImageView backButton;
    View bgColor;
    public TextView activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v1_activity_result_selection);
        getWindow().setEnterTransition(null);

        headerLayout = findViewById(R.id.headerLayout);
        storyList = findViewById(R.id.storyList);
        backButton = findViewById(R.id.backButton);
        bgColor = findViewById(R.id.bgColor);
        activityName = findViewById(R.id.activityName);

        ObjectAnimator.ofFloat(storyList, "alpha",  0, 1f).setDuration(1000).start();
        ObjectAnimator.ofFloat(bgColor, "translationY", -500, 0).setDuration(1000).start();
        ObjectAnimator.ofFloat(storyList, "translationY", 1000, 0).setDuration(1000).start();
        ObjectAnimator.ofFloat(backButton, "translationX", -200, 0).setDuration(1000).start();

        backButton.setOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        playNotification(SurveyorApplication.get(), getApplicationContext(), R.raw.button_click_no, findViewById(R.id.backButton));
        headerLayout.setBackgroundColor(Color.parseColor("#00000000"));
        ObjectAnimator.ofFloat(storyList, "alpha",  1f, 0).setDuration(750).start();
        ObjectAnimator.ofFloat(bgColor, "translationY", 0, -500).setDuration(500).start();
        ObjectAnimator.ofFloat(storyList, "translationY", 0, 1000).setDuration(750).start();
        ObjectAnimator.ofFloat(backButton, "translationX", 0, -200).setDuration(1000).start();
        super.onBackPressed();
    }

}