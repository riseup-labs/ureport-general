package io.rapidpro.surveyor.extend.fragment;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import io.rapidpro.surveyor.R;
import io.rapidpro.surveyor.SurveyorPreferences;
import io.rapidpro.surveyor.extend.SettingsActivity;
import io.rapidpro.surveyor.extend.StaticMethods;

import static io.rapidpro.surveyor.extend.StaticMethods.AppDistribution;
import static io.rapidpro.surveyor.extend.StaticMethods.playNotification;

public class SettingsFragment extends BaseFragment {

    String lang_code = "en";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.v1_fragment_settings, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lang_code = getSurveyor().getPreferences().getString(SurveyorPreferences.LANG_CODE, "en");
        setLang_code(lang_code);
        selectLanguageButton(lang_code);
        selectSoundState(getSurveyor().getPreferences().getString("sound_on", "true"));
        selectVibrationState(getSurveyor().getPreferences().getString("vibration_on", "true"));

        if(!isLoggedIn()){
            ((TextView) getView().findViewById(R.id.textLogout)).setText("Login");
        }

        getView().findViewById(R.id.layerLogout).setOnClickListener(v -> {
            playNotification(getSurveyor(), getContext(), R.raw.button_click_yes, v);
            logout();
        });

        getView().findViewById(R.id.btn_english).setOnClickListener(v -> {
            if(lang_code.equals("en")){return;}
            setLang_code("en");
            //reloadFragment();
            playNotification(getSurveyor(), getContext(), R.raw.setting_button_change, v);
        });

        getView().findViewById(R.id.btn_burmese).setOnClickListener(v -> {
            if(lang_code.equals("my")){return;}
            setLang_code("my");
            //reloadFragment();
            playNotification(getSurveyor(), getContext(), R.raw.setting_button_change, v);
        });

        getView().findViewById(R.id.btn_bangla).setOnClickListener(v -> {
            if(lang_code.equals("bn")){return;}
            setLang_code("bn");
            //reloadFragment();
            playNotification(getSurveyor(), getContext(), R.raw.setting_button_change, v);
        });

        getView().findViewById(R.id.btn_sound_on).setOnClickListener(v -> {
            if(getSurveyor().getPreferences().getString("sound_on", "true").equals("true")){return;}
            setSound_State("1");
            selectSoundState("true");
            playNotification(getSurveyor(), getContext(), R.raw.setting_button_change, v);
        });

        getView().findViewById(R.id.btn_sound_off).setOnClickListener(v -> {
            if(getSurveyor().getPreferences().getString("sound_on", "true").equals("false")){return;}
            setSound_State("0");
            selectSoundState("false");
            playNotification(getSurveyor(), getContext(), R.raw.setting_button_change, v);
        });

        getView().findViewById(R.id.btn_vibration_on).setOnClickListener(v -> {
            if(getSurveyor().getPreferences().getString("vibration_on", "true").equals("true")){return;}
            setVibration_State("1");
            selectVibrationState("true");
            playNotification(getSurveyor(), getContext(), R.raw.setting_button_change, v);
        });

        getView().findViewById(R.id.btn_vibration_off).setOnClickListener(v -> {
            if(getSurveyor().getPreferences().getString("vibration_on", "true").equals("false")){return;}
            setVibration_State("0");
            selectVibrationState("false");
            playNotification(getSurveyor(), getContext(), R.raw.setting_button_change, v);
        });
    }

    void selectLanguageButton(String lang_code) {
        Button btnEnglish = getView().findViewById(R.id.btn_english);
        Button btnBangla = getView().findViewById(R.id.btn_bangla);
        Button btnBurmese = getView().findViewById(R.id.btn_burmese);

        btnEnglish.setBackground(getResources().getDrawable(R.drawable.v3_dialog_button_grey));
        btnBangla.setBackground(getResources().getDrawable(R.drawable.v3_dialog_button_grey));
        btnBurmese.setBackground(getResources().getDrawable(R.drawable.v3_dialog_button_grey));

        btnEnglish.setTextColor(Color.BLACK);
        btnBangla.setTextColor(Color.BLACK);
        btnBurmese.setTextColor(Color.BLACK);

        if(lang_code.equals("en")){
            btnEnglish.setBackground(getResources().getDrawable(R.drawable.v1_card_bg_reports));
            btnEnglish.setTextColor(Color.WHITE);
        }
        if(lang_code.equals("bn")){
            btnBangla.setBackground(getResources().getDrawable(R.drawable.v1_card_bg_reports));
            btnBangla.setTextColor(Color.WHITE);
        }
        if(lang_code.equals("my")){
            btnBurmese.setBackground(getResources().getDrawable(R.drawable.v1_card_bg_reports));
            btnBurmese.setTextColor(Color.WHITE);
        }

    }

    void selectSoundState(String state){
        Button btnSoundOn = getView().findViewById(R.id.btn_sound_on);
        Button btnSoundOff = getView().findViewById(R.id.btn_sound_off);

        btnSoundOn.setBackground(getResources().getDrawable(R.drawable.v3_dialog_button_grey));
        btnSoundOff.setBackground(getResources().getDrawable(R.drawable.v3_dialog_button_grey));

        btnSoundOn.setTextColor(Color.BLACK);
        btnSoundOff.setTextColor(Color.BLACK);

        if(state.equals("true")){
            btnSoundOn.setBackground(getResources().getDrawable(R.drawable.v1_card_bg_reports));
            btnSoundOn.setTextColor(Color.WHITE);
        }else{
            btnSoundOff.setBackground(getResources().getDrawable(R.drawable.v1_card_bg_reports));
            btnSoundOff.setTextColor(Color.WHITE);
        }
    }

    void selectVibrationState(String state){
        Button btnVibrationOn = getView().findViewById(R.id.btn_vibration_on);
        Button btnVibrationOff = getView().findViewById(R.id.btn_vibration_off);

        btnVibrationOn.setBackground(getResources().getDrawable(R.drawable.v3_dialog_button_grey));
        btnVibrationOff.setBackground(getResources().getDrawable(R.drawable.v3_dialog_button_grey));

        btnVibrationOn.setTextColor(Color.BLACK);
        btnVibrationOff.setTextColor(Color.BLACK);

        if(state.equals("true")){
            btnVibrationOn.setBackground(getResources().getDrawable(R.drawable.v1_card_bg_reports));
            btnVibrationOn.setTextColor(Color.WHITE);
        }else{
            btnVibrationOff.setBackground(getResources().getDrawable(R.drawable.v1_card_bg_reports));
            btnVibrationOff.setTextColor(Color.WHITE);
        }
    }
    

