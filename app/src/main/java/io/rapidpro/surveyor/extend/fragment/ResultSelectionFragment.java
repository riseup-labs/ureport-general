package io.rapidpro.surveyor.extend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.rapidpro.surveyor.R;
import io.rapidpro.surveyor.SurveyorPreferences;
import io.rapidpro.surveyor.extend.OfflineUreportListActivity;
import io.rapidpro.surveyor.extend.UreportCategoryActivity;

import static io.rapidpro.surveyor.extend.StaticMethods.playNotification;

public class ResultSelectionFragment extends BaseFragment {

    boolean clicked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.v1_fragment_result_selection, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getView().findViewById(R.id.ureport_button).setOnClickListener(v -> {
            if(clicked){return;}else{clicked = true;new Handler().postDelayed(() ->{clicked = false;}, 1000);}
            playNotification(getSurveyor(), getContext(), R.raw.button_click_yes, v);
            Intent intent = new Intent(getActivity(), UreportCategoryActivity.class);
            startActivity(intent);
        });

        getView().findViewById(R.id.offline_surveyor_button).setOnClickListener(v-> {
            if(clicked){return;}else{clicked = true;new Handler().postDelayed(() ->{clicked = false;}, 1000);}
            playNotification(getSurveyor(), getContext(), R.raw.button_click_yes, v);
            Intent intent = new Intent(getActivity(), OfflineUreportListActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean requireLogin() {
        return false;
    }

}
