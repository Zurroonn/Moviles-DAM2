package com.example.preferencias20;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MisFragmentPreferencias extends PreferenceActivity{


    @Override
    public void onBuildHeaders(List<PreferenceActivity.Header> target){
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.preferences_headers,target);
    }
    @Override
    protected boolean isValidFragment (String fragmentName) {
        if (MainActivity.class.getName().equals(fragmentName)) return true;
        return false;
    }

}