//    void setLang_code(String lang_code){
//        getSurveyor().setPreference(SurveyorPreferences.LANG_CODE, lang_code);
//        this.lang_code = lang_code;
//    }

    void setLang_code(String lang_code){

    //        if(AppDistribution.equals("RV") && lang_code.equals("bn")){
    //            // Force English
    //            lang_code = "en";
    //        }

        selectLanguageButton(lang_code);
        getSurveyor().setPreference(SurveyorPreferences.LANG_CODE, lang_code);
        this.lang_code = lang_code;
        Locale myLocale = new Locale("en");

        if(lang_code.equals("bn")){
            myLocale = new Locale("bn", "BD");
        }else if(lang_code.equals("en")){
            myLocale = new Locale("en");
        }else if(lang_code.equals("my")){
            myLocale = new Locale("my");
        }

        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        config.setLocale(myLocale);
        getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
        reloadTexts();

        // Log Event
        Bundle logBundle = new Bundle();
        logBundle.putString("language", lang_code);
        StaticMethods.logFirebase("settings_change", logBundle);


    }

    void reloadFragment() {
        if(getActivity() != null){
            try{
                getActivity().getSupportFragmentManager().beginTransaction().replace(SettingsFragment.this.getId(), new SettingsFragment()).commit();
            } catch (Exception e) {
                //
            }
        }
    }

    void setSound_State(String sound_state){
        if(sound_state.equals("0")){
            // Sound Off
            getSurveyor().setPreference("sound_on", "false");

            // Log Event
            Bundle logBundle = new Bundle();
            logBundle.putString("sound", "off");
            StaticMethods.logFirebase("settings_change", logBundle);
        }else{
            getSurveyor().setPreference("sound_on", "true");

            // Log Event
            Bundle logBundle = new Bundle();
            logBundle.putString("sound", "on");
            StaticMethods.logFirebase("settings_change", logBundle);
        }
    }

    void setVibration_State(String vibration_state){
        if(vibration_state.equals("0")){
            // Sound Off
            getSurveyor().setPreference("vibration_on", "false");

            // Log Event
            Bundle logBundle = new Bundle();
            logBundle.putString("vibration", "off");
            StaticMethods.logFirebase("settings_change", logBundle);
        }else{
            getSurveyor().setPreference("vibration_on", "true");

            // Log Event
            Bundle logBundle = new Bundle();
            logBundle.putString("vibration", "on");
            StaticMethods.logFirebase("settings_change", logBundle);
        }
    }

    public void reloadTexts() {
        if(getActivity() != null){
            if(((SettingsActivity) getActivity()).activityName != null){
                ((SettingsActivity) getActivity()).activityName.setText(R.string.v1_settings);
            }
        }
        ((TextView) getView().findViewById(R.id.txtChangeLanguage)).setText(R.string.v1_change_language);
        ((TextView) getView().findViewById(R.id.txtChangeSound)).setText(R.string.v1_sound);
        ((TextView) getView().findViewById(R.id.txtChangeVibration)).setText(R.string.v1_vibration);
        ((TextView) getView().findViewById(R.id.textLogout)).setText(R.string.v1_logout);
        ((TextView) getView().findViewById(R.id.txtSupport)).setText(R.string.v1_settings_overall_support);
        ((TextView) getView().findViewById(R.id.txtDeveloped)).setText(R.string.v1_settings_designed_and_developed);

        ((Button) getView().findViewById(R.id.btn_sound_on)).setText(R.string.v1_on);
        ((Button) getView().findViewById(R.id.btn_sound_off)).setText(R.string.v1_off);
        ((Button) getView().findViewById(R.id.btn_vibration_on)).setText(R.string.v1_on);
        ((Button) getView().findViewById(R.id.btn_vibration_off)).setText(R.string.v1_off);
    }

    @Override
    public boolean requireLogin() {
        return false;
    }

}
