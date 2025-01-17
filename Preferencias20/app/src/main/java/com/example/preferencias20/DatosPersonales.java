package com.example.preferencias20;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

public class DatosPersonales extends PreferenceFragment {

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState,String caca ) {
        addPreferencesFromResource(R.xml.datos_personales);
    }
}