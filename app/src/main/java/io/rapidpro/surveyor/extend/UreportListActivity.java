package io.rapidpro.surveyor.extend;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import io.rapidpro.surveyor.R;
import io.rapidpro.surveyor.SurveyorApplication;

import static io.rapidpro.surveyor.extend.StaticMethods.playNotification;

public class UreportListActivity extends AppCompatActivity {

    ViewGroup headerLayout;
    CardView storyList;
    ImageView backButton;
    TextView activityName;
    View bgColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v1_activity_ureport_list);
        getWindow().setEnterTransition(null);

        headerLayout = findViewById(R.id.headerLayout);
        storyList = findViewById(R.id.storyList);
        backButton = findViewById(R.id.backButton);
        activityName = findViewById(R.id.activityName);
        bgColor = findViewById(R.id.bgColor);

        activityName.setText(getIntent().getExtras().getString("ResultName"));

        ObjectAnimator.ofFloat(storyList, "alpha",  0, 1f).setDuration(1000).start();
        ObjectAnimator.ofFloat(bgColor, "translationY", -500, 0).setDuration(1000).start();
        ObjectAnimator.ofFloat(storyList, "translationY", 1000, 0).setDuration(1000).start();
        ObjectAnimator.ofFloat(backButton, "translationX", -200, 0).setDuration(1000).start();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        playNotification(SurveyorApplication.get(), getApplicationContext(), R.raw.button_click_no, findViewById(R.id.backButton));
        headerLayout.setBackgroundColor(Color.parseColor("#00000000"));
        ObjectAnimator.ofFloat(bgColor, "translationY", 0, -500).setDuration(500).start();
        ObjectAnimator.ofFloat(storyList, "alpha",  1f, 0).setDuration(750).start();
        ObjectAnimator.ofFloat(storyList, "translationY", 0, 1000).setDuration(750).start();
        ObjectAnimator.ofFloat(backButton, "translationX", 0, -200).setDuration(1000).start();
        super.onBackPressed();
    }
}
