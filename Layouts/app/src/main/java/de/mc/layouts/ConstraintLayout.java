package de.mc.layouts;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class ConstraintLayout extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.constraintlayout);
    }
}
